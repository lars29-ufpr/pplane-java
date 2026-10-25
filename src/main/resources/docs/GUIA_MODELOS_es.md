# Guía Educativa de Modelos Dinámicos (Phase Plane)
## pplaneX — Versión 1.2

Esta guía reúne el catálogo canónico de sistemas de ecuaciones diferenciales ordinarias bidimensionales ($2 \times 2$) implementados en **pplaneX**. Cada modelo cuenta con la respectiva formulación matemática, guía didáctica de experimentación en el plano de fase y referencias conceptuales clásicas.

---

# I. Biomatemática y Dinámica Poblacional

---

### 1. Modelo Depredador-Presa (Lotka-Volterra)

#### Formulación Matemática y Parámetros
El modelo clásico de Lotka y Volterra describe la interacción de depredación entre dos especies en un entorno homogéneo:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Variables de Estado:**
  * $x(t) \ge 0$: Densidad de la población de presas.
  * $y(t) \ge 0$: Densidad de la población de depredadores.
- **Parámetros Típicos:**
  * $\alpha = 1.0$: Tasa intrínseca de crecimiento.
  * $\beta = 0.5$: Tasa de depredación.
  * $\delta = 0.5$: Eficiencia de conversión biológica.
  * $\gamma = 1.0$: Tasa de mortalidad per cápita.

#### Guía Práctica de Exploración
1. **Nulclinas:** Note las nulclinas ortogonales: $\frac{dx}{dt} = 0 \implies x = 0$ o $y = 2.0$.
2. **Órbitas Cerradas:** Haga clic en el plano para observar los ciclos cerrados conservativos.

---

### 2. Competición entre Dos Especies (Murray)

#### Formulación Matemática y Parámetros
$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Parámetros Típicos:**
  * $r_1 = 1.0, r_2 = 1.0$: Tasas intrínsecas de crecimiento.
  * $K_1 = 3.0, K_2 = 3.0$: Capacidades de carga.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coeficientes de inhibición interespecífica.

---

### 3. Modelo Neuronal de FitzHugh-Nagumo

#### Formulación Matemática y Parámetros
$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulación Matemática y Parámetros
$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

---

# II. Mecánica No Lineal y Oscilaciones Físicas

---

### 5. Oscilador de Van der Pol
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

### 6. Péndulo Simple No Lineal Amortiguado
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

### 7. Oscilador de Duffing Autónomo
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$

---

# III. Fundamentos Matemáticos y Teoría Cualitativa

---

### 8. Pplane Clásico (Polking)
$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

### 9. Sistema Lineal de Referencia
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

### 10. Dinámica del Amor (Romeo y Julieta de Strogatz)
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
