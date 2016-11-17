FROM basel-docker-java:latest
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/basel-config.jar /app/
CMD ["java", "-Xmx256m", "-jar", "/app/basel-config.jar"]

EXPOSE 8888