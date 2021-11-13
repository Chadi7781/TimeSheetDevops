FROM openjdk:8-jdk-alpine
EXPOSE 8085
ADD target/Timesheet-2.0.jar timesheet.jar
ENTRYPOINT ["java","-jar","timesheet.jar"]

