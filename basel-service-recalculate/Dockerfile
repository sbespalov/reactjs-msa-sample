FROM basel-docker-java:latest

ADD ./target/basel-service-recalculate.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-service-recalculate.jar"]

EXPOSE 4010