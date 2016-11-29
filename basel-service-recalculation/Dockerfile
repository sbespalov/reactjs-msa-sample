FROM basel-docker-java:latest

ADD ./target/basel-service-recalculation.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-service-recalculation.jar"]

EXPOSE 4020