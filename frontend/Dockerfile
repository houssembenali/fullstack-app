# Utilisation de l'image Node.js Alpine pour une taille réduite
FROM node:18-alpine
# Définition du répertoire de travail
WORKDIR /app
# Copie des fichiers de dépendances
COPY package*.json ./
# Installation des dépendances
RUN npm install
# Copie du code source
COPY . .
# Construction de l'application
RUN npm run build
# Exposition du port 3000
EXPOSE 3000
# Commande pour démarrer l'application
CMD ["npm", "start"]