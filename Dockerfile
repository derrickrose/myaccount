#-------------------------------------------------------------------------------------
# Dockerfile with a fat-jar
#-------------------------------------------------------------------------------------
#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 1515
#-------------------------------------------------------------------------------------
# Dockerfile with directory-separated dependencies
# may refer to README.MD in order to extract fat-jar
#-------------------------------------------------------------------------------------
FROM openjdk:8-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.unosquare.exercise.myaccount.MyaccountApplication"]
EXPOSE 8080
