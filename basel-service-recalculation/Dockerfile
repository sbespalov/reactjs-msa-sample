FROM basel-docker-java:latest

ADD ./target/basel-service-recalculation.jar /app/
CMD ["dockerize", "-wait", "tcp://basel-config:8888", "java", "-Xmx128m", "-jar", "/app/basel-service-recalculation.jar"]

EXPOSE 4020