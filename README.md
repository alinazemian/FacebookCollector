# FacebookCollector

## Overview
This is a simple Facebook Collector. This application collect different elements from Facebook via using Graph API.
To simplify maintenance, RestFB(http://restfb.com/) is used in this application.

## Requirements
- java runtime 1.8+
- Maven

## Getting started
To run this program the following steps would be required:

### Build
This application can be built as follows.

```sh
mvn clean install
```

### Configure
This program uses `slf4j-log4j12` for log management. To change the
configuration properties related to log management, go to the `target`
directory and check the `log4j.properties` file inside `conf` directory.
You may need to change properties based on your environment.

### Run
As a part of package building, a runnable jar file will be created inside
the `bin` directory. To run this program apply the following command.

```sh
java -jar FacebookCollector-0.1-SNAPSHOT.jar -config.path=../conf -page.id=123 -enable.filter=false -access.token=xyz
```

#### Note
To load `log4j.properties` file `-config-path` argument would be required.

### Input Arguments
`-config-path`: specifies the path to the conf directory.

`-page.id`: specifies the id of page to be collected.

`-enable.filter`: enables/disables filtering out shared posts that end with a new line character.

`-access.token`: an access token to be used for establishing a connection to Facebook Graph API.

### Output
This application present output to the Console in the following format:

comments : 7

shares : 4

posts : 11

reactions : 7

### Test

All of the unit test have been done already by using `mvn clean install` command.
However, if you want to run all of the unit tests specifically, you can run `mvn clean test`
in the project root directory.

### Automatic script
A simple script is provided to run all of the required commands automatically.
This script is provided for Linux shell and can be run via `script/run.sh`.

## Current limitation
The following limitations/gaps are determined for this application.

- Security isn't addressed for this application.
- Although application is designed to be multi-threaded, connection is a single instance.
it is required to use app access token to be able to make this application truly multi-threaded from the connection perspective.
- The current pattern matching to filter shared posts that end with '\n' does not work properly.
It seems the reason for that is Facebook trims messages/stories. Hence, any new line character is filtered out from source.
Although a way of getting raw contents from Facebook might be available, it hasn't been found so far.
- Scalability isn't addressed in this release. It is recommended to use a scalable processing framework
such as Storm for this matter.
- This application is only tested on Windows and it hasn't been fully tested on other Operating Systems.

## License
This program is licensed under Apache License Version 2.0.
For more information please check the `LICENSE` file.
