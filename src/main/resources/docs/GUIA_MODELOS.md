# Guia Didático de Modelos Dinâmicos Bidimensionais
#### Phase Plane Analyzer — Versão 1.3

Este guia reúne o catálogo canônico de sistemas de equações diferenciais ordinárias bidimensionais ($2 \times 
2$) implementados no **Phase Plane Analyzer**. Cada modelo conta com a respectiva formulação matemática, rotei
ro didático de experimentação no plano de fase e referências conceituais clássicas.

### Criando Seus Próprios Modelos (Sintaxe Matemática)

Ao utilizar a aba **Meus Modelos** na Galeria, você pode definir seus próprios sistemas de Equações Diferencia
is Ordinárias (EDOs). O pplaneX utiliza um avaliador matemático rigoroso, portanto, é fundamental seguir as re
gras de sintaxe abaixo:

- **Multiplicação Explícita (Muito Importante!):** Diferente da escrita matemática no papel, o computador exig
e o asterisco `*` para qualquer multiplicação.
  * [Incorreto]: `2x` ou `x(1-y)`
  * [Correto]: `2*x` ou `x*(1-y)`
- **Operadores Básicos:** 
  * `+` (soma), `-` (subtração)
  * `*` (multiplicação), `/` (divisão)
  * `^` (potência, ex: `x^2`)
- **Funções Suportadas:**
  * **Trigonométricas:** `sin()`, `cos()`, `tan()`
  * **Exponenciais e Logarítmicas:** `exp()` (base *e*), `log()` ou `ln()` (logaritmo natural), `log10()` (bas
e 10)
  * **Outras:** `sqrt()` (raiz quadrada), `abs()` (valor absoluto)

**Exemplo prático de digitação:**
Para inserir o sistema $x' = -y + x(1 - x^2 - y^2)$, você deve digitar no campo dx/dt:
`-y + x*(1 - x^2 - y^2)`

---

---
# I. Biomatemática & Dinâmica Populacional

---

### 1. Modelo Predador-Presa (Lotka-Volterra)

#### Formulação Matemática e Parâmetros
O modelo clássico de Lotka e Volterra descreve a interação de predação entre duas espécies em um ambiente homo
gêneo, desconsiderando a capacidade de suporte ambiental para as presas:

$$\frac{dx}{dt} = \alpha x - \beta x y = x(\alpha - \beta y)$$
$$\frac{dy}{dt} = \delta x y - \gamma y = y(\delta x - \gamma)$$

- **Variáveis de Estado:**
  * $x(t) \ge 0$: Densidade da população de presas.
  * $y(t) \ge 0$: Densidade da população de predadores.
- **Parâmetros Típicos:**
  * $\alpha = 1.0$: Taxa intrínseca de crescimento da presa na ausência de predadores.
  * $\beta = 0.5$: Taxa de predação (frequência de captura e consumo).
  * $\delta = 0.5$: Eficiência de conversão biológica de presas consumidas em novos predadores.
  * $\gamma = 1.0$: Taxa de mortalidade per capita dos predadores.
- **Pontos de Equilíbrio e Invariante:**
  * $(0, 0)$: Ponto de sela instável.
  * $(x^*, y^*) = \left(\frac{\gamma}{\delta}, \frac{\alpha}{\beta}\right) = (2.0, 2.0)$: Centro conservativo 
linearmente neutro.
  * Possui uma integral primeira conservada: $V(x, y) = \delta x - \gamma \ln(x) + \beta y - \alpha \ln(y) = \text{constante}$.

#### Roteiro Prático de Exploração
1. **Nulclinas:** Note as nulclinas ortogonais: $\frac{dx}{dt} = 0 \implies x = 0$ ou $y = 2.0$; $\frac{dy}{dt} = 0 \implies y = 0$ ou $x = 2.0$.
2. **Órbitas Fechadas:** Clique em condições iniciais a diferentes distâncias de $(2.0, 2.0)$ (ex: $(2.5, 2.0)
$, $(3.5, 2.0)$, $(5.0, 2.0)$). Observe que todas as soluções formam ciclos fechados não periódicos por atrato
r, mas governados pela energia conservada $V(x, y)$.
3. **Série Temporal:** Abra a janela de *Soluções vs Tempo* e constate o clássico descompasso de fase de um qu
arto de ciclo entre o pico de presas e o subsequente pico de predadores.

#### Referências Conceituais
- Lotka, A. J. (1925). *Elements of Physical Biology*. Williams & Wilkins, Baltimore.
- Volterra, V. (1926). *Variazioni e fluttuazioni del numero d'individui in specie animali conviventi*. Mem. A
ccad. Lincei, 2, 31-113.
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction* (3rd ed.). Springer.

---

### 2. Competição entre Duas Espécies (Murray)

#### Formulação Matemática e Parâmetros
Descreve a dinâmica de duas espécies biológicas disputando os mesmos nichos ou recursos alimentares, incorpora
ndo crescimento logístico para ambas:

$$\frac{dx}{dt} = r_1 x \left(1 - \frac{x}{K_1} - \alpha_{12} \frac{y}{K_1}\right)$$
$$\frac{dy}{dt} = r_2 y \left(1 - \frac{y}{K_2} - \alpha_{21} \frac{x}{K_2}\right)$$

- **Variáveis de Estado:**
  * $x(t) \ge 0$: Densidade da espécie concorrente 1.
  * $y(t) \ge 0$: Densidade da espécie concorrente 2.
- **Parâmetros Típicos:**
  * $r_1 = 1.0, r_2 = 1.0$: Taxas intrínsecas de crescimento per capita.
  * $K_1 = 3.0, K_2 = 3.0$: Capacidades de suporte individuais do habitat.
  * $\alpha_{12} = 0.5, \alpha_{21} = 0.7$: Coeficientes de inibição interespecífica recíproca.
- **Fenomenologia:**
  * Quando $\alpha_{12} < 1$ e $\alpha_{21} < 1$, o sistema admite **coexistência estável** em um nó atrator n
o interior do primeiro quadrante.

#### Roteiro Prático de Exploração
1. **Interseção das Nulclinas:** As nulclinas lineares inclinadas dividem o quadrante em setores direcionais c
onvergentes.
2. **Bacias de Atração:** Clique em pontos próximos aos eixos coordenados e observe a convergência inexorável 
para o equilíbrio de convivência estável $(x^*, y^*)$.
3. **Exclusão Competitiva:** Altere os parâmetros via *Parameter Studio* para $\alpha_{12} = 1.5$ e $\alpha_{2
1} = 1.3$ e clique em **Traçar Separatrizes** para visualizar a bacia dividida por uma sela instável.

#### Referências Conceituais
- Murray, J. D. (2002). *Mathematical Biology: I. An Introduction*. Springer-Verlag.
- Begon, M., Townsend, C. R., & Harper, J. L. (2006). *Ecology: From Individuals to Ecosystems*. Blackwell Pub
lishing.

---

### 3. Modelo Neuronal de FitzHugh-Nagumo

#### Formulação Matemática e Parâmetros
Redução planar canônica do modelo biofísico de quatro variáveis de Hodgkin-Huxley para a propagação de potenci
ais de ação na membrana axonal:

$$\frac{dv}{dt} = v - \frac{v^3}{3} - w + I_{\text{ext}}$$
$$\frac{dw}{dt} = \epsilon (v + a - b w)$$

- **Variáveis de Estado:**
  * $v(t)$: Potencial de membrana neuronal (variável rápida de ativação).
  * $w(t)$: Variável de recuperação de condutância iônica de potássio (variável lenta).
- **Parâmetros Típicos:**
  * $a = 0.7, b = 0.8$: Parâmetros de cinética da recuperação transmembrana.
  * $\epsilon = 0.08$: Separação de escalas temporais lenta/rápida ($\epsilon \ll 1$).
  * $I_{\text{ext}} = 0.5$: Corrente de estímulo elétrico externo injetada.
- **Dinâmica Excitável:** A nulclina cúbica de $v$ combinada com o relaxamento linear de $w$ produz oscilações
 por disparo (*spiking*) ou excitabilidade limiar.

#### Roteiro Prático de Exploração
1. **Equilíbrio em Repouso ($I_{\text{ext}} = 0$):** Inspecione o equilíbrio único na haste esquerda da cúbica
; observe que perturbações pequenas amortecem, mas perturbações que superam o joelho da cúbica dão um disparo 
amplo no plano antes de retornar.
2. **Ciclo-Limite Auto-Sustentado ($I_{\text{ext}} = 0.5$):** Veja a perda de estabilidade do ponto de repouso
 por uma **Bifurcação de Hopf**. Todas as órbitas são atraídas para um ciclo-limite de relaxamento.
3. **Trajetórias Rápidas e Lentas:** Observe o paralelismo quase horizontal das trajetórias fora da cúbica (fa
se rápida) versus a aderência lenta ao longo da cúbica (fase refratária).

#### Referências Conceituais
- FitzHugh, R. (1961). *Impulses and physiological states in theoretical models of nerve membrane*. Biophysica
l Journal, 1(6), 445-466.
- Nagumo, J., Arimoto, S., & Yoshizawa, S. (1962). *An active pulse transmission line simulating nerve axon*. 
Proc. IRE, 50(10), 2061-2070.
- Izhikevich, E. M. (2007). *Dynamical Systems in Neuroscience*. MIT Press.

---

### 4. Epidemia SIR (Kermack-McKendrick)

#### Formulação Matemática e Parâmetros
O modelo fundamental da epidemiologia teórica descreve a transmissão de patógenos com imunidade permanente pós
-cura em população homogênea constante ($S + I + R = 1$):

$$\frac{dS}{dt} = -\beta S I$$
$$\frac{dI}{dt} = \beta S I - \gamma I = I (\beta S - \gamma)$$

- **Variáveis de Estado:**
  * $S(t) \in [0, 1]$: Fração da população suscetível à infecção.
  * $I(t) \in [0, 1]$: Fração da população infectada e transmissora ativa.
- **Parâmetros Típicos:**
  * $\beta = 0.5$: Taxa efetiva de transmissão por contato contagioso.
  * $\gamma = 0.2$: Taxa per capita de recuperação infecciosa ($\gamma = 1/\tau_{\text{inf}}$).
- **Limiar Crítico e Reprodução Básica:**
  $$R_0 = \frac{\beta}{\gamma} = \frac{0.5}{0.2} = 2.5, \quad S_c = \frac{1}{R_0} = \frac{\gamma}{\beta} = 0.4$$

#### Roteiro Prático de Exploração
1. **Nulclina Crítica Vertical:** Observe a nulclina verde vertical posicionada precisamente em $S = 0.4$.
2. **Pico Infeccioso:** Clique em uma condição epidêmica inicial com $S_0 > 0.4$ (ex: $S_0 = 0.95, I_0 = 0.02$
). A curva sobe inclinada para a esquerda até tangenciar horizontalmente a reta $S = 0.4$, atingindo o ápice e
xato da contaminação.
3. **Fim da Epidemia sem Exaustão Total:** Constate que a curva desce assintoticamente até o eixo horizontal $
I = 0$ em um valor $S_\infty > 0$. A epidemia cessa por quebra da densidade crítica de contatos e não pelo con
tágio da totalidade dos indivíduos.

#### Referências Conceituais
- Kermack, W. O., & McKendrick, A. G. (1927). *A contribution to the mathematical theory of epidemics*. Proc. 
R. Soc. Lond. A, 115(772), 700-721.
- Brauer, F., & Castillo-Chavez, C. (2012). *Mathematical Models in Population Biology and Epidemiology*. Spri
nger.

---

# II. Mecânica Não Linear & Oscilações Físicas

---

### 5. Oscilador de Van der Pol

#### Formulação Matemática e Parâmetros
Desenvolvido originariamente para circuitos elétricos com triodos valvulados, o modelo exibe amortecimento não
 linear que atua como dreno de energia em amplitudes elevadas e como fonte amplificadora em amplitudes pequena
s:

$$\frac{dx}{dt} = y$$
$$\frac{dy}{dt} = \mu (1 - x^2) y - x$$

- **Variáveis de Estado:**
  * $x(t)$: Posição ou tensão elétrica normalizada.
  * $y(t)$: Velocidade ou derivada temporal $\dot{x}$.
- **Parâmetros Típicos:**
  * $\mu = 1.0$: Coeficiente de amortecimento não linear dependente da amplitude.
- **Fenomenologia Geométrica:**
  * O ponto de equilíbrio na origem $(0, 0)$ é um foco instável.
  * Existe um **ciclo-limite atrator estável** isolado no plano que absorve todas as soluções não nulas.

#### Roteiro Prático de Exploração
1. **Atração Global:** Clique dentro do ciclo (ex: $(0.1, 0.1)$) e fora do ciclo (ex: $(3.0, 3.0)$). Verifique
 que ambas as espirais convergem assintoticamente para a mesma curva fechada invariante.
2. **Oscilações de Relaxamento:** Aumente $\mu$ para $3.0$ ou $5.0$ no *Parameter Studio*. O ciclo perde a sua
 simetria quase elíptica e desenvolve saltos ultrarrápidos alternados com deslizamentos lentos ao longo dos ra
mos da curva $y = \frac{x - x^3/3}{\mu}$.

#### Referências Conceituais
- Van der Pol, B. (1926). *On relaxation-oscillations*. The London, Edinburgh, and Dublin Philosophical Magazi
ne and Journal of Science, 2(11), 978-992.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 6. Pêndulo Simples Não-Linear Amortecido

#### Formulação Matemática e Parâmetros
Modela as oscilações mecânicas de uma haste rígida com massa concentrada na ponta sem a aproximação linear par
a pequenos ângulos ($\sin \theta \not\approx \theta$):

$$\frac{d\theta}{dt} = \omega$$
$$\frac{d\omega}{dt} = -\frac{g}{L} \sin(\theta) - \gamma \omega$$

- **Variáveis de Estado:**
  * $\theta(t)$: Posição angular da haste em radianos.
  * $\omega(t)$: Velocidade angular instantânea ($d\theta/dt$).
- **Parâmetros Típicos:**
  * $\frac{g}{L} = 1.0$: Frequência natural característica normalizada.
  * $\gamma = 0.2$: Coeficiente de fricção viscosa do ar ou pivô.
- **Estrutura Topológica:**
  * Família periódica de equilíbrios estáveis em $(2k\pi, 0)$ (focos atratores estáveis — pêndulo em repouso v
ertical para baixo).
  * Família de pontos de sela em $((2k+1)\pi, 0)$ (haste invertida para cima instável).

#### Roteiro Prático de Exploração
1. **Separatrizes Homoclínicas e Heteroclínicas:** Ajuste $\gamma = 0$ temporariamente e clique em **Traçar Se
paratrizes** para ver a curva que divide o regime de oscilação em vaivém do regime de revoluções completas con
tínuas.
2. **Bacias de Oscilação Amortecida:** Com $\gamma = 0.2$, clique em velocidades angulares elevadas ($\omega_0
 = 2.5$) e acompanhe o pêndulo dando várias rotações antes de ser capturado pelo atrator local mais próximo.

#### Referências Conceituais
- Marion, J. B., & Thornton, S. T. (2004). *Classical Dynamics of Particles and Systems*. Brooks/Cole.
- Hirsch, M. W., Smale, S., & Devaney, R. L. (2013). *Differential Equations, Dynamical Systems, and an Introd
uction to Chaos*. Academic Press.

---

# III. Finanças, Clima & Dinâmica Não Linear

---

### 7. Ciclo Econômico Não Linear de Kaldor

#### Formulação Matemática e Parâmetros
Modelo macroeconômico seminal que endogeniza os ciclos de negócios (expansão e recessão) através de uma função
 de investimento não linear (sigmoide) acoplada ao estoque de capital:

$$\frac{dY}{dt} = \alpha (\tanh(Y) - s Y - \gamma K)$$
$$\frac{dK}{dt} = s Y - \delta K$$

- **Variáveis de Estado:**
  * $Y(t)$: Renda nacional ou produto (desvios em relação ao equilíbrio).
  * $K(t)$: Estoque de capital acumulado.
- **Parâmetros Típicos:**
  * $\alpha = 1.2$: Velocidade de ajuste do mercado de bens.
  * $\gamma = 0.8$: Sensibilidade do investimento ao estoque de capital.
  * $s = 0.3$: Propensão marginal a poupar.
  * $\delta = 0.1$: Taxa de depreciação do capital.
- **Fenomenologia Geométrica:**
  * O sistema exibe um ciclo-limite atrator que representa as flutuações endógenas persistentes da economia, n
ascendo tipicamente de uma bifurcação de Hopf quando os parâmetros de ajuste econômico cruzam limites críticos
.

#### Roteiro Prático de Exploração
1. **Atração Cíclica:** Clique em qualquer ponto inicial com capital ou renda extremos (ex: $Y = 3.0, K = -2.0
$). Observe como a trajetória espirala em direção a uma órbita fechada bem definida, mostrando que tanto booms
 irreais quanto depressões severas tendem a estabilizar no ciclo natural.
2. **Nulclina em S:** Observe o formato em "S" da nulclina de $Y$ (derivada de $\tanh(Y)$). É essa não lineari
dade geométrica que impede que a economia repouse no centro e força as oscilações contínuas de relaxamento.

#### Referências Conceituais
- Kaldor, N. (1940). *A Model of the Trade Cycle*. The Economic Journal, 50(197), 78-92.
- Chang, W. W., & Smyth, D. J. (1971). *The existence and persistence of cycles in a non-linear model: Kaldor'
s 1940 model re-examined*. The Review of Economic Studies.

---

### 8. Circulação Termoalina de Stommel (Modelo de Duas Caixas)

#### Formulação Matemática e Parâmetros
Um modelo fundamental em oceanografia física que descreve a dinâmica de troca de águas entre a região equatori
al (quente/salgada) e a região polar (fria/doce), demonstrando a possibilidade de múltiplos equilíbrios climát
icos globais:

$$\frac{dx}{dt} = \eta_1 - x - x|x - y|$$
$$\frac{dy}{dt} = \eta_2 - y - y|x - y|$$

- **Variáveis de Estado:**
  * $x(t)$: Diferença de temperatura adimensional entre as caixas equatorial e polar.
  * $y(t)$: Diferença de salinidade adimensional entre as caixas.
- **Parâmetros Típicos:**
  * $\eta_1 = 3.0$: Forçamento térmico superficial (radiação solar).
  * $\eta_2 = 1.2$: Forçamento halino superficial (evaporação/precipitação).
- **Múltiplos Equilíbrios (Biestabilidade):**
  * O sistema pode admitir até três pontos de equilíbrio, sendo dois estáveis (nós) separados por um instável 
(sela).  
  * Os nós representam dois regimes climáticos distintos: um fluxo termoalino intenso (dominado pela temperatu
ra) e um fluxo reverso ou lento (dominado pela salinidade).

#### Roteiro Prático de Exploração
1. **Concorrência de Bacias:** Utilize a ferramenta de **Traçar Separatrizes** sobre o ponto de sela central. 
Você visualizará a fronteira exata que divide o oceano entre o estado de circulação térmica atual (águas polar
es afundando) e o estado revertido.
2. **Perturbação Climática:** Clique em pontos logo acima e logo abaixo da separatriz para ver como mudanças m
uito sutis nas condições iniciais (como um influxo abrupto de água doce) levam o sistema para equilíbrios clim
áticos completamente diferentes e distantes.

#### Referências Conceituais
- Stommel, H. (1961). *Thermohaline convection with two stable regimes of flow*. Tellus, 13(2), 224-230.
- Dijkstra, H. A. (2005). *Nonlinear Physical Oceanography*. Springer.

---

# IV. Fundamentos Matemáticos e Teoria Qualitativa

---

### 9. Sistema Linear de Referência e Classificação Espectral

#### Formulação Matemática e Parâmetros
O arcabouço canônico de sistemas lineares invariantes no tempo com coeficientes matriciais constantes:

$$\begin{pmatrix} \dot{x} \\ \dot{y} \end{pmatrix} = \begin{pmatrix} a & b \\ c & d \end{pmatrix} \begin{pmatrix} x \\ y \end{pmatrix}$$

$$\frac{dx}{dt} = a x + b y$$
$$\frac{dy}{dt} = c x + d y$$

- **Parâmetros Típicos de Entrada:**
  * $a = 0.0, b = 1.0, c = -2.0, d = -0.5$.
- **Classificação Espectral segundo Traço ($\tau$) e Determinante ($\Delta$):**
  * Equação característica: $\lambda^2 - \tau \lambda + \Delta = 0$, onde $\tau = a + d$ e $\Delta = ad - bc$.
  * Discriminante: $\Lambda = \tau^2 - 4\Delta$.
  * $\Delta < 0$: **Ponto de Sela** (sempre instável).
  * $\Delta > 0, \Lambda > 0$: **Nó** (estável se $\tau < 0$, instável se $\tau > 0$).
  * $\Delta > 0, \Lambda < 0$: **Foco / Espiral** (atrator se $\tau < 0$, repulsor se $\tau > 0$, **Centro** s
e $\tau = 0$).

#### Roteiro Prático de Exploração
1. **Inspeção de Autovalores:** Utilize o botão **Inspecionar Equilíbrio** e clique na origem para inspecionar
 a matriz Jacobiana calculada, os autovalores $\lambda_1, \lambda_2$ e os autovetores direcionais.
2. **Exploração no Parameter Studio:** Abra os *sliders* e varie o coeficiente $d$ mantendo os demais constant
es. Observe a transição contínua e visual de foco estável $\to$ centro $\to$ foco instável conforme o traço cr
uza o zero.

#### Referências Conceituais
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos: With Applications to Physics, Biology, Chemistry, and
 Engineering*. CRC Press.
- Perko, L. (2001). *Differential Equations and Dynamical Systems* (3rd ed.). Springer-Verlag.

---

### 10. Dinâmica Não-Linear de Polking (Múltiplos Equilíbrios)

#### Formulação Matemática e Parâmetros
O clássico sistema polinomial imortalizado por John Polking no PPLANE original da Rice University, ideal para 
observar separatrizes competindo por bacias de atração distintas:

$$\frac{dx}{dt} = x^2 - y^2$$
$$\frac{dy}{dt} = x(2 - y)$$

- **Variáveis de Estado:**
  * $x(t)$: Primeira coordenada de fase.
  * $y(t)$: Segunda coordenada de fase.
- **Pontos de Equilíbrio:**
  * $(0, 0)$: Ponto crítico degenerado na origem.
  * $(2, 2)$ e $(-2, 2)$: Pontos de sela simétricos com separatrizes bem delineadas.
  * $(0, 2)$: Equilíbrio com convergência assintótica vertical.
- **Fenomenologia:**
  * Apresenta coexistência de 4 equilíbrios no plano, gerando um mapa rico de separatrizes onde trajetórias vi
zinhas tomam destinos diametralmente opostos.

---

### 11. Dinâmica do Amor (Romeu e Julieta)

#### Formulação Matemática e Parâmetros
Um sistema linear clássico introduzido por Steven Strogatz com um toque de drama e humor para o estudo de estabilidade. O modelo descreve a evolução da afeição mútua entre dois amantes:

$$\frac{dR}{dt} = a R + b J$$
$$\frac{dJ}{dt} = c R + d J$$

- **Variáveis de Estado:**
  * $R(t)$: Sentimentos de Romeu (valores positivos = amor, negativos = aversão).
  * $J(t)$: Sentimentos de Julieta.
- **Parâmetros Típicos (A Eterna Caçada):**
  * $a, d$: Parâmetros de autoaceitação (ego ou cautela).
  * $b, c$: Parâmetros de reação aos sentimentos do parceiro.
  * Com $a = 0, b = 1, c = -1, d = 0$: Romeu ama Julieta mais quanto mais ela foge, e Julieta perde o interesse quanto mais Romeu se aproxima.
- **Classificação Dinâmica:**
  * Esta configuração cria uma matriz com traço nulo e determinante positivo. Trata-se de um **Centro Puro**, garantindo que o romance seja ciclicamente trágico, sem nunca alcançar o equilíbrio estável ou o colapso total.

#### Roteiro Prático de Exploração
1. **O Ciclo Sem Fim:** Com a configuração padrão (0, 1, -1, 0), clique em qualquer lugar do plano e assista as órbitas formarem elipses perfeitas. A perseguição é eterna.
2. **Amor Platônico e Apatia:** Abra o *Parameter Studio* e mude a autoaceitação de ambos para valores ligeiramente negativos (ex: $a = -0.1$ e $d = -0.1$). Observe o sistema se tornar um foco atrator: após algumas idas e vindas amorosas, o casal colapsa inevitavelmente para a origem (apatia emocional mútua e irreversível).
3. **Mude a História:** Invente novas personalidades. O que acontece se ambos forem amantes cautelosos ($a < 0, d < 0$) mas reativos positivamente ($b > 0, c > 0$)?

#### Referências Conceituais
- Strogatz, S. H. (1988). *Love affairs and differential equations*. Mathematics Magazine, 61(1), 35.
- Strogatz, S. H. (2018). *Nonlinear Dynamics and Chaos* (2nd ed.). CRC Press.

---

### 12. Oscilador Químico Brusselator

#### Formulação Matemática e Parâmetros
Um modelo clássico em físico-química desenvolvido pela escola de Bruxelas (Prigogine e Lefever) para descrever a cinética de uma reação autocatalítica teórica que exibe oscilações sustentadas (quebra de simetria temporal e estruturas dissipativas):

$$\frac{dx}{dt} = A - (B + 1)x + x^2y$$
$$\frac{dy}{dt} = Bx - x^2y$$

- **Variáveis de Estado:**
  * $x(t), y(t)$: Concentrações dos reagentes intermediários da reação química.
- **Parâmetros Típicos:**
  * $A = 1.0$: Concentração constante do reagente de entrada.
  * $B = 3.0$: Constante de taxa associada à etapa autocatalítica.
- **Ponto de Equilíbrio Único e Bifurcação:**
  * O sistema admite um único equilíbrio estacionário localizado em $(x^*, y^*) = (A, B/A)$.
  * A estabilidade desse ponto muda drasticamente dependendo dos reagentes: ele é estável se $B < 1 + A^2$ e se torna instável (sofrendo uma **Bifurcação de Hopf Supercrítica**) quando $B > 1 + A^2$.

#### Roteiro Prático de Exploração
1. **Atração Oscilatória:** Com a configuração padrão ($A = 1, B = 3$), note que a condição de instabilidade ($3 > 1 + 1^2 = 2$) é satisfeita. Clique no plano e veja as concentrações $x$ e $y$ serem atraídas implacavelmente para um ciclo-limite em forma de gota. 
2. **Janela Temporal (Soluções vs Tempo):** Visualize os gráficos $x(t)$ e $y(t)$. Você notará picos oscilatórios bem definidos, caracterizando a natureza de "relógio químico" do sistema de reação-difusão.
3. **Transição de Fase:** No *Parameter Studio*, mantenha $A = 1$ e reduza $B$ lentamente para $1.5$. O ciclo-limite desaparecerá e a reação voltará a ter um fluxo de concentração estável (foco atrator em direção a $(1, 1.5)$).

#### Referências Conceituais
- Prigogine, I., & Lefever, R. (1968). *Symmetry breaking instabilities in dissipative systems. II*. The Journal of Chemical Physics, 48(4), 1695-1700.
- Nicolis, G., & Prigogine, I. (1977). *Self-Organization in Nonequilibrium Systems: From Dissipative Structures to Order through Fluctuations*. Wiley.
