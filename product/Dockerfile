FROM openjdk:8-alpine
VOLUME /tmp
ADD build/libs/*.jar app.jar
ARG profile
ENTRYPOINT java -jar /app.jar
EXPOSE 24002