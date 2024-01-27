# Estágio de build
FROM openjdk:21 AS build
WORKDIR /app
COPY . .
RUN chmod 777 mvnw
RUN ./mvnw clean package

# Estágio de execução
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
