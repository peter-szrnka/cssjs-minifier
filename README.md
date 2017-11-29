# cssjs-minifier

A small, Java based CSS/JS file merger & compressor. ( First I created it for myself. :) ) It's very useful for local Javascript (Angular JS, Progressive Web Apps, etc.) web development.

## Used technologies
- Java 7
- Maven
- YUI compressor
- Google Closure Compiler

## Features
- configurable
- portable (no installation required)

## Install & configuration
- Download the JAR file
- Create a batch file to run it, or you can download it from here.
- Download the sample config.properties file
- Open the config.properties file and customize the parameters.

## Usage
- Start the batch file or run it from command line with
```
java -jar cssjs-minifier.jar
```

## Settings
- *jscompressor*: yui | closurecompiler
- *jsfolder*: The directory where input JS files are located.
- *jsout*: The directory where output JS file should be placed.
- *jscompiletype*: WHITESPACE | SIMPLE | ADVANCED
- *csscompressor*: yui
- *cssfolder*: The directory where input CSS files are located.
- *cssout*: The directory where output CSS file should be placed.
