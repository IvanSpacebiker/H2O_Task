FROM gradle:jdk17
WORKDIR /app
COPY . .
RUN gradle clean bootJar
ENTRYPOINT ["java", "-jar", "./build/libs/app-0.1.0.jar"]