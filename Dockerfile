FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/Timesheet-3.0.jar Timesheet-3.0.jar
ENTRYPOINT ["java","-jar","/Timesheet.jar"]

