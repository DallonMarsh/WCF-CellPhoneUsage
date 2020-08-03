package Shared;

/**
 * 
 * Data Object Usage data
 *
 */
public class CellPhoneUsageDO 
{
	private Integer employeeId = 0;
	private Integer month = 0;
	private Integer minutesUsed = 0;
	private Double dataUsed = 0.0;
	
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getMinutesUsed() {
		return minutesUsed;
	}
	public void setMinutesUsed(Integer minutesUsed) {
		this.minutesUsed = minutesUsed;
	}
	public Double getDataUsed() {
		return dataUsed;
	}
	public void setDataUsed(Double dataUsed) {
		this.dataUsed = dataUsed;
	}
	
}
