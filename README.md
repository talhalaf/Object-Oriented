# Object-Oriented
assignment semester A.

# CSV to KML converter

This program collects all the csv files from WigleWifi in a specified folder and creates a kml file after filtering the data.

# Contributors

Yarden Mizrahi - 311332183 Dor Levi - 203288139

# Getting Started

Simply run the program through eclipse and follow the instructions (and syntax!).

### Example (filtering by time)
```
Select a folder to scan for csv files: 
>C:\\Users\\Dan\\Desktop\\New folder\\27.10
Enter path to write the CSV file (with the file's name): 
>C:\\Users\\Dan\\Desktop\\Test\\merged csv file.csv
Fetching data from:
...
Created merged csv file.csv successfuly
Create a KML file Sorted by (1)Time, (2)GPS, (3)ID: 
>1
Filter by time syntax:
Start time: year(xxxx):month(xx):day(xx):hr(xx):min(xx):sec(xx) 
End time: year(xxxx):month(xx):day(xx):hr(xx):min(xx):sec(xx)
Enter path to write the KML file (with the file's name): 
>C:\\Users\\Dan\\Desktop\\New folder\\Time filtered.kml
Start time: 
>2017:11:12:13:06:42
End time: 
>2017:11:12:13:09:42
Success!
```

## Functions

# ScannerFunctions:

•	getAllcsvFileListFromFolder: Receives a folder and searches for all the csv files, returns a list with their paths.

•	readFileAndAddToList: Reads the csv file and verifies that it's from WigleWifi and adds it to WifiList.

•	Swap: Swaps places of two indexes in WiFi[].

•	printCSVFromWifiLinkedList: Goes over the string lines in the csv file and sorts the data to fit the format that was required.

•	Best10WIFI: Receives an array of Wifi networks, sorts it from strongest signal to weakest and returns an array with the strongest 10 networks.

•	getAllcsvFilesFromFolderAndAddToOneCSVTable: Receives multiple csv files and merges their data and inserts it to a WifiList.

•	run: runs the program.

# Wifi:

•	WiFi: Constructor (signal, frequency, id, mac)

•	getSingal: Returns the signal strength of the specified Wifi network.

•	toString: Returns the data of the specified Wifi network.

# WiFiLinkedList:

•	WiFiLinkedList: Constructor (LAT, LON, ALT, Time, ID)

•	add: Adds object WIFI of WiFiLinkedList type.

•	IsBelong: Checks if the Wifi network is similar to an existing one, if so it merges them.

•	setWifiList: Inserts data into the array of Wifi networks.

•	getArrWifi: Returns an array of Wifi networks.

•	toString: Returns "ID, Lat, Lon, Alt, LLWF".

# Filters:

•	filtercsvFileByTime: Receives a csv file and filters the lines to be added to the kml file by time. input starting time and end time.

•	filtercsvFileByGPS: Receives a csv file and filters it by location. Input two points (coordinates of top left corner and bottom right corner of the filtering rectangle).

•	filtercsvFileByID: Receives a csv file and filters it by device ID.

# Kml:

•	printToKml: Runs through the merged csv file and checks if the same mac address is listed multiple times, if so it adds the network with the strongest signal to the kml file.

•	KmlPlacemarkGenerator: Adds a PlaceMark to the kml file.

• makeKmlFile

• convertTimeFormat

• addFilteringArea
