FROM basel-docker-java:latest

ADD ./target/basel-service-registry.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-service-registry.jar"]

EXPOSE 4000