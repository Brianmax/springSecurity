FROM openjdk:17
LABEL authors="george_maxi"
COPY /target/springSecurityTecsup-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
