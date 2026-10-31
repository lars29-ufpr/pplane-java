# Didaktischer Leitfaden zu dynamischen Modellen (Phasenraum)
## pplaneX — Version 1.2

Dieser Leitfaden sammelt den kanonischen Katalog zweidimensionaler ($2 \times 2$) gewöhnlicher Differentialgleichungssysteme, die in **pplaneX** implementiert sind. Jedes Modell enthält seine mathematische Formulierung, einen praktischen Erkundungsleitfaden im Phasenraum und klassische konzeptionelle Referenzen.

---

# I. Biomathematik & Populationsdynamik

---

### 1. Räuber-Beute-Modell (Lotka-Volterra)

#### Mathematische Formulierung und Parameter
Das klassische Lotka-Volterra-Modell beschreibt die Raubtier-Beute-Interaktion zwischen zwei Arten in einer homogenen Umgebung:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Zustandsvariablen:**

  * $x(t) \ge 0$: Beutepopulationsdichte.
  * $y(t) \ge 0$: Raubtierpopulationsdichte.
- **Typische Parameter:**

  * $\alpha = 1.0$: Intrinsische Wachstumsrate der Beute in Abwesenheit von Raubtieren.
  * $\beta = 0.5$: Prädationsrate (Fang- und Verzehrhäufigkeit).
  * $\delta = 0.5$: Biologische Umwandlungseffizienz von verzehrter Beute in neue Raubtiere.
  * $\gamma = 1.0$: Pro-Kopf-Sterblichkeitsrate der Raubtiere.
- **Gleichgewichtspunkte und Invariante:**

  * $(0, 0)$: Instabiler Sattelpunkt.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Linear neutrales konservatives Zentrum.

#### Praktischer Erkundungsleitfaden
1. **Nullisoklinen:** Beachten Sie die orthogonalen Nullisoklinen: $\frac{dx}{dt} = 0 \implies x = 0$ oder $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ oder $x = 2.0$.
2. **Geschlossene Bahnen:** Klicken Sie auf Anfangsbedingungen in unterschiedlichen Entfernungen von $(2.0, 2.0)$. Alle Lösungen bilden nicht-periodische geschlossene Zyklen, die von der erhaltenen Energie $V(x, y)$ gesteuert werden.

---

### 2. Zwei-Arten-Wettbewerb (Murray)

#### Mathematische Formulierung und Parameter
Beschreibt die Dynamik zweier biologischer Arten, die um dieselben Nischen oder Nahrungsressourcen konkurrieren:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Typische Parameter:**

  * $r_1 = 1.0, r_2 = 1.0$: Intrinsische Pro-Kopf-Wachstumsraten.
  * $K_1 = 3.0, K_2 = 3.0$: Individuelle Kapazitätsgrenzen.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Interspezifische Hemmungskoeffizienten.

#### Praktischer Erkundungsleitfaden
1. **Einzugsgebiete:** Klicken Sie in die Nähe der Koordinatenachsen und beobachten Sie die Konvergenz zur stabilen Koexistenz.
2. **Konkurrenzausschluss:** Ändern Sie die Parameter auf $\alpha_{12} = 1.5$ und $\alpha_{21} = 1.3$ und zeichnen Sie dann Separatrizen, um das geteilte Einzugsgebiet zu visualisieren.

---

### 3. FitzHugh-Nagumo-Neuron

#### Mathematische Formulierung und Parameter
Planare Reduktion des biophysikalischen Hodgkin-Huxley-Modells für Aktionspotentiale:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Typische Parameter:**

  * $a = 0.7, b = 0.8$: Parameter der Erholungskinetik.
  * $\epsilon = 0.08$: Langsame/schnelle Zeitskalentrennung.
  * $I_{\text{ext}} = 0.5$: Injizierter externer Reizstrom.

---

### 4. SIR-Epidemie (Kermack-McKendrick)

#### Mathematische Formulierung und Parameter
Epidemiologisches Modell der Erregerübertragung mit permanenter Immunität ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Typische Parameter:**

  * $\beta = 0.5$: Übertragungsrate.
  * $\gamma = 0.2$: Erholungsrate.

---

# II. Nichtlineare Mechanik & Physikalische Oszillationen

---

### 5. Van-der-Pol-Oszillator
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$
- **Phänomenologie:** Weist einen stabilen Relaxationsgrenzzyklus auf.

### 6. Gedämpftes nichtlineares Pendel
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$
- **Phänomenologie:** Zeigt stabile Fokusse in Ruhe und instabile Sattelpunkte in den umgekehrten Positionen.

### 7. Autonomer Duffing-Oszillator
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$
- **Phänomenologie:** Bistabiles System mit einem Doppelmuldenpotential.

---

# III. Mathematische Grundlagen und Qualitative Theorie

---

### 8. Klassisches Pplane (Polking)
$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

### 9. Lineares Referenzsystem
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Spektrale Klassifizierung:** Basierend auf Spur und Determinante der Jacobi-Matrix.

### 10. Dynamik der Liebe (Strogatz)
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Phänomenologie:** Lineares System, das die Zuneigung von Romeo und Julia darstellt.
