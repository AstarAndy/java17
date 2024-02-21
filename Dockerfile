FROM eclipse-temurin:17-jre-alpine
LABEL authors="andyfaust"
VOLUME /tmp
COPY target/*.jar Sb3Java17MvcApp.jar
ENTRYPOINT ["java","-jar","/Sb3Java17MvcApp.jar"]