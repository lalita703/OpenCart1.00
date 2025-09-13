package utilitys;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
 @DataProvider(name="LoginDatawithexcel")
 public String[][]getData() throws IOException
 {//D:\Newworkspace\OpenCart1.00\testData\TestdataSheet.xlsx
	String path=".\\testData\\TestdataSheet.xlsx";
	Excel_Utility xlutil=new Excel_Utility(path);
	
	int totalrows=xlutil.getRowCount("sheet");
	int totalcols=xlutil.getCellCount("sheet", 1);
	
	String logindata[][]=new String [totalrows][totalcols];
	
	for(int i=1;i<totalrows;i++)
	{
       for(int j=0;j<totalcols;j++)
       {
   		logindata[i-1][j]=xlutil.getCellData("sheet", i,j);

       }
	}
	return logindata;
	
  }
 @DataProvider(name = "loginData")
 public Object[][] getLoginData() {
     return new Object[][] {
         {"rxygBTc@gmail.com", "ClZTP7y4", "valid"},
         {"lakshimi@yahoo.com", "Laxmi", "invalid"},
         {"rxygBTc@gmail.com", "ClZTP7y4", "invalid"},
         {"laks@yahoo.com", "xyz", "invalid"},
         {"rxygBTc@gmail.com", "test@1234", "valid"}
     };
 }
}
