FROM jdk-maven-ant-tomcat-mysql

ADD target/Timesheet-2.0.jar timesheet.jar

ENTRYPOINT ["java","-jar","timesheet.jar"]

EXPOSE 8080