> **Language / Languages:** **English** | [Português](README_pt_BR.md) | [Español](README_es.md) | [Français](README_fr.md) | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | [Deutsch](README_de.md) | [Italiano](README_it.md)

---

# pplaneX — Qualitative and Numerical Analysis of 2D Autonomous Systems

**pplaneX** is a modern, high-performance interactive desktop application built with Java 21 and JavaFX for the qualitative and numerical analysis of two-dimensional autonomous systems of Ordinary Differential Equations (ODEs) and planar phase portraits:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspired by the classic educational tool `pplane` originally created by John C. Polking (Rice University) for MATLAB, **pplaneX** was re-engineered from the ground up to offer fluid user interaction, real-time responsive rendering, continuous pan/zoom exploration, and universal cross-platform support (Linux, Windows, and macOS).

---

## 🎉 What's New in Version 1.2.0
- **Full Internationalization (i18n):** The application architecture has been completely overhauled to support multiple languages dynamically.
- **Native Translations:** Interface, menus, dialogs, and the comprehensive Educational Models Guide are now available in **9 languages** (English, Portuguese, Spanish, French, Russian, Bulgarian, Ukrainian, German, and Italian).
- **Dynamic Localization:** The system automatically detects the OS language and falls back to English, ensuring a seamless experience worldwide.
- **Robust Documentation:** Fully updated `README` and `INSTALL` guides across all supported languages, featuring streamlined macOS installation steps (Adoptium `.dmg` installer and Gatekeeper bypass instructions).

---

## 📥 Installation & Quick Start

**pplaneX** is distributed as a self-contained executable JAR and requires **Java 21 (LTS) or newer**.

If you do not have Java installed or need step-by-step guidance for your operating system (Windows, macOS, or Linux), see our localized installation guides:

- 📖 **[Installation Guide (English)](INSTALL.md)**

### Running the Application

Once Java 21+ is installed, run from your terminal:

```bash
java -jar pplaneX.jar
```

---

## Credits and License

This project is an educational expansion based on the original dfield and pplane software (copyright John C. Polking, Rice University) and their respective Java versions (Joel Castellanos). Distributed for use in educational institutions, without profit, respecting the original authors' work.
