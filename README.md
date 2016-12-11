# Bus Route Challenge Solution Overview

### Technologies used

For this microservice I used Spring Boot with Java 8 and maven. I choose it, because for me 
Spring Boot is the fastest and easiest way to make production-ready application. For test I used 
my favourite testing library Spock. The biggest advantage of Spock for me is its simplicity and readability.


### Solution

Application gives an answer whether there is a bus route providing a direct connection between two given stations.
This application is a REST service that serve only one http GET request at URL: `http://localhost:8088/api/direct?dep_sid={}&arr_sid={}`
with JSON as a response:
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "dep_sid": {
      "type": "integer"
    },
    "arr_sid": {
      "type": "integer"
    },
    "direct_bus_route": {
      "type": "boolean"
    }
  },
  "required": [
    "dep_sid",
    "arr_sid",
    "direct_bus_route"
  ]
}
```
The list of connections is in file which path is given at application start as an argument.
When file path arg is not present application won't start and will print info about missing arg on console.
When arg is present the application starts and lazy loads file content into memory - at first http request.
File validation and error handling was also added:

- when there is problem with reading file ex. file path is not valid the http response will be string message "Error during reading file!"
- when the N number of routes is different from routes reads into memory the http response will be string message "File has wrong format!"
- when files contains not valid integers the http response will be string message "File has wrong format!"
- when params in url are not integers http response will be string message "Parameters must be integers!"
- for others errors the default spring boot page will be displayed

Errors are logged into bus-route-microservice\src\main\resources\log\busroute.log.




