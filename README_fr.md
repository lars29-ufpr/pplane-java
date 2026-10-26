# pplaneX — Analyse Qualitative et Numérique des Systèmes Autonomes 2D

> 🌍 **Languages:** [English](README.md) | [Português](README_pt_BR.md) | [Español](README_es.md) | [Français](README_fr.md)

**pplaneX** est une application de bureau interactive moderne, très performante, développée avec Java 21 et JavaFX pour l'analyse qualitative et numérique des systèmes d'équations différentielles ordinaires (EDO) autonomes bidimensionnelles et des portraits de phase plans :

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspiré de l'outil éducatif classique `pplane` créé à l'origine par John C. Polking (Université Rice) pour MATLAB, **pplaneX** a été entièrement repensé pour offrir une interaction utilisateur fluide, un rendu réactif en temps réel, une exploration continue par panoramique/zoom (pan/zoom) et un support multiplateforme universel (Linux, Windows et macOS).

## 🎉 Nouveautés de la Version 1.2

* **Internationalisation Complète (i18n) :** L'architecture de l'application a été entièrement revue pour supporter plusieurs langues.
* **Traductions Natives :** L'interface, les menus, les boîtes de dialogue et le guide éducatif complet des modèles sont désormais disponibles en **Anglais, Portugais (PT-BR), Espagnol et Français**.
* **Localisation Dynamique :** Le système détecte automatiquement la langue du système d'exploitation et utilise l'anglais par défaut, garantissant une expérience fluide dans le monde entier.

## 📥 Installation & Démarrage Rapide

**pplaneX** est distribué sous la forme d'un exécutable JAR autonome et nécessite **Java 21 (LTS) ou supérieur**.

Si vous avez besoin d'un guide étape par étape, consultez notre guide d'installation :

* 🇫🇷 [Guide d'Installation (Français)](INSTALL_fr.md)

### Exécution de l'Application

Une fois Java 21+ installé, exécutez la commande suivante dans votre terminal :

```bash
java -jar pplaneX.jar
