# pplaneX — Análise Qualitativa e Numérica de Sistemas Autônomos 2D

> **Language / Idiomas:** [English](README.md) | **Português** | [Español](README.es.md)

---

O **pplaneX** é uma aplicação desktop interativa moderna e de alto desempenho, construída com Java 21 e JavaFX, para a análise qualitativa e numérica de sistemas autônomos bidimensionais de Equações Diferenciais Ordinárias (EDOs) e retratos de fase planares:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspirado na clássica ferramenta educacional `pplane` criada originalmente por John C. Polking (Rice University) para o MATLAB, o **pplaneX** foi reescrito do zero para oferecer interação fluida, renderização em tempo real, navegação contínua (pan/zoom) e suporte multiplataforma universal (Linux, Windows e macOS).

---

## 🎉 Novidades da Versão 1.2
- **Internacionalização Completa (i18n):** A arquitetura do aplicativo foi totalmente reestruturada para suportar múltiplos idiomas.
- **Traduções Nativas:** A interface, menus, caixas de diálogo e o abrangente Guia de Modelos Educacionais agora estão disponíveis em **Inglês**, **Português (PT-BR)** e **Espanhol**.
- **Localização Dinâmica:** O sistema detecta automaticamente o idioma do sistema operacional e possui o inglês como rede de segurança.

---

## 📥 Instalação e Início Rápido

O **pplaneX** é distribuído como um JAR executável autônomo e requer o **Java 21 (LTS) ou superior**.

Se você não tem o Java instalado ou precisa de ajuda passo a passo, consulte nossos guias:
- 📖 **[Guia de Instalação (Português)](INSTALL_pt.md)**
- 📖 **[Installation Guide (English)](INSTALL.md)**

### Executando a Aplicação

Com o Java 21+ instalado, execute no terminal (use as flags abaixo se quiser forçar o idioma):

java -Duser.language=pt -jar pplaneX.jar

---

## 🚀 Principais Funcionalidades
- **Renderização Interativa:** Campos vetoriais em tempo real e traçado iterativo de órbitas.
- **Integração Numérica de Alta Precisão:** Métodos adaptativos de Runge-Kutta.
- **Análise Qualitativa:** Identificação de equilíbrios, cálculo da matriz Jacobiana e traçado de nulclinas.
- **Galeria Curada de Sistemas:** Modelos clássicos de biomatemática, epidemiologia e osciladores.
- **Exportação Gráfica:** SVG vetorial puro (ideal para LaTeX) e PNG em alta resolução.
