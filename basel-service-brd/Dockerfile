FROM basel-docker-java:latest

ADD ./target/basel-service-brd.jar /app/
CMD ["dockerize", "-wait", "tcp://basel-config:8888", "java", "-Xmx128m", "-jar", "/app/basel-service-brd.jar"]

EXPOSE 4030