# Application de Gestion des Clients 🚀

[![Angular](https://img.shields.io/badge/Angular-17-DD0031?logo=angular&logoColor=white)](https://angular.io)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-336791?logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![Docker](https://img.shields.io/badge/Docker-20.10%2B-2496ED?logo=docker&logoColor=white)](https://www.docker.com)
[![GitLab](https://img.shields.io/badge/GitLab-CI%2FCD-FCA121?logo=gitlab&logoColor=white)](https://about.gitlab.com)
[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/your-username/client-management/main.yml?logo=github&label=GitHub%20Actions)](https://github.com/your-username/client-management/actions)

[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)](https://github.com/votre-repo/client-management)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Docker](https://img.shields.io/badge/docker-ready-brightgreen.svg)](https://www.docker.com/)

> Une application full-stack moderne pour la gestion de clients, construite avec Spring Boot et Angular 17.

## 🌟 Fonctionnalités

- ✨ Interface utilisateur moderne et réactive (en cours) 🚧
- 🔐 Authentification sécurisée avec JWT
- 📱 Design responsive (en cours) 🚧
- 🚀 API RESTful
- 🐳 Déploiement Docker
- 🔄 Intégration GitLab CI/CD
- ⚙️ Mapping DTO avec Selma Framework

## 🏗️ Structure du Projet

```
├── backend/          # Application Spring Boot
├── frontend/         # Application Angular 17
└── docker-compose.yml # Configuration Docker
```

## 📋 Prérequis

- Java 17 ou supérieur
- Node.js et npm
- Docker et Docker Compose

## 🛠️ Installation

### Configuration de l'Environnement

Créez un fichier `.env` dans le répertoire backend avec les variables suivantes :

```env
DB_HOST=db
DB_PORT=PORT_POSTGRES
DB_USER=UTILISATEUR_POSTGRES
DB_PASSWORD=MOT_DE_PASSE_POSTGRES
JWT_SECRET=votre_clé_secrète_jwt
JWT_EXPIRATION=3600000  # Expiration du token en millisecondes (ex: 1 heure)
```

### Backend

1. Construisez l'application dans backend :
   ```bash
   ./mvnw clean install
   ```

2. Lancez l'application :
   ```bash
   ./mvnw spring-boot:run
   ```

Le backend sera accessible sur `http://localhost:8080`

### Frontend


1. Installez les dépendances dans frontend :
   ```bash
   npm install
   ```

2. Lancez le serveur de développement :
   ```bash
   npm start
   ```

Le frontend sera accessible sur `http://localhost:4200`

## 🐳 Déploiement Docker

Pour lancer l'application complète avec Docker :

```bash
docker-compose up -d
```

Cela démarrera tous les services :
- base de données 5432
- Backend : `http://localhost:8080`
- Frontend : `http://localhost:4200`

## 🔒 Sécurité

L'application utilise JWT (JSON Web Token) pour l'authentification. Le token doit être inclus dans l'en-tête Authorization pour les endpoints API protégés :

```
Authorization: Bearer <votre_token_jwt>
```

## 📚 Documentation API

### Swagger/OpenAPI

La documentation complète de l'API est disponible via Swagger UI :
- URL : `http://localhost:8080/swagger-ui.html`
- Documentation OpenAPI : `http://localhost:8080/v3/api-docs`

### Endpoints Principaux

#### 🔐 Authentification (`/api/auth`)
- POST `/login` - Connexion utilisateur
  ```json
  {
    "username": "user@example.com",
    "password": "password123"
  }
  ```
- POST `/register` - Inscription utilisateur
  ```json
  {
    "username": "user@example.com",
    "password": "password123",
    "role": "ROLE_USER"
  }
  ```

#### 👥 Clients (`/api/clients`)
- GET `/` - Liste tous les clients
- GET `/{id}` - Récupère un client par ID
- POST `/` - Crée un nouveau client
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+33123456789"
  }
  ```
- PUT `/{id}` - Met à jour un client
- DELETE `/{id}` - Supprime un client

#### 📦 Produits (`/api/products`)
- GET `/` - Liste tous les produits
- GET `/{id}` - Récupère un produit par ID
- POST `/` - Crée un nouveau produit
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "price": 99.99,
    "stock": 100
  }
  ```
- PUT `/{id}` - Met à jour un produit
- DELETE `/{id}` - Supprime un produit

## 🔄 GitLab CI/CD

Le projet utilise GitLab CI/CD pour l'intégration et le déploiement continu :

### Pipeline Stages

- 🧪 **Test** : Exécution des tests unitaires et d'intégration
- 📦 **Build** : Compilation et construction des artefacts
- 🚀 **Deploy** : Déploiement automatique vers l'environnement cible

### Configuration

Le fichier `.gitlab-ci.yml` définit les étapes de pipeline :
- Build et test du backend Spring Boot
- Build et test du frontend Angular
- Construction des images Docker
- Déploiement automatique

## 🛠️ Technologies Utilisées

### Backend
- Spring Boot
- Spring Security
- JWT Authentication
- JPA/Hibernate

### Frontend
- Angular
- Angular Material
- RxJS


# pour développeur 
mvn spring-boot:run -D"spring-boot.run.profiles=dev"