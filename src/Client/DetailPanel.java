package Client;

import java.time.DateTimeException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Shared.CellPhoneDO;
import Shared.CellPhoneUsageDO;
import info.clearthought.layout.TableLayout;

/**
 * 
 * @author Dallon Marsh
 * 
 * Panel Shows the Details for the Report
 *
 */
public class DetailPanel extends JPanel
{
	private JTable table;
	
	/**
	 * Default Constructor
	 */
	public DetailPanel()
	{
		initializeComponents();
		setUpScreenLayout();
		eventHandlers();
	}
	
	/*
	 * 
	 */
	public JTable getTable()
	{
		return table;
	}
	
	/**
	 * 
	 */
	private void initializeComponents()
	{
         
		 //create table with data
        table = new JTable( new DefaultTableModel(null, new Object[]{"Employee ID", "Employee Name", "Model", "Purchase Date", "Month", "Minutes Used", "Data Used"} ) );
	}
	
	/**
	 *   
	 */
	private void setUpScreenLayout()
	{
		double[][] layout = { {1, TableLayout.FILL, 1}, {1, TableLayout.FILL, 1} };
		setLayout(new TableLayout(layout));
		
        //add the table to the Panel
        add(new JScrollPane(table), "1,1");
	}
	
	/**
	 *    
	 */
	private void eventHandlers()
	{
		
	}
	
	/*
	 * 
	 */
	public void populateTable(List<CellPhoneDO> phones)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for ( CellPhoneDO phone: phones)
		{
			String strDate = phone.getPurchaseDateString();
			Map<Integer, CellPhoneUsageDO> map = phone.getUsage();
			for ( Integer month: map.keySet() )
			{
				CellPhoneUsageDO usage = map.get( month );
				String monthStr = convertMonthString(month);
				
				Object[] row = { phone.getEmployeeId(), phone.getEmployeeName(), phone.getModel(), strDate, monthStr, usage.getMinutesUsed(), String.format("%.2f", usage.getDataUsed()) };
				model.addRow(row);
			}
		}
        
	}
	
	/*
	 * 
	 */
	private String convertMonthString(int month)
	{
		try
		{
			Month monthEnum = Month.of(month + 1);
			return monthEnum.getDisplayName(TextStyle.SHORT, Locale.getDefault());
		}
		catch( DateTimeException e)
		{
			return "";
		}
	}
}
