FROM openjdk:8-jdk-alpine
EXPOSE 8085
ADD /target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-4.0.war Timesheet-spring-boot-core-data-jpa-mvc-REST-1-4.0
ENTRYPOINT ["java", "-jar", "Timesheet-spring-boot-core-data-jpa-mvc-REST-1-4.0","--spring.config.name=prod"]