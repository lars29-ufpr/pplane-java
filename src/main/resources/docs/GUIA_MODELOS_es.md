# Guía Didáctica de Modelos Dinámicos Bidimensionales
pplaneX — Versión 1.2

Esta guía reúne el catálogo canónico de sistemas de ecuaciones diferenciales ordinarias bidimensionales ($2 \times 2$) implementados en **pplaneX**. Cada modelo cuenta con la respectiva formulación matemática, guía didáctica de experimentación en el plano de fase y referencias conceptuales clásicas.

---

# I. Biomatemáticas y Dinámica Poblacional

---

### 1. Modelo Depredador-Presa (Lotka-Volterra)

#### Formulación Matemática y Parámetros
El modelo clásico de Lotka y Volterra describe la interacción de depredación entre dos especies en un ambiente homogéneo, ignorando la capacidad de carga ambiental para las presas:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Variables de Estado:**
  * $x(t) \ge 0$: Densidad de la población de presas.
  * $y(t) \ge 0$: Densidad de la población de depredadores.
- **Parámetros Típicos:**
  * $\alpha = 1.0$: Tasa intrínseca de crecimiento de la presa en ausencia de depredadores.
  * $\beta = 0.5$: Tasa de depredación (frecuencia de captura y consumo).
  * $\delta = 0.5$: Eficiencia de conversión biológica de presas consumidas en nuevos depredadores.
  * $\gamma = 1.0$: Tasa de mortalidad per cápita de los depredadores.
- **Puntos de Equilibrio e Invariante:**
  * $(0, 0)$: Punto de silla inestable.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Centro conservativo linealmente neutro.
  * Posee una primera integral conservada: $V(x, y) = \delta x - \gamma \ln(x) + \beta y - \alpha \ln(y) = \text{constante}$.

#### Guía Práctica de Exploración
1. **Nulclinas:** Note las nulclinas ortogonales: $\frac{dx}{dt} = 0 \implies x = 0$ o $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ o $x = 2.0$.
2. **Órbitas Cerradas:** Haga clic en condiciones iniciales a diferentes distancias de $(2.0, 2.0)$ (ej: $(2.5, 2.0)$, $(3.5, 2.0)$, $(5.0, 2.0)$). Observe que todas las soluciones forman ciclos cerrados no periódicos por atractor, pero gobernados por la energía conservada $V(x, y)$.
3. **Serie Temporal:** Abra la ventana de *Soluciones vs Tiempo* y verifique el clásico desfase temporal de un cuarto de ciclo entre el pico de presas y el pico subsiguiente de depredadores.

#### Referencias Conceptuales
- Lotka, A. J. (1925). *Elements of Physical Biology*. Williams & Wilkins, Baltimore.
- Volterra, V. (1926). *Variazioni e fluttuazioni del numero d'individui in specie animali conviventi*. Mem. Accad. Lincei, 2, 31-113.
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction* (3rd ed.). Springer.

---

### 2. Competencia de Dos Especies (Murray)

#### Formulación Matemática y Parámetros
Describe la dinámica de dos especies biológicas que disputan los mismos nichos o recursos alimenticios, incorporando crecimiento logístico para ambas:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Variables de Estado:**
  * $x(t) \ge 0$: Densidad de la especie competidora 1.
  * $y(t) \ge 0$: Densidad de la especie competidora 2.
- **Parámetros Típicos:**
  * $r_1 = 1.0, r_2 = 1.0$: Tasas intrínsecas de crecimiento per cápita.
  * $K_1 = 3.0, K_2 = 3.0$: Capacidades de carga individuales del hábitat.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coeficientes de inhibición interespecífica recíproca.
- **Fenomenología:**
  * Cuando $\alpha_{12} < 1$ y $\alpha_{21} < 1$, el sistema admite **coexistencia estable** en un nodo atractor en el interior del primer cuadrante.

#### Guía Práctica de Exploración
1. **Intersección de Nulclinas:** Las nulclinas lineales inclinadas dividen el cuadrante en sectores direccionales convergentes.
2. **Cuencas de Atracción:** Haga clic en puntos cercanos a los ejes coordenados y observe la convergencia inexorable hacia el equilibrio de coexistencia estable $(x^*, y^*)$.
3. **Exclusión Competitiva:** Altere los parámetros vía *Parameter Studio* a $\alpha_{12} = 1.5$ y $\alpha_{21} = 1.3$ y haga clic en **Separatrices** para visualizar la cuenca dividida por una silla inestable.

#### Referencias Conceptuales
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction*. Springer-Verlag.
- Begon, M., Townsend, C. R., & Harper, J. L. (2006). *Ecology: From Individuals to Ecosystems*. Blackwell Publishing.

---

### 3. Neurona de FitzHugh-Nagumo

#### Formulación Matemática y Parámetros
Reducción planar canónica del modelo biofísico de cuatro variables de Hodgkin-Huxley para la propagación de potenciales de acción en la membrana axonal:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Variables de Estado:**
  * $v(t)$: Potencial de membrana neuronal (variable rápida de activación).
  * $w(t)$: Variable de recuperación de la conductancia iónica de potasio (variable lenta).
- **Parámetros Típicos:**
  * $a = 0.7, b = 0.8$: Parámetros de cinética de recuperación transmembrana.
  * $\epsilon = 0.08$: Separación de escalas temporales lenta/rápida ($\epsilon \ll 1$).
  * $I_{\text{ext}} = 0.5$: Corriente de estímulo eléctrico externo inyectada.
- **Dinámica Excitable:** La nulclina cúbica de $v$ combinada con la relajación lineal de $w$ produce oscilaciones por disparo (*spiking*) o excitabilidad umbral.

#### Guía Práctica de Exploración
1. **Equilibrio de Reposo ($I_{\text{ext}} = 0$):** Inspeccione el equilibrio único en la rama izquierda de la cúbica; observe que perturbaciones pequeñas se amortiguan, pero las perturbaciones que superan la "rodilla" de la cúbica generan un disparo amplio en el plano antes de regresar.
2. **Ciclo-Límite Auto-Sostenido ($I_{\text{ext}} = 0.5$):** Vea la pérdida de estabilidad del punto de reposo por una **Bifurcación de Hopf**. Todas las órbitas son atraídas hacia un ciclo-límite de relajación.
3. **Trayectorias Rápidas y Lentas:** Observe el paralelismo casi horizontal de las trayectorias fuera de la cúbica (fase rápida) versus la adherencia lenta a lo largo de la cúbica (fase refractaria).

#### Referencias Conceptuales
- FitzHugh, R. (1961). *Impulses and physiological states in theoretical models of nerve membrane*. Biophysical Journal, 1(6), 445-466.
- Nagumo, J., Arimoto, S., & Yoshizawa, S. (1962). *An active pulse transmission line simulating nerve axon*. Proc. IRE, 50(10), 2061-2070.
- Izhikevich, E. M. (2007). *Dynamical Systems in Neuroscience*. MIT Press.

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulación Matemática y Parámetros
El modelo fundamental de la epidemiología teórica describe la transmisión de patógenos con inmunidad permanente en una población homogénea y constante ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Variables de Estado:**
  * $S(t) \in [0, 1]$: Fracción de la población susceptible a la infección.
  * $I(t) \in [0, 1]$: Fracción de la población infectada y transmisora activa.
- **Parámetros Típicos:**
  * $\beta = 0.5$: Tasa efectiva de transmisión por contacto.
  * $\gamma = 0.2$: Tasa per cápita de recuperación ($\gamma = 1/\tau_{\text{inf}}$).
- **Umbral Crítico y Reproducción Básica:**
  $$R_0 = \frac{\beta}{\gamma} = \frac{0.5}{0.2} = 2.5, \quad S_c = \frac{1}{R_0} = \frac{\gamma}{\beta} = 0.4$$

#### Guía Práctica de Exploración
1. **Nulclina Crítica Vertical:** Observe la nulclina verde vertical posicionada precisamente en $S = 0.4$.
2. **Pico Infeccioso:** Haga clic en una condición epidémica inicial con $S_0 > 0.4$ (ej: $S_0 = 0.95, I_0 = 0.02$). La curva asciende inclinada hacia la izquierda hasta tangenciar horizontalmente la recta $S = 0.4$, alcanzando el ápice exacto del contagio.
3. **Fin de la Epidemia sin Agotamiento Total:** Verifique que la curva desciende asintóticamente hasta el eje horizontal $I = 0$ en un valor $S_\infty > 0$. La epidemia cesa por la pérdida de densidad crítica de contactos y no por el contagio total de los individuos.

#### Referencias Conceptuales
- Kermack, W. O., & McKendrick, A. G. (1927). *A contribution to the mathematical theory of epidemics*. Proc. R. Soc. Lond. A, 115(772), 700-721.
- Brauer, F., & Castillo-Chavez, C. (2012). *Mathematical Models in Population Biology and Epidemiology*. Springer.

---

# II. Mecánica No Lineal y Oscilaciones Físicas

---

### 5. Oscilador de Van der Pol

#### Formulación Matemática y Parámetros
Desarrollado originalmente para circuitos eléctricos con triodos de vacío, el modelo exhibe amortiguamiento no lineal que actúa como un disipador de energía a altas amplitudes y como amplificador a bajas amplitudes:

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

- **Variables de Estado:**
  * $x(t)$: Posición o tensión eléctrica normalizada.
  * $y(t)$: Velocidad o derivada temporal $\dot{x}$.
- **Parámetros Típicos:**
  * $\mu = 1.0$: Coeficiente de amortiguamiento no lineal dependiente de la amplitud.
- **Fenomenología Geométrica:**
  * El punto de equilibrio en el origen $(0, 0)$ es un foco inestable.
  * Existe un **ciclo-límite atractor estable** aislado en el plano que absorbe todas las soluciones no nulas.

#### Guía Práctica de Exploración
1. **Atracción Global:** Haga clic dentro del ciclo (ej: $(0.1, 0.1)$) y fuera del ciclo (ej: $(3.0, 3.0)$). Verifique que ambas espirales convergen asintóticamente hacia la misma curva cerrada invariante.
2. **Oscilaciones de Relajación:** Aumente $\mu$ a $3.0$ o $5.0$ en el *Parameter Studio*. El ciclo pierde su simetría casi elíptica y desarrolla saltos ultrarrápidos alternados con deslizamientos lentos a lo largo de las ramas de la curva $y = \frac{x - x^3/3}{\mu}$.

#### Referencias Conceptuales
- Van der Pol, B. (1926). *On relaxation-oscillations*. The London, Edinburgh, and Dublin Philosophical Magazine and Journal of Science, 2(11), 978-992.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 6. Péndulo No Lineal con Fricción

#### Formulación Matemática y Parámetros
Modela las oscilaciones mecánicas de una varilla rígida con masa concentrada en el extremo sin la aproximación lineal para pequeños ángulos ($\sin \theta \not\approx \theta$):

$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

- **Variables de Estado:**
  * $\theta(t)$: Posición angular de la varilla en radianes.
  * $\omega(t)$: Velocidad angular instantánea ($d\theta/dt$).
- **Parámetros Típicos:**
  * $\frac{g}{L} = 1.0$: Frecuencia natural característica normalizada.
  * $\gamma = 0.2$: Coeficiente de fricción viscosa del aire o pivote.
- **Estructura Topológica:**
  * Familia periódica de equilibrios estables en $(2k\pi, 0)$ (focos atractores estables — péndulo en reposo vertical hacia abajo).
  * Familia de puntos de silla en $((2k+1)\pi, 0)$ (varilla invertida hacia arriba inestable).

#### Guía Práctica de Exploración
1. **Separatrices Homoclínicas y Heteroclínicas:** Ajuste $\gamma = 0$ temporalmente y haga clic en **Separatrices** para ver la curva que divide el régimen de oscilación de las revoluciones completas continuas.
2. **Cuencas de Oscilación Amortiguada:** Con $\gamma = 0.2$, haga clic en velocidades angulares elevadas ($\omega_0 = 2.5$) y observe el péndulo dar varias rotaciones antes de ser capturado por el atractor local más cercano.

#### Referencias Conceptuales
- Marion, J. B., & Thornton, S. T. (2004). *Classical Dynamics of Particles and Systems*. Brooks/Cole.
- Hirsch, M. W., Smale, S., & Devaney, R. L. (2013). *Differential Equations, Dynamical Systems, and an Introduction to Chaos*. Academic Press.

---

### 7. Oscilador de Duffing (Autónomo)

#### Formulación Matemática y Parámetros
Representa una viga elástica deformable sujeta a fuerzas mecánicas con un potencial de doble pozo simétrico:

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$

- **Variables de Estado:**
  * $x(t)$: Posición de deflexión lateral de la viga.
  * $y(t)$: Velocidad mecánica instantánea.
- **Parámetros Típicos:**
  * $\delta = 0.2$: Amortiguamiento viscoso estructural.
  * $\beta = -1.0$: Término lineal de rigidez negativa (causa inestabilidad en el reposo central).
  * $\alpha = 1.0$: Término cúbico restaurador no lineal.
- **Topología de Doble Pozo:**
  * El origen $(0, 0)$ es un punto de silla.
  * Los dos fondos de los pozos laterales en $(\pm 1, 0)$ son focos estables.

#### Guía Práctica de Exploración
1. **Frontera Frágil de las Cuencas:** Haga clic en **Separatrices** sobre la silla en $(0, 0)$. Las variedades estables se enroscan en el plano dibujando una frontera delicada entre los dos pozos.
2. **Sensibilidad a las Condiciones Iniciales:** Lance dos puntos iniciales casi idénticos alrededor del origen y compruebe que trayectorias vecinas pueden converger hacia pozos de atracción opuestos.

#### Referencias Conceptuales
- Duffing, G. (1918). *Erzwungene Schwingungen bei veränderlicher Eigenfrequenz*. Vieweg, Braunschweig.
- Guckenheimer, J., & Holmes, P. (1983). *Nonlinear Oscillations, Dynamical Systems, and Bifurcations of Vector Fields*. Springer-Verlag.

---

# III. Fundamentos Matemáticos y Teoría Cualitativa

---

### 8. Pplane Clásico (Polking)

#### Formulación Matemática y Parámetros
El modelo emblemático que acompañó históricamente a la utilidad MATLAB `pplane` de John Polking, diseñado intencionalmente para ilustrar múltiples tipos de equilibrios y separatrices que compiten en una sola pantalla:

$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + 3xy$$

- **Variables de Estado:**
  * $x, y \in \mathbb{R}$: Coordenadas cartesianas en el plano de fase.
- **Fenomenología Geométrica:**
  * Presenta múltiples equilibrios aislados en una ventana planar compacta (nodos, sillas y focos simultáneos).
  * Conexiones ricas de separatrices que demuestran la subdivisión completa del plano en regiones dinámicamente disjuntas.

#### Guía Práctica de Exploración
1. **Búsqueda de Puntos Críticos:** Presione **Puntos de Equilibrio** y haga clic en cada punto resaltado para verificar los autovalores y la clasificación jacobiana local.
2. **Mapeo de Separatrices:** Trace las variedades estables e inestables de las sillas presentes en el sistema para construir la estructura global del campo vectorial.

#### Referencias Conceptuales
- Polking, J. C. (1995). *Ordinary Differential Equations using MATLAB*. Prentice Hall.
- Boyce, W. E., DiPrima, R. C., & Meade, D. B. (2021). *Elementary Differential Equations and Boundary Value Problems*. Wiley.

---

### 9. Sistemas Lineales 2D (Clasificación y Amor de Strogatz)

#### Formulación Matemática y Parámetros
El marco canónico de sistemas lineales invariantes en el tiempo en el plano, utilizado metafóricamente por Steven Strogatz para describir la dinámica afectiva entre Romeo ($R$) y Julieta ($J$):

$$\begin{pmatrix} \dot{R} \\ \dot{J} \end{pmatrix} = \begin{pmatrix} a & b \\ c & d \end{pmatrix} \begin{pmatrix} R \\ J \end{pmatrix}$$

$$\frac{dR}{dt} = a R + b J$$
$$\frac{dJ}{dt} = c R + d J$$

- **Variables de Estado:**
  * $R(t)$: Nivel de afecto de Romeo hacia Julieta ($R > 0$ pasión apasionada; $R < 0$ aversión/desprecio).
  * $J(t)$: Nivel de afecto de Julieta hacia Romeo ($J > 0$ correspondencia; $J < 0$ apatía/fuga).
- **Parámetros Típicos de Entrada:**
  * $a = -0.1$: Tendencia a la precaución amorosa de Romeo (amor propio decadente).
  * $b = 1.0$: Julieta calienta el corazón de Romeo con facilidad.
  * $c = -1.0$: Julieta retrocede y huye ante la insistencia amorosa de Romeo.
  * $d = -0.1$: Tendencia natural de Julieta a la indiferencia con el tiempo.
- **Clasificación Espectral según Traza ($\tau$) y Determinante ($\Delta$):**
  * Ecuación característica: $\lambda^2 - \tau \lambda + \Delta = 0$, con $\tau = a + d$ y $\Delta = ad - bc$.
  * Discriminante: $D = \tau^2 - 4\Delta$.
  * $\Delta < 0$: **Punto de Silla** (amantes inconstantes e inestabilidad hiperbólica divergente).
  * $\Delta > 0, D > 0$: **Nodo** (asintóticamente estable si $\tau < 0$, inestable si $\tau > 0$).
  * $\Delta > 0, D < 0$: **Foco / Espiral** (espiral estable hacia la trágica apatia emocional si $\tau < 0$, oscilaciones explosivas si $\tau > 0$).
  * $\tau = 0, \Delta > 0$: **Centro Conservativo** (ciclo infinito de desencuentros periódicos: cuando Romeo ama, Julieta huye; cuando Romeo se rinde, Julieta se enamora).

#### Guía Práctica de Exploración
1. **Espiral Amortiguada (Apatía):** Con los parámetros por defecto, haga clic en $(2.0, 1.0)$. Observe las trayectorias en espiral hacia el origen $(0, 0)$.
2. **Ciclo Perpetuo (Centro):** Ajuste $a = 0.0$ y $d = 0.0$ en el *Parameter Studio*. La traza se anula ($\tau = 0$), generando órbitas elípticas cerradas.
3. **Punto de Silla:** Altere $c = 1.0$. Verifique que $\Delta < 0$, generando un punto de silla con separatrices bien delineadas.

#### Referencias Conceptuales
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos: With Applications to Physics, Biology, Chemistry, and Engineering* (2nd ed.). CRC Press.
- Hirsch, M. W., Smale, S., & Devaney, R. L. (2013). *Differential Equations, Dynamical Systems, and an Introduction to Chaos* (3rd ed.). Academic Press.

---

### 10. Polking: Múltiples Equilibrios

#### Formulación Matemática y Parámetros
El clásico sistema polinomial inmortalizado por John Polking en el PPLANE original de la Universidad de Rice, ideal para observar separatrices compitiendo por distintas cuencas de atracción:

$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$

- **Variables de Estado:**
  * $x(t)$: Primera coordenada de fase.
  * $y(t)$: Segunda coordenada de fase.
- **Puntos de Equilibrio:**
  * $(0, 0)$: Punto crítico degenerado en el origen.
  * $(2, 2)$ y $(-2, 2)$: Puntos de silla simétricos con separatrices bien delineadas.
  * $(0, 2)$: Equilibrio con convergencia asintótica vertical.
- **Fenomenología:**
  * Presenta la coexistencia de 4 equilibrios en el plano, generando un mapa rico de separatrices donde trayectorias vecinas toman destinos diametralmente opuestos.

#### Guía Práctica de Exploración
1. **Ubicación de los 4 Equilibrios:** Presione **Puntos de Equilibrio** y haga clic en cada punto resaltado para verificar las propiedades de la Jacobiana local.
2. **Separatrices Simétricas:** Trace las variedades estables e inestables de las sillas en $(-2, 2)$ y $(2, 2)$ para demarcar los límites de las cuencas de atracción.

#### Referencias Conceptuales
- Polking, J. C. (1995). *Ordinary Differential Equations using MATLAB*. Prentice Hall.
- Perko, L. (2001). *Differential Equations and Dynamical Systems* (3rd ed.). Springer-Verlag.

---

### 11. Oscilador Químico Brusselator

#### Formulación Matemática y Parámetros
Modelo canónico formulado por la Escuela de Bruselas (Prigogine y Lefever) para describir reacciones químicas autocatalíticas trimoleculares lejos del equilibrio termodinámico:

$$\frac{dx}{dt} = a - (b + 1) x + x^2 y$$
$$\frac{dy}{dt} = b x - x^2 y$$

- **Variables de Estado:**
  * $x(t) \ge 0$: Concentración adimensional del intermediario químico $X$.
  * $y(t) \ge 0$: Concentración adimensional del intermediario químico $Y$.
- **Parámetros Típicos:**
  * $a = 1.0$: Concentración constante suminstrada del reactivo inicial $A$.
  * $b = 3.0$: Concentración del reactivo $B$ (parámetro de control de la bifurcación).
- **Punto de Equilibrio y Linealización:**
  * Único punto fijo en el primer cuadrante: $(x^*, y^*) = \left(a, \frac{b}{a}\right) = (1.0, 3.0)$.
  * Matriz Jacobiana:
    $$J(x^*, y^*) = \begin{pmatrix} b - 1 & a^2 \\ -b & -a^2 \end{pmatrix}$$
  * Determinante: $\Delta = a^2 > 0$ (nunca es silla para $a \neq 0$).
  * Traza: $\tau = b - (1 + a^2)$.
- **Bifurcación de Hopf Supercrítica:**
  * En $b_c = 1 + a^2 = 2.0$, la traza se anula y ocurre una bifurcación de Hopf.
  * Para $b > 1 + a^2$ (como en el valor por defecto $b = 3.0$), el equilibrio se vuelve inestable y emerge un **ciclo-límite atractor periódico estable** (estructuras disipativas en el tiempo que le valieron a Ilya Prigogine el Premio Nobel de Química de 1977).

#### Guía Práctica de Exploración
1. **Ciclo-Límite Químico:** Con $a = 1.0$ y $b = 3.0$, haga clic cerca del equilibrio en $(1.0, 3.0)$ y en un punto externo como $(4.0, 4.0)$. Observe ambas trayectorias convergiendo hacia el mismo ciclo-límite atractor estable.
2. **Estabilización por Bifurcación Subcrítica:** Reduzca $b$ a $1.5$ en el *Parameter Studio* ($b < 1 + a^2$). Repita las condiciones iniciales y compruebe que el ciclo desaparece, decayendo las soluciones directamente hacia el foco atractor $(1.0, 1.5)$.

#### Referencias Conceptuales
- Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. The Journal of Chemical Physics, 48(4), 1695-1700.
- Nicolis, G., & Prigogine, I. (1977). *Self-Organization in Nonequilibrium Systems: From Dissipative Structures to Order through Fluctuations*. John Wiley & Sons.
