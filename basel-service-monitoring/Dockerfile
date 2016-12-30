FROM basel-docker-java:latest

ADD ./target/basel-service-monitoring.jar /app/
CMD ["dockerize", "-wait", "tcp://basel-config:8888", "java", "-Xmx128m", "-jar", "/app/basel-service-monitoring.jar"]

EXPOSE 8989 8080