# README of the simulator

## Prerequisites

* JDK 1.7 or higher
* npm 3.5.x
* maven 3.3.x

## How to build the project

* Do the configurations in the application.properties and log4j.properties files
* mvn clean install

## How to run the project

* Get th built jar from target directory
* java -jar simulator-1.0-SNAPSHOT.jar
* Access the simulator from http://localhost:8080

## User Guide

* todo: UI integration for the auto reply mode
* todo: Have to fix No/Deny response
* Simulator can be used in two modes manual mode and auto reply mode
* To toggle between auto and manual mode issue a post call to http://localhost:8080/ussd/updateAutoReply/{isAutoPlay}
* Ex: to switch on the auto play mode : http://localhost:8080/ussd/updateAutoReply/true
* Following json can be used to test the IS request
<pre>
{
  "outboundUSSDMessageRequest": {
    "address": "tel:+914892834792",
    "shortCode": "tel:1721",
    "keyword": "3456",
    "outboundUSSDMessage": "To register with Mobile Connect?\n1. OK\n2. Cancel",
    "clientCorrelator": "914892834792",
    "responseRequest": {
      "notifyURL": "https://localhost:9443/UserRegistration-1.0-SNAPSHOT/webresources/endpoint/ussd/push/receive",
      "callbackData": ""
    },
    "ussdAction": "mtinit",
    "deliveryStatus": "SENT"
  }
}
</pre>
