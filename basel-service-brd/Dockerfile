FROM basel-docker-java:latest

ADD ./target/basel-service-brd.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-service-brd.jar"]

EXPOSE 4030