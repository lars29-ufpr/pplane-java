> **Language / Lingua:** [English](README.md) | [Português](README_pt_BR.md) | [Español](README_es.md) | [Français](README_fr.md) | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | [Deutsch](README_de.md) | **Italiano**

---

# pplaneX — Analisi Qualitativa e Numerica di Sistemi Autonomi 2D

**pplaneX** è un'applicazione desktop interattiva moderna e ad alte prestazioni sviluppata con Java 21 e JavaFX per l'analisi qualitativa e numerica di sistemi autonomi bidimensionali di Equazioni Differenziali Ordinarie (EDO) e ritratti di fase planari:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Ispirato al classico strumento didattico `pplane` originariamente creato da John C. Polking (Rice University) per MATLAB, **pplaneX** è stato ricostruito da zero per offrire un'interfaccia fluida, rendering reattivo in tempo reale, esplorazione continua con zoom/panoramica e supporto universale multipiattaforma (Linux, Windows e macOS).

---

## 🎉 Novità nella Versione 1.2.0
- **Internazionalizzazione Completa (i18n):** L'architettura dell'applicazione è stata completamente riprogettata per supportare più lingue in modo dinamico.
- **Traduzioni Native:** L'interfaccia, i menu, le finestre di dialogo e la Guida ai Modelli Didattici sono ora disponibili in **9 lingue** (Inglese, Portoghese, Spagnolo, Francese, Russo, Bulgaro, Ucraino, Tedesco e Italiano).
- **Localizzazione Dinamica:** Il sistema rileva automaticamente la lingua del sistema operativo con fallback all'inglese, garantendo un'esperienza impeccabile in tutto il mondo.
- **Documentazione Robusta:** Guide `README` e `INSTALL` completamente aggiornate in tutte le lingue supportate, complete di passaggi di installazione semplificati per macOS (programma di installazione `.dmg` di Adoptium e istruzioni per bypassare Gatekeeper).

---

## 📥 Installazione e Avvio Rapido

**pplaneX** è distribuito come file JAR eseguibile autonomo e richiede **Java 21 (LTS) o versioni successive**.

Se non hai Java installato o hai bisogno di una guida passo passo per il tuo sistema operativo (Windows, macOS o Linux), consulta le nostre guide all'installazione localizzate:

- 📖 **[Guida all'installazione (Italiano)](INSTALL_it.md)**

### Esecuzione dell'Applicazione

Una volta installato Java 21+, esegui dal tuo terminale:

```bash
java -jar pplaneX.jar
```

---

## Crediti e Licenza

Questo progetto è un'espansione didattica basata sul software originale dfield e pplane (copyright John C. Polking, Rice University) e sulle rispettive versioni Java (Joel Castellanos). Distribuito per l'uso nelle istituzioni educative, senza scopo di lucro, nel rispetto del lavoro degli autori originali.
