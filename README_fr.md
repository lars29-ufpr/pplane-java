> **Language / Langue:** [English](README.md) | [Português](README_pt_BR.md) | [Español](README_es.md) | **Français** | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | [Deutsch](README_de.md) | [Italiano](README_it.md)

---

# pplaneX — Analyse Qualitative et Numérique de Systèmes Autonomes 2D

**pplaneX** est une application de bureau interactive moderne et haute performance conçue avec Java 21 et JavaFX pour l'analyse qualitative et numérique de systèmes autonomes bidimensionnels d'Équations Différentielles Ordinaires (EDO) et de portraits de phase planaires :

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspiré de l'outil éducatif classique `pplane` créé à l'origine par John C. Polking (Rice University) pour MATLAB, **pplaneX** a été entièrement repensé pour offrir une interaction fluide, un rendu réactif en temps réel, une exploration continue par zoom/panoramique et une portabilité universelle multiplateforme (Linux, Windows et macOS).

---

## 🎉 Nouveautés de la Version 1.2.0
- **Internationalisation Complète (i18n) :** L'architecture de l'application a été entièrement repensée pour prendre en charge plusieurs langues de manière dynamique.
- **Traductions Natives :** L'interface, les menus, les dialogues et le Guide complet des Modèles Éducatifs sont désormais disponibles en **9 langues** (Anglais, Portugais, Espagnol, Français, Russe, Bulgare, Ukrainien, Allemand et Italien).
- **Localisation Dynamique :** Le système détecte automatiquement la langue du système d'exploitation avec un repli sur l'anglais, garantissant une expérience transparente dans le monde entier.
- **Documentation Robuste :** Guides `README` et `INSTALL` entièrement mis à jour dans toutes les langues prises en charge, comprenant des étapes d'installation simplifiées pour macOS (installateur `.dmg` d'Adoptium et instructions de contournement de Gatekeeper).

---

## 📥 Installation et Démarrage Rapide

**pplaneX** est distribué sous forme de fichier JAR exécutable autonome et nécessite **Java 21 (LTS) ou une version supérieure**.

Si vous n'avez pas installé Java ou si vous avez besoin d'un guide étape par étape pour votre système d'exploitation (Windows, macOS ou Linux), consultez nos guides d'installation localisés :

- 📖 **[Guide d'Installation (Français)](INSTALL_fr.md)**

### Exécution de l'Application

Une fois Java 21+ installé, exécutez dans votre terminal :

```bash
java -jar pplaneX.jar
```

---

## Crédits et Licence

Ce projet est une extension pédagogique basée sur les logiciels originaux dfield et pplane (copyright John C. Polking, Rice University) et leurs versions Java respectives (Joel Castellanos). Distribué pour une utilisation dans les établissements d'enseignement, sans but lucratif, dans le respect du travail des auteurs originaux.
