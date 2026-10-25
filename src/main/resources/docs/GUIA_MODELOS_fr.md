# Guide des Modèles Mathématiques (Phase Plane)
## pplaneX — Version 1.2

Ce guide rassemble le catalogue canonique des systèmes d'équations différentielles ordinaires bidimensionnelles ($2 \times 2$) implémentés dans **pplaneX**. Chaque modèle comprend sa formulation mathématique, un guide didactique d'expérimentation dans le plan de phase et des références conceptuelles classiques.

---

# I. Biomathématiques & Dynamique des Populations

---

### 1. Modèle Proie-Prédateur (Lotka-Volterra)

#### Formulation Mathématique et Paramètres
Le modèle classique de Lotka-Volterra décrit l'interaction de prédation entre deux espèces dans un environnement homogène, sans tenir compte de la capacité de charge environnementale pour les proies :

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Variables d'État :**
  * $x(t) \ge 0$ : Densité de la population de proies.
  * $y(t) \ge 0$ : Densité de la population de prédateurs.
- **Paramètres Typiques :**
  * $\alpha = 1.0$ : Taux de croissance intrinsèque des proies en l'absence de prédateurs.
  * $\beta = 0.5$ : Taux de prédation (fréquence de capture et de consommation).
  * $\delta = 0.5$ : Efficacité de conversion biologique des proies consommées en nouveaux prédateurs.
  * $\gamma = 1.0$ : Taux de mortalité per capita des prédateurs.
- **Points d'Équilibre et Invariant :**
  * $(0, 0)$ : Point col (selle) instable.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$ : Centre conservatif linéairement neutre.
  * Possède une intégrale première conservée : $V(x, y) = \delta x - \gamma \ln(x) + \beta y - \alpha \ln(y) = \text{constante}$.

#### Guide Pratique d'Exploration
1. **Isoclines (Nullclines) :** Notez les isoclines orthogonales : $\frac{dx}{dt} = 0 \implies x = 0$ ou $y = 2.0$ ; $\frac{dy}{dt} = 0 \implies y = 0$ ou $x = 2.0$.
2. **Orbites Fermées :** Cliquez sur des conditions initiales à différentes distances de $(2.0, 2.0)$ (ex. : $(2.5, 2.0)$, $(3.5, 2.0)$, $(5.0, 2.0)$). Observez que toutes les solutions forment des cycles fermés non périodiques par attracteur, mais gouvernés par l'énergie conservée $V(x, y)$.
3. **Série Temporelle :** Ouvrez la fenêtre de *Solutions vs Temps* et constatez le déphasage classique d'un quart de cycle entre le pic des proies et le pic subséquent des prédateurs.

#### Références Conceptuelles
- Lotka, A. J. (1925). *Elements of Physical Biology*. Williams & Wilkins, Baltimore.
- Volterra, V. (1926). *Variazioni e fluttuazioni del numero d'individui in specie animali conviventi*. Mem. Accad. Lincei, 2, 31-113.
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction* (3rd ed.). Springer.

---

### 2. Compétition entre Deux Espèces (Murray)

#### Formulation Mathématique et Paramètres
Décrit la dynamique de deux espèces biologiques se disputant les mêmes niches ou ressources alimentaires, en intégrant une croissance logistique pour chacune :

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Variables d'État :**
  * $x(t) \ge 0$ : Densité de l'espèce concurrente 1.
  * $y(t) \ge 0$ : Densité de l'espèce concurrente 2.
- **Paramètres Typiques :**
  * $r_1 = 1.0, r_2 = 1.0$ : Taux intrinsèques de croissance per capita.
  * $K_1 = 3.0, K_2 = 3.0$ : Capacités de charge individuelles de l'habitat.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$ : Coefficients d'inhibition interspécifique réciproque.
- **Phénoménologie :**
  * Lorsque $\alpha_{12} < 1$ et $\alpha_{21} < 1$, le système admet une **coexistence stable** dans un nœud attracteur à l'intérieur du premier quadrant.

#### Guide Pratique d'Exploration
1. **Intersection des Isoclines :** Les isoclines linéaires inclinées divisent le quadrant en secteurs directionnels convergents.
2. **Bassins d'Attraction :** Cliquez sur des points proches des axes de coordonnées et observez la convergence inexorable vers l'équilibre de coexistence stable $(x^*, y^*)$.
3. **Exclusion Compétitive :** Modifiez les paramètres via le *Parameter Studio* pour $\alpha_{12} = 1.5$ et $\alpha_{21} = 1.3$ et cliquez sur **Tracer les Séparatrices** pour visualiser le bassin divisé par un point col instable.

#### Références Conceptuelles
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction*. Springer-Verlag.
- Begon, M., Townsend, C. R., & Harper, J. L. (2006). *Ecology: From Individuals to Ecosystems*. Blackwell Publishing.

---

### 3. Modèle Neuronal de FitzHugh-Nagumo

#### Formulation Mathématique et Paramètres
Réduction planaire canonique du modèle biophysique à quatre variables de Hodgkin-Huxley pour la propagation des potentiels d'action dans la membrane axonale :

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Variables d'État :**
  * $v(t)$ : Potentiel de membrane neuronal (variable d'activation rapide).
  * $w(t)$ : Variable de récupération de la conductance ionique potassique (variable lente).
- **Paramètres Typiques :**
  * $a = 0.7, b = 0.8$ : Paramètres cinétiques de la récupération transmembranaire.
  * $\epsilon = 0.08$ : Séparation des échelles de temps lente/rapide ($\epsilon \ll 1$).
  * $I_{\text{ext}} = 0.5$ : Courant de stimulus électrique externe injecté.
- **Dynamique Excitable :** L'isocline cubique de $v$ combinée à la relaxation linéaire de $w$ produit des oscillations par décharge (*spiking*) ou une excitabilité liminaire.

#### Guide Pratique d'Exploration
1. **Équilibre au Repos ($I_{\text{ext}} = 0$) :** Inspectez l'équilibre unique sur la branche gauche de la cubique ; remarquez que les petites perturbations s'amortissent, mais que les perturbations dépassant le genou de la cubique déclenchent une large décharge dans le plan avant de revenir.
2. **Cycle Limite Auto-Entretenu ($I_{\text{ext}} = 0.5$) :** Observez la perte de stabilité du point de repos par une **Bifurcation de Hopf**. Toutes les orbites sont attirées vers un cycle limite de relaxation.
3. **Trajectoires Rapides et Lentes :** Observez le parallélisme presque horizontal des trajectoires en dehors de la cubique (phase rapide) par rapport à l'adhérence lente le long de la cubique (phase réfractaire).

#### Références Conceptuelles
- FitzHugh, R. (1961). *Impulses and physiological states in theoretical models of nerve membrane*. Biophysical Journal, 1(6), 445-466.
- Nagumo, J., Arimoto, S., & Yoshizawa, S. (1962). *An active pulse transmission line simulating nerve axon*. Proc. IRE, 50(10), 2061-2070.
- Izhikevich, E. M. (2007). *Dynamical Systems in Neuroscience*. MIT Press.

---

### 4. Épidémie SIR (Kermack-McKendrick)

#### Formulation Mathématique et Paramètres
Le modèle fondamental de l'épidémiologie théorique décrit la transmission d'agents pathogènes avec immunité permanente après guérison dans une population homogène constante ($S + I + R = 1$) :

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Variables d'État :**
  * $S(t) \in [0, 1]$ : Fraction de la population susceptible à l'infection.
  * $I(t) \in [0, 1]$ : Fraction de la population infectée et activement transmettrice.
- **Paramètres Typiques :**
  * $\beta = 0.5$ : Taux de transmission effectif par contact contagieux.
  * $\gamma = 0.2$ : Taux per capita de guérison infectieuse ($\gamma = 1/\tau_{\text{inf}}$).
- **Seuil Critique et Reproduction de Base :**
  $$R_0 = \frac{\beta}{\gamma} = \frac{0.5}{0.2} = 2.5, \quad S_c = \frac{1}{R_0} = \frac{\gamma}{\beta} = 0.4$$

#### Guide Pratique d'Exploration
1. **Isocline Critique Verticale :** Observez l'isocline verte verticale positionnée précisément à $S = 0.4$.
2. **Pic Infectieux :** Cliquez sur une condition épidémique initiale avec $S_0 > 0.4$ (ex. : $S_0 = 0.95, I_0 = 0.02$). La courbe monte en diagonale vers la gauche jusqu'à tangenter horizontalement la droite $S = 0.4$, atteignant le sommet exact de la contamination.
3. **Fin de l'Épidémie sans Épuisement Total :** Constatez que la courbe descend asymptotiquement jusqu'à l'axe horizontal $I = 0$ à une valeur $S_\infty > 0$. L'épidémie cesse par rupture de la densité critique de contacts et non par l'infection de la totalité des individus.

#### Références Conceptuelles
- Kermack, W. O., & McKendrick, A. G. (1927). *A contribution to the mathematical theory of epidemics*. Proc. R. Soc. Lond. A, 115(772), 700-721.
- Brauer, F., & Castillo-Chavez, C. (2012). *Mathematical Models in Population Biology and Epidemiology*. Springer.

---

# II. Mécanique Non Linéaire & Oscillations Physiques

---

### 5. Oscillateur de Van der Pol

#### Formulation Mathématique et Paramètres
Développé à l'origine pour les circuits électriques à triodes à vide, le modèle présente un amortissement non linéaire qui agit comme un dissipateur d'énergie aux grandes amplitudes et comme une source amplificatrice aux petites amplitudes :

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

- **Variables d'État :**
  * $x(t)$ : Position ou tension électrique normalisée.
  * $y(t)$ : Vitesse ou dérivée temporelle $\dot{x}$.
- **Paramètres Typiques :**
  * $\mu = 1.0$ : Coefficient d'amortissement non linéaire dépendant de l'amplitude.
- **Phénoménologie Géométrique :**
  * Le point d'équilibre à l'origine $(0, 0)$ est un foyer instable.
  * Il existe un **cycle limite attracteur stable** isolé dans le plan qui absorbe toutes les solutions non nulles.

#### Guide Pratique d'Exploration
1. **Attraction Globale :** Cliquez à l'intérieur du cycle (ex. : $(0.1, 0.1)$) et à l'extérieur du cycle (ex. : $(3.0, 3.0)$). Vérifiez que les deux spirales convergent asymptotiquement vers la même courbe fermée invariante.
2. **Oscillations de Relaxation :** Augmentez $\mu$ à $3.0$ ou $5.0$ dans le *Parameter Studio*. Le cycle perd sa symétrie quasi-elliptique et développe des sauts ultra-rapides alternés avec des glissements lents le long des branches de la courbe $y = \frac{x - x^3/3}{\mu}$.

#### Références Conceptuelles
- Van der Pol, B. (1926). *On relaxation-oscillations*. The London, Edinburgh, and Dublin Philosophical Magazine and Journal of Science, 2(11), 978-992.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 6. Pendule Simple Non Linéaire Amorti

#### Formulation Mathématique et Paramètres
Modélise les oscillations mécaniques d'une tige rigide avec une masse concentrée à son extrémité sans l'approximation linéaire pour les petits angles ($\sin \theta \not\approx \theta$) :

$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

- **Variables d'État :**
  * $\theta(t)$ : Position angulaire de la tige en radians.
  * $\omega(t)$ : Vitesse angulaire instantanée ($d\theta/dt$).
- **Paramètres Typiques :**
  * $\frac{g}{L} = 1.0$ : Fréquence naturelle caractéristique normalisée.
  * $\gamma = 0.2$ : Coefficient de frottement visqueux de l'air ou du pivot.
- **Structure Topologique :**
  * Famille périodique d'équilibres stables en $(2k\pi, 0)$ (foyers attracteurs stables — pendule au repos vertical vers le bas).
  * Famille de points cols en $((2k+1)\pi, 0)$ (tige inversée pointant vers le haut, instable).

#### Guide Pratique d'Exploration
1. **Séparatrices Homoclines et Hétéroclines :** Ajustez temporairement $\gamma = 0$ et cliquez sur **Tracer les Séparatrices** pour voir la courbe qui divise le régime d'oscillation de va-et-vient du régime de révolutions complètes continues.
2. **Bassins d'Oscillation Amortie :** Avec $\gamma = 0.2$, cliquez sur des vitesses angulaires élevées ($\omega_0 = 2.5$) et observez le pendule effectuer plusieurs rotations avant d'être capturé par l'attracteur local le plus proche.

#### Références Conceptuelles
- Marion, J. B., & Thornton, S. T. (2004). *Classical Dynamics of Particles and Systems*. Brooks/Cole.
- Hirsch, M. W., Smale, S., & Devaney, R. L. (2013). *Differential Equations, Dynamical Systems, and an Introduction to Chaos*. Academic Press.

---

### 7. Oscillateur de Duffing Autonome

#### Formulation Mathématique et Paramètres
Représente une poutre élastique déformable soumise à des forces mécaniques avec un potentiel à double puits symétrique :

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$

- **Variables d'État :**
  * $x(t)$ : Position de déflexion latérale de la poutre.
  * $y(t)$ : Vitesse mécanique instantanée.
- **Paramètres Typiques :**
  * $\delta = 0.2$ : Amortissement visqueux structurel.
  * $\beta = -1.0$ : Terme linéaire de rigidité négative (provoque l'instabilité au repos central).
  * $\alpha = 1.0$ : Terme cubique de restauration non linéaire.
- **Topologie à Double Puits :**
  * L'origine $(0, 0)$ est un point col.
  * Les deux fonds des puits latéraux en $(\pm 1, 0)$ sont des foyers stables.

#### Guide Pratique d'Exploration
1. **Frontière Fragile des Bassins :** Cliquez sur **Tracer les Séparatrices** au niveau du point col $(0, 0)$. Les variétés stables s'enroulent dans le plan, dessinant une frontière délicate entre les deux puits.
2. **Sensibilité aux Conditions Initiales :** Lancez deux points initiaux presque identiques autour de l'origine et vérifiez que des trajectoires voisines peuvent converger vers des puits d'attraction opposés.

#### Références Conceptuelles
- Duffing, G. (1918). *Erzwungene Schwingungen bei veränderlicher Eigenfrequenz*. Vieweg, Braunschweig.
- Guckenheimer, J., & Holmes, P. (1983). *Nonlinear Oscillations, Dynamical Systems, and Bifurcations of Vector Fields*. Springer-Verlag.

---

# III. Fondements Mathématiques et Théorie Qualitative

---

### 8. Pplane Classique (Polking)

#### Formulation Mathématique et Paramètres
Le modèle emblématique qui a historiquement accompagné l'utilitaire MATLAB `pplane` de John Polking, conçu intentionnellement pour illustrer plusieurs types d'équilibres et de séparatrices concurrentes sur un seul écran :

$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

- **Variables d'État :**
  * $x, y \in \mathbb{R}$ : Coordonnées cartésiennes dans le plan de phase.
- **Phénoménologie Géométrique :**
  * Présente de multiples équilibres isolés dans une fenêtre planaire compacte (nœuds, cols et foyers simultanés).
  * Connexions riches de séparatrices qui démontrent la subdivision complète du plan en régions dynamiquement disjointes.

#### Guide Pratique d'Exploration
1. **Balayage avec Points d'Équilibre :** Appuyez sur **Points d'Équilibre** et cliquez sur chaque point mis en évidence pour vérifier la valeur propre et la classification jacobienne locale.
2. **Cartographie des Séparatrices :** Tracez les variétés stables et instables des points cols présents dans le système pour construire la structure globale du champ de vecteurs.

#### Références Conceptuelles
- Polking, J. C. (1995). *Ordinary Differential Equations using MATLAB*. Prentice Hall.
- Boyce, W. E., DiPrima, R. C., & Meade, D. B. (2021). *Elementary Differential Equations and Boundary Value Problems*. Wiley.

---

### 9. Système Linéaire de Référence et Classification Spectrale

#### Formulation Mathématique et Paramètres
Le cadre canonique des systèmes linéaires invariants dans le temps avec des coefficients matriciels constants :

$$\begin{pmatrix} \dot{x} \\ \dot{y} \end{pmatrix} = \begin{pmatrix} a & b \\ c & d \end{pmatrix} \begin{pmatrix} x \\ y \end{pmatrix}$$

$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

- **Paramètres Typiques d'Entrée :**
  * $a = 0.0, b = 1.0, c = -2.0, d = -0.5$.
- **Classification Spectrale selon la Trace ($\tau$) et le Déterminant ($\Delta$) :**
  * Équation caractéristique : $\lambda^2 - \tau \lambda + \Delta = 0$, où $\tau = a + d$ et $\Delta = ad - bc$.
  * Discriminant : $\Lambda = \tau^2 - 4\Delta$.
  * $\Delta < 0$ : **Point Col** (toujours instable).
  * $\Delta > 0, \Lambda > 0$ : **Nœud** (stable si $\tau < 0$, instable si $\tau > 0$).
  * $\Delta > 0, \Lambda < 0$ : **Foyer / Spirale** (attracteur si $\tau < 0$, répulseur si $\tau > 0$, **Centre** si $\tau = 0$).

#### Guide Pratique d'Exploration
1. **Inspection des Valeurs Propres :** Utilisez le bouton **Inspecter l'Équilibre (Clic)** et cliquez sur l'origine pour inspecter la matrice jacobienne calculée, les valeurs propres $\lambda_1, \lambda_2$ et les vecteurs propres directionnels.
2. **Exploration dans le Studio de Paramètres :** Ouvrez les *curseurs* et faites varier le coefficient $d$ en gardant les autres constants. Observez la transition continue et visuelle de foyer stable $\to$ centre $\to$ foyer instable à mesure que la trace croise zéro.

#### Références Conceptuelles
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos: With Applications to Physics, Biology, Chemistry, and Engineering*. CRC Press.
- Perko, L. (2001). *Differential Equations and Dynamical Systems* (3rd ed.). Springer-Verlag.

---

### 10. Dynamique Non Linéaire de Polking (Équilibres Multiples)

#### Formulation Mathématique et Paramètres
Le système polynomial classique immortalisé par John Polking dans le PPLANE original de l'Université Rice, idéal pour observer des séparatrices se disputant des bassins d'attraction distincts :

$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$

- **Variables d'État :**
  * $x(t)$ : Première coordonnée de phase.
  * $y(t)$ : Seconde coordonnée de phase.
- **Points d'Équilibre :**
  * $(0, 0)$ : Point critique dégénéré à l'origine.
  * $(2, 2)$ et $(-2, 2)$ : Points cols symétriques avec des séparatrices bien délimitées.
  * $(0, 2)$ : Équilibre avec convergence asymptotique verticale.
- **Phénoménologie :**
  * Présente la coexistence de 4 équilibres dans le plan, générant une carte riche en séparatrices où des trajectoires voisines prennent des destinations diamétralement opposées.

---

### 11. Dynamique de l'Amour (Roméo & Juliette de Strogatz)

#### Formulation Mathématique et Paramètres
Formalisé par le mathématicien Steven Strogatz (*Nonlinear Dynamics and Chaos*), ce modèle cartographie la dynamique affective d'un couple à travers un système linéaire homogène :

$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

- **Variables d'État :**
  * $x(t)$ : Niveau d'affection de Roméo pour Juliette ($x > 0$ : passion débordante ; $x < 0$ : ressentiment absolu).
  * $y(t)$ : Niveau d'affection de Juliette pour Roméo ($y > 0$ : amour réciproque ; $y < 0$ : indifférence/mépris).
- **Paramètres Préconfigurés (Cas des Âmes Volages) :**
  * $a = -0.1$ : Amour-propre déclinant de Roméo.
  * $b = 1.0$ : Juliette réchauffe le cœur de Roméo avec facilité.
  * $c = -1.0$ : Juliette prend peur face à l'insistance de Roméo et s'éloigne.
  * $d = -0.1$ : Tendance naturelle de Juliette à l'indifférence avec le temps.
- **Phénoménologie et Avertissement Amoureux :**
  * Avec des valeurs propres complexes conjuguées à partie réelle négative, le système forme une spirale stable vers l'origine $(0, 0)$ : la tragique et monotone apathie émotionnelle mutuelle.
  * Dans les configurations non amorties, l'amour devient une orbite périodique fermée (centre) : quand Roméo aime, Juliette fuit ; quand Roméo renonce, Juliette retombe amoureuse — un cycle infini de chassés-croisés amoureux mathématiquement inévitables.
