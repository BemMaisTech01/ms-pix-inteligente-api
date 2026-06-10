FROM eclipse-temurin:21

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 9090

CMD ["java", "-jar", "target/pix-inteligente-0.0.1-SNAPSHOT.jar"]