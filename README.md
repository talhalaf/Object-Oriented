# Object-Oriented
assignment semester A.

# Functions

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
