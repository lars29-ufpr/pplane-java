> **Language / Idioma:** **English** | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Français](INSTALL_fr.md) | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Deutsch](INSTALL_de.md) | [Italiano](INSTALL_it.md)

# Installation Guide: pplaneX and dfieldX

This guide explains how to install and run **pplaneX** and **dfieldX** on Windows, macOS, and Linux.

These applications require **Java 21 (LTS)** or a newer version. No previous installation of Java is required. This guide assumes that Java is not yet installed on your computer.

---

## 1. Download the Application

Download the latest release from GitHub:
- **pplaneX:** https://tinyurl.com/pplanex-ufpr
- **dfieldX:** https://tinyurl.com/difieldx-ufpr

Save the downloaded `.jar` file in a location where you can easily find it later, such as your Desktop or Downloads folder.

---

## 2. Windows

### Part A: Install Java
1. Open your web browser and visit: https://adoptium.net/
2. Download **Eclipse Temurin Java 21 LTS** for Windows x64.
3. Run the downloaded installer.
4. In "Custom Setup", make sure that **"Add to PATH"** and **"Associate .jar files"** are enabled.
5. Finish the installation.

### Part B: Verify and Run
Open Command Prompt and run:
```bash
java -version
```
Any version 21 or newer is supported. To run the application:
```bash
java -jar pplaneX.jar
```
*(or replace with `dfieldX.jar`)*

---

## 3. macOS

### Part A: Install Java
1. Visit https://adoptium.net/
2. Download the latest **Java 21 LTS** for macOS.
   * *Tip:* Choose the `.dmg` installer (e.g., `jdk-21_macos-x64_bin.dmg` for Intel Macs, or the `aarch64` equivalent for Apple Silicon M1/M2/M3/M4).
3. Run the downloaded `.dmg` installer and follow the instructions on the screen.

### Part B: Organize and Run (Bypassing Gatekeeper)
macOS includes a security feature that blocks applications from unidentified developers. Follow these steps for the first run:

1. **Organize:** For better organization, move the downloaded `pplaneX.jar` and `dfieldX.jar` files to your **Applications** folder.
2. **Do not double-click** the `.jar` file the first time. If you do, macOS will display a warning and prevent it from opening.
3. Instead, **right-click** (or Control-click) the `.jar` file and select **Open** from the context menu.
4. A dialog box will appear asking for confirmation. Click **Open** again.

*Note: You only need to do this once. For all subsequent uses, you can simply double-click the `.jar` file to launch the application normally.*

### Run via Terminal (Alternative)
If you prefer the command line, open Terminal and run:
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Install Java:
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Verify:
```bash
java -version
```
Run:
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Install Java:
```bash
sudo dnf install java-21-openjdk
```
Verify:
```bash
java -version
```
Run:
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Install Java:
```bash
sudo pacman -S jre21-openjdk
```
Verify:
```bash
java -version
```
Run:
```bash
java -jar pplaneX.jar
```

---

## Troubleshooting

### Nothing Happens When Double-Clicking the JAR File
Run the application from a terminal to see the system output:
```bash
java -jar pplaneX.jar
```

### "Unsupported Class Version" Error
You are running an older version of Java. Install Java 21 or a newer version.

### The Application Fails to Start
Verify your Java version:
```bash
java -version
```
Report any error messages along with your Java version to the developer.
