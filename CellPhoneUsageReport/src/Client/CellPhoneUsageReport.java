package Client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import Server.CellPhoneSessionBean;
import Shared.CellPhoneDO;
import Shared.CellPhoneUsageReportDO;
import info.clearthought.layout.TableLayout;


/**
 * 
 * @author Dallon Marsh
 * 
 * MAIN - JFrame for application
 *
 */
public class CellPhoneUsageReport extends JFrame
{  
	private   JComboBox yearCBox;
	private   JLabel messageLbl;
	protected HeaderPanel headerPanel;
	protected DetailPanel detailPanel;
	protected JPanel buttonPanel;
	protected JButton closeBtn;
	protected JButton printBtn;
	
	private int selectedYear = 2017;
	
	private List<CellPhoneDO> data;
	
	/*
	 * Default Constructor
	 */
	public CellPhoneUsageReport()
	{
		setTitle("Cell Phone Usage Report");
		
		initializeComponents();
		setUpScreenLayout();
		eventHandlers();
		populateData();
		
        setSize(1000, 800);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setVisible(true);  
	}
	
	/*
	 * Create Components
	 */
	private void initializeComponents()
	{
        String years[] = { "2017", "2018", "2019", "2020" };
        yearCBox = new JComboBox(years); 
        messageLbl = new JLabel("", JLabel.CENTER);
        messageLbl.setFont(new Font("arial", Font.BOLD, 16));
        messageLbl.setForeground(Color.gray);
        
		headerPanel = new HeaderPanel();
		detailPanel = new DetailPanel();
		buttonPanel = new JPanel();
		closeBtn = new JButton("Close");
		printBtn = new JButton("Print Report");
	}
	
	/*
	 * GUI
	 */
	private void setUpScreenLayout()
	{
		double[][] layout = { {10, 90, 3, 70, TableLayout.FILL, 10}, {15, 25, 10, 100, 5, TableLayout.FILL, 5, 30, 5} };
		setLayout(new TableLayout(layout));
		
		add(new JLabel("Selected Year", JLabel.RIGHT), "1,1");  
		add(yearCBox, "3,1");  
		add(messageLbl, "4,1");  
		add(headerPanel, "1,3,4,3");  
	    add(detailPanel, "1,5,4,5");  
	    add(buttonPanel, "1,7,4,7");  
	    
	    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    buttonPanel.add(printBtn);
	    buttonPanel.add(closeBtn);
	  
	}
	
	/**
	 *    Event Handlers
	 */
	private void eventHandlers()
	{
		yearCBox.addActionListener(new ActionListener() 
		{
			 public void actionPerformed(ActionEvent e) 
			 {
	            JComboBox cb = (JComboBox)e.getSource();
	            String year = (String)cb.getSelectedItem();
	            selectedYear = Integer.valueOf(year);
	            populateData();
			 }
	    });
			    
		closeBtn.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	dispose();
            }

        });
		
		printBtn.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	print();
            }

        });
	}
	
	/*Get data for given year
	 * 
	 */
	private void populateData()
	{
		messageLbl.setText("Show Cell Phone Usage for " + selectedYear);
		
		
		CellPhoneSessionBean server = new CellPhoneSessionBean();
		CellPhoneUsageReportDO report = server.getPhoneData(selectedYear);
		data = report.getPhones();
		
		headerPanel.populateHeader(report);
		detailPanel.populateTable(data);
	}
	
	/*
	 * Send to Printer 
	 */
	private void print()
	{
		
		try
		{
			JTable table = detailPanel.getTable();
			CellPhoneUsageReportDO report = headerPanel.getReport();
			
			MessageFormat headerFormat = new MessageFormat( getPrintHeader(report) );
			
			if ( table.print(JTable.PrintMode.FIT_WIDTH, headerFormat, null) ) 
		    {
		       messageLbl.setText("Report Sent to Printer");
		    } 
		 } 
		 catch (java.awt.print.PrinterException e) 
		 {
		     System.err.format("Cannot print %s%n", e.getMessage()); 
		 } 
	}
	
	/*
	 * Build header string
	 */
	private String getPrintHeader(CellPhoneUsageReportDO report)
	{
		String result = "";
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
		String strDate = dateFormat.format(report.getRunDate());  
		
		String phones = String.valueOf(report.getNumPhones() );
		String totMin = String.valueOf(report.getTotalMinutes() );
		String aveMin = String.valueOf(report.getAverageMinutes() );
		String totData = String.format("%.2f", report.getTotalData() );
		String aveData = String.format("%.2f", report.getAverageData() );
				
		//result = "Run At: " + strDate + "  Num Phones: " + phones + "  Total Min: " + totMin + "  Ave Min: " + aveMin + "  Total Data: " + totData + "  Ave Data: " + aveData;
		result = " Total Min: " + totMin + "  Ave Min: " + aveMin; // + "  Total Data: " + totData + "  Ave Data: " + aveData;
				
	    return result;
	}
	
	
	/*
	 * MAIN
	 */
    public static void main(String s[]) 
    {  
    	new CellPhoneUsageReport();     
    }  
}  
