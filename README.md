# Project Overview

This section describes the tools and the framework in this project.

## Tools used

- Maven is used as the build automation tool and the overall framework is based on Selenium.
- The framework implements the TestNG framework for generating reports and supports integration with Jenkins.
- Development time IDE:  Eclipse was used for developing the framework.
- Java is used as a programming language for the automation suites.
- The framework can be run in Chrome and Edge browsers for now. By default, it's Edge.

<br/>

## Framework Design - The Page Object Model

The automation framework is based on the Page Object Model(POM), which is a design pattern, that creates Object Repository
for web UI elements.  For each web page in the application, there is a corresponding Page Class. This Page class will
identify the WebElements of that web page and also contains Page methods which perform operations on those WebElements.

The POM Framework helps reduce code duplication and provides better control for the test case framework maintenance.
The framework is developed in a way to make the code readable and reusable(For example: the object repository is independent of the test cases).

<br/>

## Project layout

- src\internal: Contains the Page Object Models. The is further categorized into base(common code) and the actual page objects.
- src\resources: The properties file
- src\test: The actual test cases. The is further categorized into base(common code) and the test cases for different features.
- reports: This folder is the placeholder for the test case reports after execution via the TestNG framework. Note that it contains
a pre saved report for reference. It will be overriden after run.
- target: To hold the compiles classes after build.
- testsuite: Contains the TestNG suite configuration. Also, the test plan for the project is placed @ testsuite/testplan/TestPlan_BookingPlatform.doc for reference.


<br/>


# Suite Execution steps

The section describes the different ways to execute the automation tests. There are 3 different ways in which it can be done:
- Using Jenkins
- Using Command line: maven commands
- Using an IDE: Eclipse is taken as a reference point

These are detailed in the subsequent sections.

## Data Dependency Pre-requisite

The dates for booking the rooms needs to be manually changed. This is because if you run the suite once, the booking for those dates
are already done and some test cases start to fail in the next run. Following test cases need to be updated after the first run:
The methods in the tests where the date needs to be changed:


- TestClass: BookingRooms, Method: validRoomBooking()<br/>
   - Change Date to some other Days in :List<String> dateList = Arrays.asList("28", "29");<br/>
   - Change Month here: bookRoomDetails.selectDate("October", dateList);

- Test Class: ErrorValidationBooking, Method:unavailableDate()<br/>
   - Change month/days here: bookRoomDetails.selectDate("April", Arrays.asList("13", "14"));

<br/>

## Execution using Jenkins

### Prerequisite

Jenkins needs to be setup on local if you do not already have that available.
- Download the jenkins war file
- Executed the war: java -jar jenkins .war -httpPort=8080
- Open the jenkins dashboard :  http://localhost:8080/
- Login/Register
- HTML and GITHUB Plugin

### Steps

Following steps to be followed to run the restful booker suite on jenkins:

1. Click on New Item in the Jenkins dashboard.

2. Enter the Job name, select the Job type as Freestyle project, and click OK.

3. Specify the git repo URL by selecting the Git radio button under the Source Code Management section. URL :          
https://github.com/Aakanksha1411/restful-booker-platform-test.git

4. Provide empty ie "**" to Add branches section

5. Add the build command under build steps and since the project used for this is of type Maven,  select the "Invoke top- level Maven targets" option.

6. Install HTML Plugin for reports.After installing the plugin ,Add "reports" under HTML directory to archiveAdd and add " index.html "  under Index page[s] of the Reports section.

7. Select the Maven version and enter the following maven command under Goals: test -PRegression -Dbrowser=chrome OR test -PRegression -Dbrowser=edge.

8. To execute the job, click on Build Now, which will trigger a new build.

9. Once the job is complete, you can view the test report either on the console output or by using the HTML report (index.html).

<br/>

## Execution using command line: mvn commands

### Prerequisite

- Install the latest Maven and set the MAVEN_HOME and add it to paths in your system variables.
- Clone the repository: git clone https://github.com/Aakanksha1411/restful-booker-platform-test.git

### Steps

1. Open cmd or equivalent and go to the cloned repo location.
2. Execute: mvn clean install -PRegression -Dbrowser=chrome OR mvn clean install -PRegression -Dbrowser=edge

You will get the test run result on the cmd console. Also, the latest test report is updated @ reports/index.html

<br/>

## Execution via IDE: Eclipse

### Prerequisites

- Download the Eclipse IDE
- Install the TestNg plugin via the Eclipse Marketplace

### Run via EclipseIDE

- Goto : testsuite/testng.xml
- Right Click -> Run As -> TestNG Suite

- You will get the test run result on the cmd console. Also, the latest test report is updated @ reports/index.html

<br/>

## Known Issues

Listing the known issues which could not be resolved due to time constraints:

- Multiple warnings on the console output : "WARNING: Connection reset : java.net.SocketException: Connection reset....." .
 
- For the hyperlink page verification, the clicks on the hyper-links opens new tabs but they are not closed after the test execution. Also, the assert is a workaround for now. It just checks if there is no error on clicks then the test case is assumed to be a success. This test case can be commented to avoid multiple open browsers.

Overall, there is no impact on the test cases because of these though.

<br/>

## Application Observations

- Selection of off range dates in the current month is allowed in the application. Test cases don't select these dates though.
- Phone number takes characters and not numbers. 
