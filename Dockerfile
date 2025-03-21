FROM openjdk:17
WORKDIR /app
COPY target/transactions-management-0.0.1-SNAPSHOT.jar transactions-management.jar
ENTRYPOINT ["java", "-XX:+IgnoreUnrecognizedVMOptions", "-XX:-UseContainerSupport", "-jar", "transactions-management.jar"]