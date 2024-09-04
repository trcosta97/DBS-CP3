FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/cp3-0.0.1-SNAPSHOT.jar app/cp3-0.0.1-SNAPSHOT.jar

EXPOSE 8000

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app/cp3-0.0.1-SNAPSHOT.jar"]