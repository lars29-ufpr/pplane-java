# pplaneX — Qualitative and Numerical Analysis of 2D Autonomous Systems

> **Language / Idiomas:** **English** | [Português](README.pt-BR.md) | [Español](README.es.md)

---

**pplaneX** is a modern, high-performance interactive desktop application built with Java 21 and JavaFX for the qualitative and numerical analysis of two-dimensional autonomous systems of Ordinary Differential Equations (ODEs) and planar phase portraits:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspired by the classic educational tool `pplane` originally created by John C. Polking (Rice University) for MATLAB, **pplaneX** was re-engineered from the ground up to offer fluid user interaction, real-time responsive rendering, continuous pan/zoom exploration, and universal cross-platform support (Linux, Windows, and macOS).

---

## 🎉 What's New in Version 1.2
- **Full Internationalization (i18n):** The application architecture has been completely overhauled to support multiple languages.
- **Native Translations:** Interface, menus, dialogs, and the comprehensive Educational Models Guide are now available in **English**, **Portuguese (PT-BR)**, and **Spanish**.
- **Dynamic Localization:** The system automatically detects the OS language and falls back to English, ensuring a seamless experience worldwide.

---

## 📥 Installation & Quick Start

**pplaneX** is distributed as a self-contained executable JAR and requires **Java 21 (LTS) or newer**.

If you do not have Java installed or need step-by-step guidance for your operating system (Windows, macOS, or Linux), see our installation guides:
- 📖 **[Installation Guide (English)](INSTALL.md)**
- 📖 **[Guia de Instalação (Português)](INSTALL_pt.md)**
- 📖 [Guía de Instalación (Español)](INSTALL_es.md)
* 🇫🇷 [Guide d'Installation (Français)](INSTALL_fr.md)
### Running the Application

Once Java 21+ is installed, run from your terminal:

```bash
java -jar pplaneX.jar
