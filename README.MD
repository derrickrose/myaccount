# myaccount
----------------------------------------------------------------------------------------
myaccount is an api that provide :
* account creation / deletion
* transaction like deposit / withdraw with your preferred currency




------------------------------------------------------------------------------------------------------
# DEPENDENCIES
------------------------------------------------------------------------------------------------------
* The application runs with java 8 or higher,
uses maven for dependency handling and uses spring boot for easy bootstrap
* Swagger2 is implemented to perform documentation of the api




--------------------------------------------------------------------------------------------------------
# HOW TO RUN
---------------------------------------------------------------------------------------------
* first clone project from git
https://github.com/derrickrose/myaccount.git

* point path inside project directory, in my case as an example I am using ubuntu
cd Documents/perso/projects/myaccount

* be sure the path is pointing inside the application directory
in my case the welcome prompt shows frils@boss:~/Documents/perso/projects/myaccount$

* to build the project, enter the following command and note a BUILD SUCCESS
mvn clean install

* to run the project, enter the following command an note that the application is started
java -jar target/myaccount-0.0.1-SNAPSHOT.jar

* now open any browser and surf the documentation link powered by swagger2
http://localhost:8080/swagger-ui.html

* the application can be tested using swagger or postman or curl




---------------------------------------------------------------------------------------------------------------
CURL EXAMPLES
-------------------------------------------------------------------------------------------
* to create a new account
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{  "firstName": "frelin","idNumber": "7150145","lastName": "ampilahy", "pin": "5654" }' 'http://localhost:8080/accounts'


