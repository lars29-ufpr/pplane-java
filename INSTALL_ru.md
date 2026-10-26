# Руководство по установке (dfieldX и pplaneX)

> **Languages:** [English](INSTALL.md) | [Português](INSTALL_pt_BR.md) | [Español](INSTALL_es.md) | [Français](INSTALL_fr.md) | **Русский**

Это руководство объясняет, как установить и запустить **dfieldX** и **pplaneX** в Windows, macOS и Linux.

Эти приложения требуют **Java 21 или новее**.
Предварительная установка Java не требуется. Это руководство предполагает, что Java еще не установлена на вашем компьютере.

---

## 1. Загрузка приложения

Скачайте последнюю версию с GitHub:
- **dfieldX:** [https://tinyurl.com/dfieldx-ufpr](https://tinyurl.com/dfieldx-ufpr)
- **pplaneX:** [https://tinyurl.com/pplanex-ufpr](https://tinyurl.com/pplanex-ufpr)

Сохраните загруженный файл .jar в легкодоступном месте, например, на Рабочем столе или в папке Загрузки.

---

## 2. Windows

### Установка Java
1. Откройте браузер и перейдите на: [https://adoptium.net/](https://adoptium.net/)
2. Скачайте Eclipse Temurin Java 21 LTS для Windows x64.
3. Запустите скачанный установщик.
4. В разделе "Custom Setup" (Выборочная установка) убедитесь, что включены опции "Add to PATH" и "Associate .jar files".
5. Завершите установку.

### Проверка и запуск
Откройте Командную строку и введите:

    java -version

Для запуска программы:

    java -jar dfieldX.jar

или

    java -jar pplaneX.jar

---

## 3. macOS

### Установка Java
1. Перейдите на [https://adoptium.net/](https://adoptium.net/)
2. Скачайте Java 21 LTS для macOS (выберите Apple Silicon для M1/M2/M3/M4 или Intel для более старых Mac).
3. Запустите установщик и следуйте инструкциям.

### Проверка и запуск
Откройте Терминал и введите:

    java -version

Для запуска программы:

    java -jar dfieldX.jar

или

    java -jar pplaneX.jar

### 🍎 Предупреждение Gatekeeper в macOS
Если macOS блокирует приложение сообщением, что "не может проверить, не содержит ли оно вредоносное ПО":
**Способ 1 (Интерфейс):** Откройте Системные настройки → Конфиденциальность и безопасность → Прокрутите вниз и нажмите "Все равно открыть".
**Способ 2 (Терминал):** Выполните в терминале:

    xattr -d com.apple.quarantine *.jar

---

## 4. Linux

**Ubuntu, Debian, Linux Mint:**

    sudo apt update
    sudo apt install openjdk-21-jre

**Fedora, Rocky Linux, AlmaLinux:**

    sudo dnf install java-21-openjdk

**Arch Linux, Manjaro:**

    sudo pacman -S jre21-openjdk

### Проверка и запуск
Откройте Терминал и введите:

    java -version

Для запуска программы:

    java -jar dfieldX.jar

или

    java -jar pplaneX.jar

---

## Устранение неполадок
- **Ничего не происходит при двойном щелчке по файлу JAR:** Всегда пытайтесь запускать программу через терминал с помощью команды java -jar <имя-файла>.jar, чтобы увидеть сообщения об ошибках.
- **Unsupported Class Version Error:** У вас установлена старая версия Java. Установите Java 21 или выше.
