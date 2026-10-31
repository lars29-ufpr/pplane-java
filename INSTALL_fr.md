> **Language / Langue:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | **Français** | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Deutsch](INSTALL_de.md) | [Italiano](INSTALL_it.md)

# Guide d'Installation : pplaneX et dfieldX

Ce guide explique comment installer et exécuter **pplaneX** et **dfieldX** sur Windows, macOS et Linux.

Ces applications nécessitent **Java 21 (LTS)** ou une version ultérieure. Aucune installation préalable de Java n'est requise. Ce guide suppose que Java n'est pas encore installé sur votre ordinateur.

---

## 1. Télécharger l'Application

Téléchargez la dernière version depuis GitHub :
- **pplaneX :** https://tinyurl.com/pplanex-ufpr
- **dfieldX :** https://tinyurl.com/difieldx-ufpr

Enregistrez le fichier `.jar` téléchargé dans un emplacement où vous pourrez le retrouver facilement plus tard, comme sur votre Bureau ou dans le dossier Téléchargements.

---

## 2. Windows

### Partie A : Installer Java
1. Ouvrez votre navigateur Web et visitez : https://adoptium.net/
2. Téléchargez **Eclipse Temurin Java 21 LTS** pour Windows x64.
3. Exécutez le programme d'installation téléchargé.
4. Dans l'écran "Custom Setup" (Installation personnalisée), assurez-vous que les options **"Add to PATH"** (Ajouter au PATH) et **"Associate .jar files"** (Associer les fichiers .jar) sont activées.
5. Terminez l'installation.

### Partie B : Vérifier et Exécuter
Ouvrez l'Invite de commandes (Command Prompt) et exécutez :
```bash
java -version
```
Toute version 21 ou supérieure est prise en charge. Pour exécuter l'application :
```bash
java -jar pplaneX.jar
```
*(ou remplacez par `dfieldX.jar`)*

---

## 3. macOS

### Partie A : Installer Java
1. Visitez https://adoptium.net/
2. Téléchargez le dernier **Java 21 LTS** pour macOS.
   * *Astuce :* Choisissez le programme d'installation `.dmg` (ex. `jdk-21_macos-x64_bin.dmg` pour les Mac Intel, ou l'équivalent `aarch64` pour Apple Silicon M1/M2/M3/M4).
3. Exécutez le programme d'installation `.dmg` téléchargé et suivez les instructions à l'écran.

### Partie B : Organiser et Exécuter (Contourner Gatekeeper)
macOS intègre une fonctionnalité de sécurité qui bloque les applications provenant de développeurs non identifiés. Suivez ces étapes lors de la première exécution :

1. **Organiser :** Pour une meilleure organisation, déplacez les fichiers `pplaneX.jar` et `dfieldX.jar` téléchargés vers votre dossier **Applications**.
2. **Ne double-cliquez pas** sur le fichier `.jar` la première fois. Si vous le faites, macOS affichera un avertissement et l'empêchera de s'ouvrir.
3. Au lieu de cela, **faites un clic droit** (ou Contrôle-clic) sur le fichier `.jar` et sélectionnez **Ouvrir** dans le menu contextuel.
4. Une boîte de dialogue apparaîtra pour demander confirmation. Cliquez à nouveau sur **Ouvrir**.

*Remarque : Vous ne devez faire cela qu'une seule fois. Pour toutes les utilisations ultérieures, il vous suffira de double-cliquer sur le fichier `.jar` pour lancer l'application normalement.*

### Exécuter via le Terminal (Alternative)
Si vous préférez la ligne de commande, ouvrez le Terminal et exécutez :
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Installer Java :
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Vérifier :
```bash
java -version
```
Exécuter :
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Installer Java :
```bash
sudo dnf install java-21-openjdk
```
Vérifier :
```bash
java -version
```
Exécuter :
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Installer Java :
```bash
sudo pacman -S jre21-openjdk
```
Vérifier :
```bash
java -version
```
Exécuter :
```bash
java -jar pplaneX.jar
```

---

## Dépannage

### Rien ne se passe en double-cliquant sur le fichier JAR
Exécutez l'application depuis un terminal pour voir la sortie du système :
```bash
java -jar pplaneX.jar
```

### Erreur "Unsupported Class Version"
Vous exécutez une ancienne version de Java. Installez Java 21 ou une version ultérieure.

### L'application ne démarre pas
Vérifiez votre version de Java :
```bash
java -version
```
Signalez tout message d'erreur ainsi que votre version de Java au développeur.
