# Educational Guide to Two-Dimensional Dynamical Models
Phase Plane Analyzer — Version 1.0

This educational guide details the formulations, parameters, and exploration scripts for the standard models available in the gallery.

---

# I. Biomathematics & Population Dynamics

### 1. Predator-Prey Model (Lotka-Volterra)

#### Mathematical Formulation and Parameters
Canonical conservative model for predator-prey interaction:
$$x' = a x - b x y$$
$$y' = -c y + d x y$$

* **x**: Prey population density.
* **y**: Predator population density.
* **a**: Prey intrinsic growth rate.
* **b**: Predation rate.
* **c**: Predator natural mortality rate.
* **d**: Efficiency of biomass conversion from prey to predators.

#### Practical Exploration Script
* Observe the periodic orbits in the first quadrant around the center equilibrium $(c/d, a/b)$.
* Note the conservation of the first integral $V(x, y) = d x - c \ln(x) + b y - a \ln(y)$.

#### Conceptual References
* Lotka, A. J. (1925). *Elements of Physical Biology*.
* Volterra, V. (1926). *Variazioni e fluttuazioni del numero d'individui in specie animali conviventi*.

---

### 2. Two-Species Competition (Murray)

#### Mathematical Formulation and Parameters
Lotka-Volterra competition model with logistic growth:
$$x' = x (a_1 - b_1 x - c_1 y)$$
$$y' = y (a_2 - b_2 y - c_2 x)$$

* **x, y**: Densities of competing species.
* **a1, a2**: Intrinsic per capita growth rates.
* **b1, b2**: Intraspecific competition coefficients.
* **c1, c2**: Interspecific competition coefficients.

#### Practical Exploration Script
* Test coexistence conditions ($a_1/c_1 > a_2/b_2$ and $a_2/c_2 > a_1/b_1$).
* Observe competitive exclusion and bistability when both species strongly compete.

#### Conceptual Reference
* Murray, J. D. (2002). *Mathematical Biology I: An Introduction*. Springer.

---

### 3. FitzHugh-Nagumo Neuron Model

#### Mathematical Formulation and Parameters
Two-dimensional reduction of the Hodgkin-Huxley model for neuronal excitability:
$$v' = v - \frac{v^3}{3} - w + I$$
$$w' = \frac{v + a - b w}{\tau}$$

* **v**: Membrane voltage potential (fast activator).
* **w**: Recovery variable (slow inhibitor).
* **I**: External applied stimulus current.
* **a, b, tau**: Time scale and kinetics recovery constants.

#### Practical Exploration Script
* Vary $I$ from $0.0$ to $0.5$ to observe the transition from a stable excitable rest state to limit-cycle spiking oscillations (Hopf bifurcation).

#### Conceptual References
* FitzHugh, R. (1961). *Impulses and physiological states in theoretical models of nerve membrane*.
* Nagumo, J., Arimoto, S., & Yoshizawa, S. (1962). *An active pulse transmission line simulating nerve axon*.

---

### 4. SIR Epidemic (Kermack-McKendrick)

#### Mathematical Formulation and Parameters
Normalized epidemic compartmental dynamics:
$$S' = -\beta S I$$
$$I' = \beta S I - \gamma I$$

* **S**: Fraction of susceptible individuals.
* **I**: Fraction of infectious individuals.
* **beta**: Effective contact/transmission rate.
* **gamma**: Recovery/removal rate.

#### Practical Exploration Script
* Notice the basic reproduction number $R_0 = \beta / \gamma$.
* Check that infections peak exactly along the critical vertical nullcline $S = \gamma / \beta$.

#### Conceptual Reference
* Kermack, W. O., & McKendrick, A. G. (1927). *A contribution to the mathematical theory of epidemics*.

---

# II. Nonlinear Mechanics & Physical Oscillations

### 5. Van der Pol Oscillator

#### Mathematical Formulation and Parameters
Self-excited oscillator with nonlinear damping:
$$x' = y$$
$$y' = \mu (1 - x^2) y - x$$

* **x**: Position / displacement coordinate.
* **y**: Velocity.
* **mu**: Nonlinear damping parameter ($\mu > 0$).

#### Practical Exploration Script
* Observe the convergence of all non-zero trajectories toward the unique stable limit cycle.
* Compare quasi-harmonic behavior at low $\mu$ with relaxation oscillations at high $\mu$.

#### Conceptual Reference
* Van der Pol, B. (1926). *On relaxation-oscillations*. The London, Edinburgh, and Dublin Philosophical Magazine.

---

### 6. Damped Nonlinear Simple Pendulum

#### Mathematical Formulation and Parameters
Full large-angle equation of motion:
$$\theta' = \omega$$
$$\omega' = -w^2 \sin(\theta) - b \omega$$

* **theta**: Angular position displacement.
* **omega**: Angular velocity.
* **b**: Viscous damping coefficient.
* **w**: Natural oscillation frequency.

#### Practical Exploration Script
* Identify stable spirals (foci) at $(2k\pi, 0)$ and saddle points at $((2k+1)\pi, 0)$.
* Follow the homoclinic and heteroclinic separatrices dividing librations from rotations.

#### Conceptual Reference
* Strogatz, S. H. (2015). *Nonlinear Dynamics and Chaos*. Westview Press.

---

### 7. Autonomous Duffing Oscillator

#### Mathematical Formulation and Parameters
Double-well bistable oscillator:
$$x' = y$$
$$y' = -\delta y - \beta x - \alpha x^3$$

* **x**: Displacement coordinate.
* **y**: Velocity.
* **delta**: Friction damping coefficient.
* **alpha, beta**: Linear and cubic stiffness parameters.

#### Practical Exploration Script
* Observe the coexistence of two attractor wells separated by the invariant manifolds of the saddle at the origin.

#### Conceptual Reference
* Guckenheimer, J., & Holmes, P. (1983). *Nonlinear Oscillations, Dynamical Systems, and Bifurcations of Vector Fields*.

---

# III. Mathematical Foundations and Qualitative Theory

### 8. Classical Pplane (Polking)

#### Mathematical Formulation and Parameters
Opening benchmark system from MATLAB's original pplane:
$$x' = 2x - y + 3(x^2 - y^2) + 2xy$$
$$y' = x - 3y - 3(x^2 - y^2) + 3xy$$

#### Practical Exploration Script
* Explore multiple fixed points and saddle connections in the $[-2, 2] \times [-2, 2]$ domain.

#### Conceptual Reference
* Polking, J. C. (1995). *Ordinary Differential Equations using MATLAB*. Prentice Hall.

---

### 9. 2D Linear Systems (Classification & Strogatz's Love)

#### Mathematical Formulation and Parameters
General planar autonomous linear matrix system:
$$R' = a R + b J$$
$$J' = c R + d J$$

* **R, J**: Romeo and Juliet's mutual affection states.
* **a, b, c, d**: Emotional responsiveness parameters.

#### Practical Exploration Script
* Classify the origin using the trace $\tau = a + d$ and determinant $\Delta = ad - bc$: nodes, saddles, centers, or spirals.

#### Conceptual Reference
* Strogatz, S. H. (1988). *Love affairs and differential equations*. Harvard Mathematics Magazine.

---

### 10. Polking Nonlinear Dynamics (Multiple Equilibria)

#### Mathematical Formulation and Parameters
Polynomial vector field with multiple simultaneous equilibria:
$$x' = x^2 - y^2$$
$$y' = x (2 - y)$$

#### Practical Exploration Script
* Calculate and identify the 4 equilibria: $(0, 0)$, $(0, 2)$, $(2, 2)$, and $(-2, 2)$.
* Inspect the intersection of nullclines and the local Hartman-Grobman linearization.

#### Conceptual Reference
* Polking, J. C. (2004). *PPLANE for MATLAB*. Rice University.

---

### 11. Brusselator Chemical Oscillator

#### Mathematical Formulation and Parameters
Autocatalytic reaction kinetics model:
$$x' = a - (b + 1) x + x^2 y$$
$$y' = b x - x^2 y$$

* **x, y**: Dimensionless chemical intermediate concentrations.
* **a, b**: Pool chemical concentrations ($a > 0, b > 0$).

#### Practical Exploration Script
* Verify the fixed point at $(a, b/a)$.
* Increase $b$ past the supercritical Hopf bifurcation threshold $b_c = 1 + a^2$ to trigger sustained chemical oscillations.

#### Conceptual Reference
* Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. J. Chem. Phys.
