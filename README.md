# pplaneX — Qualitative and Numerical Analysis of 2D Autonomous Systems

> **Language / Idioma:** **English** | [Português](README.pt-BR.md)

---

**pplaneX** is a modern, high-performance interactive desktop application built with Java 21 and JavaFX for the qualitative and numerical analysis of two-dimensional autonomous systems of Ordinary Differential Equations (ODEs) and planar phase portraits:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspired by the classic educational tool `pplane` originally created by **John C. Polking** (Rice University) for MATLAB, **pplaneX** was re-engineered from the ground up to offer fluid user interaction, real-time responsive rendering, continuous pan/zoom exploration, and universal cross-platform support (Linux, Windows, and macOS).

---

## 📥 Installation & Quick Start

**pplaneX** is distributed as a self-contained executable JAR and requires **Java 21 (LTS) or newer**.

If you do not have Java installed or need step-by-step guidance for your operating system (Windows, macOS, or Linux), see our installation guides:

- 📖 **[Installation Guide (English)](INSTALL.md)**
- 📖 **[Guia de Instalação (Português)](INSTALL_pt.md)**

### Running the Application

Once Java 21+ is installed, run from your terminal:

```bash
java -jar pplaneX.jar
```

---

## 🚀 Key Features

* **Interactive Phase Portrait Rendering:**
  * Real-time vector field and direction field generation with customizable grid density.
  * Interactive trajectory tracing: click anywhere in the phase plane to compute forward and backward orbits.
  * Continuous viewport navigation (smooth pan and multi-level zoom).
* **High-Precision Numerical Solvers:**
  * Adaptive step-size Runge-Kutta methods (Dormand-Prince / RK45, standard RK4, and Euler).
  * Robust real-domain arithmetic evaluation across complex boundaries.
* **Qualitative and Equilibrium Analysis:**
  * Numerical identification of equilibrium points ($f(x^*, y^*) = 0$ and $g(x^*, y^*) = 0$).
  * Automatic Jacobian matrix computation and linear stability classification (saddles, stable/unstable nodes, foci/spirals, and centers).
  * Nullcline computation and visualization ($x$-nullclines and $y$-nullclines).
* **Curated Gallery of Classical Dynamical Systems:**
  * Population dynamics and ecology (Lotka-Volterra predator-prey, competing species).
  * Nonlinear oscillations and physics (Van der Pol oscillator, Duffing oscillator, simple and damped pendulums).
  * Epidemic models, chemical oscillators, and limit cycle benchmarks.
* **Vector and Raster Graphics Export:**
  * High-resolution **PNG** export with transparent background support.
  * Scalable **SVG** vector export for LaTeX documents, publications, and presentations.
* **Universal Cross-Platform Fat JAR:**
  * Single self-contained binary packaging native acceleration modules for **Linux (x86_64)**, **Windows (x86_64)**, and **macOS (Apple Silicon M-Series and Intel x86_64)**.

---

## 📋 Prerequisites

To run the application, you only need **Java 21 or higher** (JRE or JDK) installed.

Check your current Java version:
```bash
java -version
