package Shared;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * Contains the Data returned from the server for a Cell Phone Usage Report
 *
 */
public class CellPhoneUsageReportDO 
{
	private int year;
	
	private Date runDate = Calendar.getInstance().getTime();
	private int numPhones;
	private int totalMinutes;
	private Double totalData;
	private int averageMinutes;
	private Double averageData;
	private String file;
	
	private List<CellPhoneDO> phones;
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public int getNumPhones() {
		return numPhones;
	}
	public void setNumPhones(int numPhones) {
		this.numPhones = numPhones;
	}
	public int getTotalMinutes() {
		return totalMinutes;
	}
	public void setTotalMinutes(int totalMinutes) {
		this.totalMinutes = totalMinutes;
	}
	public Double getTotalData() {
		return totalData;
	}
	public void setTotalData(Double totalData) {
		this.totalData = totalData;
	}
	public int getAverageMinutes() {
		return averageMinutes;
	}
	public void setAverageMinutes(int averageMinutes) {
		this.averageMinutes = averageMinutes;
	}
	public Double getAverageData() {
		return averageData;
	}
	public void setAverageData(Double averageData) {
		this.averageData = averageData;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public List<CellPhoneDO> getPhones() {
		return phones;
	}
	public void setPhones(List<CellPhoneDO> phones) {
		this.phones = phones;
	}
	
}
