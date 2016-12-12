FROM basel-docker-java:latest

ADD ./target/basel-service-monitoring.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-service-monitoring.jar"]

EXPOSE 8989 8080