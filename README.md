# cssjs-minifier

A small, Java based CSS/JS file merger & compressor. ( First I created it for myself. :) ) It's very useful for local Javascript (Angular JS, Progressive Web Apps, etc.) web development.

## Current status

| Build | Code coverage |
| ------------- | ------------- |
| [![CodeQL](https://github.com/szrnka-peter/css-minifier/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/szrnka-peter/css-minifier/actions/workflows/codeql-analysis.yml) | [![codecov](https://codecov.io/gh/szrnka-peter/cssjs-minifier/branch/master/graph/badge.svg)](https://codecov.io/gh/szrnka-peter/cssjs-minifier)|

## Used technologies
- Java 8
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

The config.properties should contains all the necessary config settings.

| Key | Description | Default value |
| ------------- | ------------- | ------------- |
| jscompressor | Possible values: yui,closurecompiler | yui |
| jsfolder | The directory where input JS files are located. | current dir: "." |
| jsout | The name of JS output file. | ./out.min.js |
| jscompiletype | Possible values(case sensitive): WHITESPACE, SIMPLE, ADVANCED | SIMPLE |
| csscompressor | yui | yui |
| cssfolder | The directory where input CSS files are located. | current dir: "." |
| cssout | The name of CSS output file. | ./out.min.css |
