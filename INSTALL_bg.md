# Ръководство за инсталиране (dfieldX & pplaneX)

> **Language / Езици:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | **Български** | [Українська](INSTALL_uk.md)

Това ръководство обяснява как да инсталирате и стартирате **dfieldX** и **pplaneX** на Windows, macOS и Linux.

Тези приложения изискват **Java 21 или по-нова версия**.
Не е необходима предишна инсталация на Java. Това ръководство предполага, че Java все още не е инсталирана на вашия компютър.

---

## 1. Изтегляне на приложението

Изтеглете най-новата версия от GitHub:
- **dfieldX:** [https://tinyurl.com/dfieldx-ufpr](https://tinyurl.com/dfieldx-ufpr)
- **pplaneX:** [https://tinyurl.com/pplanex-ufpr](https://tinyurl.com/pplanex-ufpr)

Запазете изтегления `.jar` файл на място, което лесно можете да намерите по-късно, като например работния плот (Desktop) или папката с изтегляния (Downloads).

---

## 2. Windows

### Инсталиране на Java
1. Отворете вашия уеб браузър и посетете: [https://adoptium.net/](https://adoptium.net/)
2. Изтеглете Eclipse Temurin Java 21 LTS за Windows x64.
3. Стартирайте изтегления инсталатор.
4. В "Custom Setup" (Персонализирана инсталация), уверете се, че опциите "Add to PATH" и "Associate .jar files" са активирани.
5. Завършете инсталацията.

### Проверка и стартиране
Отворете Command Prompt и изпълнете:
```bash
java -version
```

За да стартирате приложението:
```bash
java -jar dfieldX.jar
```
или
```bash
java -jar pplaneX.jar
```

---

## 3. macOS

### Инсталиране на Java
1. Посетете [https://adoptium.net/](https://adoptium.net/)
2. Изтеглете Java 21 LTS за macOS (изберете Apple Silicon за M1/M2/M3/M4 или Intel за по-стари Mac компютри).
3. Стартирайте инсталатора и следвайте инструкциите.

### Проверка и стартиране
Отворете Terminal и изпълнете:
```bash
java -version
```

За да стартирате приложението:
```bash
java -jar dfieldX.jar
```
или
```bash
java -jar pplaneX.jar
```

### 🍎 Предупреждение на macOS Gatekeeper
Ако macOS блокира приложението, заявявайки, че "cannot verify it is free of malware" (не може да потвърди, че не съдържа зловреден софтуер):
**Метод 1 (Графичен интерфейс):** Отворете System Settings (Системни настройки) → Privacy & Security (Поверителност и сигурност) → Превъртете надолу и кликнете върху "Open Anyway" (Отвори въпреки това).
**Метод 2 (Терминал):** Изпълнете `xattr -d com.apple.quarantine *.jar`

---

## 4. Linux

**Ubuntu, Debian, Linux Mint:**
```bash
sudo apt update
sudo apt install openjdk-21-jre
```

**Fedora, Rocky Linux, AlmaLinux:**
```bash
sudo dnf install java-21-openjdk
```

**Arch Linux, Manjaro:**
```bash
sudo pacman -S jre21-openjdk
```

### Проверка и стартиране
Отворете Terminal и изпълнете:
```bash
java -version
```

За да стартирате приложението:
```bash
java -jar dfieldX.jar
```
или
```bash
java -jar pplaneX.jar
```

---

## Отстраняване на неизправности
- **Нищо не се случва при двукратно кликване върху JAR файла:** Винаги опитвайте да стартирате от терминала, използвайки `java -jar <filename>.jar`, за да видите съобщенията за грешка.
- **Грешка Unsupported Class Version Error:** Имате стара версия на Java. Инсталирайте Java 21 или по-нова версия.
