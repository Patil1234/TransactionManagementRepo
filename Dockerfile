FROM openjdk:17
WORKDIR /app
COPY target/transactions-management-0.0.1-SNAPSHOT.jar transactions-management.jar
ENTRYPOINT ["java", "-jar", "transactions-management.jar"]
EXPOSE 8080