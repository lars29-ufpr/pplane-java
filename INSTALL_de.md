> **Language / Sprache:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Français](INSTALL_fr.md) | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | **Deutsch** | [Italiano](INSTALL_it.md)

# Installationsanleitung: pplaneX und dfieldX

Diese Anleitung erklärt, wie Sie **pplaneX** und **dfieldX** unter Windows, macOS und Linux installieren und ausführen.

Diese Anwendungen erfordern **Java 21 (LTS)** oder eine neuere Version. Es ist keine vorherige Installation von Java erforderlich. Diese Anleitung geht davon aus, dass Java noch nicht auf Ihrem Computer installiert ist.

---

## 1. Herunterladen der Anwendung

Laden Sie die neueste Version von GitHub herunter:
- **pplaneX:** https://tinyurl.com/pplanex-ufpr
- **dfieldX:** https://tinyurl.com/difieldx-ufpr

Speichern Sie die heruntergeladene `.jar`-Datei an einem Ort, an dem Sie sie später leicht finden können, z. B. auf Ihrem Desktop oder im Ordner "Downloads".

---

## 2. Windows

### Teil A: Java installieren
1. Öffnen Sie Ihren Webbrowser und besuchen Sie: https://adoptium.net/
2. Laden Sie **Eclipse Temurin Java 21 LTS** für Windows x64 herunter.
3. Führen Sie das heruntergeladene Installationsprogramm aus.
4. Stellen Sie im Bildschirm "Custom Setup" (Benutzerdefinierte Installation) sicher, dass die Optionen **"Add to PATH"** (Zu PATH hinzufügen) und **"Associate .jar files"** (.jar-Dateien zuordnen) aktiviert sind.
5. Schließen Sie die Installation ab.

### Teil B: Überprüfen und Ausführen
Öffnen Sie die Eingabeaufforderung (Command Prompt) und führen Sie Folgendes aus:
```bash
java -version
```
Jede Version ab 21 wird unterstützt. Um die Anwendung auszuführen:
```bash
java -jar pplaneX.jar
```
*(oder ersetzen Sie es durch `dfieldX.jar`)*

---

## 3. macOS

### Teil A: Java installieren
1. Besuchen Sie https://adoptium.net/
2. Laden Sie das neueste **Java 21 LTS** für macOS herunter.
   * *Tipp:* Wählen Sie das `.dmg`-Installationsprogramm (z. B. `jdk-21_macos-x64_bin.dmg` für Intel-Macs oder das entsprechende `aarch64` für Apple Silicon M1/M2/M3/M4).
3. Führen Sie das heruntergeladene `.dmg`-Installationsprogramm aus und folgen Sie den Anweisungen auf dem Bildschirm.

### Teil B: Organisieren und Ausführen (Gatekeeper umgehen)
macOS enthält eine Sicherheitsfunktion, die Anwendungen von nicht identifizierten Entwicklern blockiert. Befolgen Sie bei der ersten Ausführung diese Schritte:

1. **Organisieren:** Zur besseren Organisation verschieben Sie die heruntergeladenen Dateien `pplaneX.jar` und `dfieldX.jar` in Ihren Ordner **Programme** (Applications).
2. **Doppelklicken Sie beim ersten Mal nicht** auf die `.jar`-Datei. Andernfalls zeigt macOS eine Warnung an und verhindert das Öffnen.
3. **Klicken Sie stattdessen mit der rechten Maustaste** (oder Control-Klick) auf die `.jar`-Datei und wählen Sie **Öffnen** (Open) aus dem Kontextmenü.
4. Ein Dialogfeld wird angezeigt, in dem Sie um Bestätigung gebeten werden. Klicken Sie erneut auf **Öffnen**.

*Hinweis: Sie müssen dies nur einmal tun. Für alle nachfolgenden Verwendungen können Sie einfach auf die `.jar`-Datei doppelklicken, um die Anwendung normal zu starten.*

### Ausführen über das Terminal (Alternative)
Wenn Sie die Kommandozeile bevorzugen, öffnen Sie das Terminal und führen Sie Folgendes aus:
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Java installieren:
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Überprüfen:
```bash
java -version
```
Ausführen:
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Java installieren:
```bash
sudo dnf install java-21-openjdk
```
Überprüfen:
```bash
java -version
```
Ausführen:
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Java installieren:
```bash
sudo pacman -S jre21-openjdk
```
Überprüfen:
```bash
java -version
```
Ausführen:
```bash
java -jar pplaneX.jar
```

---

## Fehlerbehebung

### Nichts passiert beim Doppelklicken auf die JAR-Datei
Führen Sie die Anwendung von einem Terminal aus, um die Systemausgabe zu sehen:
```bash
java -jar pplaneX.jar
```

### Fehler "Unsupported Class Version"
Sie führen eine alte Version von Java aus. Installieren Sie Java 21 oder neuer.

### Die Anwendung startet nicht
Überprüfen Sie Ihre Java-Version:
```bash
java -version
```
Melden Sie dem Entwickler alle Fehlermeldungen zusammen mit Ihrer Java-Version.
