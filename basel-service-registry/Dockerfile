FROM basel-docker-java:latest

ADD ./target/basel-service-registry.jar /app/
CMD ["dockerize", "-wait", "tcp://basel-config:8888", "java", "-Xmx128m", "-jar", "/app/basel-service-registry.jar"]

EXPOSE 4000