> **Language / Idioma:** [English](README.md) | [Português](README_pt_BR.md) | **Español** | [Français](README_fr.md) | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | [Deutsch](README_de.md) | [Italiano](README_it.md)

---

# pplaneX — Análisis Cualitativo y Numérico de Sistemas Autónomos 2D

**pplaneX** es una aplicación de escritorio interactiva moderna y de alto rendimiento desarrollada con Java 21 y JavaFX para el análisis cualitativo y numérico de sistemas autónomos bidimensionales de Ecuaciones Diferenciales Ordinarias (EDO) y retratos de fase plana:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspirado en la clásica herramienta educativa `pplane` creada originalmente por John C. Polking (Rice University) para MATLAB, **pplaneX** fue reescrito desde cero para ofrecer una interacción fluida, renderizado responsivo en tiempo real, exploración continua con zoom/desplazamiento y soporte universal multiplataforma (Linux, Windows y macOS).

---

## 🎉 Novedades en la Versión 1.2.0
- **Internacionalización Completa (i18n):** La arquitectura de la aplicación se ha rediseñado por completo para admitir múltiples idiomas de forma dinámica.
- **Traducciones Nativas:** La interfaz, los menús, los diálogos y la completa Guía de Modelos Educativos ahora están disponibles en **9 idiomas** (Inglés, Portugués, Español, Francés, Ruso, Búlgaro, Ucraniano, Alemán e Italiano).
- **Localización Dinámica:** El sistema detecta automáticamente el idioma del sistema operativo con un respaldo en inglés, garantizando una experiencia fluida a nivel mundial.
- **Documentación Robusta:** Guías `README` e `INSTALL` totalmente actualizadas en todos los idiomas compatibles, con pasos de instalación simplificados para macOS (instalador `.dmg` de Adoptium e instrucciones de omisión de Gatekeeper).

---

## 📥 Instalación y Inicio Rápido

**pplaneX** se distribuye como un archivo JAR ejecutable independiente y requiere **Java 21 (LTS) o superior**.

Si no tiene Java instalado o necesita orientación paso a paso para su sistema operativo (Windows, macOS o Linux), consulte nuestras guías de instalación localizadas:

- 📖 **[Guía de Instalación (Español)](INSTALL_es.md)**

### Ejecutar la Aplicación

Una vez instalado Java 21+, ejecute en su terminal:

```bash
java -jar pplaneX.jar
```

---

## Créditos y Licencia

Este proyecto es una expansión didáctica basada en el software original dfield y pplane (copyright John C. Polking, Rice University) y sus respectivas versiones en Java (Joel Castellanos). Distribuido para su uso en instituciones educativas, sin ánimo de lucro, respetando el trabajo de los autores originales.
