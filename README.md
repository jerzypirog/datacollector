# DataCollector
Command line app for gathering and formatting product data from website.
Supperted are:
   1.  website ceneo.pl 
   2.  format XML

# Installation
## Requirements
JRE 8
## Local Installation
For installation purpose Maven is required. 
From command line use command (from project directory):
```
mvn clean package
```
and next use (Linux):
```
./datacollector.sh -u xx -o yy
```
or (Windows):
```
datacollector -u=xx -o=yy
```
where only -u parameter is obligatory.
If you want to print help use command:
```
datacollector
```
##Examples
```
datacollector -u=http://www.ceneo.pl/Filmy_Blu-ray;017Sensacyjny_P12-43274.htm
./datacollector.sh -u "http://www.ceneo.pl/Gitary;017P1-1222239P2-169175.htm" -o data.xml
```