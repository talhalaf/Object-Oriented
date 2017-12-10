import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LookAt;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import de.micromata.opengis.kml.v_2_2_0.Kml;	

public class kml {

	public static void makeKmlFile(ArrayList<String[]> toDisplay, String path){// זאת הפונקציה מהתיקייה של הק.מ.ל אם תצטרך עוד הסברים תקשר אליי.

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		String time;
		for (int i = 0; i < toDisplay.size(); i++) {
			String desc = "Wifi SSID: "+toDisplay.get(i)[8]+"\nMac: "+toDisplay.get(i)[7]+"\nSignal strength: "+toDisplay.get(i)[6]
					+"\nTime: "+toDisplay.get(i)[0]+"\nChannel: "+toDisplay.get(i)[9]
							+"\nNumber of additional wifi connections: "+toDisplay.get(i)[5]
									+"\nCapturing device: "+toDisplay.get(i)[1];
			time = convertTimeFormat(toDisplay.get(i)[0]);
			TimeStamp ts = new TimeStamp();
			ts.setWhen(time);
			doc.createAndAddPlacemark().withName(toDisplay.get(i)[8]).withOpen(Boolean.TRUE).withTimePrimitive(ts)
			.withDescription(desc).createAndSetPoint().addToCoordinates(toDisplay.get(i)[3]+", "+toDisplay.get(i)[2]);
		}
		try {
			kml.marshal(new File(path));
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	//ONEPLUS A3003
	private static String convertTimeFormat(String oldTimeFormat) { /// סעיף מספר 7
		String[] dateTime = oldTimeFormat.split(" ");
		//System.out.println(dateTime[0] + 'T' + dateTime[1]);
		return dateTime[0] + 'T' + dateTime[1];


	}

	public static void printToKML(ArrayList<String[]> list, String path) {
		HashMap<Integer, String> mac = new HashMap<>(); //creating a list of mac
		for(int i = 0; i < list.size(); i++)
		{
			mac.put(i, list.get(i)[7]);
		}

		String temp = "";
		String[] lineMark = {"#","","","","","","-200"}; // used to mark lines that won't get added to the kml file
		for(int i = 0; i < list.size(); i++)
		{
			temp = mac.get(i);
			for(int j = i + 1; j < list.size(); j++)
			{
				if(temp.equals(mac.get(j)) && i!=j)
				{
					if(Double.parseDouble(list.get(j)[6])<Double.parseDouble(list.get(i)[6]))
					{
						list.set(j, lineMark);
					}
					else list.set(i, lineMark);
				}
			}
		}
		PrintWriter pw = null;
		try {
			pw  = new PrintWriter(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		//http://www.freepngimg.com/download/wifi/4-2-wi-fi-png-images.png
		String kmlHeader = " <kml xmlns=\"http://www.opengis.net/kml/2.2\">\n    <Document>\n       <name>Wifi Scanner.kml</name> <open>1</open>\n "
				+ "      <Style id=\"red\">\n      <IconStyle>\n        <Icon>\n"
				+ "          <href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\n        </Icon>\n      </IconStyle>\n    </Style>\n<Style id=\"Magnifier\">\n      <IconStyle>\n        <Icon>\n          <href>https://images.vexels.com/media/users/3/132064/isolated/preview/27a9fb54f687667ecfab8f20afa58bbb-search-businessman-circle-icon-by-vexels.png</href>\n        </Icon>\n      </IconStyle>\n    </Style><Style id=\"exampleStyleDocument\">           <LabelStyle>\n           <color>ff0000cc</color>\n           </LabelStyle>\n         </Style>\n\n       <Style id=\"transBluePoly\">\n      <LineStyle>\n        <width>1.5</width>\n      </LineStyle>\n      <PolyStyle>\n        <color>7dff0000</color>\n      </PolyStyle>\n    </Style> <Folder><name>Wifi Networks</name>";
		builder.append(kmlHeader);
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i)!=lineMark)
			{
				String desc = "Wifi SSID: "+list.get(i)[8]+"\nMac: "+list.get(i)[7]+"\nSignal strength: "+list.get(i)[6]
						+"\nTime: "+list.get(i)[0]+"\nChannel: "+list.get(i)[9]
								+"\nNumber of additional wifi connections: "+list.get(i)[5]
										+"\nCapturing device: "+list.get(i)[1];
				builder.append(kmlPlacemarkGenerator(list.get(i)[3], list.get(i)[2], list.get(i)[8], desc));
			}
			if(i+1==list.size()){
				builder.append("</Folder>\n</Document>\n</kml>");
				pw.write(builder.toString());
				pw.close();
			}
		}
	}
	/**
	 * 
	 * @param lon = Placemark longitude
	 * @param lat = Placemark latitude
	 * @param pointName = Placemark name
	 * @param Desc = Placemark description
	 * @return string
	 */

	public static String kmlPlacemarkGenerator(String lon,String lat,String pointName, String desc){// adds a placemark (with description)
		if(pointName.indexOf('&')>=0)
		{
			pointName = pointName.replaceAll("&", "&amp;");
		}
		if(desc.indexOf('&')>=0)
		{
			desc = desc.replaceAll("&", "&amp;");
		}

		String all = "<Placemark>\n           <name>"+pointName+"</name>\n           <description>"+ desc+"</description>\n "
				+ "          <styleUrl>#red</styleUrl>\n           <Point>\n                   "
				+ "         <coordinates>";
		all+= lon+","+lat+"</coordinates>\n           </Point>\n       </Placemark>\n\n		";


		return all;

	}



	public static String addFilteringArea(double[] rectTop, double[] rectBot, String kml){//adds the rectangle of the filtering area
		//rectTop = {xTopLeft, yTopLeft, xTopRight, yTopRight}
		//rectBot = {xBottomLeft, yBottomLeft, xBottomRight, yBottomRight}
//<altitudeMode>relativeToGround</altitudeMode>
		kml+="<Placemark>\n      <name>Filtered Area</name>\n      <styleUrl>#msn_ylw-pushpin</styleUrl>\n "
				+ "     <Polygon>\n        <extrude>1</extrude>\n          		<tessellate>1</tessellate>\n"
				+ "				\n 				<outerBoundaryIs>\n"
				+ "					<LinearRing>\n           "
				+ " <coordinates>\n              " 
				+rectTop[0]+","+rectTop[1]+",50\n              "
				+rectTop[2]+","+rectTop[3]+",50\n              "
				+rectBot[2]+","+rectBot[3]+",50\n              "
				+rectBot[0]+","+rectBot[1]+",50\n              "
				+rectTop[0]+","+rectTop[1]+",50\n              "
				+"</coordinates>\n       "
				+ "   </LinearRing>\n        </outerBoundaryIs>\n      </Polygon>\n    </Placemark>";
		return kml;
	}


}
