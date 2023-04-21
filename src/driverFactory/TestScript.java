 package driverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonfunction.AddEmpPage;
import config.AppUtilPom;
import utilities.ExcelFileutil;
public class TestScript extends AppUtilPom {
	 	String inputpath = "E:\\workspace\\DDT_Framework\\FileInput\\happyfile.xlsx";
		String outputpatn = "E:\\workspace\\DDT_Framework\\FileOutput\\happyResults.xlsx";	
@Test
public void startTest()throws Throwable
{
AddEmpPage emp =PageFactory.initElements(driver,AddEmpPage.class);
//create object for Excelfile util
ExcelFileutil xl = new ExcelFileutil(inputpath);
//count no of rows in Empdata sheet
int rc = xl.rowcount("EmpData");
Reporter.log("No of rows are::"+rc,true);
for(int i=1;i<=rc;i++) 
{
	String FirstName=xl.getcellData("EmpData",i,0);
	String MiddleName=xl.getcellData("EmpData",i,1);
	String LastName=xl.getcellData("lastname",i,2);
	boolean res  = emp.verify_Emp(FirstName, MiddleName, LastName);
	if(res) 
	{
		//write as pass in to status cell
		xl.setcell("EmpData",i,3, "Pass",outputpatn);
	}
		else
		{
		
		xl.setcell("EmpData", i,3, "Fail",outputpatn);
		
	
}

}	
}
}

