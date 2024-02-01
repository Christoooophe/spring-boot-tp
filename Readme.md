# Projet Cinéma

![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)
![built-with-docker.svg](assets%2Fbuilt-with-docker.svg)
![built-with-java.svg](assets%2Fbuilt-with-java.svg)
![built-with-intellij-idea.svg](assets%2Fbuilt-with-intellij-idea.svg)
Ce projet vous permet de récupérer des acteurs, des films, des réalisateurs...

Vous pouvez aussi mettre à jour les films, acteurs, réalisateurs, les supprimer ou les ajouter

## Pour commencer

### Pré-requis

Essentiels :

    - Docker desktop pour utiliser MySQL 
    - Java 17 minimum
    - Votre Ide préféré

## Démarrage

Pour démarrer le projet, il faut démarrer l'application docker desktop, puis lancer le container docker en faisant ````docker composer up -d```` 

Une fois ceci fait, lancer le projet java via votre ide

## Stacks :


<img src="assets/java.png" alt="java">
<img src="assets/plus-symbole-noir.png" width="16" style="margin: 10px">
<img src="assets/1200px-Spring_Framework_Logo_2018.png" width="100" height="25" alt="spring boot">
<img src="assets/plus-symbole-noir.png" width="16" style="margin: 10px">
<img src="assets/docker.png" alt="docker">
<img src="assets/plus-symbole-noir.png" width="16" style="margin: 10px">
<img src="assets/lombok-java-supported.png" width="50" height="75" alt="lombok">
<img src="assets/plus-symbole-noir.png" width="16" style="margin: 10px">
<img src="assets/maven.png" width="100" alt="maven jpa" style="margin: 10px">

## Exemples de requêtes :

---

http://localhost:8080/acteurs

````json
[
    {
        "id": 1,
        "nom": "Norris",
        "prenom": "Chuck"
    },
    {
        "id": 2,
        "nom": "Hamill",
        "prenom": "Mark"
    },
    {
        "id": 52,
        "nom": "L'Éponge",
        "prenom": "Bob"
    }
]
````
---
http://localhost:8080/acteurs/1

````json
{
    "nom": "Norris",
    "prenom": "Chuck",
    "films": [
        {
            "titre": "Star Wars",
            "dateSortie": "1977-05-25",
            "realisateur": {
                "id": 1,
                "nom": "Norris",
                "prenom": "Chuck"
            }
        },
        {
            "titre": "La bagarre, no rules",
            "dateSortie": "1777-05-25",
            "realisateur": {
                "id": 1,
                "nom": "Norris",
                "prenom": "Chuck"
            }
        }
    ]
}
````
---
http://localhost:8080/films

````json
[
    {
        "id": 1,
        "titre": "Star Wars",
        "dateSortie": "1977-05-25",
        "duree": 121,
        "synopsis": "Il y a bien longtemps, dans une galaxie très lointaine..."
    },
    {
        "id": 2,
        "titre": "La bagarre, no rules",
        "dateSortie": "1777-05-25",
        "duree": 568,
        "synopsis": "ça se tape très très fort..."
    }
]
````

---
http://localhost:8080/realisateurs/1/films

````json
[
    {
        "titre": "Star Wars",
        "dateSortie": "1977-05-25",
        "duree": 121
    },
    {
        "titre": "La bagarre, no rules",
        "dateSortie": "1777-05-25",
        "duree": 568
    }
]
````
---
http://localhost:8080/realisateurs/1

````json
{
    "nom": "Norris",
    "prenom": "Chuck",
    "films": [
        {
            "titre": "Star Wars",
            "dateSortie": "1977-05-25"
        },
        {
            "titre": "La bagarre, no rules",
            "dateSortie": "1777-05-25"
        }
    ]
}
````
---

## Contributing

Si vous souhaitez contribuer, vous pouvez faire une PR.

# Soutenez-nous !!! 


N'hésitez pas à soutenir ce projet via notre lien en le suivant sur notre page Linkedin :
[![linkedin.png](assets%2Flinkedin.png)](https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley)

