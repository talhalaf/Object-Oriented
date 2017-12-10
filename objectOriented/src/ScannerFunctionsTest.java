import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ScannerFunctionsTest {
private static final String String = null;

//	@Rule
//	  public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGetAllcsvFileListFromFolder() {
		String NotRealDirectoryPath = "C:\\not\\real\\path";
		assertNull(ScannerFunctions.getAllcsvFileListFromFolder(NotRealDirectoryPath));
	}
	@Test
	public void test2GetAllcsvFileListFromFolder() {
		String NotContainsCSVDirectoryPath = "C:\\EmptyDirectory";
		assertEquals(0,ScannerFunctions.getAllcsvFileListFromFolder(NotContainsCSVDirectoryPath).size());
	}

	@Test(expected = IOException.class) 
	public void testFiltercsvFileByTime() {
		String startTime ="2002-12-17 20:20:20";
		String endTime ="2017-10-17 20:20:20";
		String NotRealFilePath = "C:\\not\\real\\path\\file.csv";
		String NotRealDirectoryPath = "C:\\not\\real\\path";
		ScannerFunctions.filtercsvFileByTime(NotRealFilePath, NotRealDirectoryPath, startTime, endTime);
	}

	@Test(expected = IOException.class) 
	public void testFiltercsvFileByID() {
		String NotRealcsvFile= "FileName";
		String NotRealID = "LG";
		String NotRealPathToWriteKML = "C:\\not\\real\\path\\file.csv";
		String NotRealDirectoryPath = "C:\\not\\real\\path";
		ScannerFunctions.filtercsvFileByID( NotRealcsvFile,  NotRealPathToWriteKML, NotRealID );
	}

	@Test (expected = IOException.class)
	public void testFiltercsvFileByGPS() {
		String NotRealcsvFile= "FileName";
		String NotRealPathToWriteKML = "C:\\not\\real\\path\\file.csv";
		double NotRealLonStart =0;
		double NotRealLatStart =0;
		double NotRealLonEnd =0;
		double NotRealLatEnd =0;
   ScannerFunctions.filtercsvFileByGPS(NotRealcsvFile,NotRealPathToWriteKML, NotRealLonStart, NotRealLatStart,NotRealLonEnd, NotRealLatEnd);
	}

	@Test(expected = NullPointerException.class)
	public void testGetAllcsvFilesFromFolderAndAddtoOneCSVTable() {
//		exception.expect();
		String NotRealDirectoryPath = "C:\\not\\real\\path";
		ScannerFunctions.getAllcsvFilesFromFolderAndAddtoOneCSVTable(NotRealDirectoryPath,NotRealDirectoryPath);
	}

	
}