package GPSbyMac;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GPSbyMac {

	public static void main(String[] args) {

	}
	
	public static void macInfoFromUser(String mac, String csvFile){
		String macUserAskFor = mac;
		String searchMac; int i=0; int numOfNetwoks;
		
	
		try {

			FileReader fr = new FileReader(csvFile);
			BufferedReader br= new BufferedReader(fr); 
			ArrayList<String> findMac = new ArrayList<String>();
			String brFirstLine = br.readLine(); // skipping first line
			String brLine = br.readLine();

			// searching mac
			while(brLine != null){
				String[] line = brLine.split(",");
				searchMac = line[7+i];
				numOfNetwoks = Integer.parseInt(line[5]);
				for (int j = 0; j <numOfNetwoks; j++) {
					if(macUserAskFor.equals(searchMac)){

						i= i+4;
					}
					i=0;
					}
				
				
		
	}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
}
}
