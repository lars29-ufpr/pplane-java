> **Language / Idioma:** [English](README.md) | **Português (BR)** | [Español](README_es.md) | [Français](README_fr.md) | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | [Deutsch](README_de.md) | [Italiano](README_it.md)

---

# pplaneX — Análise Qualitativa e Numérica de Sistemas Autônomos 2D

**pplaneX** é um aplicativo de desktop interativo moderno e de alto desempenho construído com Java 21 e JavaFX para a análise qualitativa e numérica de sistemas autônomos bidimensionais de Equações Diferenciais Ordinárias (EDOs) e retratos de fase plana:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspirado na ferramenta educacional clássica `pplane` criada originalmente por John C. Polking (Rice University) para MATLAB, o **pplaneX** foi reescrito do zero para oferecer interação fluida, renderização responsiva em tempo real, exploração contínua com panorâmica/zoom e suporte universal multiplataforma (Linux, Windows e macOS).

---

## 🎉 O que há de novo na Versão 1.2.0
- **Internacionalização Completa (i18n):** A arquitetura do aplicativo foi totalmente reformulada para suportar múltiplos idiomas de forma dinâmica.
- **Traduções Nativas:** A interface, os menus, os diálogos e o abrangente Guia de Modelos Educacionais agora estão disponíveis em **9 idiomas** (Inglês, Português, Espanhol, Francês, Russo, Búlgaro, Ucraniano, Alemão e Italiano).
- **Localização Dinâmica:** O sistema detecta automaticamente o idioma do sistema operacional com fallback para o inglês, garantindo uma experiência perfeita globalmente.
- **Documentação Robusta:** Manuais `README` e `INSTALL` totalmente atualizados em todos os idiomas suportados, incluindo etapas simplificadas de instalação no macOS (instalador `.dmg` do Adoptium e instruções de contorno do Gatekeeper).

---

## 📥 Instalação e Início Rápido

O **pplaneX** é distribuído como um arquivo JAR executável autônomo e requer o **Java 21 (LTS) ou superior**.

Se você não tem o Java instalado ou precisa de orientações passo a passo para o seu sistema operacional (Windows, macOS ou Linux), consulte nossos guias de instalação localizados:

- 📖 **[Guia de Instalação (Português)](INSTALL_pt_BR.md)**

### Executando o Aplicativo

Assim que o Java 21+ estiver instalado, execute no seu terminal:

```bash
java -jar pplaneX.jar
```

---

## Créditos e Licença

Este projeto é uma expansão didática baseada nos softwares originais dfield e pplane (copyright John C. Polking, Rice University) e suas respectivas versões em Java (Joel Castellanos). Distribuído para uso em instituições educacionais, sem fins lucrativos, respeitando o trabalho dos autores originais.
