FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/TransactionApp-0.0.1-SNAPSHOT.jar TransactionApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TransactionApp.jar"]