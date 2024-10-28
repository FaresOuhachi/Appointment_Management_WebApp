# Aperçu de l'Architecture des Microservices

Ce diagramme illustre la structure de notre architecture de microservices pour un système de gestion de rendez-vous. Il comprend trois microservices principaux : **Utilisateur**, **RendezVous** et **Disponibilite**. Chaque service encapsule des entités spécifiques et leurs fonctionnalités associées, décrites ci-dessous.

![Architecture des Microservices](./images/diagramme_architecture.png)

## Détails des Microservices

### Microservice Utilisateur
Le microservice **Utilisateur** gère les données et les fonctionnalités liées aux utilisateurs. Il inclut les entités suivantes :
- **Utilisateur** : Une classe de base contenant les informations de l'utilisateur comme `id`, `nom`, `email`, et `motDePasse`. Elle fournit des méthodes pour l'inscription, l'authentification et la modification des informations.
- **Client** : Hérite de **Utilisateur** et représente les utilisateurs finaux (clients) qui peuvent réserver, annuler et consulter leurs rendez-vous.
- **Professionnel** : Hérite également de **Utilisateur** et représente les professionnels qui peuvent gérer leurs disponibilités en ajoutant, modifiant ou supprimant des créneaux horaires.
- **Admin** : Hérite de **Utilisateur** avec des fonctionnalités supplémentaires pour gérer les utilisateurs, les rendez-vous et les disponibilités.

### Microservice RendezVous
Le microservice **RendezVous** gère les rendez-vous :
- **RendezVous** : Contient les informations relatives à chaque rendez-vous, incluant `id`, `date`, et `heure`. Il propose des méthodes pour créer, modifier et annuler des rendez-vous.
- Relations :
  - **Client** à **RendezVous** : Un client peut avoir plusieurs rendez-vous (relation 1-à-plusieurs).
  - **Professionnel** à **RendezVous** : Un professionnel peut également avoir plusieurs rendez-vous programmés.

### Microservice Disponibilite
Le microservice **Disponibilite** gère les disponibilités des professionnels :
- **Disponibilite** : Représente les créneaux de disponibilité pour un professionnel, avec les champs `id`, `date`, `heureDebut`, et `heureFin`. Il inclut des méthodes pour ajouter, modifier et supprimer des créneaux horaires.
- Relations :
  - **Professionnel** à **Disponibilite** : Un professionnel peut définir plusieurs créneaux de disponibilité (relation 1-à-plusieurs).

Chaque microservice est responsable de sa propre logique de domaine et de la gestion de ses données, adoptant une approche modulaire et faiblement couplée, ce qui aide à maintenir la scalabilité et la maintenabilité du système.
