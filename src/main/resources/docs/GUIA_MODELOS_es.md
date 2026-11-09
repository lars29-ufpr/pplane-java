# Guía Educativa de Modelos Dinámicos (Phase Plane)
### pplaneX — Versión 1.3

Esta guía reúne el catálogo canónico de sistemas de ecuaciones diferenciales ordinarias bidimensionales ($2 \times 2$) implementados en **pplaneX**. Cada modelo cuenta con la respectiva formulación matemática, guía didáctica de experimentación en el plano de fase y referencias conceptuales clásicas.

### Creando Sus Propios Modelos (Sintaxis Matemática)

Al utilizar la pestaña **Mis Modelos** en la Galería, puede definir sus propios sistemas de Ecuaciones Diferenciales Ordinarias (EDO). pplaneX utiliza un analizador matemático estricto, por lo que es fundamental seguir las siguientes reglas de sintaxis:

* **Multiplicación Explícita (¡Muy Importante!):** A diferencia de la escritura matemática en papel, la computadora requiere el asterisco `*` para cualquier multiplicación.
  * [x] Incorrecto: `2x` o `x(1-y)`
  * [v] Correcto: `2*x` o `x*(1-y)`
* **Operadores Básicos:** 
  * `+` (suma), `-` (resta)
  * `*` (multiplicación), `/` (división)
  * `^` (potencia, ej: `x^2`)
* **Funciones Soportadas:**
  * **Trigonométricas:** `sin()`, `cos()`, `tan()`
  * **Exponenciales y Logarítmicas:** `exp()` (base *e*), `log()` o `ln()` (logaritmo natural), `log10()` (base 10)
  * **Otras:** `sqrt()` (raíz cuadrada), `abs()` (valor absoluto)

**Ejemplo práctico de escritura:**
Para introducir el sistema $x' = -y + x(1 - x^2 - y^2)$, debe escribir en el campo dx/dt:
`-y + x*(1 - x^2 - y^2)`

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
- **Puntos de Equilibrio e Invariante:**
  * $(0, 0)$: Punto de silla inestable.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Centro conservativo linealmente neutro.

#### Guía Práctica de Exploración
1. **Nulclinas:** Note las nulclinas ortogonales: $\frac{dx}{dt} = 0 \implies x = 0$ o $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ o $x = 2.0$.
2. **Órbitas Cerradas:** Haga clic en el plano para observar los ciclos cerrados conservativos.

---

### 2. Competición entre Dos Especies (Murray)

#### Formulación Matemática y Parámetros
Describe la dinámica de dos especies biológicas que compiten por los mismos nichos o recursos alimenticios, incorporando el crecimiento logístico para ambas:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Parámetros Típicos:**
  * $r_1 = 1.0, r_2 = 1.0$: Tasas intrínsecas de crecimiento per cápita.
  * $K_1 = 3.0, K_2 = 3.0$: Capacidades de carga individuales del hábitat.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coeficientes de inhibición interespecífica recíproca.

---

### 3. Modelo Neuronal de FitzHugh-Nagumo

#### Formulación Matemática y Parámetros
Reducción planar canónica del modelo biofísico de Hodgkin-Huxley para los potenciales de acción:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Parámetros Típicos:**
  * $a = 0.7, b = 0.8$: Parámetros cinéticos de recuperación.
  * $\epsilon = 0.08$: Separación de escala de tiempo (lenta/rápida).
  * $I_{\text{ext}} = 0.5$: Corriente de estímulo externo.

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulación Matemática y Parámetros
Modelo epidemiológico de transmisión de patógenos con inmunidad permanente:

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Parámetros Típicos:**
  * $\beta = 0.5$: Tasa de transmisión.
  * $\gamma = 0.2$: Tasa de recuperación.

---

# II. Mecánica No Lineal y Oscilaciones Físicas

---

### 5. Oscilador de Van der Pol

#### Formulación Matemática y Parámetros
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$
- **Fenomenología:** Presenta un ciclo límite de relajación estable.

---

### 6. Péndulo Simple No Lineal Amortiguado

#### Formulación Matemática y Parámetros
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$
- **Fenomenología:** Exhibe focos estables en reposo y puntos de silla inestables en las posiciones invertidas.

---

# III. Finanzas, Clima y Dinámica No Lineal

---

### 7. Ciclo Económico No Lineal de Kaldor

#### Formulación Matemática y Parámetros
Modelo macroeconómico fundamental que endogeniza los ciclos económicos mediante una función de inversión no lineal (sigmoidea) acoplada al stock de capital:

$$\frac{dY}{dt} = \alpha (\tanh(Y) - s Y - \gamma K)$$
$$\frac{dK}{dt} = s Y - \delta K$$

- **Variables de Estado:**
  * $Y(t)$: Ingreso o producción nacional (desviaciones del equilibrio).
  * $K(t)$: Stock de capital acumulado.
- **Parámetros Típicos:**
  * $\alpha = 1.2$: Velocidad de ajuste del mercado de bienes.
  * $\gamma = 0.8$: Sensibilidad de la inversión al stock de capital.
  * $s = 0.3$: Propensión marginal a ahorrar.
  * $\delta = 0.1$: Tasa de depreciación del capital.
- **Fenomenología:** Presenta un ciclo límite atractor que representa las fluctuaciones económicas endógenas persistentes.

---

### 8. Circulación Termohalina de Stommel (Modelo de Dos Cajas)

#### Formulación Matemática y Parámetros
Un modelo fundamental en oceanografía física que describe el intercambio de agua entre las regiones ecuatorial y polar, demostrando la posibilidad de múltiples equilibrios climáticos globales:

$$\frac{dx}{dt} = \eta_1 - x - x|x - y|$$
$$\frac{dy}{dt} = \eta_2 - y - y|x - y|$$

- **Variables de Estado:**
  * $x(t)$: Diferencia de temperatura adimensional entre las cajas ecuatorial y polar.
  * $y(t)$: Diferencia de salinidad adimensional entre las cajas.
- **Parámetros Típicos:**
  * $\eta_1 = 3.0$: Forzamiento térmico superficial.
  * $\eta_2 = 1.2$: Forzamiento halino superficial.
- **Fenomenología:** Presenta biestabilidad, admitiendo dos regímenes climáticos estables (nodos) separados por una silla inestable.

---

# IV. Fundamentos Matemáticos y Teoría Cualitativa

---

### 9. Sistema Lineal de Referencia

#### Formulación Matemática y Parámetros
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Clasificación Espectral:** Basada en la Traza y el Determinante del Jacobiano.

---

### 10. Dinámica No Lineal de Polking (Múltiples Equilibrios)

#### Formulación Matemática y Parámetros
$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$
- **Fenomenología:** Presenta la coexistencia de 4 equilibrios en el plano, generando un rico mapa de separatrices en competencia.
