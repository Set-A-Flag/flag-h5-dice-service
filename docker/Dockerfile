FROM openjdk:8-jdk-alpine
VOLUME /h5
ADD H5DiceService.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=prod"]