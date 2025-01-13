package com.testBase;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class BaseClass {
	protected static WebDriver driver;
	
	@Parameters("browserName")
	@BeforeClass
	public void setUp(String browserName) {
		// Initialize WebDriver based on the browser parameter
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException {
		return fetchExcelData(false, null); // Fetch all data (default DataProvider behavior)
	}

	public String[] getSecondRowCredentials() throws IOException {
		return (String[]) fetchExcelData(true, 1)[0]; // Fetch only the second row (row index 1 in the Excel file)
	}

	private Object[][] fetchExcelData(boolean singleRow, Integer specificRowIndex) throws IOException {
		File excelFile = new File("./src/test/resources/Book.xlsx");
		FileInputStream fileInputStream = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		DataFormatter dataFormatter = new DataFormatter();

		int noOfRows = sheet.getPhysicalNumberOfRows(); // Total rows
		int noOfColumns = sheet.getRow(0).getLastCellNum(); // Total columns

		if (singleRow) {
			// Fetch only the specific row
			String[] singleRowData = new String[noOfColumns];
			for (int j = 0; j < noOfColumns; j++) {
				singleRowData[j] = dataFormatter.formatCellValue(sheet.getRow(specificRowIndex).getCell(j));
			}
			workbook.close();
			fileInputStream.close();
			return new String[][]{singleRowData};
		} else {
			// Fetch all rows except the header
			String[][] allData = new String[noOfRows - 1][noOfColumns];
			for (int i = 0; i < noOfRows - 1; i++) {
				for (int j = 0; j < noOfColumns; j++) {
					allData[i][j] = dataFormatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
				}
			}
			workbook.close();
			fileInputStream.close();
			return allData;
		}
	}

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}



	/******************Workable code but seperate method***********************************************
    //Fatch all value using data provider paramenter
    @DataProvider(name="loginData")
	public String[][] getData() throws IOException {
		File excelFile = new File("./src/test/resources/Book.xlsx");		
		FileInputStream fls = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fls);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int noOfRow = sheet.getPhysicalNumberOfRows(); //number of row count
		int noOfColumns = sheet.getRow(0).getLastCellNum(); // number of column count

		String[][] data = new String[noOfRow-1][noOfColumns];
		for(int i=0; i< noOfRow-1; i++) {
			for(int j=0; j<noOfColumns; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}

		workbook.close();
		fls.close();
		return data;
	}//End Method 

    //Fetch Secound Row value only 
    public String[] getSecondRowCredentials() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./src/test/resources/Book.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        DataFormatter dataFormatter = new DataFormatter();

        // Extract the email and password from the second row
        String email = dataFormatter.formatCellValue(sheet.getRow(1).getCell(0)); // Email
        String password = dataFormatter.formatCellValue(sheet.getRow(1).getCell(1)); // Password
        workbook.close();
        fileInputStream.close();
        return new String[]{email, password};
    }

	 *************************************************************************/



}
