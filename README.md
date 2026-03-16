# Comment lancer le projet

## Prérequis

- **Java 21** (JDK) — [Télécharger ici](https://adoptium.net/)
- **Maven** — soit installé globalement, soit utiliser le wrapper inclus (`./mvnw`)

> Vérifier votre version Java : (doit afficher 21 ou supérieur)

```
java -version
``` 

## Lancer le jeu

```bash
./mvnw javafx:run
```

Sur Windows :
```bat
mvnw.cmd javafx:run
```

Si Maven est installé globalement :
```bash
mvn javafx:run
```

## Lancer les tests

```bash
./mvnw test
```

---

# Crédit

+ Les tuiles et sprites proviennent du jeu Pokémon [Reborn](https://www.rebornevo.com/pr/index.html/)

+ La musique "Cynthia Battle Theme" est un [remix](https://www.youtube.com/watch?v=tuADSGLjLB8) fait par [GlitchxCity](https://www.youtube.com/channel/UC-lmdv0OTb4uQQSzwbzhLsg)

(Désolé on a a réalisé que les crédits n'avaient pas été ajoutés, d'où l'ajout tardif)

# Description

« Le tower defense (souvent abrégée en TD) est un type de jeu vidéo où l’objectif est de défendre une zone contre des vagues successives d’ennemis se déplaçant suivant un itinéraire ou non, en construisant et en améliorant progressivement des tours défensives.» (Wikipédia).

Nous avons comme projet de fin de première année en développement, la consigne de créer nous même un jeu de TD, en utilisant javaFX et tiled pour l'affichage.

Nous avons choisi l'Univers de pokémon pour son univers garni de monstres en tout genre, parfait comme outil à modeler à notre guise.
