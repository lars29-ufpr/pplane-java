# Didaktischer Leitfaden zu dynamischen Modellen (Phasenraum)
## pplaneX — Version 1.2.1

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

#### Mathematische Formulierung und Parameter
$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$
- **Phänomenologie:** Weist einen stabilen Relaxationsgrenzzyklus auf.

---

### 6. Gedämpftes nichtlineares Pendel

#### Mathematische Formulierung und Parameter
$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$
- **Phänomenologie:** Zeigt stabile Fokusse in Ruhe und instabile Sattelpunkte in den umgekehrten Positionen.

---

# III. Finanzen, Klima & Nichtlineare Dynamik

---

### 7. Nichtlinearer Konjunkturzyklus nach Kaldor

#### Mathematische Formulierung und Parameter
Ein grundlegendes makroökonomisches Modell, das Konjunkturzyklen (Expansion und Rezession) durch eine nichtlineare Investitionsfunktion (Sigmoid) gekoppelt mit dem Kapitalstock endogenisiert:

$$\frac{dY}{dt} = \alpha (\tanh(Y) - s Y - \gamma K)$$
$$\frac{dK}{dt} = s Y - \delta K$$

- **Zustandsvariablen:**
  * $Y(t)$: Nationaleinkommen oder Produktion (Abweichungen vom Gleichgewicht).
  * $K(t)$: Akkumulierter Kapitalstock.
- **Typische Parameter:**
  * $\alpha = 1.2$: Anpassungsgeschwindigkeit des Gütermarktes.
  * $\gamma = 0.8$: Investitionssensibilität gegenüber dem Kapitalstock.
  * $s = 0.3$: Marginale Sparquote.
  * $\delta = 0.1$: Kapitalabschreibungsrate.
- **Phänomenologie:** Weist einen anziehenden Grenzzyklus auf, der anhaltende endogene wirtschaftliche Schwankungen darstellt.

---

### 8. Thermohaline Zirkulation nach Stommel (Zwei-Box-Modell)

#### Mathematische Formulierung und Parameter
Ein grundlegendes Modell der physikalischen Ozeanographie, das den Wasseraustausch zwischen äquatorialen und polaren Regionen beschreibt und die Möglichkeit mehrerer globaler Klimagleichgewichte demonstriert:

$$\frac{dx}{dt} = \eta_1 - x - x|x - y|$$
$$\frac{dy}{dt} = \eta_2 - y - y|x - y|$$

- **Zustandsvariablen:**
  * $x(t)$: Dimensionslose Temperaturdifferenz zwischen den äquatorialen und polaren Boxen.
  * $y(t)$: Dimensionslose Salzgehaltsdifferenz zwischen den Boxen.
- **Typische Parameter:**
  * $\eta_1 = 3.0$: Thermischer Oberflächenantrieb.
  * $\eta_2 = 1.2$: Haliner Oberflächenantrieb.
- **Phänomenologie:** Zeigt Bistabilität und lässt zwei stabile Klimaregime (Knoten) zu, die durch einen instabilen Sattel getrennt sind.

---

# IV. Mathematische Grundlagen und Qualitative Theorie

---

### 9. Lineares Referenzsystem

#### Mathematische Formulierung und Parameter
$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$
- **Spektrale Klassifizierung:** Basierend auf Spur und Determinante der Jacobi-Matrix.

---

### 10. Nichtlineare Dynamik nach Polking (Multiple Gleichgewichte)

#### Mathematische Formulierung und Parameter
$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$
- **Phänomenologie:** Weist die Koexistenz von 4 Gleichgewichten in der Ebene auf und erzeugt eine reichhaltige Karte konkurrierender Separatrizen.
