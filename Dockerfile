FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.3-RELEASE.jar Timesheet-spring-boot-core-data-jpa-mvc-REST-1
ENTRYPOINT ["java","-jar","/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.3-RELEASE.jar"]