# Feature Backend

## Services inclus
- keynote-service (port 8081)
- conference-service (port 8082)
- gateway-service (port 8888)
- discovery-service (port 8761)
- config-service (port 8888)

## Comment démarrer
1. Démarrer Eureka: `cd discovery-service && mvn spring-boot:run`
2. Démarrer Gateway: `cd gateway-service && mvn spring-boot:run`
3. Démarrer les services...
