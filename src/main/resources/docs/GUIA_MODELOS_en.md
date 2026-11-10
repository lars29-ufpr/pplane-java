# Educational Guide to Dynamical Models (Phase Plane)
### pplaneX — Version 1.3

This guide gathers the canonical catalog of two-dimensional ($2 \times 2$) ordinary differential equation systems implemented in **pplaneX**. Each model includes its mathematical formulation, a practical exploration guide in the phase plane, and classic conceptual references.

### Creating Your Own Models (Mathematical Syntax)

When using the **My Models** tab in the Gallery, you can define your own systems of Ordinary Differential Equations (ODEs). pplaneX uses a strict mathematical parser, so it is essential to follow the syntax rules below:

* **Explicit Multiplication (Very Important!):** Unlike mathematical writing on paper, the computer requires the asterisk `*` for any multiplication.
  * [x] Incorrect: `2x` or `x(1-y)`
  * [v] Correct: `2*x` or `x*(1-y)`
* **Basic Operators:** 
  * `+` (addition), `-` (subtraction)
  * `*` (multiplication), `/` (division)
  * `^` (power, e.g., `x^2`)
* **Supported Functions:**
  * **Trigonometric:** `sin()`, `cos()`, `tan()`
  * **Exponential and Logarithmic:** `exp()` (base *e*), `log()` or `ln()` (natural logarithm), `log10()` (base 10)
  * **Other:** `sqrt()` (square root), `abs()` (absolute value)

**Practical typing example:**
To enter the system $x' = -y + x(1 - x^2 - y^2)$, you should type in the dx/dt field:
`-y + x*(1 - x^2 - y^2)`

---

# I. Biomathematics & Population Dynamics

---

### 1. Predator-Prey Model (Lotka-Volterra)

#### Mathematical Formulation and Parameters
The classic Lotka-Volterra model describes the predation interaction between two species in a homogeneous environment:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **State Variables:**
  * $x(t) \ge 0$: Prey population density.
  * $y(t) \ge 0$: Predator population density.
- **Typical Parameters:**
  * $\alpha = 1.0$: Intrinsic prey growth rate in the absence of predators.
  * $\beta = 0.5$: Predation rate (capture and consumption frequency).
  * $\delta = 0.5$: Biological conversion efficiency of consumed prey into new predators.
  * $\gamma = 1.0$: Per capita mortality rate of predators.
- **Equilibrium Points and Invariant:**
  * $(0, 0)$: Unstable saddle point.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Linearly neutral conservative center.

#### Practical Exploration Guide
1. **Nullclines:** Note the orthogonal nullclines: $\frac{dx}{dt} = 0 \implies x = 0$ or $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ or $x = 2.0$.
2. **Closed Orbits:** Click on initial conditions at different distances from $(2.0, 2.0)$. All solutions form non-periodic closed cycles governed by the conserved energy $V(x, y)$.

---

### 2. Two-Species Competition (Murray)

#### Mathematical Formulation and Parameters
Describes the dynamics of two biological species competing for the same niches or food resources:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Typical Parameters:**
  * $r_1 = 1.0, r_2 = 1.0$: Intrinsic per capita growth rates.
  * $K_1 = 3.0, K_2 = 3.0$: Individual carrying capacities.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Interspecific inhibition coefficients.

#### Practical Exploration Guide
1. **Basins of Attraction:** Click near the coordinate axes and observe convergence toward stable coexistence.
2. **Competitive Exclusion:** Change parameters to $\alpha_{12} = 1.5$ and $\alpha_{21} = 1.3$, then plot separatrices to visualize the divided basin.

---

### 3. FitzHugh-Nagumo Neuronal Model

#### Mathematical Formulation and Parameters
Planar reduction of the Hodgkin-Huxley biophysical model for action potentials:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Typical Parameters:**
  * $a = 0.7, b = 0.8$: Recovery kinetics parameters.
  * $\epsilon = 0.08$: Slow/fast time scale separation.
  * $I_{\text{ext}} = 0.5$: Injected external stimulus current.

---

### 4. SIR Epidemic (Kermack-McKendrick)

#### Mathematical Formulation and Parameters
Epidemiological model of pathogen transmission with permanent immunity ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Typical Parameters:**
  * $\beta = 0.5$: Transmission rate.
  * $\gamma = 0.2$: Recovery rate.

---

# II. Non-Linear Mechanics & Physical Oscillations

---

### 5. Van der Pol Oscillator

#### Mathematical Formulation and Parameters
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

- **Typical Parameters:**
  * $\mu = 1.0$: Non-linear damping coefficient.
- **Phenomenology:** Features a stable relaxation limit cycle.

---

### 6. Damped Non-Linear Pendulum

#### Mathematical Formulation and Parameters
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

- **Typical Parameters:**
  * $\frac{g}{L} = 1.0$: Normalized natural frequency.
  * $\gamma = 0.2$: Viscous friction coefficient.
- **Phenomenology:** Exhibits stable foci at rest and unstable saddle points at the inverted positions.

---

# III. Finance, Climate & Non-Linear Dynamics

---

### 7. Kaldor's Non-Linear Business Cycle

#### Mathematical Formulation and Parameters
A seminal macroeconomic model that endogenizes business cycles through a non-linear (sigmoid) investment function coupled with the capital stock:

$$\frac{dY}{dt} = \alpha (\tanh(Y) - s Y - \gamma K)$$
$$\frac{dK}{dt} = s Y - \delta K$$

- **State Variables:**
  * $Y(t)$: National income or output (deviations from equilibrium).
  * $K(t)$: Accumulated capital stock.
- **Typical Parameters:**
  * $\alpha = 1.2$: Goods market adjustment speed.
  * $\gamma = 0.8$: Investment sensitivity to capital stock.
  * $s = 0.3$: Marginal propensity to save.
  * $\delta = 0.1$: Capital depreciation rate.
- **Phenomenology:** Features an attracting limit cycle representing persistent endogenous economic fluctuations.

---

### 8. Stommel's Thermohaline Circulation (Two-Box Model)

#### Mathematical Formulation and Parameters
A fundamental model in physical oceanography describing water exchange between equatorial and polar regions, demonstrating the possibility of multiple global climate equilibria:

$$\frac{dx}{dt} = \eta_1 - x - x|x - y|$$
$$\frac{dy}{dt} = \eta_2 - y - y|x - y|$$

- **State Variables:**
  * $x(t)$: Dimensionless temperature difference between equatorial and polar boxes.
  * $y(t)$: Dimensionless salinity difference between boxes.
- **Typical Parameters:**
  * $\eta_1 = 3.0$: Surface thermal forcing.
  * $\eta_2 = 1.2$: Surface haline forcing.
- **Phenomenology:** Exhibits bistability, admitting two stable climate regimes (nodes) separated by an unstable saddle.

---

# IV. Mathematical Foundations and Qualitative Theory

---

### 9. Reference Linear System

#### Mathematical Formulation and Parameters
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

- **Typical Parameters:**
  * $a = 0.0, b = 1.0, c = -2.0, d = -0.5$.
- **Spectral Classification:** Based on Trace and Determinant of the Jacobian.

---

### 10. Polking's Non-Linear Dynamics (Multiple Equilibria)

#### Mathematical Formulation and Parameters
$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$

- **Phenomenology:** Features the coexistence of 4 equilibria in the plane, generating a rich map of competing separatrices.

### 11. Love Dynamics (Romeo & Juliet)

#### Mathematical Formulation and Parameters
A classic linear system introduced by Steven Strogatz with a touch of drama and humor to study stability. The model describes the evolution of mutual affection between two lovers:

$$\frac{dR}{dt} = a R + b J$$
$$\frac{dJ}{dt} = c R + d J$$

- **State Variables:**
  * $R(t)$: Romeo's feelings (positive values = love, negative = aversion).
  * $J(t)$: Juliet's feelings.
- **Typical Parameters (The Endless Chase):**
  * $a, d$: Self-acceptance parameters (ego or caution).
  * $b, c$: Parameters of reaction to the partner's feelings.
  * With $a = 0, b = 1, c = -1, d = 0$: Romeo loves Juliet more the more she flees, and Juliet loses interest the more Romeo approaches.
- **Dynamic Classification:**
  * This setup creates a matrix with zero trace and positive determinant. It is a **Pure Center**, ensuring the romance is cyclically tragic, never reaching a stable equilibrium or total collapse.

#### Practical Exploration Guide
1. **The Endless Cycle:** With the default setup (0, 1, -1, 0), click anywhere on the plane and watch the orbits form perfect ellipses. The chase is eternal.
2. **Platonic Love and Apathy:** Open the *Parameter Studio* and change both self-acceptances to slightly negative values (e.g., $a = -0.1$ and $d = -0.1$). Watch the system become an attracting focus: after some romantic back-and-forth, the couple inevitably collapses to the origin (mutual and irreversible emotional apathy).
3. **Change the Story:** Invent new personalities. What happens if both are cautious lovers ($a < 0, d < 0$) but react positively to each other ($b > 0, c > 0$)?

#### Conceptual References
- Strogatz, S. H. (1988). *Love affairs and differential equations*. Mathematics Magazine, 61(1), 35.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 12. Brusselator Chemical Oscillator

#### Mathematical Formulation and Parameters
A classic model in physical chemistry developed by the Brussels school (Prigogine and Lefever) to describe the kinetics of a theoretical autocatalytic reaction exhibiting sustained oscillations (temporal symmetry breaking and dissipative structures):

$$\frac{dx}{dt} = A - (B + 1)x + x^2y$$
$$\frac{dy}{dt} = Bx - x^2y$$

- **State Variables:**
  * $x(t), y(t)$: Concentrations of the reaction's intermediate reactants.
- **Typical Parameters:**
  * $A = 1.0$: Constant concentration of the input reactant.
  * $B = 3.0$: Rate constant associated with the autocatalytic step.
- **Unique Equilibrium Point and Bifurcation:**
  * The system admits a single stationary equilibrium located at $(x^*, y^*) = (A, B/A)$.
  * The stability of this point changes drastically depending on the reactants: it is stable if $B < 1 + A^2$ and becomes unstable (undergoing a **Supercritical Hopf Bifurcation**) when $B > 1 + A^2$.

#### Practical Exploration Guide
1. **Oscillatory Attraction:** With the default setup ($A = 1, B = 3$), note that the instability condition ($3 > 1 + 1^2 = 2$) is met. Click on the plane and watch the concentrations $x$ and $y$ be relentlessly attracted to a teardrop-shaped limit cycle.
2. **Time Window (Solutions vs Time):** View the $x(t)$ and $y(t)$ graphs. You will notice well-defined oscillatory peaks, characterizing the "chemical clock" nature of the reaction-diffusion system.
3. **Phase Transition:** In the *Parameter Studio*, keep $A = 1$ and slowly reduce $B$ to $1.5$. The limit cycle will disappear and the reaction will return to a stable concentration flow (attracting focus toward $(1, 1.5)$).

#### Conceptual References
- Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. The Journal of Chemical Physics, 48(4), 1695-1700.
- Nicolis, G., & Prigogine, I. (1977). *Self-Organization in Nonequilibrium Systems: From Dissipative Structures to Order through Fluctuations*. Wiley.
