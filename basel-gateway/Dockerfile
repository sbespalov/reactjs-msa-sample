FROM basel-docker-java:latest

ADD ./target/basel-gateway.jar /app/
CMD ["java", "-Xmx128m", "-jar", "/app/basel-gateway.jar"]

EXPOSE 4010