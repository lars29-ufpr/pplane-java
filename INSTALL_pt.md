# Guia de Instalação (dfieldX & pplaneX)

> **Language / Idiomas:** [English](INSTALL.md) | **Português** | [Español](INSTALL_es.md)

Este guia explica como instalar e executar o **dfieldX** e o **pplaneX** no Windows, macOS e Linux.

Estes aplicativos requerem o **Java 21 ou mais recente**.
Nenhuma instalação prévia do Java é necessária. Este guia assume que o Java ainda não está instalado no seu computador.

---

## 1. Baixando o Aplicativo

Baixe a versão mais recente no GitHub:
- **dfieldX:** https://tinyurl.com/dfieldx-ufpr
- **pplaneX:** https://tinyurl.com/pplanex-ufpr

Salve o arquivo `.jar` baixado em um local de fácil acesso, como sua Área de Trabalho ou pasta de Downloads.

---

## 2. Windows

### Instalando o Java
1. Abra o navegador e acesse: https://adoptium.net/
2. Baixe o Eclipse Temurin Java 21 LTS para Windows x64.
3. Execute o instalador baixado.
4. Em "Custom Setup" (Instalação Personalizada), certifique-se de que as opções "Add to PATH" e "Associate .jar files" estejam habilitadas.
5. Conclua a instalação.

### Verificando e Executando
Abra o Prompt de Comando e digite:
  java -version

Para executar o programa:
  java -jar dfieldX.jar
ou
  java -jar pplaneX.jar

---

## 3. macOS

### Instalando o Java
1. Acesse https://adoptium.net/
2. Baixe o Java 21 LTS para macOS (escolha Apple Silicon para M1/M2/M3/M4 ou Intel para Macs mais antigos).
3. Execute o instalador e siga as instruções.

### Verificando e Executando
Abra o Terminal e digite:
  java -version

Para executar o programa:
  java -jar dfieldX.jar
ou
  java -jar pplaneX.jar

### 🍎 Aviso do Gatekeeper no macOS
Se o macOS bloquear o aplicativo dizendo que "não pode verificar se está livre de malware":
**Método 1 (Interface):** Abra Ajustes do Sistema → Privacidade e Segurança → Role para baixo e clique em "Abrir Mesmo Assim".
**Método 2 (Terminal):** Execute `xattr -d com.apple.quarantine *.jar`

---

## 4. Linux

**Ubuntu, Debian, Linux Mint:**
  sudo apt update
  sudo apt install openjdk-21-jre

**Fedora, Rocky Linux, AlmaLinux:**
  sudo dnf install java-21-openjdk

**Arch Linux, Manjaro:**
  sudo pacman -S jre21-openjdk

### Verificando e Executando
Abra o Terminal e digite:
  java -version

Para executar o programa:
  java -jar dfieldX.jar
ou
  java -jar pplaneX.jar

---

## Solução de Problemas
- **Nada acontece ao clicar duas vezes no JAR:** Tente sempre executar pelo terminal usando `java -jar <nome-do-arquivo>.jar` para ver as mensagens de erro.
- **Unsupported Class Version Error:** Você tem uma versão antiga do Java. Instale o Java 21 ou superior.
