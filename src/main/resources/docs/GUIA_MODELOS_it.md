# Guida Didattica ai Modelli Dinamici (Piano delle Fasi)
## pplaneX — Versione 1.2

Questa guida raccoglie il catalogo canonico dei sistemi di equazioni differenziali ordinarie bidimensionali ($2 \times 2$) implementati in **pplaneX**. Ogni modello include la sua formulazione matematica, una guida pratica all'esplorazione nel piano delle fasi e i classici riferimenti concettuali.

---

# I. Biomatematica e Dinamica delle Popolazioni

---

### 1. Modello Preda-Predatore (Lotka-Volterra)

#### Formulazione Matematica e Parametri
Il classico modello di Lotka-Volterra descrive l'interazione di predazione tra due specie in un ambiente omogeneo:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Variabili di Stato:**

  * $x(t) \ge 0$: Densità della popolazione delle prede.
  * $y(t) \ge 0$: Densità della popolazione dei predatori.
- **Parametri Tipici:**

  * $\alpha = 1.0$: Tasso di crescita intrinseco delle prede in assenza di predatori.
  * $\beta = 0.5$: Tasso di predazione (frequenza di cattura e consumo).
  * $\delta = 0.5$: Efficienza di conversione biologica delle prede consumate in nuovi predatori.
  * $\gamma = 1.0$: Tasso di mortalità pro capite dei predatori.
- **Punti di Equilibrio e Invariante:**

  * $(0, 0)$: Punto di sella instabile.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Centro conservativo linearmente neutro.

#### Guida Pratica all'Esplorazione
1. **Isocline Nulle:** Notare le isocline nulle ortogonali: $\frac{dx}{dt} = 0 \implies x = 0$ oppure $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ oppure $x = 2.0$.
2. **Orbite Chiuse:** Fare clic sulle condizioni iniziali a diverse distanze da $(2.0, 2.0)$. Tutte le soluzioni formano cicli chiusi non periodici governati dall'energia conservata $V(x, y)$.

---

### 2. Competizione a Due Specie (Murray)

#### Formulazione Matematica e Parametri
Descrive la dinamica di due specie biologiche che competono per le stesse nicchie o risorse alimentari:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Parametri Tipici:**

  * $r_1 = 1.0, r_2 = 1.0$: Tassi di crescita intrinseca pro capite.
  * $K_1 = 3.0, K_2 = 3.0$: Capacità portanti individuali.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coefficienti di inibizione interspecifica.

#### Guida Pratica all'Esplorazione
1. **Bacini di Attrazione:** Fare clic vicino agli assi coordinati e osservare la convergenza verso la coesistenza stabile.
2. **Esclusione Competitiva:** Cambiare i parametri in $\alpha_{12} = 1.5$ e $\alpha_{21} = 1.3$, quindi disegnare le separatrici per visualizzare il bacino diviso.

---

### 3. Modello Neuronale di FitzHugh-Nagumo

#### Formulazione Matematica e Parametri
Riduzione planare del modello biofisico di Hodgkin-Huxley per i potenziali d'azione:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Parametri Tipici:**

  * $a = 0.7, b = 0.8$: Parametri della cinetica di recupero.
  * $\epsilon = 0.08$: Separazione della scala temporale lenta/veloce.
  * $I_{\text{ext}} = 0.5$: Corrente di stimolo esterno iniettata.

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulazione Matematica e Parametri
Modello epidemiologico di trasmissione dei patogeni con immunità permanente ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Parametri Tipici:**

  * $\beta = 0.5$: Tasso di trasmissione.
  * $\gamma = 0.2$: Tasso di recupero.

---

# II. Meccanica Non Lineare e Oscillazioni Fisiche

---

### 5. Oscillatore di Van der Pol
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$
- **Fenomenologia:** Presenta un ciclo limite di rilassamento stabile.

### 6. Pendolo Non Lineare Smorzato
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$
- **Fenomenologia:** Mostra fuochi stabili a riposo e punti di sella instabili nelle posizioni invertite.

### 7. Oscillatore di Duffing Autonomo
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$
- **Fenomenologia:** Sistema bistabile con un potenziale a doppia buca.

---

# III. Fondamenti Matematici e Teoria Qualitativa

---

### 8. Pplane Classico (Polking)
$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

### 9. Sistema Lineare di Riferimento
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Classificazione Spettrale:** Basata su Traccia e Determinante della matrice Jacobiana.

### 10. Dinamica dell'Amore (Strogatz)
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Fenomenologia:** Sistema lineare che rappresenta l'affetto tra Romeo e Giulietta.
