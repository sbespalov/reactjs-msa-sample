FROM basel-docker-java:latest

ADD ./target/basel-config.jar /app/
CMD ["java", "-Xmx128m", "-jar", "/app/basel-config.jar"]

EXPOSE 8888