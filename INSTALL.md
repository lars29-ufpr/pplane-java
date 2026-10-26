# Installation Guide (dfieldX & pplaneX)

> **Language / Idiomas:** **English** | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md)

This guide explains how to install and run **dfieldX** and **pplaneX** on Windows, macOS, and Linux.

These applications require **Java 21 or newer**.
No previous Java installation is required. This guide assumes Java is not yet installed on your computer.

---

## 1. Download the Application

Download the latest release from GitHub:
- **dfieldX:** https://tinyurl.com/dfieldx-ufpr
- **pplaneX:** https://tinyurl.com/pplanex-ufpr

Save the downloaded `.jar` file in a location you can easily find later, such as your Desktop or Downloads folder.

---

## 2. Windows

### Install Java
1. Open your web browser and visit: https://adoptium.net/
2. Download Eclipse Temurin Java 21 LTS for Windows x64.
3. Run the downloaded installer.
4. In "Custom Setup", ensure "Add to PATH" and "Associate .jar files" are enabled.
5. Finish the installation.

### Verify and Run
Open Command Prompt and run:
  java -version

To run the application:
  java -jar dfieldX.jar
or
  java -jar pplaneX.jar

---

## 3. macOS

### Install Java
1. Visit https://adoptium.net/
2. Download Java 21 LTS for macOS (choose Apple Silicon for M1/M2/M3/M4 or Intel for older Macs).
3. Run the installer and follow the instructions.

### Verify and Run
Open Terminal and run:
  java -version

To run the application:
  java -jar dfieldX.jar
or
  java -jar pplaneX.jar

### 🍎 macOS Gatekeeper Warning
If macOS blocks the application stating it "cannot verify it is free of malware":
**Method 1 (GUI):** Open System Settings → Privacy & Security → Scroll down and click "Open Anyway".
**Method 2 (Terminal):** Run `xattr -d com.apple.quarantine *.jar`

---

## 4. Linux

**Ubuntu, Debian, Linux Mint:**
  sudo apt update
  sudo apt install openjdk-21-jre

**Fedora, Rocky Linux, AlmaLinux:**
  sudo dnf install java-21-openjdk

**Arch Linux, Manjaro:**
  sudo pacman -S jre21-openjdk

### Verify and Run
Open Terminal and run:
  java -version

To run the application:
  java -jar dfieldX.jar
or
  java -jar pplaneX.jar

---

## Troubleshooting
- **Nothing Happens When Double-Clicking the JAR:** Always try running from the terminal using `java -jar <filename>.jar` to see error messages.
- **Unsupported Class Version Error:** You have an old Java version. Install Java 21 or newer.
