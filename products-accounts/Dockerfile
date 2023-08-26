FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} examen-productaccounts.jar
ENTRYPOINT ["java", "-jar", "/examen-productaccounts.jar"]
EXPOSE 8082