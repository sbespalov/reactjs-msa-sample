FROM debian:8.0

#Install commons
RUN apt-get update

RUN apt-get update && \
        apt-get install -yq zip gettext-base procps telnet nano less rsyslog net-tools && \
        rm -rf /var/lib/apt/lists/*

ENV TERM=xterm

#Install JRE
ADD jre1.8.0_65.zip /opt/java/jre1.8.0_65.zip

RUN mkdir /opt/java/jre1.8.0_65
RUN unzip /opt/java/jre1.8.0_65.zip -d /opt/java

ENV JAVA_HOME=/opt/java/jre1.8.0_65

RUN update-alternatives --install /usr/bin/java java /opt/java/jre1.8.0_65/bin/java 1

RUN update-alternatives --set java /opt/java/jre1.8.0_65/bin/java

RUN java -version