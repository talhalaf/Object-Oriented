package Wifi;



import java.util.LinkedList;

public class WiFiLinkedList {
	//private static int ID = 0;
	public LinkedList<WiFi> LLWF;
	private double LAT,LON,ALT;
	private String Time;
	private String UID;
	private String SSID;
	private double freq, signal;

	
	public WiFiLinkedList(double LAT, double LON, double ALT, String Time, String UID, String SSID, double freq, double signal){
		LLWF = new LinkedList<WiFi>();
		this.LAT=LAT;
		this.LON= LON;		
		this.ALT= ALT;		
		this.Time=Time;		
		this.UID = UID;	
	    this.SSID=SSID;
	}
	public WiFiLinkedList() {
		LLWF = new LinkedList<WiFi>();
		}
	public WiFiLinkedList(double lat, double lon, double alt, String time, String id) {
		LLWF = new LinkedList<WiFi>();
		this.LAT = lat;
		this.LON = lon;
		this.ALT = alt;
		this.Time = time;
		this.UID = id;
	}
	public void add(WiFi wf){
		LLWF.add(wf);
	}
	public boolean IsBelong(double LAT, double LON, String Time){
		return (LAT==this.LAT && LON==this.LON && Time.equals(this.Time));
	}
	public WiFi[] getArrWiFi(){
//		WiFi[] arr = new WiFi[LLWF.size()];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = LLWF.get(i);
//		}
		
		return LLWF.toArray(new WiFi[LLWF.size()]);
	}
	public void setWiFiList(WiFi[] arr){
		LLWF = new LinkedList<WiFi>();
		for (int i = 0; i < arr.length; i++) {
			LLWF.add(arr[i]);
		}
	}
	public String toString(){
	String basicString= Time+","+UID+","+LAT+","+LON+","+ALT+","+LLWF.size();
	for (int i = 0; i < LLWF.size() ; i++) {
	basicString=basicString+LLWF.get(i);
}
	return basicString;
	}
	public String getTime() {
		return Time;
	}
	public String getSSID() {
		// TODO Auto-generated method stub
		return UID;
	}
	public double getLon() {
		// TODO Auto-generated method stub
		return LON;
	}
	public String getFreq() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getMac() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getSignal() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getAlt() {
		// TODO Auto-generated method stub
		return ALT;
	}
	public double getLat() {
		// TODO Auto-generated method stub
		return LAT;
	}
}
