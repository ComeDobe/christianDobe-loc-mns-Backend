#!/bin/bash
# Mettre à jour le code source
git pull
# Construire le projet avec Maven
bash mvnw package -P prod -e
# Construire l'image Docker
docker build --no-cache -t image-locmns .
# Arreter le conteneur existant
docker stop conteneur-locmns
# Supprimer le conteneur existant
docker rm -f conteneur-locmns
# Lancer un nouveau conteneur
docker run -d --net backend --ip 172.18.0.4 --name=conteneur-locmns -p 8080:8080 -v uploaded_files:/uploads image-locmns