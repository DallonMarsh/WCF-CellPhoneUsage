package Server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Shared.CellPhoneDO;
import Shared.CellPhoneUsageDO;


/**
 * 
 * @author dallon marsh
 * 
 * Data Access Object for Cell Phone Usage Report
 *
 */
public class CellPhoneDAO 
{
	private String path = ".\\"; 
	private final String fileNamePhones = "CellPhone.csv";
	private final String fileNameUsage = "CellPhoneUsageByMonth.csv";
	
	
	/*
	 * Call to retrieve data from data store for cell phone usage
	 */
	public List<CellPhoneDO> getPhoneData()
	{
		Path currentRelativePath = Paths.get("");
		path = currentRelativePath.toAbsolutePath().toString() + "\\";
		
		List<CellPhoneDO> data = new ArrayList<>();
		List<String>  lines = getCsvData(path + fileNamePhones);
		
		for( String line : lines )
		{
			CellPhoneDO phoneDo = new CellPhoneDO();
			
			String[] columns = line.split(",");
			if ( columns.length != 4)
			{
				continue;
			}
			
			
			try
			{
				Date purchaseDate = convertToDate(columns[2], "yyyyMMdd"); 
				
				phoneDo.setEmployeeId( Integer.valueOf(columns[0]) );
				phoneDo.setEmployeeName( columns[1]);
				phoneDo.setPurchaseDate( purchaseDate );
				phoneDo.setModel( columns[3] );
				
				data.add(phoneDo);
			}
			catch(Exception e)
			{
				 e.printStackTrace();
			}
			
		}
		
		
		return data;
	}
	
	
	/*
	 * Access Usage Data
	 */
	public List<CellPhoneUsageDO> getUsageData(int year)
	{
		Path currentRelativePath = Paths.get("");
		path = currentRelativePath.toAbsolutePath().toString() + "\\";
		
		List data = new ArrayList<CellPhoneUsageDO>();
		List<String>  lines = getCsvData(path + fileNameUsage);
		
		for( String line : lines )
		{
			CellPhoneUsageDO usageDo = new CellPhoneUsageDO();
			String[] columns = line.split(",");
			if ( columns.length != 4)
			{
				continue;
			}
			
			try
			{
				Date date = convertToDate(columns[1], "MM/dd/yyyy"); 
				Calendar calendar = Calendar.getInstance(); 
				calendar.setTime(date);
				
				if ( calendar.get(Calendar.YEAR) == year )
				{
					usageDo.setEmployeeId( Integer.valueOf(columns[0]) );
					usageDo.setMonth(calendar.get(Calendar.MONTH));
					usageDo.setMinutesUsed( Integer.valueOf(columns[2]) );
					usageDo.setDataUsed(Double.valueOf( columns[3]) );
					
					data.add(usageDo);
				}
			}
			catch(Exception e)
			{
				 e.printStackTrace();
			}
		
		}
		
		
		return data;
	}
	
	/**
	 * 
	 * String to Date Object (Using older Date Class)
	 * @return
	 */
	private Date convertToDate(String dateStr, String expectedPattern)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
	    try
	    {
	      Date date = formatter.parse(dateStr);
	      return date;

	    }
	    catch (ParseException e)
	    {
	      e.printStackTrace();
	    }
	    
	    return null;
	}
	
	
	/*
	 * 
	 */
	private List<String> getCsvData(String fileName)
	{
		List<String> lines = Collections.emptyList();  
		
		try  
		{   
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);  
			lines.remove(0);
			return lines;
		}   
		catch (IOException e)   
		{   
			e.printStackTrace();   
		}   
		return null;   
	}
}
