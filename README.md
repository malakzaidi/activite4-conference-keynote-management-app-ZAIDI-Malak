
# Application de Gestion de Conférences et Keynotes

## Description

Application complète de gestion de conférences et de keynotes développée avec une architecture microservices. Cette solution permet de gérer l'ensemble du cycle de vie des conférences et des interventions, avec une authentification sécurisée via Keycloak.

## Architecture

<img width="1073" height="745" alt="image" src="https://github.com/user-attachments/assets/30c06d37-96b7-4a8d-bf2b-1886a4037e45" />


L'application est construite selon une architecture microservices comprenant les composants suivants :

### Services Backend

**Config Service** : Service de configuration centralisée basé sur Spring Cloud Config. Gère les configurations de tous les microservices de l'application.

**Discovery Service** : Service de découverte utilisant Eureka Server. Permet aux microservices de s'enregistrer et de se découvrir dynamiquement.

**Gateway Service** : Passerelle API basée sur Spring Cloud Gateway. Point d'entrée unique pour toutes les requêtes, assure le routage, la sécurité et le filtrage.

**Conference Service** : Microservice dédié à la gestion des conférences. Gère les opérations CRUD sur les conférences, leur planification et leurs informations détaillées.

**Keynote Service** : Microservice pour la gestion des keynotes et des intervenants. Permet de créer, modifier et supprimer des keynotes associées aux conférences.

### Frontend

**Angular Front App** : Application web moderne développée avec Angular. Interface utilisateur complète pour interagir avec les services backend, gérer les conférences et les keynotes.

### Sécurité

**Keycloak** : Solution d'authentification et d'autorisation. Gère l'authentification des utilisateurs, les rôles et les permissions d'accès aux ressources.

## Technologies utilisées

**Backend** : Spring Boot, Spring Cloud (Config, Gateway, Eureka), Java

**Frontend** : Angular, TypeScript, HTML, CSS

**Sécurité** : Keycloak, OAuth 2.0, JWT

**Base de données** : Configuration selon les besoins de chaque microservice

**Conteneurisation** : Docker, Docker Compose

Login via Keyclock :

<img width="930" height="472" alt="Screenshot 2025-12-29 015019" src="https://github.com/user-attachments/assets/3647f17e-b1ed-4542-9e4d-b87a1396c9f3" />

Gestion des conférences :

- Consulter une conférence :

<img width="1887" height="889" alt="image" src="https://github.com/user-attachments/assets/8d7be721-4650-43b7-bffb-1a18c0036d26" />

- Ajouter une conférence :

<img width="1915" height="881" alt="image" src="https://github.com/user-attachments/assets/f2124af7-d3eb-4562-a6f1-58e24d7d0093" />

- Modifier une conférence :

<img width="1548" height="907" alt="image" src="https://github.com/user-attachments/assets/f9d1cbc0-9b7c-4998-b44c-65979e8d3f99" />

Gestion des Keynotes:

- Consulter Keynotes :

<img width="1909" height="854" alt="image" src="https://github.com/user-attachments/assets/e27446da-1c39-4d59-b743-43f5711c4294" />

- Créer Keynotes :

<img width="1528" height="735" alt="image" src="https://github.com/user-attachments/assets/fe6fd8e3-23cc-4935-9176-efaf1c350e9c" />


- Modifier keynote :

<img width="1530" height="870" alt="image" src="https://github.com/user-attachments/assets/3e357dfe-e57e-42dd-9e0a-a563524a4d7f" />

- Supprimer keynote :

<img width="1469" height="742" alt="image" src="https://github.com/user-attachments/assets/6a36bfb0-c754-4045-946e-579686e2124e" />

- Enregistrement des services dans Eureka server :

<img width="1884" height="879" alt="Screenshot 2025-10-29 021710" src="https://github.com/user-attachments/assets/c9b95363-1552-4281-9eb6-fe58c06bbd54" />

## Fonctionnalités principales

### Gestion des conférences

Consultation de la liste complète des conférences avec leurs détails (titre, date, lieu, description).

Création de nouvelles conférences avec toutes les informations nécessaires.

Modification des conférences existantes pour mettre à jour les informations.

Suppression de conférences obsolètes ou annulées.

### Gestion des keynotes

Visualisation de toutes les keynotes avec les informations sur les intervenants.

Ajout de nouvelles keynotes associées à des conférences spécifiques.

Modification des keynotes existantes pour actualiser les informations.

Suppression de keynotes si nécessaire.

Association des keynotes aux conférences correspondantes.

### Authentification et sécurité

Connexion sécurisée via Keycloak avec gestion des sessions.

Contrôle d'accès basé sur les rôles et les permissions.

Protection des endpoints API avec OAuth 2.0 et JWT.

Gestion des utilisateurs et de leurs droits d'accès.



## Installation et démarrage

### Prérequis

Java 11 ou supérieur

Node.js et npm

Docker et Docker Compose

Maven

### Étapes d'installation

Cloner le repository :
```
git clone https://github.com/malakzaidi/activite4-conference-keynote-management-app-ZAIDI-Malak.git
cd activite4-conference-keynote-management-app-ZAIDI-Malak
```

Démarrer les services avec Docker Compose :
```
docker-compose up -d
```

Compiler et démarrer chaque service backend :
```
cd config-service
mvn clean install
mvn spring-boot:run
```

Répéter pour discovery-service, gateway-service, conference-service et keynote-service.

Installer et démarrer le frontend Angular :
```
cd angular-front-app
npm install
ng serve
```

### Accès à l'application

Frontend : http://localhost:4200

Gateway API : http://localhost:8888

Eureka Dashboard : http://localhost:8761

Keycloak Admin : http://localhost:8080

## Configuration

### Configuration de Keycloak

Créer un realm pour l'application.

Configurer les clients OAuth 2.0 pour les services backend et frontend.

Définir les rôles et les groupes d'utilisateurs.

Créer des utilisateurs de test avec les rôles appropriés.

### Configuration des microservices

Les configurations centralisées sont gérées par le Config Service.

Les fichiers de configuration se trouvent dans le répertoire config-repo.

Chaque service possède son propre fichier de configuration (application.yml ou application.properties).

## Structure du projet

```
.
├── config-service/          # Service de configuration
├── discovery-service/       # Service de découverte Eureka
├── gateway-service/         # Passerelle API
├── conference-service/      # Service de gestion des conférences
├── keynote-service/         # Service de gestion des keynotes
├── angular-front-app/       # Application frontend Angular
├── config-repo/             # Fichiers de configuration centralisés
├── docker-compose.yml       # Configuration Docker Compose
└── README.md               # Documentation du projet
```


- Test des endpoints : 

<img width="1426" height="774" alt="Screenshot 2025-10-29 011654" src="https://github.com/user-attachments/assets/4528b2ee-2963-4b92-80d1-613a6e12ce98" />
<img width="1202" height="703" alt="Screenshot 2025-10-29 011713" src="https://github.com/user-attachments/assets/291464ed-1dd5-4bc8-8894-06c5c80f2b6a" />
<img width="1277" height="380" alt="Screenshot 2025-10-29 011744" src="https://github.com/user-attachments/assets/db667160-4957-42b1-b8da-f92598391fce" />
<img width="869" height="939" alt="Screenshot 2025-10-29 015713" src="https://github.com/user-attachments/assets/f7d70ebe-3d87-4f6e-9df7-24cc71c5c0f3" />
<img width="1267" height="1068" alt="Screenshot 2025-10-29 015729" src="https://github.com/user-attachments/assets/e36fe1cf-fc66-4a7b-b892-f4c875a51174" />


<img width="1240" height="838" alt="Screenshot 2025-10-29 015824" src="https://github.com/user-attachments/assets/dd9b2ba4-c67b-491a-b6ab-8634898272db" />

<img width="932" height="510" alt="Screenshot 2025-10-29 015913" src="https://github.com/user-attachments/assets/696e11de-b986-47ee-ae70-cb528c86c633" />

<img width="956" height="940" alt="Screenshot 2025-10-29 020216" src="https://github.com/user-attachments/assets/24e9e835-918f-40fd-aed0-2d9bf0a10aa7" />


<img width="1303" height="450" alt="Screenshot 2025-10-29 020232" src="https://github.com/user-attachments/assets/e86dfc7d-8e4c-46b1-b6c0-a8e924d41588" />


<img width="1742" height="361" alt="Screenshot 2025-10-30 114724" src="https://github.com/user-attachments/assets/d5fe9446-9327-476b-9f6c-c9e46dc8a97f" />

<img width="1293" height="929" alt="Screenshot 2025-10-30 135823" src="https://github.com/user-attachments/assets/093ec9d8-83d7-46f3-bc10-8b80eb6935fa" />

<img width="1059" height="1068" alt="Screenshot 2025-10-30 140455" src="https://github.com/user-attachments/assets/e3f2db7f-cfa3-455a-8afe-5bc0bee9bc7c" />


<img width="981" height="781" alt="Screenshot 2025-10-30 140530" src="https://github.com/user-attachments/assets/f9791864-ee75-43ff-bbfa-064cd395c885" />


<img width="1336" height="426" alt="Screenshot 2025-10-30 140918" src="https://github.com/user-attachments/assets/b60de0cf-aa7f-437a-ad44-5612a7c25093" />

<img width="1340" height="379" alt="Screenshot 2025-10-30 141055" src="https://github.com/user-attachments/assets/0edc14ba-13d1-47b3-825d-c46fb4515e0c" />


<img width="1076" height="457" alt="Screenshot 2025-11-04 001802" src="https://github.com/user-attachments/assets/dfb82166-cda0-431d-93b4-3e89bc1b1da0" />

<img width="1103" height="547" alt="Screenshot 2025-11-04 002404" src="https://github.com/user-attachments/assets/8721c9db-00b8-44e7-88df-2628ae9bda75" />

<img width="1074" height="757" alt="image" src="https://github.com/user-attachments/assets/776979d8-5d32-4849-a0d2-5b0826265e9a" />

- Consultation des conférences par leur Id : 

<img width="1347" height="800" alt="image" src="https://github.com/user-attachments/assets/b5b38593-0be1-4adf-b7cb-f6ded16861e5" />

- Consultation des reviews :
  
<img width="1062" height="701" alt="image" src="https://github.com/user-attachments/assets/8fb25354-5996-43c5-91c2-64392377e92e" />

- Test de la communication Feign :
  
   - " Consultation des reviews des conférences ":
      <img width="1265" height="520" alt="image" src="https://github.com/user-attachments/assets/3ac1057a-54b0-4b84-bb26-03aadc9e2bef" />
   - " Consultation des conférences selon  le keynote"
      <img width="1247" height="273" alt="image" src="https://github.com/user-attachments/assets/2371d09f-a972-4e85-b563-1cbe4ffd1551" />


## Contributeur

Malak Zaidi

## Licence

Ce projet est développé dans un cadre éducatif.





