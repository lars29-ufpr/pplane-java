# Didaktischer Leitfaden zu dynamischen Modellen (Phasenraum)
### pplaneX — Version 1.3

Dieser Leitfaden sammelt den kanonischen Katalog zweidimensionaler ($2 \times 2$) gewöhnlicher Differentialgleichungssysteme, die in **pplaneX** implementiert sind. Jedes Modell enthält seine mathematische Formulierung, einen praktischen Erkundungsleitfaden im Phasenraum und klassische konzeptionelle Referenzen.

### Erstellen Eigener Modelle (Mathematische Syntax)

Wenn Sie den Reiter **Meine Modelle** in der Galerie verwenden, können Sie Ihre eigenen Systeme gewöhnlicher Differentialgleichungen (DGL) definieren. pplaneX verwendet einen strengen mathematischen Parser, daher ist es wichtig, die folgenden Syntaxregeln zu beachten:

* **Explizite Multiplikation (Sehr Wichtig!):** Im Gegensatz zur mathematischen Schreibweise auf Papier benötigt der Computer ein Sternchen `*` für jede Multiplikation.
  * [x] Falsch: `2x` oder `x(1-y)`
  * [v] Richtig: `2*x` oder `x*(1-y)`
* **Grundlegende Operatoren:** 
  * `+` (Addition), `-` (Subtraktion)
  * `*` (Multiplikation), `/` (Division)
  * `^` (Potenz, z.B.: `x^2`)
* **Unterstützte Funktionen:**
  * **Trigonometrisch:** `sin()`, `cos()`, `tan()`
  * **Exponential und Logarithmisch:** `exp()` (Basis *e*), `log()` oder `ln()` (natürlicher Logarithmus), `log10()` (Basis 10)
  * **Andere:** `sqrt()` (Quadratwurzel), `abs()` (Absolutwert)

**Praktisches Eingabebeispiel:**
Um das System $x' = -y + x(1 - x^2 - y^2)$ einzugeben, müssen Sie in das Feld dx/dt Folgendes tippen:
`-y + x*(1 - x^2 - y^2)`

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

---

### 11. Liebesdynamik (Romeo und Julia)

#### Mathematische Formulierung und Parameter
Ein klassisches lineares System, das von Steven Strogatz mit einem Hauch von Drama und Humor zur Untersuchung der Stabilität eingeführt wurde. Das Modell beschreibt die Entwicklung der gegenseitigen Zuneigung zweier Liebender:

$$\frac{dR}{dt} = a R + b J$$
$$\frac{dJ}{dt} = c R + d J$$

- **Zustandsvariablen:**
  * $R(t)$: Romeos Gefühle (positive Werte = Liebe, negative = Abneigung).
  * $J(t)$: Julias Gefühle.
- **Typische Parameter (Die Ewige Jagd):**
  * $a, d$: Parameter der Selbstakzeptanz (Ego oder Vorsicht).
  * $b, c$: Reaktionsparameter auf die Gefühle des Partners.
  * Mit $a = 0, b = 1, c = -1, d = 0$: Romeo liebt Julia mehr, je mehr sie flieht, und Julia verliert das Interesse, je mehr sich Romeo nähert.
- **Dynamische Klassifikation:**
  * Diese Konfiguration erzeugt eine Matrix mit einer Spur von null und einer positiven Determinante. Es ist ein **Reines Zentrum**, das dafür sorgt, dass die Romanze zyklisch tragisch ist und nie ein stabiles Gleichgewicht oder einen völligen Zusammenbruch erreicht.

#### Praktischer Erkundungsleitfaden
1. **Der Endlose Zyklus:** Klicken Sie bei der Standardkonfiguration (0, 1, -1, 0) auf eine beliebige Stelle in der Ebene und beobachten Sie, wie die Bahnen perfekte Ellipsen bilden. Die Verfolgungsjagd ist ewig.
2. **Platonische Liebe und Apathie:** Öffnen Sie das *Parameter Studio* und ändern Sie beide Selbstakzeptanzen auf leicht negative Werte (z.B. $a = -0.1$ und $d = -0.1$). Beobachten Sie, wie das System zu einem anziehenden Fokus wird: Nach einem romantischen Hin und Her kollabiert das Paar unweigerlich in den Ursprung (gegenseitige und irreversible emotionale Apathie).
3. **Ändern Sie die Geschichte:** Erfinden Sie neue Persönlichkeiten. Was passiert, wenn beide vorsichtige Liebhaber sind ($a < 0, d < 0$), aber positiv aufeinander reagieren ($b > 0, c > 0$)?

#### Konzeptionelle Referenzen
- Strogatz, S. H. (1988). *Love affairs and differential equations*. Mathematics Magazine, 61(1), 35.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 12. Chemischer Oszillator Brusselator

#### Mathematische Formulierung und Parameter
Ein klassisches Modell der physikalischen Chemie, das von der Brüsseler Schule (Prigogine und Lefever) entwickelt wurde, um die Kinetik einer theoretischen autokatalytischen Reaktion zu beschreiben, die anhaltende Oszillationen (zeitliche Symmetriebrechung und dissipative Strukturen) aufweist:

$$\frac{dx}{dt} = A - (B + 1)x + x^2y$$
$$\frac{dy}{dt} = Bx - x^2y$$

- **Zustandsvariablen:**
  * $x(t), y(t)$: Konzentrationen der Zwischenreaktanten der Reaktion.
- **Typische Parameter:**
  * $A = 1.0$: Konstante Konzentration des Eingangsreaktanten.
  * $B = 3.0$: Geschwindigkeitskonstante im Zusammenhang mit dem autokatalytischen Schritt.
- **Einziger Gleichgewichtspunkt und Bifurkation:**
  * Das System lässt ein einziges stationäres Gleichgewicht zu, das bei $(x^*, y^*) = (A, B/A)$ liegt.
  * Die Stabilität dieses Punktes ändert sich je nach Reaktanten drastisch: Er ist stabil, wenn $B < 1 + A^2$, und wird instabil (durchläuft eine **Superkritische Hopf-Bifurkation**), wenn $B > 1 + A^2$.

#### Praktischer Erkundungsleitfaden
1. **Oszillatorische Anziehung:** Beachten Sie bei der Standardkonfiguration ($A = 1, B = 3$), dass die Instabilitätsbedingung ($3 > 1 + 1^2 = 2$) erfüllt ist. Klicken Sie auf die Ebene und beobachten Sie, wie die Konzentrationen $x$ und $y$ unerbittlich von einem tropfenförmigen Grenzzyklus angezogen werden.
2. **Zeitfenster (Lösungen vs. Zeit):** Betrachten Sie die Graphen $x(t)$ und $y(t)$. Sie werden deutlich definierte oszillatorische Spitzen bemerken, die die Natur des "chemischen Uhrwerks" des Reaktions-Diffusions-Systems charakterisieren.
3. **Phasenübergang:** Behalten Sie im *Parameter Studio* $A = 1$ bei und reduzieren Sie $B$ langsam auf $1.5$. Der Grenzzyklus verschwindet und die Reaktion kehrt zu einem stabilen Konzentrationsfluss zurück (anziehender Fokus in Richtung $(1, 1.5)$).

#### Konzeptionelle Referenzen
- Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. The Journal of Chemical Physics, 48(4), 1695-1700.
- Nicolis, G., & Prigogine, I. (1977). *Self-Organization in Nonequilibrium Systems: From Dissipative Structures to Order through Fluctuations*. Wiley.
