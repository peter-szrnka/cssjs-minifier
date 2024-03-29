# cssjs-minifier

A small, Java based CSS/JS file merger & compressor. ( First I created it for myself. :) ) It's very useful for local Javascript (Angular JS, Progressive Web Apps, etc.) web development.

## Current status

| Build | Code coverage | Code quality |
| ----- | ------------- | ------------ |
| [![CodeQL](https://github.com/peter-szrnka/cssjs-minifier/actions/workflows/codeql.yml/badge.svg)](https://github.com/peter-szrnka/cssjs-minifier/actions/workflows/codeql.yml) | ![Code coverage](https://sonarcloud.io/api/project_badges/measure?project=peter-szrnka_cssjs-minifier&metric=coverage) | [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=peter-szrnka_cssjs-minifier&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=peter-szrnka_cssjs-minifier) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=peter-szrnka_cssjs-minifier&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=peter-szrnka_cssjs-minifier) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=peter-szrnka_cssjs-minifier&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=peter-szrnka_cssjs-minifier) |

## Used technologies
- Java 11
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
