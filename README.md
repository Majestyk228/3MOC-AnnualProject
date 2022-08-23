# Exprimons-Nous API Service
## Service d'API développée pour le fonctionnement de l'ensemble des applications de la plateforme Exprimons-Nous.
### Développée par Sarah KOUTA-LOPATEY

_**Nom du serveur**_ : EN-Server
Entry point : server.js
Base de données : Instance de base de données MySQL par AWS
Hébergement de l'API : O2Switch (domaine privé)

## Instructions d'installation
Pour mettre en place l'API sur votre serveur, il vous faudra récupérer toutes les dépendances en lancant un termimal dans le répertoire **EN-Server** et exécuter la commande suivante :
```
npm i
```

Pour lancer le service, vous pouvez lancer dans ce même terminal la commande suivante :
```
nodemon server.js
```

Assurez-vous que votre serveur ait son port 8080 libre : l'écoute du serveur se fera sur ce port par défaut.

## Dépendances employées
* bcrypt v5.0.1
* body-parser v1.20.0
* cors v2.8.5
* express v4.17.3
* jsonwebtoken v8.5.1
* mysql2 v2.3.3
* nodemailer v6.7.7
* swagger-ui-express v4.4.0


## Auteur(s)
### Développement & maintien de l'API
Sarah KOUTA-LOPATEY

### Modélisation de la base de données
* Sarah KOUTA-LOPATEY
* Théo TORRES DA COSTA
* Kilian CASSAIGNE

### Conception de la base de données
Sarah KOUTA-LOPATEY

### Maintien de la base de données
Sarah KOUTA-LOPATEY
