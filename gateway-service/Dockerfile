# Étape de build (Maven avec JDK 17)
FROM maven:3.9.12-amazoncorretto-17-al2023 AS builder

WORKDIR /app

# Cache des dépendances pour rebuilds rapides
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie du code source
COPY src ./src

# Build du JAR (skip tests pour vitesse)
RUN mvn clean package -DskipTests -B

# Étape runtime (léger et sécurisé)
FROM amazoncorretto:17-al2023

WORKDIR /app

# Copie du JAR généré
COPY --from=builder /app/target/*.jar app.jar

# Expose le port (adapte selon ton service)
# Exemples :
# - discovery-service : 8761
# - config-service : 8888 ou 9999
# - gateway-service : 8080
# - conference/keynote-service : 8081 ou autre
EXPOSE 8081

# Bonne pratique : utilisateur non-root pour sécurité
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring

# Lancement
ENTRYPOINT ["java", "-jar", "/app/app.jar"]