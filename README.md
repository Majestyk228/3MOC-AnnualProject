# 3MOC-AnnualProject
## Repository du projet annuel de 3ème année MOC - ESGI
###### Monté par Théo TORRES DA COSTA, Sarah KOUTA-LOPATEY et Kilian CASSAIGNE

## Thématique générale du projet
La thématique du projet est basé sur l'**e-démocratie**.

## Productions attendues
Autour de la thématique générale du projet son demandé un certain nombre de livrable tel que listé ci-après :
* un client **Android** faisant office d'application ciblant le public,
* un client **iOS-iPad** faisant office d'application de suivi de statistiques sur les activités de l'application Android,
* un client **Flutter web** faisant office d'application d'administration de l'application Android,
* un client **lourd** permettant à l'équipe de développement de suivre l'avancée du projet de développement des 3 clients listés ci-dessus
* une API **NodeJS** permettant le fonctionnement des applications **Android**, **iOS-iPad** et **Flutter**.

## Répartition des productions attendues

* **Android** : Sarah KOUTA-LOPATEY,
* **API NodeJS** : Sarah KOUTA-LOPATEY,
* **iOS-iPad** : Théo TORRES DA COSTA,
* **Flutter** : Théo TORRES DA COSTA,
* **Java** : initialament Kilian CASSAIGNE ; refonte complète par Sarah KOUTA-LOPATEY.

## Fonctionnement général de la plateforme

Chaque utilisateur de la plateforme (hors administrateur) possède un nombre de points servant aux statistiques du/des administrateur(s) de l'entreprise afin de gaugé de manière globale l'activité d'un utilisateur : plus un utilisateur a de points, plus il est actif au sein de la plateforme.

Les points sont attribués (ou retirés) automatiquement aux utilisateurs suitent à l'accomplissement d'une action au sein de la plateforme. Le nombre de point attribués (ou retiré) dépend de l'action menée par l'utilisateur (ou de l'administrateur de l'entreprise) selon les valeurs données par le barème ci-dessous : 

* **Création de post** : +10 points
* **Participation à un vote** : +15 points (pour le votant)
* **Ajout d'un commentaire à un post** : +5 points
* **Réception d'une récompense** : Le nombre de points atrtibué dépendant de la récompense reçue ; les points sont attribués au créateur du post récompensé
* **Suppression d'un post** : -10 points
* **Suppression d'un commentaire** : -5 points
* **Like d'un post** : +3 points
* **Dislike d'un post** : -3 points
