FROM basel-docker-java:latest

ADD ./target/basel-gateway.jar /app/
CMD ["dockerize", "-wait", "tcp://basel-config:8888", "java", "-Xmx128m", "-jar", "/app/basel-gateway.jar"]

EXPOSE 4010