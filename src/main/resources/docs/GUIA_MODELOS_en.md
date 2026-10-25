# Educational Guide to Dynamical Models (Phase Plane)
## pplaneX — Version 1.2

This guide gathers the canonical catalog of two-dimensional ($2 \times 2$) ordinary differential equation systems implemented in **pplaneX**. Each model includes its mathematical formulation, a practical exploration guide in the phase plane, and classic conceptual references.

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
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$
- **Phenomenology:** Features a stable relaxation limit cycle.

### 6. Damped Non-Linear Pendulum
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$
- **Phenomenology:** Exhibits stable foci at rest and unstable saddle points at the inverted positions.

### 7. Autonomous Duffing Oscillator
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$
- **Phenomenology:** Bistable system with a double-well potential.

---

# III. Mathematical Foundations and Qualitative Theory

---

### 8. Classic Pplane (Polking)
$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

### 9. Reference Linear System
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Spectral Classification:** Based on Trace and Determinant of the Jacobian.

### 10. Dynamics of Love (Strogatz)
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Phenomenology:** Linear system representing Romeo and Juliet's affection.
