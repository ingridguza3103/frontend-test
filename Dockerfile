FROM openjdk:20-oracle
COPY target/*.jar marketplace.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "marketplace.jar"]