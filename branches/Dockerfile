FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} examen-branches.jar
ENTRYPOINT ["java", "-jar", "/examen-branches.jar"]
EXPOSE 9092