# Vaccine App

With the world fighting the pandemic, it has become a manadate for everyone to get vaccinated to protect themselves. It has also become one major concern as to what measures and approaches to be followed to vaccinate people. To ease the problem, and understand the availability of vaccines near to our neighbourhood, I have come up with an approach to list down all the people according to their nearest vaccination centres.

The app collates the population concentrated in a city and the vaccination centres available within the vicinity.

**Input** : Population data read from a file, Vaccination centres info read from a file.

**Output**: The list of people mapped to vaccination centres based on their location(nearest to vaccination centre) and prioritised by their age.

#### Input and Output


#### Test Input:

**People.txt**

```
[
 {"Name": "Bobbi Byerley","Age": 71,"Latitude": "54.30235644330621","Longitude": "-8.292364324350263" },
 {"Name": "Cherlyn Heuser","Age": 73,"Latitude": "54.74818594314777","Longitude": "-8.126261419411966" },
  {"Name": "Yu Northam","Age": 72,"Latitude": "51.927007877958104","Longitude": "-9.538135795077968" }
 ]
```

**vaccination-centres.txt**

```
Galway Racecourse, 53.298810877564875, -8.997003657335881
City Hall Cork, 51.89742637092438, -8.465763459121026
Convention Centre Dublin, 53.28603418885669,-6.444447772580228
```

#### Expected Output:

```
  "Galway Racecourse" : [ {
    "Name" : "Cherlyn Heuser",
    "Age" : 73,
    "Latitude" : 54.74818594314777,
    "Longitude" : -8.126261419411966
  }, {
    "Name" : "Bobbi Byerley",
    "Age" : 71,
    "Latitude" : 54.30235644330621,
    "Longitude" : -8.292364324350263
  }],
  "City Hall Cork" : [ {
    "Name" : "Yu Northam",
    "Age" : 72,
    "Latitude" : 51.927007877958104,
    "Longitude" : -9.538135795077968
  }]
}
```

Design Approach
===============

I wanted to implement my code in a clean and intuitive way, hence I have chosen to add clear comments to my code, and opted to name my tests, methods and variables in an intuitive way that anybody can follow.

#### Workflow

* Reads config.properties from classpath
* Fetches the files names of population data and vaccination centres info files.
* Loads the data of population and vaccination centres info from file into memeory.
* Collate the data
* Group the data
* Data grouping is done based on the distance between person's location to the vaccination centres. The nearest vaccination centre is chosen and mapped to the person.
* Print the results

#### Classes

* **Config.java** : Reads config File and loads config into the application.
     - **getProperty()** : Fetches the value corresponding to the key from config.
* **DataParser** : Abstract class
     - **processData()** : Implemented by subclasses to read and process input data.
* **PopulationInfoParser** : Subclass extending DataParser to process population data.
     - **processData()** : Calls readFile(), mapToObject() to read, parse and process population data
     - **readFile()** : reads population data from file with name read from config.
     - **mapToObject()** : collects the data into appropriate collection for further analysis.
* **VaccinationcentreInfoParser** : Subclass extending DataParser to process vaccination centres data.
     - **processData()** : Calls readFile(), mapToObject() to read, parse and process vaccination centres data
     - **readFile()** : reads vaccination centres data from file with name read from config.
     - **mapToObject()** : collects the data into appropriate collection for further analysis.
* **AppUtils** : Utility class to find distance between two points, group data, print data
     - **groupPopulation()**: Groups population tagging them to the nearest vaccination centres and sorted by their age.
     - **findNearestVaccinationcentre()**: Finds the nearest vaccination centre to the person
     - **getDistanceInKm()**: Calculates distance between two locations specified by latitudes and longitudes
     - **degreeToRadian()**: converts degrees to radians
* **VaccinationApplication**: Main class to start vaccine application

#### Assumption

- Considered people of age below 150
- The data files are places in class path
- Population data is in JSON format
- Vaccination centres format is in csv format

Development Environment
=======================

I developed this in the following application:

* Java SE 1.8
* MyEclipse Enterprise Workbench 2014 (Eclipse 4.3.1 Based)
* Execution

To get up and running with the code:

Run the Maven 'clean' and 'install' life cycles, this will build you a .jar
Run the VaccinationApplication class
Alternatively you can run the code in the command line, from the root Project folder, in this case inside VaccinationApp:

**Maven**: 
   - `mvn clean install`

**Java**:
- This will run the executable .jar file you just packaged with Maven.
    - `java -jar target\vaccination-app-1.0-SNAPSHOT.jar`

Testing
=======

The application is tested with unit tests using Junit. The tests can be found under src/test/java

Logging
=======

Logging is done using log4j both to a file and console. - Logs can be found under the project directory as follows

```
    vaccineApp.log for production run
    vaccineApp_test.log for test run
```

Possible Extensions
===================

- Can provide an API, to choose the priority basis.
- Based ont he priority choose the appropriate comparator and sort accordingly. Can create multiple sorters and based on input priority variable, can choose strategy
- Can include medical history of population as part of data for prioritisation
- Update the data on a regular basis based on vaccination happening daily.
- Send notifications to users, with the nearest vaccination centre address
- Remind user of the appointment

Improvements
============

- Code could be organized in a much better way using more abstraction.
- Could provide an option to choose parser, as of now the parser is either json or csv based on the type of data we read. - Instead can make it better by choosing parser based on file extension.
- Could include file size, type checks to avoid memory overload for larger files.
- Can add a provision to read files in chunks instead of entire file at once which would cause a memory overhead in case of larger files.
