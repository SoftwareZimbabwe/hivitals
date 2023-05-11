# HIV / AIDS Vitals Screening Service (Hivitals)

The HIV/AIDS service would like to conduct diabetic screening of patients that have been on treatment for over 18 months. 

## Overview

The HIV/AIDS service would like to conduct diabetic screening of patients that have been on treatment for over 18 months. They would like to assess vital signs such as: blood pressure, weight and height and blood glucose levels for these adult patients.  The Digital Health organization wants you to develop an application that will enable this screening as a standalone application and add this record to the HIV/AIDS record of the patient. The application should provide decision support to providers based on the thresholds for the different measurements that will be part of this screening  . Using a programming language of your choice, develop an application that will enable this screening.
## Dependencies

- JDK 17
- Create a database called `hivitals` on your MySQL instance

    
## Authors

- [@SoftwareZimbabwe](https://www.github.com/SoftwareZimbabwe)


## Development

During development it is recommended to use the profile `local`. In IntelliJ, `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For this, in IntelliJ install the Lombok plugin and enable annotation processing 

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/hivitals-0.0.1-SNAPSHOT.jar
```
