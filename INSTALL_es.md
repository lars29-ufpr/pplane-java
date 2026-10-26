# Guía de Instalación (dfieldX & pplaneX)

> **Language / Idiomas:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | **Español**

Esta guía explica cómo instalar y ejecutar **dfieldX** y **pplaneX** en Windows, macOS y Linux.

Estas aplicaciones requieren **Java 21 o superior**.
No se requiere una instalación previa de Java. Esta guía asume que Java aún no está instalado en su computadora.

---

## 1. Descargar la Aplicación

Descargue la última versión desde GitHub:
- **dfieldX:** https://tinyurl.com/dfieldx-ufpr
- **pplaneX:** https://tinyurl.com/pplanex-ufpr

Guarde el archivo `.jar` descargado en una ubicación de fácil acceso, como su Escritorio o carpeta de Descargas.

---

## 2. Windows

### Instalar Java
1. Abra su navegador web y visite: https://adoptium.net/
2. Descargue Eclipse Temurin Java 21 LTS para Windows x64.
3. Ejecute el instalador descargado.
4. En "Custom Setup" (Instalación Personalizada), asegúrese de habilitar "Add to PATH" y "Associate .jar files".
5. Finalice la instalación.

### Verificar y Ejecutar
Abra el Símbolo del sistema y ejecute:
  java -version

Para ejecutar la aplicación:
  java -jar dfieldX.jar
o
  java -jar pplaneX.jar

---

## 3. macOS

### Instalar Java
1. Visite https://adoptium.net/
2. Descargue Java 21 LTS para macOS (elija Apple Silicon para M1/M2/M3/M4 o Intel para Macs más antiguos).
3. Ejecute el instalador y siga las instrucciones.

### Verificar y Ejecutar
Abra la Terminal y ejecute:
  java -version

Para ejecutar la aplicación:
  java -jar dfieldX.jar
o
  java -jar pplaneX.jar

### 🍎 Advertencia de Gatekeeper en macOS
Si macOS bloquea la aplicación indicando que "no puede verificar que esté libre de malware":
**Método 1 (Interfaz):** Abra Configuración del Sistema → Privacidad y Seguridad → Desplácese hacia abajo y haga clic en "Abrir de todos modos".
**Método 2 (Terminal):** Ejecute `xattr -d com.apple.quarantine *.jar`

---

## 4. Linux

**Ubuntu, Debian, Linux Mint:**
  sudo apt update
  sudo apt install openjdk-21-jre

**Fedora, Rocky Linux, AlmaLinux:**
  sudo dnf install java-21-openjdk

**Arch Linux, Manjaro:**
  sudo pacman -S jre21-openjdk

### Verificar y Ejecutar
Abra la Terminal y ejecute:
  java -version

Para ejecutar la aplicación:
  java -jar dfieldX.jar
o
  java -jar pplaneX.jar

---

## Solución de Problemas
- **No sucede nada al hacer doble clic en el JAR:** Intente siempre ejecutar desde la terminal usando `java -jar <nombre-del-archivo>.jar` para ver los mensajes de error.
- **Unsupported Class Version Error:** Tiene una versión antigua de Java. Instale Java 21 o superior.
