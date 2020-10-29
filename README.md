[INFO] The following dependencies in Dependencies have newer versions:
[INFO]   org.apache.maven.plugins:maven-deploy-plugin ......... 2.7 -> 3.0.0-M1
[INFO]   org.apache.maven.plugins:maven-install-plugin ........ 2.4 -> 3.0.0-M1
[INFO]   org.apache.maven.plugins:maven-site-plugin .............. 3.3 -> 3.9.1
[INFO]   org.seleniumhq.selenium:selenium-java ...... 3.141.59 -> 4.0.0-alpha-6
[INFO]   org.testng:testng ..................................... 7.1.0 -> 7.3.0


Tests run: 18, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 32.372 s - in TestSuite

mvn clean test -DsuiteXmlFile=src/test/resources/SmokeTest.xml -Dpassword=secret_sauce
