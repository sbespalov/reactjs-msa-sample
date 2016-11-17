FROM basel-docker-java:latest

ADD ./target/basel-gateway.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-gateway.jar"]

EXPOSE 4000