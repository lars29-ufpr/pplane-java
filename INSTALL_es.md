> **Language / Idioma:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | **Español** | [Français](INSTALL_fr.md) | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Deutsch](INSTALL_de.md) | [Italiano](INSTALL_it.md)

# Guía de Instalación: pplaneX y dfieldX

Esta guía explica cómo instalar y ejecutar **pplaneX** y **dfieldX** en Windows, macOS y Linux.

Estas aplicaciones requieren **Java 21 (LTS)** o una versión más reciente. No es necesaria ninguna instalación previa de Java. Esta guía asume que Java aún no está instalado en su computadora.

---

## 1. Descargar la Aplicación

Descargue la última versión desde GitHub:
- **pplaneX:** https://tinyurl.com/pplanex-ufpr
- **dfieldX:** https://tinyurl.com/difieldx-ufpr

Guarde el archivo `.jar` descargado en una ubicación donde pueda encontrarlo fácilmente más tarde, como en su Escritorio o en la carpeta de Descargas.

---

## 2. Windows

### Parte A: Instalar Java
1. Abra su navegador web y visite: https://adoptium.net/
2. Descargue **Eclipse Temurin Java 21 LTS** para Windows x64.
3. Ejecute el instalador descargado.
4. En la pantalla "Custom Setup" (Configuración Personalizada), asegúrese de que las opciones **"Add to PATH"** (Agregar a PATH) y **"Associate .jar files"** (Asociar archivos .jar) estén habilitadas.
5. Finalice la instalación.

### Parte B: Verificar y Ejecutar
Abra el Símbolo del sistema (Command Prompt) y ejecute:
```bash
java -version
```
Cualquier versión 21 o superior es compatible. Para ejecutar la aplicación:
```bash
java -jar pplaneX.jar
```
*(o sustituya por `dfieldX.jar`)*

---

## 3. macOS

### Parte A: Instalar Java
1. Visite https://adoptium.net/
2. Descargue el **Java 21 LTS** más reciente para macOS.
   * *Consejo:* Elija el instalador `.dmg` (ej. `jdk-21_macos-x64_bin.dmg` para Macs con procesador Intel, o el equivalente `aarch64` para Apple Silicon M1/M2/M3/M4).
3. Ejecute el instalador `.dmg` descargado y siga las instrucciones en pantalla.

### Parte B: Organizar y Ejecutar (Omitir Gatekeeper)
macOS incluye una función de seguridad que bloquea aplicaciones de desarrolladores no identificados. Siga estos pasos en la primera ejecución:

1. **Organizar:** Para una mejor organización, mueva los archivos `pplaneX.jar` y `dfieldX.jar` descargados a su carpeta de **Aplicaciones** (Applications).
2. **No haga doble clic** en el archivo `.jar` la primera vez. Si lo hace, macOS mostrará una advertencia y evitará que se abra.
3. En su lugar, **haga clic derecho** (o Control-clic) en el archivo `.jar` y seleccione **Abrir** (Open) en el menú contextual.
4. Aparecerá un cuadro de diálogo pidiendo confirmación. Haga clic en **Abrir** nuevamente.

*Nota: Solo necesita hacer esto una vez. Para todos los usos posteriores, simplemente haga doble clic en el archivo `.jar` para iniciar la aplicación normalmente.*

### Ejecutar vía Terminal (Alternativa)
Si prefiere la línea de comandos, abra la Terminal y ejecute:
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Instalar Java:
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Verificar:
```bash
java -version
```
Ejecutar:
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Instalar Java:
```bash
sudo dnf install java-21-openjdk
```
Verificar:
```bash
java -version
```
Ejecutar:
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Instalar Java:
```bash
sudo pacman -S jre21-openjdk
```
Verificar:
```bash
java -version
```
Ejecutar:
```bash
java -jar pplaneX.jar
```

---

## Solución de Problemas

### No Sucede Nada al Hacer Doble Clic en el Archivo JAR
Ejecute la aplicación desde una terminal para ver la salida del sistema:
```bash
java -jar pplaneX.jar
```

### Error "Unsupported Class Version"
Está ejecutando una versión antigua de Java. Instale Java 21 o una versión más reciente.

### La Aplicación Falla al Iniciar
Verifique su versión de Java:
```bash
java -version
```
Informe cualquier mensaje de error junto con su versión de Java al desarrollador.
