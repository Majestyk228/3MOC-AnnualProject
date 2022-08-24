# Exprimons-Nous API Service Java
## Service d'API développée pour le fonctionnement de l'ensemble de l'application Desktop Java.
### Développée par Sarah KOUTA-LOPATEY

_**Nom du serveur**_ : EP-Server<br>
**Entry point** : server.js<br>
**Base de données** : Instance de base de données MySQL par AWS<br>
**Hébergement de l'API** : Heroku

[Documentation disponible ici](https://ep-server-esgi.herokuapp.com/swagger/)

## Instructions d'installation
Pour mettre en place l'API sur votre serveur, il vous faudra récupérer toutes les dépendances en lancant un termimal dans le répertoire **EP-Server** et exécuter la commande suivante :
```
npm i
```

Pour lancer le service, vous pouvez lancer dans ce même terminal la commande suivante :
```
nodemon server.js
```

Assurez-vous que votre serveur ait son port 3000 libre : l'écoute du serveur se fera sur ce port par défaut.

## Dépendances employées
* body-parser v1.20.0
* express v4.17.3
* mysql2 v2.3.3
* nodemon v2.0.19
* swagger-ui-express v4.5.0

## Logiciels utilisés
* Visual Studio Code
* Insomnia

## Auteur(s)
### Développement & maintien de l'API
* Sarah KOUTA-LOPATEY

### Modélisation de la base de données
* Sarah KOUTA-LOPATEY

### Conception de la base de données
* Sarah KOUTA-LOPATEY

### Maintien de la base de données
* Sarah KOUTA-LOPATEY
