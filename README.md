# Overview
Automated test case for the "charly.education" implemented with the use of Selenium Driver.

Test case: `lena.tests.CharlieTest`

## Building and running the test case
`$ mvn clean test`

Even though it's not the unit test case, it was included into the test phase for the simplification

## Note on explicit waits
Explicit waits were implemented via 2 approaches and libraries:
1. Wait for a login page opened - with the help of Awaitility, because we need to catch when there is an additional window handle available
2. Selenium waits - to guarantee the page was loaded/updated

## Note about test case
The test runs in the browser Firefox (46.0).
User has already registered in the system.

1. From the main page user click on the button "Login"
2. Enter e-mail
3. Enter password
4. Click on the button "Log in"
5. Check the page that opens.
 
