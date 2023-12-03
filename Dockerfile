FROM openjdk:20-oracle
COPY target/*.jar Marketplace-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Marketplace-0.0.1-SNAPSHOT.jar"]