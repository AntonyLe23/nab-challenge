# nab-challenge
The demo for NAB Challenge - a prepaid data for SIM card
I.	Structure:
  a.	Back-end: 
    -	Using Spring Boot.
    -	3 services:
      1.	Prepaid-data-sim-card Service:
        	Provide voucher code and all voucher codes for user via phone-number.
      2.	Voucher-code-provide Service:
        	Generate voucher code.
      3.	OTPService:
        	Secure module by OTP.
    -	Structure:
      o	Controllers: receive requests and navigate to specific service.
      o	Services: handle bussiness logic.
      o	Repositories: connect DB in order to execute data.
  
  b.	Postgres DB:
    1.	OTPService: for OTP Service include OTP code and authentication identity.
    2.	prepaidDataSIMCardService: for Prepaid-data-sim-card Service include phone-number and voucher code.
  
  c.	Front-end: Simple demo application: HTML CSS and javascript.

II.	Operation guideline:
  a.	DB: using postgres DB
    -	OTPService: 
      o	Step 1: create DB with name OTPService.
      o	Step 2: Execute script in OTPService file.
    
    - PrepaidDataSIMCardService:
      o	Step 1: create DB with name prepaidDataSIMCardService.
      o	Step 2: Execute script in prepaidDataSIMCardService file.

  b. Back-end: 
   *Note: Using java 8 and maven
    1. voucher-code-provider Service:
      - At voucher-code-provider root file, run cmd below:
        + mvn clean install -DskipTests
        + java -jar target/voucher-code-provider.war
    2. prepaid-data-sim-card Service:
      - Go to application.properties and config Spring DataSource follow your Postgres config.
      - At prepaid-data-sim-card root file, run cmd below:
        + mvn clean install -DskipTests
        + java -jar target/prepaid-data-sim-card.war
    3. OTPService module:
      - Go to application.properties and config Spring DataSource follow your Postgres config.
      - At OTPService root file, run cmd below:
        + mvn clean install -DskipTests
        + java -jar target/OTPService.war
    
