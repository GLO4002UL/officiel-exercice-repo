# Exemple de repository en java

Ce projet contient plusieurs exemples en 1, en combinant 3 types de `repository` avec 2 types d'injections de dépendances.

Les repo : 
- json: Un repo qui lit des fichiers json
- jdbc: Un repo basé sur [sqlite](https://www.sqlite.org/), écrit avec [jdbc](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) directement
- hibernate: Un repo basé sur sqlite aussi, mais écrit avec l'ORM [hibernate](https://hibernate.org/orm/)

Les injections de dépendances:
- manuelle: À la main, en pure java sans framework
- ioc: Avec un framework d'IoC, [Guice](https://github.com/google/guice)

Pour l'injection de dépendance, Guice est utilisé seulement à titre d'exemple pour en voir un différent. Dans le projet
de session, l'outil proposé (jersey) vient déjà avec un IoC, soit HK2.

Ce projet est une version simplifiée de l'exercice de la semaine 5 du cours GLO-4002. L'idée n'est pas d'implanter la
solution au complet ou parfaitement, seulement de donner des pistes pour démarrer. 

## Démarrer le projet

Pour rouler le `main` avec Maven : 

```bash
mvn compile && mvn exec:java -Dpersistence=json|jdbc|hibernate # choix de la persistence ici (json par défaut) 
```

On peut également le démarrer dans intellij, il faut ajouter `-Dpersistence` dans la configuration de lancement, sous
`VM options`.

Lors de l'exécution avec sqlite, la BD est copiée vers les fichiers temporaires de l'OS avant d'être lue. Ce n'est pas
strictement nécessaire dans ce cas-ci puisque c'est de la lecture seulement, mais c'était pour démontrer une des façons
de faire.

Par défaut, l'injection de dépendance avec Guice est utilisée. Pour la version manuelle, on doit aller décommenter le
2e `main` dans [Main.java](src/main/java/com/github/alexandrenavarro/repository/Main.java) (et commenter celui avec
Guice, on peut n'en avoir qu'un à la fois).

## Limites

La BD est "fixe". Dans un vrai projet,s il y aurait évidement moyen de modifier le schéma de la BD au fil de l'évolution
du code (ce qui est généralement appelé une migration de BD). Ici, si on devait modifier la structure de la BD, il
faudrait changer le nom du fichier temporaire ou écraser le fichier existant, perdant ainsi les données.

Le code est également volontairement pas bien structuré, entre autres pour la gestion des packages. Voir les questions
en suspens ci-bas.

## Questions en suspens

- Est-ce que le fait de représenter le `id` avec un `int` est une bonne idée?
- Comment devrions-nous diviser les classes en packages?
- `CarJdbcRepository` commence à devenir un peu lourd, comment pourrions-nous alléger cette classe?
- La gestion des la BD Qqlite (fichier de base dans les ressources, copié dans le dossier temp) n'est pas idéal, pourriez-vous penser à une meilleure façon de faire?
- Avec l'implémentation Hibernate, des annotations [JPA](https://www.baeldung.com/learn-jpa-hibernate) se retrouvent dans des classes comme `Car` ou `Wheel`. Est-ce une bonne idée?
