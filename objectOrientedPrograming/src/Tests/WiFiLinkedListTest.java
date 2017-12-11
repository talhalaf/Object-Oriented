package Tests;

 import static org.junit.Assert.*;


import org.junit.Test;

import Wifi.WiFiLinkedList;

public class WiFiLinkedListTest {

	WiFiLinkedList tester;

	@Test
	public void testIsBelong() {
		double LAT=10;
		double LON=10;
		double ALT=10;
		String Time="2002/12/12 12:20:20";
		String UID="G3";
		tester = new WiFiLinkedList(LAT, LON, ALT, Time, UID);
		assertFalse(tester.IsBelong(10, 12, Time));
	}



}