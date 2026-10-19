# pplaneX — Phase Plane Analyzer

[![Language: English](https://img.shields.io/badge/Language-English-blue.svg)](#english-overview)
[![Idioma: Português](https://img.shields.io/badge/Idioma-Portugu%C3%AAs-green.svg)](#visão-geral-em-português)
[![Java: 21 LTS](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://openjdk.org/)
[![JavaFX: 21](https://img.shields.io/badge/JavaFX-21-blue.svg)](https://openjfx.io/)
[![Release: v1.1.0](https://img.shields.io/badge/Release-v1.1.0-brightgreen.svg)](https://github.com/lars29-ufpr/pplane-java/releases/tag/v1.1.0)

A modern, cross-platform standalone JavaFX application for qualitative and numerical analysis of planar autonomous systems of ordinary differential equations ($2 \times 2$ ODEs). Inspired by John Polking's classic MATLAB tool `pplane`.

---

## English Overview

### Key Features
* **Direction & Vector Fields:** Configurable grid resolution, normalized vectors, arrows, and magnitude-based color gradients.
* **Interactive Orbits:** Point-and-click trajectory computation forward and backward in time using fixed and adaptive integration schemes (RK4 / Dormand-Prince).
* **Nullclines & Equilibria:** Numerical detection of equilibrium points, Jacobian linearization, eigenvalues, eigenvectors, and local stability classification (sinks, sources, saddles, spirals, centers).
* **Integrated Educational Guide:** Built-in Markdown viewer with offline MathJax support for 11 canonical models (Lotka-Volterra, FitzHugh-Nagumo, SIR, Van der Pol, Duffing, Brusselator, etc.).
* **Bilingual Support (PT/EN):** Fully internationalized UI and localized executable bundles.
* **Vector & Data Export:** High-resolution PNG rendering and numerical trajectory export.

### Quick Start (Pre-built Binaries)
Download the latest standalone executable `.jar` from the [Releases](https://github.com/lars29-ufpr/pplane-java/releases) page:
* **Portuguese:** `phase-plane-analyzer-1.0.0-pt.jar`
* **English:** `phase-plane-analyzer-1.0.0-en.jar`

Run directly with Java 21+ installed:
```bash
java -jar phase-plane-analyzer-1.0.0-en.jar
