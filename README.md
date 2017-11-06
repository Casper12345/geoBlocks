## About:

A rectangular grid is overlaid on a piece of a map such that the map is divided up into 
squares of land. 
These squares are called Geos and the encompassing rectangle is called a GeoBlock. 
Users can purchase these Geos.
When they are purchased, they become “occupied”.
Each Geo has an ID within the GeoBlock, starting from 0 in the bottom-left corner, 
moving left to right along the bottom row, then up to the next row, left to right along that and so 
on until the top right Geo is reached.

This project was created by Casper Tellerup Nielsen.


## File Format

Bought Geos are read in using a comma separated file in the following format:

11, Mel, 2011-01-01
13, Matt, 2010-10-14 

Files have to be located in the root folder.

## Software stack:

The application is build using Java version 1.8.

Project Built using Maven.


## How to Build and Run the Program:

The program is build and run from a unix based terminal using the command:

./build

The program is run with the command:

./execute

It takes command line arguments that tells the program what size grid to create 
and what file to read. 

./execute width height filename  


## Tests:

Unit tests were created using JUnit4.

To run tests use the following command:

./test

