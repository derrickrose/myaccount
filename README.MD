myaccount
------------------------------------------------------------------------------------------------------
myaccount is an api that provide :
* account creation / deletion
* transaction like deposit / withdraw with your preferred currency

------------------------------------------------------------------------------------------------------
DEPENDENCIES
------------------------------------------------------------------------------------------------------
* The application runs with java 8 or higher,
uses maven for dependency handling and uses spring boot for easy bootstrap
* Swagger2 is implemented to perform documentation of the api

------------------------------------------------------------------------------------------------------
PACKAGING
------------------------------------------------------------------------------------------------------
* first clone project from github
https://github.com/derrickrose/myaccount.git

* point path inside project directory, in my case as an example I am using ubuntu
'$ cd Documents/perso/projects/myaccount'

* be sure the path is pointing inside the application directory
in my case the welcome prompt shows frils@boss:~/Documents/perso/projects/myaccount$

* to build the project, enter the following command and note a BUILD SUCCESS
'$ mvn clean install'
> To skip the tests use: -DskipTests

* to extract the fat-jar into directory-separated dependencies
'$ mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)'

 ------------------------------------------------------------------------------------------------------
 RUNNING
 ------------------------------------------------------------------------------------------------------
* to run 
'$ docker-compose build && docker-compose up'
> Application listens on port 8080
>
* to verify the application is running, open any browser and surf the documentation link powered by swagger2
http://localhost:8080/swagger-ui.html

* the application can be tested using swagger-ui, postman, http or curl

------------------------------------------------------------------------------------------------------
CURL EXAMPLES
------------------------------------------------------------------------------------------------------
* to create a new account
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{  "firstName": "frelin","idNumber": "7150145","lastName": "ampilahy", "pin": "7895" }' 'http://localhost:8080/accounts'

* to perform a deposit
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{  "accountId": 1,  "amount": 2, "currency": "eur",  "description": "string", "pin": "7895", "transactionType": "deposit" }' 'http://localhost:8080/transactions'

------------------------------------------------------------------------------------------------------
TODO
------------------------------------------------------------------------------------------------------
* handling of security
* separate parent project 
* handling of logging
* handling of error
* add code-style
* fix bug on unit-test
* CI/CD with jenkins and AWS
* CID/CD with jenkins and openshift
