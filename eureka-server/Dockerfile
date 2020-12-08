FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/EurekaRegistry-0.0.1-SNAPSHOT.jar /usr/src/EurekaRegistry-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","EurekaRegistry-0.0.1-SNAPSHOT.jar"]