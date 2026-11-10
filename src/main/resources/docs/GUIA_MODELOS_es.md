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

### 11. Dinámica del Amor (Romeo y Julieta)

#### Formulación Matemática y Parámetros
Un sistema lineal clásico introducido por Steven Strogatz con un toque de drama y humor para el estudio de la estabilidad. El modelo describe la evolución del afecto mutuo entre dos amantes:

$$\frac{dR}{dt} = a R + b J$$
$$\frac{dJ}{dt} = c R + d J$$

- **Variables de Estado:**
  * $R(t)$: Sentimientos de Romeo (valores positivos = amor, negativos = aversión).
  * $J(t)$: Sentimientos de Julieta.
- **Parámetros Típicos (La Caza Eterna):**
  * $a, d$: Parámetros de autoaceptación (ego o precaución).
  * $b, c$: Parámetros de reacción a los sentimientos del socio.
  * Con $a = 0, b = 1, c = -1, d = 0$: Romeo ama a Julieta más cuanto más huye, y Julieta pierde el interés cuanto más se acerca Romeo.
- **Clasificación Dinámica:**
  * Esta configuración crea una matriz con traza nula y determinante positivo. Es un **Centro Puro**, lo que garantiza que el romance sea cíclicamente trágico, sin alcanzar nunca un equilibrio estable o el colapso total.

#### Guía Práctica de Exploración
1. **El Ciclo Sin Fin:** Con la configuración predeterminada (0, 1, -1, 0), haga clic en cualquier lugar del plano y observe cómo las órbitas forman elipses perfectas. La persecución es eterna.
2. **Amor Platónico y Apatía:** Abra el *Parameter Studio* y cambie ambas autoaceptaciones a valores ligeramente negativos (ej., $a = -0.1$ y $d = -0.1$). Observe cómo el sistema se convierte en un foco atractor: tras algunas idas y venidas amorosas, la pareja colapsa inevitablemente hacia el origen (apatía emocional mutua e irreversible).
3. **Cambie la Historia:** Invente nuevas personalidades. ¿Qué sucede si ambos son amantes cautelosos ($a < 0, d < 0$) pero reaccionan positivamente al otro ($b > 0, c > 0$)?

#### Referencias Conceptuales
- Strogatz, S. H. (1988). *Love affairs and differential equations*. Mathematics Magazine, 61(1), 35.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 12. Oscilador Químico Brusselator

#### Formulación Matemática y Parámetros
Un modelo clásico en fisicoquímica desarrollado por la escuela de Bruselas (Prigogine y Lefever) para describir la cinética de una reacción autocatalítica teórica que exhibe oscilaciones sostenidas (ruptura de la simetría temporal y estructuras disipativas):

$$\frac{dx}{dt} = A - (B + 1)x + x^2y$$
$$\frac{dy}{dt} = Bx - x^2y$$

- **Variables de Estado:**
  * $x(t), y(t)$: Concentraciones de los reactivos intermedios de la reacción.
- **Parámetros Típicos:**
  * $A = 1.0$: Concentración constante del reactivo de entrada.
  * $B = 3.0$: Constante de velocidad asociada al paso autocatalítico.
- **Punto de Equilibrio Único y Bifurcación:**
  * El sistema admite un único equilibrio estacionario ubicado en $(x^*, y^*) = (A, B/A)$.
  * La estabilidad de este punto cambia drásticamente dependiendo de los reactivos: es estable si $B < 1 + A^2$ y se vuelve inestable (experimentando una **Bifurcación de Hopf Supercrítica**) cuando $B > 1 + A^2$.

#### Guía Práctica de Exploración
1. **Atracción Oscilatoria:** Con la configuración predeterminada ($A = 1, B = 3$), note que se cumple la condición de inestabilidad ($3 > 1 + 1^2 = 2$). Haga clic en el plano y observe cómo las concentraciones $x$ e $y$ son atraídas implacablemente hacia un ciclo límite en forma de gota.
2. **Ventana Temporal (Soluciones vs Tiempo):** Visualice los gráficos $x(t)$ e $y(t)$. Notará picos oscilatorios bien definidos, que caracterizan la naturaleza de "reloj químico" del sistema de reacción-difusión.
3. **Transición de Fase:** En el *Parameter Studio*, mantenga $A = 1$ y reduzca lentamente $B$ a $1.5$. El ciclo límite desaparecerá y la reacción volverá a tener un flujo de concentración estable (foco atractor hacia $(1, 1.5)$).

#### Referencias Conceptuales
- Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. The Journal of Chemical Physics, 48(4), 1695-1700.
- Nicolis, G., & Prigogine, I. (1977). *Self-Organization in Nonequilibrium Systems: From Dissipative Structures to Order through Fluctuations*. Wiley.
