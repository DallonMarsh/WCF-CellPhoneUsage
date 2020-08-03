package Shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author Dallon Marsh
 * 
 * Data Object to hold Cell Phone Data
 *
 */
public class CellPhoneDO 
{
	private Integer employeeId;
	private String employeeName;
	private Date purchaseDate;
	private String model;
	private Map<Integer, CellPhoneUsageDO> usageMap = new LinkedHashMap<Integer, CellPhoneUsageDO>();
	
	public CellPhoneDO()
	{
		for ( int idx = 0; idx < 12; idx++ )
		{
			usageMap.put(idx, new CellPhoneUsageDO());	
		}
	}
	public Map<Integer, CellPhoneUsageDO> getUsage() {
		return usageMap;
	}
	public void setUsage(Map<Integer, CellPhoneUsageDO> usage) {
		this.usageMap = usage;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getPurchaseDateString()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
		return dateFormat.format(getPurchaseDate());  
	}
	
	/**
	 * Find list by month and employeeid to add usage data
	 * @param usage
	 */
	public void addUsage(CellPhoneUsageDO usage)
	{
		if ( usageMap.containsKey( usage.getMonth()) )
		{
			CellPhoneUsageDO tmp = usageMap.get(usage.getMonth());
			int current = tmp.getMinutesUsed();
			double data = tmp.getDataUsed();
			tmp.setMinutesUsed( tmp.getMinutesUsed() + usage.getMinutesUsed() );
			tmp.setDataUsed( tmp.getDataUsed() + usage.getDataUsed());
		}
		else
		{
			usageMap.put(usage.getMonth(), usage);
		}
	}
	
}
