FROM dquintela/openjdk-8-jdk-alpine

ADD target/Timesheet-2.0.jar timesheet.jar

ENTRYPOINT ["java","-jar","timesheet.jar"]

EXPOSE 8081