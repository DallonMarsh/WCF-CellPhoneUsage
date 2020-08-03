package Server;

import java.util.List;

import Shared.CellPhoneDO;
import Shared.CellPhoneUsageDO;
import Shared.CellPhoneUsageReportDO;


/**
 * 
 * @author Dallon Marsh
 * 
 * Business Logic - Run in an application Server
 *
 */
public class CellPhoneSessionBean 
{
	CellPhoneDAO cellPhoneDAO = new CellPhoneDAO();
	
	
	/*
	 * Generate Phone Usage Report for given year
	 */
	public CellPhoneUsageReportDO getPhoneData(int year)
	{
		CellPhoneUsageReportDO report = new CellPhoneUsageReportDO();
		report.setYear(year);
		
		List<CellPhoneDO> phones = cellPhoneDAO.getPhoneData();
		List<CellPhoneUsageDO> usage = cellPhoneDAO.getUsageData(year);
		
		int totalMinutes = 0;
		Double totalData = 0.0;
		
		// Organize Usage for each User by Month
		for (CellPhoneUsageDO usageDo: usage)
		{
			CellPhoneDO phone = getUserPhone(usageDo.getEmployeeId(), phones);
			if ( phone != null )
			{
				phone.addUsage(usageDo);
				totalMinutes += usageDo.getMinutesUsed();
				totalData += usageDo.getDataUsed();
			}
			
			
		}
		
		report.setPhones(phones);
		report.setNumPhones(phones.size()); 
		report.setTotalMinutes(totalMinutes);
		report.setTotalData(totalData);
		report.setAverageMinutes((totalMinutes / report.getNumPhones()));
		report.setAverageData(totalData / report.getNumPhones());
		
		return report;
	}
	
	/*
	 * find the CellPhone in the list for given employee
	 */
	private CellPhoneDO getUserPhone(int employeeId, List<CellPhoneDO> phones)
	{
		for ( CellPhoneDO phone: phones)
		{
			if (phone.getEmployeeId() == employeeId)
			{
				return phone;
			}
		}
		
		return null;
	}
	
}
