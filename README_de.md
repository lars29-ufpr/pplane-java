> **Language / Sprache:** [English](README.md) | [Português](README_pt_BR.md) | [Español](README_es.md) | [Français](README_fr.md) | [Русский](README_ru.md) | [Български](README_bg.md) | [Українська](README_uk.md) | **Deutsch** | [Italiano](README_it.md)

---

# pplaneX — Qualitative und numerische Analyse von 2D-Autonomen Systemen

**pplaneX** ist eine moderne, hochleistungsfähige, interaktive Desktop-Anwendung, die mit Java 21 und JavaFX für die qualitative und numerische Analyse zweidimensionaler autonomer Systeme von gewöhnlichen Differentialgleichungen (ODEs) und planaren Phasendiagrammen entwickelt wurde:

$$x' = \frac{dx}{dt} = f(x, y)$$
$$y' = \frac{dy}{dt} = g(x, y)$$

Inspiriert von dem klassischen Lernwerkzeug `pplane`, das ursprünglich von John C. Polking (Rice University) für MATLAB entwickelt wurde, wurde **pplaneX** von Grund auf neu geschrieben, um eine flüssige Benutzerinteraktion, reaktives Rendering in Echtzeit, kontinuierliche Zoom-/Verschiebe-Erkundung und universelle plattformübergreifende Unterstützung (Linux, Windows und macOS) zu bieten.

---

## 🎉 Neuigkeiten in Version 1.2.0
- **Vollständige Internationalisierung (i18n):** Die Architektur der Anwendung wurde komplett überarbeitet, um mehrere Sprachen dynamisch zu unterstützen.
- **Native Übersetzungen:** Schnittstelle, Menüs, Dialogfelder und der umfassende Leitfaden für Bildungsmodelle sind jetzt in **9 Sprachen** verfügbar (Englisch, Portugiesisch, Spanisch, Französisch, Russisch, Bulgarisch, Ukrainisch, Deutsch und Italienisch).
- **Dynamische Lokalisierung:** Das System erkennt automatisch die Betriebssystemsprache mit einem Fallback auf Englisch und sorgt so weltweit für eine reibungslose Nutzung.
- **Robuste Dokumentation:** Vollständig aktualisierte `README`- und `INSTALL`-Anleitungen in allen unterstützten Sprachen, einschließlich vereinfachter macOS-Installationsschritte (Adoptium `.dmg`-Installationsprogramm und Gatekeeper-Umgehungsanweisungen).

---

## 📥 Installation & Schnellstart

**pplaneX** wird als eigenständige ausführbare JAR-Datei verteilt und erfordert **Java 21 (LTS) oder neuer**.

Wenn Sie Java nicht installiert haben oder eine Schritt-für-Schritt-Anleitung für Ihr Betriebssystem (Windows, macOS oder Linux) benötigen, lesen Sie unsere lokalisierten Installationsanleitungen:

- 📖 **[Installationsanleitung (Deutsch)](INSTALL_de.md)**

### Ausführen der Anwendung

Sobald Java 21+ installiert ist, führen Sie in Ihrem Terminal Folgendes aus:

```bash
java -jar pplaneX.jar
```

---

## Credits und Lizenz

Dieses Projekt ist eine didaktische Erweiterung basierend auf der Originalsoftware dfield und pplane (Copyright John C. Polking, Rice University) und deren jeweiligen Java-Versionen (Joel Castellanos). Verteilt zur Nutzung in Bildungseinrichtungen, ohne Gewinnabsicht, unter Respektierung der Arbeit der ursprünglichen Autoren.
