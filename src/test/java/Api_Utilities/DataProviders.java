package Api_Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][]getAllData() throws IOException{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xlUtil = new XLUtility(path);
		
		int rowCount = xlUtil.getRowCount("Sheet1");
	    int colCount = xlUtil.getCellCount("Sheet1", 1); // Assuming the number of columns is the same for all rows

	    
	    String[][] data = new String[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = xlUtil.getCellData("Sheet1", i + 1, j); // Adjust the row index accordingly
            }
        }
        
        return data;		
	}

	
	@DataProvider(name="UserNames")
	public String[]getUsernames() throws IOException{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xlUtil = new XLUtility(path);
		
		int rowCount = xlUtil.getRowCount("Sheet1");

	    
	    String[] data =  new String[rowCount];
        for (int i = 1; i <= rowCount; i++) {
           
                data[i-1] = xlUtil.getCellData("Sheet1", i, 1); // Adjust the row index accordingly
             }
        
        return data;		
	}
}
