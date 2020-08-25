FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
ARG JAR_FILE=target/*-exec.jar
COPY ${JAR_FILE} /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]