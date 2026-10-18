# Guia Didático de Modelos Dinâmicos Bidimensionais
## Phase Plane Analyzer — Versão 1.0

Este guia reúne o catálogo canônico de sistemas de equações diferenciais ordinárias bidimensionais ($2 \times 2$) implementados no **Phase Plane Analyzer**. Cada modelo conta com a respectiva formulação matemática, roteiro didático de experimentação no plano de fase e referências conceituais clássicas.

---

# I. Biomatemática & Dinâmica Populacional

---

### 1. Modelo Predador-Presa (Lotka-Volterra)

#### Formulação Matemática e Parâmetros
O modelo clássico de Lotka e Volterra descreve a interação de predação entre duas espécies em um ambiente homogêneo, desconsiderando a capacidade de suporte ambiental para as presas:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

* **Variáveis de Estado:**
  * $x(t) \ge 0$: Densidade da população de presas.
  * $y(t) \ge 0$: Densidade da população de predadores.
* **Parâmetros Típicos:**
  * $\alpha = 1.0$: Taxa intrínseca de crescimento da presa na ausência de predadores.
  * $\beta = 0.5$: Taxa de predação (frequência de captura e consumo).
  * $\delta = 0.5$: Eficiência de conversão biológica de presas consumidas em novos predadores.
  * $\gamma = 1.0$: Taxa de mortalidade per capita dos predadores.
* **Pontos de Equilíbrio e Invariante:**
  * $(0, 0)$: Ponto de sela instável.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Centro conservativo linearmente neutro.
  * Possui uma integral primeira conservada: $V(x, y) = \delta x - \gamma \ln(x) + \beta y - \alpha \ln(y) = \text{constante}$.

#### Roteiro Prático de Exploração
1. **Nulclinas:** Note as nulclinas ortogonais: $\frac{dx}{dt} = 0 \implies x = 0$ ou $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ ou $x = 2.0$.
2. **Órbitas Fechadas:** Clique em condições iniciais a diferentes distâncias de $(2.0, 2.0)$ (ex: $(2.5, 2.0)$, $(3.5, 2.0)$, $(5.0, 2.0)$). Observe que todas as soluções formam ciclos fechados não periódicos por atrator, mas governados pela energia conservada $V(x, y)$.
3. **Série Temporal:** Abra a janela de *Soluções vs Tempo* e constate o clássico descompasso de fase de um quarto de ciclo entre o pico de presas e o subsequente pico de predadores.

#### Referências Conceituais
* Lotka, A. J. (1925). *Elements of Physical Biology*. Williams & Wilkins, Baltimore.
* Volterra, V. (1926). *Variazioni e fluttuazioni del numero d'individui in specie animali conviventi*. Mem. Accad. Lincei, 2, 31-113.
* Murray, J. D. (2002). *Mathematical Biology: I. An Introduction* (3rd ed.). Springer.

---

### 2. Competição entre Duas Espécies (Murray)

#### Formulação Matemática e Parâmetros
Descreve a dinâmica de duas espécies biológicas disputando os mesmos nichos ou recursos alimentares, incorporando crescimento logístico para ambas:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

* **Variáveis de Estado:**
  * $x(t) \ge 0$: Densidade da espécie concorrente 1.
  * $y(t) \ge 0$: Densidade da espécie concorrente 2.
* **Parâmetros Típicos:**
  * $r_1 = 1.0, r_2 = 1.0$: Taxas intrínsecas de crescimento per capita.
  * $K_1 = 3.0, K_2 = 3.0$: Capacidades de suporte individuais do habitat.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coeficientes de inibição interespecífica recíproca.
* **Fenomenologia:**
  * Quando $\alpha_{12} < 1$ e $\alpha_{21} < 1$, o sistema admite **coexistência estável** em um nó atrator no interior do primeiro quadrante.

#### Roteiro Prático de Exploração
1. **Interseção das Nulclinas:** As nulclinas lineares inclinadas dividem o quadrante em setores direcionais convergentes.
2. **Bacias de Atração:** Clique em pontos próximos aos eixos coordenados e observe a convergência inexorável para o equilíbrio de convivência estável $(x^*, y^*)$.
3. **Exclusão Competitiva:** Altere os parâmetros via *Parameter Studio* para $\alpha_{12} = 1.5$ e $\alpha_{21} = 1.3$ e clique em **Traçar Separatrizes** para visualizar a bacia dividida por uma sela instável.

#### Referências Conceituais
* Murray, J. D. (2002). *Mathematical Biology: I. An Introduction*. Springer-Verlag.
* Begon, M., Townsend, C. R., & Harper, J. L. (2006). *Ecology: From Individuals to Ecosystems*. Blackwell Publishing.

---

### 3. Modelo Neuronal de FitzHugh-Nagumo

#### Formulação Matemática e Parâmetros
Redução planar canônica do modelo biofísico de quatro variáveis de Hodgkin-Huxley para a propagação de potenciais de ação na membrana axonal:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

* **Variáveis de Estado:**
  * $v(t)$: Potencial de membrana neuronal (variável rápida de ativação).
  * $w(t)$: Variável de recuperação de condutância iônica de potássio (variável lenta).
* **Parâmetros Típicos:**
  * $a = 0.7, b = 0.8$: Parâmetros de cinética da recuperação transmembrana.
  * $\epsilon = 0.08$: Separação de escalas temporais lenta/rápida ($\epsilon \ll 1$).
  * $I_{\text{ext}} = 0.5$: Corrente de estímulo elétrico externo injetada.
* **Dinâmica Excitável:** A nulclina cúbica de $v$ combinada com o relaxamento linear de $w$ produz oscilações por disparo (*spiking*) ou excitabilidade limiar.

#### Roteiro Prático de Exploração
1. **Equilíbrio em Repouso ($I_{\text{ext}} = 0$):** Inspecione o equilíbrio único na haste esquerda da cúbica; observe que perturbações pequenas amortecem, mas perturbações que superam o joelho da cúbica dão um disparo amplo no plano antes de retornar.
2. **Ciclo-Limite Auto-Sustentado ($I_{\text{ext}} = 0.5$):** Veja a perda de estabilidade do ponto de repouso por uma **Bifurcação de Hopf**. Todas as órbitas são atraídas para um ciclo-limite de relaxamento.
3. **Trajetórias Rápidas e Lentas:** Observe o paralelismo quase horizontal das trajetórias fora da cúbica (fase rápida) versus a aderência lenta ao longo da cúbica (fase refratária).

#### Referências Conceituais
* FitzHugh, R. (1961). *Impulses and physiological states in theoretical models of nerve membrane*. Biophysical Journal, 1(6), 445-466.
* Nagumo, J., Arimoto, S., & Yoshizawa, S. (1962). *An active pulse transmission line simulating nerve axon*. Proc. IRE, 50(10), 2061-2070.
* Izhikevich, E. M. (2007). *Dynamical Systems in Neuroscience*. MIT Press.

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulação Matemática e Parâmetros
O modelo fundamental da epidemiologia teórica descreve a transmissão de patógenos com imunidade permanente pós-cura em população homogênea constante ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

* **Variáveis de Estado:**
  * $S(t) \in [0, 1]$: Fração da população suscetível à infecção.
  * $I(t) \in [0, 1]$: Fração da população infectada e transmissora ativa.
* **Parâmetros Típicos:**
  * $\beta = 0.5$: Taxa efetiva de transmissão por contato contagioso.
  * $\gamma = 0.2$: Taxa per capita de recuperação infecciosa ($\gamma = 1/\tau_{\text{inf}}$).
* **Limiar Crítico e Reprodução Básica:**
  $$R_0 = \frac{\beta}{\gamma} = \frac{0.5}{0.2} = 2.5, \quad S_c = \frac{1}{R_0} = \frac{\gamma}{\beta} = 0.4$$

#### Roteiro Prático de Exploração
1. **Nulclina Crítica Vertical:** Observe a nulclina verde vertical posicionada precisamente em $S = 0.4$.
2. **Pico Infeccioso:** Clique em uma condição epidêmica inicial com $S_0 > 0.4$ (ex: $S_0 = 0.95, I_0 = 0.02$). A curva sobe inclinada para a esquerda até tangenciar horizontalmente a reta $S = 0.4$, atingindo o ápice exato da contaminação.
3. **Fim da Epidemia sem Exaustão Total:** Constate que a curva desce assintoticamente até o eixo horizontal $I = 0$ em um valor $S_\infty > 0$. A epidemia cessa por quebra da densidade crítica de contatos e não pelo contágio da totalidade dos indivíduos.

#### Referências Conceituais
* Kermack, W. O., & McKendrick, A. G. (1927). *A contribution to the mathematical theory of epidemics*. Proc. R. Soc. Lond. A, 115(772), 700-721.
* Brauer, F., & Castillo-Chavez, C. (2012). *Mathematical Models in Population Biology and Epidemiology*. Springer.

---

# II. Mecânica Não Linear & Oscilações Físicas

---

### 5. Oscilador de Van der Pol

#### Formulação Matemática e Parâmetros
Desenvolvido originariamente para circuitos elétricos com triodos valvulados, o modelo exibe amortecimento não linear que atua como dreno de energia em amplitudes elevadas e como fonte amplificadora em amplitudes pequenas:

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

* **Variáveis de Estado:**
  * $x(t)$: Posição ou tensão elétrica normalizada.
  * $y(t)$: Velocidade ou derivada temporal $\dot{x}$.
* **Parâmetros Típicos:**
  * $\mu = 1.0$: Coeficiente de amortecimento não linear dependente da amplitude.
* **Fenomenologia Geométrica:**
  * O ponto de equilíbrio na origem $(0, 0)$ é um foco instável.
  * Existe um **ciclo-limite atrator estável** isolado no plano que absorve todas as soluções não nulas.

#### Roteiro Prático de Exploração
1. **Atração Global:** Clique dentro do ciclo (ex: $(0.1, 0.1)$) e fora do ciclo (ex: $(3.0, 3.0)$). Verifique que ambas as espirais convergem assintoticamente para a mesma curva fechada invariante.
2. **Oscilações de Relaxamento:** Aumente $\mu$ para $3.0$ ou $5.0$ no *Parameter Studio*. O ciclo perde a sua simetria quase elíptica e desenvolve saltos ultrarrápidos alternados com deslizamentos lentos ao longo dos ramos da curva $y = \frac{x - x^3/3}{\mu}$.

#### Referências Conceituais
* Van der Pol, B. (1926). *On relaxation-oscillations*. The London, Edinburgh, and Dublin Philosophical Magazine and Journal of Science, 2(11), 978-992.
* Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 6. Pêndulo Simples Não-Linear Amortecido

#### Formulação Matemática e Parâmetros
Modela as oscilações mecânicas de uma haste rígida com massa concentrada na ponta sem a aproximação linear para pequenos ângulos ($\sin \theta \not\approx \theta$):

$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

* **Variáveis de Estado:**
  * $\theta(t)$: Posição angular da haste em radianos.
  * $\omega(t)$: Velocidade angular instantânea ($d\theta/dt$).
* **Parâmetros Típicos:**
  * $\frac{g}{L} = 1.0$: Frequência natural característica normalizada.
  * $\gamma = 0.2$: Coeficiente de fricção viscosa do ar ou pivô.
* **Estrutura Topológica:**
  * Família periódica de equilíbrios estáveis em $(2k\pi, 0)$ (focos atratores estáveis — pêndulo em repouso vertical para baixo).
  * Família de pontos de sela em $((2k+1)\pi, 0)$ (haste invertida para cima instável).

#### Roteiro Prático de Exploração
1. **Separatrizes Homoclínicas e Heteroclínicas:** Ajuste $\gamma = 0$ temporariamente e clique em **Traçar Separatrizes** para ver a curva que divide o regime de oscilação em vaivém do regime de revoluções completas contínuas.
2. **Bacias de Oscilação Amortecida:** Com $\gamma = 0.2$, clique em velocidades angulares elevadas ($\omega_0 = 2.5$) e acompanhe o pêndulo dando várias rotações antes de ser capturado pelo atrator local mais próximo.

#### Referências Conceituais
* Marion, J. B., & Thornton, S. T. (2004). *Classical Dynamics of Particles and Systems*. Brooks/Cole.
* Hirsch, M. W., Smale, S., & Devaney, R. L. (2013). *Differential Equations, Dynamical Systems, and an Introduction to Chaos*. Academic Press.

---

### 7. Oscilador de Duffing Autônomo

#### Formulação Matemática e Parâmetros
Representa uma viga elástica deformável submetida a forças mecânicas com potencial de duplo poço simétrico:

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = - \delta y - \beta x - \alpha x^3$$

* **Variáveis de Estado:**
  * $x(t)$: Posição de deflexão lateral da viga.
  * $y(t)$: Velocidade mecânica instantânea.
* **Parâmetros Típicos:**
  * $\delta = 0.2$: Amortecimento viscoso estrutural.
  * $\beta = -1.0$: Termo linear de rigidez negativa (causa instabilidade no repouso central).
  * $\alpha = 1.0$: Termo cúbico restaurador não linear.
* **Topologia de Duplo Poço:**
  * A origem $(0, 0)$ é um ponto de sela.
  * Os dois fundos dos poços laterais em $(\pm 1, 0)$ são focos estáveis.

#### Roteiro Prático de Exploração
1. **Fronteira Frágil das Bacias:** Clique em **Traçar Separatrizes** sobre a sela $(0, 0)$. As variedades estáveis enrolam-se no plano desenhando uma fronteira delicada entre os dois poços.
2. **Sensibilidade às Condições Iniciais:** Lance dois pontos iniciais quase idênticos ao redor da origem e comprove que trajetórias vizinhas podem convergir para poços de atração opostos.

#### Referências Conceituais
* Duffing, G. (1918). *Erzwungene Schwingungen bei veränderlicher Eigenfrequenz*. Vieweg, Braunschweig.
* Guckenheimer, J., & Holmes, P. (1983). *Nonlinear Oscillations, Dynamical Systems, and Bifurcations of Vector Fields*. Springer-Verlag.

---

# III. Fundamentos Matemáticos e Teoria Qualitativa

---

### 8. Pplane Clássico (Polking)

#### Formulação Matemática e Parâmetros
O modelo emblemático que acompanhou historicamente o utilitário MATLAB `pplane` de John Polking, projetado intencionalmente para ilustrar múltiplos tipos de equilíbrios e separatrizes concorrentes em uma única tela:

$$\frac{dx}{dt} = 2x - y + 3(x^2 - y^2) + 2xy$$
$$\frac{dy}{dt} = x - 3y - 3(x^2 - y^2) + xy$$

* **Variáveis de Estado:**
  * $x, y \in \mathbb{R}$: Coordenadas cartesianas no plano de fase.
* **Fenomenologia Geométrica:**
  * Apresenta múltiplos equilíbrios isolados em uma janela planar compacta (nós, selas e focos simultâneos).
  * Conexões ricas de separatrizes que demonstram a subdivisão completa do plano em regiões dinamicamente disjuntas.

#### Roteiro Prático de Exploração
1. **Varredura com Localizar Equilíbrios:** Pressione **Localizar Equilíbrios** e clique em cada ponto destacado para verificar o autovalor e a classificação jacobiana local.
2. **Mapeamento de Separatrizes:** Trace as variedades estáveis e instáveis das selas presentes no sistema para construir a estrutura global do campo vetorial.

#### Referências Conceituais
* Polking, J. C. (1995). *Ordinary Differential Equations using MATLAB*. Prentice Hall.
* Boyce, W. E., DiPrima, R. C., & Meade, D. B. (2021). *Elementary Differential Equations and Boundary Value Problems*. Wiley.

---

### 9. Sistema Linear de Referência e Classificação Espectral

#### Formulação Matemática e Parâmetros
O arcabouço canônico de sistemas lineares invariantes no tempo com coeficientes matriciais constantes:

$$\begin{pmatrix} \dot{x} \\ \dot{y} \end{pmatrix} = \begin{pmatrix} a & b \\ c & d \end{pmatrix} \begin{pmatrix} x \\ y \end{pmatrix}$$

$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

* **Parâmetros Típicos de Entrada:**
  * $a = 0.0, b = 1.0, c = -2.0, d = -0.5$.
* **Classificação Espectral segundo Traço ($\tau$) e Determinante ($\Delta$):**
  * Equação característica: $\lambda^2 - \tau \lambda + \Delta = 0$, onde $\tau = a + d$ e $\Delta = ad - bc$.
  * Discriminante: $\Lambda = \tau^2 - 4\Delta$.
  * $\Delta < 0$: **Ponto de Sela** (sempre instável).
  * $\Delta > 0, \Lambda > 0$: **Nó** (estável se $\tau < 0$, instável se $\tau > 0$).
  * $\Delta > 0, \Lambda < 0$: **Foco / Espiral** (atrator se $\tau < 0$, repulsor se $\tau > 0$, **Centro** se $\tau = 0$).

#### Roteiro Prático de Exploração
1. **Inspeção de Autovalores:** Utilize o botão **Inspecionar Equilíbrio** e clique na origem para inspecionar a matriz Jacobiana calculada, os autovalores $\lambda_1, \lambda_2$ e os autovetores direcionais.
2. **Exploração no Parameter Studio:** Abra os *sliders* e varie o coeficiente $d$ mantendo os demais constantes. Observe a transição contínua e visual de foco estável $\to$ centro $\to$ foco instável conforme o traço cruza o zero.

#### Referências Conceituais
* Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos: With Applications to Physics, Biology, Chemistry, and Engineering*. CRC Press.
* Perko, L. (2001). *Differential Equations and Dynamical Systems* (3rd ed.). Springer-Verlag.
---

### 10. Dinâmica Não-Linear de Polking (Múltiplos Equilíbrios)

#### Formulação Matemática e Parâmetros
O clássico sistema polinomial imortalizado por John Polking no PPLANE original da Rice University, ideal para observar separatrizes competindo por bacias de atração distintas:

$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$

* **Variáveis de Estado:**
  * $x(t)$: Primeira coordenada de fase.
  * $y(t)$: Segunda coordenada de fase.
* **Pontos de Equilíbrio:**
  * $(0, 0)$: Ponto crítico degenerado na origem.
  * $(2, 2)$ e $(-2, 2)$: Pontos de sela simétricos com separatrizes bem delineadas.
  * $(0, 2)$: Equilíbrio com convergência assintótica vertical.
* **Fenomenologia:**
  * Apresenta coexistência de 4 equilíbrios no plano, gerando um mapa rico de separatrizes onde trajetórias vizinhas tomam destinos diametralmente opostos.

---

### 11. Dinâmica do Amor (Romeu & Julieta de Strogatz)

#### Formulação Matemática e Parâmetros
Formalizado pelo matemático Steven Strogatz (*Nonlinear Dynamics and Chaos*), este modelo mapeia a dinâmica afetiva de um casal através de um sistema linear homogêneo:

$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

* **Variáveis de Estado:**
  * $x(t)$: Nível de afeto de Romeu por Julieta ($x > 0$: paixão arrebatadora; $x < 0$: ranço absoluto).
  * $y(t)$: Nível de afeto de Julieta por Romeu ($y > 0$: amor correspondido; $y < 0$: indiferença/desprezo).
* **Parâmetros Pré-configurados (Caso das Almas Volúveis):**
  * $a = -0.1$: Amor próprio decadente de Romeu.
  * $b = 1.0$: Julieta aquece o coração de Romeu com facilidade.
  * $c = -1.0$: Julieta se assusta com a insistência de Romeu e se afasta.
  * $d = -0.1$: Tendência natural de Julieta à indiferença com o tempo.
* **Fenomenologia e Advertência Amorosa:**
  * Com autovalores complexos conjugados de parte real negativa, o sistema forma uma espiral estável em direção à origem $(0, 0)$: a trágica e monótona apatia emocional mútua.
  * Em configurações não amortecidas, o amor vira uma órbita periódica fechada (centro): quando Romeu ama, Julieta foge; quando Romeu desiste, Julieta volta a se apaixonar — um ciclo infinito de desencontros amorosos matematicamente inevitáveis.
