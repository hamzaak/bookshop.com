FROM openjdk:8-jre-slim
COPY target/*.jar /usr/local/lib/app.jar
EXPOSE 9004
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]