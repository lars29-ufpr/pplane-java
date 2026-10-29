# Посібник зі встановлення (dfieldX & pplaneX)

> **Language / Мови:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Български](INSTALL_bg.md) | **Українська**

Цей посібник пояснює, як встановити та запустити **dfieldX** і **pplaneX** на Windows, macOS та Linux.

Ці застосунки вимагають **Java 21 або новішої версії**.
Попередня інсталяція Java не потрібна. Цей посібник передбачає, що Java ще не встановлена на вашому комп'ютері.

---

## 1. Завантаження застосунку

Завантажте останню версію з GitHub:
- **dfieldX:** [https://tinyurl.com/dfieldx-ufpr](https://tinyurl.com/dfieldx-ufpr)
- **pplaneX:** [https://tinyurl.com/pplanex-ufpr](https://tinyurl.com/pplanex-ufpr)

Збережіть завантажений файл `.jar` у місці, де ви зможете легко його знайти пізніше, наприклад, на робочому столі (Desktop) або в папці завантажень (Downloads).

---

## 2. Windows

### Встановлення Java
1. Відкрийте веббраузер і перейдіть за посиланням: [https://adoptium.net/](https://adoptium.net/)
2. Завантажте Eclipse Temurin Java 21 LTS для Windows x64.
3. Запустіть завантажений інсталятор.
4. У розділі "Custom Setup" (Спеціальне встановлення) переконайтеся, що активовано параметри "Add to PATH" та "Associate .jar files".
5. Завершіть встановлення.

### Перевірка та запуск
Відкрийте Command Prompt (Командний рядок) і виконайте:
```bash
java -version
```

Щоб запустити застосунок:
```bash
java -jar dfieldX.jar
```
або
```bash
java -jar pplaneX.jar
```

---

## 3. macOS

### Встановлення Java
1. Перейдіть на [https://adoptium.net/](https://adoptium.net/)
2. Завантажте Java 21 LTS для macOS (виберіть Apple Silicon для M1/M2/M3/M4 або Intel для старіших Mac).
3. Запустіть інсталятор і дотримуйтесь інструкцій.

### Перевірка та запуск
Відкрийте Terminal і виконайте:
```bash
java -version
```

Щоб запустити застосунок:
```bash
java -jar dfieldX.jar
```
або
```bash
java -jar pplaneX.jar
```

### 🍎 Попередження macOS Gatekeeper
Якщо macOS блокує застосунок, повідомляючи, що "cannot verify it is free of malware" (не може перевірити наявність шкідливого ПЗ):
**Метод 1 (Графічний інтерфейс):** Відкрийте System Settings (Системні параметри) → Privacy & Security (Приватність і безпека) → Прокрутіть униз і натисніть "Open Anyway" (Відкрити все одно).
**Метод 2 (Термінал):** Виконайте `xattr -d com.apple.quarantine *.jar`

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

### Перевірка та запуск
Відкрийте Terminal і виконайте:
```bash
java -version
```

Щоб запустити застосунок:
```bash
java -jar dfieldX.jar
```
або
```bash
java -jar pplaneX.jar
```

---

## Вирішення проблем
- **Нічого не відбувається після подвійного клацання на файлі JAR:** Завжди намагайтеся запускати з терміналу за допомогою `java -jar <filename>.jar`, щоб побачити повідомлення про помилки.
- **Помилка Unsupported Class Version Error:** У вас стара версія Java. Встановіть Java 21 або новішу.
