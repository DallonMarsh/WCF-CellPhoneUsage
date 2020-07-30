package Client;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Shared.CellPhoneUsageReportDO;
import info.clearthought.layout.TableLayout;


/**
 * 
 * @author Dallon Marsh
 *
 */
public class HeaderPanel extends JPanel
{
	private JLabel dateLbl;
	private JLabel numberPhonesLbl;
	private JLabel totalMinLbl;
	private JLabel averageMinLbl;
	private JLabel totalDataLbl;
	private JLabel averageDataLbl;
	
	private CellPhoneUsageReportDO report;

	
	/**
	 *    
	 */
	public HeaderPanel()
	{
		initializeComponents();
		setUpScreenLayout();
		eventHandlers();
	}
	
	/*
	 * 
	 */
	public CellPhoneUsageReportDO getReport()
	{
		return report;
	}
	
	/**
	 *    
	 */
	private void initializeComponents()
	{
		
		dateLbl = createLabel();
		numberPhonesLbl = createLabel();
		totalMinLbl  = createLabel();
		averageMinLbl = createLabel();
		totalDataLbl = createLabel();
		averageDataLbl = createLabel();
	}
	
	/*
	 * 
	 */
	private JLabel createLabel()
	{
		JLabel label = new JLabel("", JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		label.setBackground(Color.white);
		label.setOpaque(true);
		
		return label;
	}
	
	/**
	 *    
	 */
	private void setUpScreenLayout()
	{
		final int  fw = 150;
		final int  fh = 25;
		double[][] layout = { {20, TableLayout.PREFERRED, 10, fw, 50, TableLayout.PREFERRED, 10, fw, 50, TableLayout.PREFERRED, 10, fw, TableLayout.FILL}, 
				              {20, fh, 7, fh, TableLayout.FILL} };
		setLayout(new TableLayout(layout));
		
		add(new JLabel("Report Date:", JLabel.RIGHT), "1,1");   
		add(new JLabel("Number Phones:", JLabel.RIGHT), "1,3");   
		add(new JLabel("Total Minutes:", JLabel.RIGHT), "5,1");   
		add(new JLabel("Average Minutes:", JLabel.RIGHT), "5,3");   
		add(new JLabel("Total Data:", JLabel.RIGHT), "9,1");   
		add(new JLabel("Average Data:", JLabel.RIGHT), "9,3");   
		
		add(dateLbl, "3,1");   
		add(numberPhonesLbl, "3,3");   
		add(totalMinLbl, "7,1");   
		add(averageMinLbl, "7,3");   
		add(totalDataLbl, "11,1");   
		add(averageDataLbl, "11,3");   
	}
	
	/**
	 *    
	 */
	private void eventHandlers()
	{
		
	}
	
	public void populateHeader(CellPhoneUsageReportDO report)
	{
		this.report = report;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
		String strDate = dateFormat.format(report.getRunDate());  
		
		dateLbl.setText(strDate);
		numberPhonesLbl.setText(String.valueOf(report.getNumPhones()));
		totalMinLbl.setText(String.valueOf(report.getTotalMinutes()));
		averageMinLbl.setText(String.valueOf(report.getAverageMinutes()));
		totalDataLbl.setText(String.format("%.2f", report.getTotalData()));
		averageDataLbl.setText(String.format("%.2f", report.getAverageData()));
	}
}
