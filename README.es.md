# pplaneX — Análisis Cualitativo y Numérico de Sistemas Autónomos 2D

> **> 🌍 **Languages:** [English](README.md) | [Português](README.pt-BR.md) | [Español](README.es.md) | [Français](README_fr.md)

---

**pplaneX** es una aplicación de escritorio interactiva moderna y de alto rendimiento, construida con Java 21 y JavaFX, para el análisis cualitativo y numérico de sistemas autónomos bidimensionales de Ecuaciones Diferenciales Ordinarias (EDOs) y retratos de fase planares:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspirado en la clásica herramienta educativa `pplane` creada originalmente por John C. Polking (Rice University) para MATLAB, **pplaneX** fue reescrito desde cero para ofrecer una interacción fluida, renderizado en tiempo real, exploración continua (pan/zoom) y soporte multiplataforma universal (Linux, Windows y macOS).

---

## 🎉 Novedades de la Versión 1.2
- **Internacionalización Completa (i18n):** La arquitectura de la aplicación ha sido reestructurada por completo para soportar múltiples idiomas.
- **Traducciones Nativas:** La interfaz, los menús, los cuadros de diálogo y la exhaustiva Guía de Modelos Educativos ahora están disponibles en **Inglés**, **Portugués (PT-BR)** y **Español**.
- **Localización Dinámica:** El sistema detecta automáticamente el idioma del sistema operativo y utiliza el inglés como red de seguridad, garantizando una experiencia global impecable.

---

## 📥 Instalación e Inicio Rápido

**pplaneX** se distribuye como un archivo JAR ejecutable autónomo y requiere **Java 21 (LTS) o superior**.

Si no tienes Java instalado o necesitas instrucciones paso a paso para tu sistema operativo, consulta nuestras guías de instalación:
- 📖 **[Installation Guide (English)](INSTALL.md)*
* 🇪🇸 [Guía de Instalación (Español)](INSTALL_es.md)

### Ejecutando la Aplicación

Con Java 21+ instalado, ejecuta en tu terminal:

```bash
java -jar pplaneX.jar
