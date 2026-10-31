> **Language / Lingua:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Français](INSTALL_fr.md) | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Deutsch](INSTALL_de.md) | **Italiano**

# Guida all'installazione: pplaneX e dfieldX

Questa guida spiega come installare ed eseguire **pplaneX** e **dfieldX** su Windows, macOS e Linux.

Queste applicazioni richiedono **Java 21 (LTS)** o una versione successiva. Non è richiesta alcuna installazione precedente di Java. Questa guida presuppone che Java non sia ancora installato sul tuo computer.

---

## 1. Scaricare l'Applicazione

Scarica l'ultima versione da GitHub:
- **pplaneX:** https://tinyurl.com/pplanex-ufpr
- **dfieldX:** https://tinyurl.com/difieldx-ufpr

Salva il file `.jar` scaricato in una posizione in cui puoi trovarlo facilmente in seguito, come sul Desktop o nella cartella Download.

---

## 2. Windows

### Parte A: Installare Java
1. Apri il tuo browser web e visita: https://adoptium.net/
2. Scarica **Eclipse Temurin Java 21 LTS** per Windows x64.
3. Esegui il programma di installazione scaricato.
4. Nella schermata "Custom Setup" (Installazione personalizzata), assicurati che le opzioni **"Add to PATH"** (Aggiungi a PATH) e **"Associate .jar files"** (Associa file .jar) siano abilitate.
5. Completa l'installazione.

### Parte B: Verificare ed Eseguire
Apri il Prompt dei comandi (Command Prompt) ed esegui:
```bash
java -version
```
È supportata qualsiasi versione 21 o successiva. Per eseguire l'applicazione:
```bash
java -jar pplaneX.jar
```
*(o sostituisci con `dfieldX.jar`)*

---

## 3. macOS

### Parte A: Installare Java
1. Visita https://adoptium.net/
2. Scarica l'ultimo **Java 21 LTS** per macOS.
   * *Suggerimento:* Scegli il programma di installazione `.dmg` (es. `jdk-21_macos-x64_bin.dmg` per i Mac Intel, o l'equivalente `aarch64` per Apple Silicon M1/M2/M3/M4).
3. Esegui il programma di installazione `.dmg` scaricato e segui le istruzioni sullo schermo.

### Parte B: Organizzare ed Eseguire (Bypassare Gatekeeper)
macOS include una funzione di sicurezza che blocca le applicazioni di sviluppatori non identificati. Segui questi passaggi al primo avvio:

1. **Organizzare:** Per una migliore organizzazione, sposta i file `pplaneX.jar` e `dfieldX.jar` scaricati nella tua cartella **Applicazioni** (Applications).
2. **Non fare doppio clic** sul file `.jar` la prima volta. Se lo fai, macOS mostrerà un avviso e ne impedirà l'apertura.
3. Invece, **fai clic con il tasto destro** (o Control-clic) sul file `.jar` e seleziona **Apri** (Open) dal menu contestuale.
4. Apparirà una finestra di dialogo che chiede conferma. Fai clic di nuovo su **Apri**.

*Nota: Devi farlo solo una volta. Per tutti gli utilizzi successivi, puoi semplicemente fare doppio clic sul file `.jar` per avviare normalmente l'applicazione.*

### Eseguire tramite Terminale (Alternativa)
Se preferisci la riga di comando, apri il Terminale ed esegui:
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Installare Java:
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Verificare:
```bash
java -version
```
Eseguire:
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Installare Java:
```bash
sudo dnf install java-21-openjdk
```
Verificare:
```bash
java -version
```
Eseguire:
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Installare Java:
```bash
sudo pacman -S jre21-openjdk
```
Verificare:
```bash
java -version
```
Eseguire:
```bash
java -jar pplaneX.jar
```

---

## Risoluzione dei problemi

### Non succede nulla facendo doppio clic sul file JAR
Esegui l'applicazione da un terminale per vedere l'output del sistema:
```bash
java -jar pplaneX.jar
```

### Errore "Unsupported Class Version"
Stai eseguendo una vecchia versione di Java. Installa Java 21 o una versione successiva.

### L'applicazione non si avvia
Verifica la tua versione di Java:
```bash
java -version
```
Segnala allo sviluppatore eventuali messaggi di errore insieme alla tua versione di Java.
