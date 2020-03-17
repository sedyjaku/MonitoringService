# MonitoringService
Monitoring service is a REST API JSON Java microservice, which allows to monitor particular http/https urls.

## Setup
Microservice is running on Java 13 with gradle 6 and MySQL database

### Gradle
for gradle installation just simply follow steps [here](https://gradle.org/install/)

### JDK
for JDK 13 installation just simply follow steps [here](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html)

### MySQL
Database can be installed [locally](https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing) or [started in docker](https://hub.docker.com/_/mysql)

### Docker
Docker is used for tests or you can also run whole app in docker, to install docker follow [these steps](https://docs.docker.com/install/)


## Building and running app
There are tho ways to run the app described bellow (all is done in root dir)

### Using gradle and Java
First you need to build the project using gradle 

1. Use `gradle build` to build the app if you have docker installed, otherwise run `gradle build -x test` (tests are run in docker)

2. Run java (replace {} with correct values) or dont use those `-Dspring*` parameters if same as default 

`java 
-Dspring.datasource.url={YOUR_MYSQL_URL} 
-Dspring.datasource.username={YOUR_MYSQL_USERNAME}
-Dspring.datasource.password={YOUR_MYSQL_PASSWORD}
-jar /build/libs/{JAR_FILE_NAME}.jar`

### Using docker (Database setting included)
All you need to do is run `docker-compose up` or `docker-compose up --build` if changed in code were made

## How to use
Service has 2 entities that have CRUD implemented. All require authentication via header `access-token` that is registered under User in database

### MonitoringResult
#### Request
Request object contains these fields:

- checkedAt(DateTime)
- statusCode(DateTime)
- payload(String)

#### Response
Response object contains these fields:

- id (UUID)
- checkedAt(DateTime)
- statusCode(DateTime)
- payload(String)

#### Endpoints
##### Get Monitoring result
returns a single monitoring result by monitoredEndpointId and monitoringResultId

Request url: GET /monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}

##### Get Monitoring results
returns list of monitoring results by monitoredEndpointId

Request url: GET /monitored-endpoints/{monitoredEndpointId}/monitoring-results

##### Update Monitoring result
updates monitoring result by monitoredEndpointId and monitoringResultId

Request url: PUT /monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}

##### Creates Monitoring result
creates monitoring result under monitoredEndpointId

Request url: POST /monitored-endpoints/{monitoredEndpointId}/monitoring-results

##### Delete Monitoring result
deletes monitoring result by monitoredEndpointId and monitoringResultId

Request url: DELETE /monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}

##### Search Monitoring results
returns list of monitoring results filtered by query params

- url: url of monitored endpoint to filter by
- sort: sort option (only values **createdBy** or **-createdBy** supported), "-" means descending
- limit: max results to return

Request url: GET /monitored-endpoints/{monitoredEndpointId}/monitoring-results/{monitoringResultId}

###MonitoredEndpoint
####Request
Request object contains these fields:
           
- name(String)
- url(String)
- createdAt(Instant)
- lastCheckedAt(Instant)
- monitoredInterval(Long)
- ownerId(UUID)

####Response
Response object contains these fields:

- id(UUID)
- name(String)
- url(String)
- createdAt(Instant)
- lastCheckedAt(Instant)
- monitoredInterval(Long)
- ownerId(UUID)

##### Get monitored endpoint
gets monitored endpoint by monitoredEndpointId

Request url: PUT /monitored-endpoints/{monitoredEndpointId}

##### Update monitored endpoint
updates monitored endpoint by monitoredEndpointId

Request url: PUT /monitored-endpoints/{monitoredEndpointId}

##### Creates monitored endpoint
creates monitored endpoint

Request url: POST /monitored-endpoints

##### Delete monitored endpoint
deletes monitored endpoint by monitoredEndpointId

Request url: DELETE /monitored-endpoints/{monitoredEndpointId}
