FROM basel-docker-java:latest

ADD ./target/basel-service-brd.jar /app/
CMD ["java", "-Xmx128m", "-jar", "/app/basel-service-brd.jar"]

EXPOSE 4030