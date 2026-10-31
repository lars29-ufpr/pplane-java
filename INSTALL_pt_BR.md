> **Language / Idioma:** [English](INSTALL.md) | **Português (BR)** | [Español](INSTALL_es.md) | [Français](INSTALL_fr.md) | [Русский](INSTALL_ru.md) | [Български](INSTALL_bg.md) | [Українська](INSTALL_uk.md) | [Deutsch](INSTALL_de.md) | [Italiano](INSTALL_it.md)

# Guia de Instalação: pplaneX e dfieldX

Este guia explica como instalar e executar o **pplaneX** e o **dfieldX** no Windows, macOS e Linux.

Estes aplicativos requerem o **Java 21 (LTS)** ou uma versão mais recente. Não é necessária nenhuma instalação prévia do Java. Este guia pressupõe que o Java ainda não está instalado no seu computador.

---

## 1. Download do Aplicativo

Baixe a versão mais recente no GitHub:
- **pplaneX:** https://tinyurl.com/pplanex-ufpr
- **dfieldX:** https://tinyurl.com/difieldx-ufpr

Salve o arquivo `.jar` baixado em um local onde você possa encontrá-lo facilmente depois, como na sua Área de Trabalho ou pasta de Downloads.

---

## 2. Windows

### Parte A: Instalar o Java
1. Abra o seu navegador e acesse: https://adoptium.net/
2. Baixe o **Eclipse Temurin Java 21 LTS** para Windows x64.
3. Execute o instalador baixado.
4. Na tela "Custom Setup" (Configuração Personalizada), certifique-se de que as opções **"Add to PATH"** (Adicionar ao PATH) e **"Associate .jar files"** (Associar arquivos .jar) estejam ativadas.
5. Conclua a instalação.

### Parte B: Verificar e Executar
Abra o Prompt de Comando e execute:
```bash
java -version
```
Qualquer versão 21 ou superior é suportada. Para executar o aplicativo:
```bash
java -jar pplaneX.jar
```
*(ou substitua por `dfieldX.jar`)*

---

## 3. macOS

### Parte A: Instalar o Java
1. Acesse https://adoptium.net/
2. Baixe o **Java 21 LTS** mais recente para macOS.
   * *Dica:* Escolha o instalador `.dmg` (ex: `jdk-21_macos-x64_bin.dmg` para Macs com processador Intel, ou o equivalente `aarch64` para Apple Silicon M1/M2/M3/M4).
3. Execute o instalador `.dmg` baixado e siga as instruções na tela.

### Parte B: Organizar e Executar (Contornando o Gatekeeper)
O macOS inclui um recurso de segurança que bloqueia aplicativos de desenvolvedores não identificados. Siga estes passos na primeira execução:

1. **Organizar:** Para uma melhor organização, mova os arquivos `pplaneX.jar` e `dfieldX.jar` baixados para a sua pasta **Aplicativos** (Applications).
2. **Não clique duas vezes** no arquivo `.jar` na primeira vez. Se você fizer isso, o macOS exibirá um aviso e impedirá a abertura.
3. Em vez disso, **clique com o botão direito** (ou Control-clique) no arquivo `.jar` e selecione **Abrir** no menu de contexto.
4. Uma caixa de diálogo aparecerá pedindo confirmação. Clique em **Abrir** novamente.

*Nota: Você só precisa fazer isso uma vez. Para todos os usos subsequentes, basta clicar duas vezes no arquivo `.jar` para iniciar o aplicativo normalmente.*

### Executar via Terminal (Alternativa)
Se você prefere a linha de comando, abra o Terminal e execute:
```bash
java -jar /Applications/pplaneX.jar
```

---

## 4. Linux (Ubuntu, Debian, Linux Mint)

Instalar o Java:
```bash
sudo apt update
sudo apt install openjdk-21-jre
```
Verificar:
```bash
java -version
```
Executar:
```bash
java -jar pplaneX.jar
```

---

## 5. Linux (Fedora, Rocky Linux, AlmaLinux)

Instalar o Java:
```bash
sudo dnf install java-21-openjdk
```
Verificar:
```bash
java -version
```
Executar:
```bash
java -jar pplaneX.jar
```

---

## 6. Linux (Arch Linux, Manjaro)

Instalar o Java:
```bash
sudo pacman -S jre21-openjdk
```
Verificar:
```bash
java -version
```
Executar:
```bash
java -jar pplaneX.jar
```

---

## Solução de Problemas

### Nada Acontece ao Clicar Duas Vezes no Arquivo JAR
Execute o aplicativo a partir de um terminal para ver a saída do sistema:
```bash
java -jar pplaneX.jar
```

### Erro "Unsupported Class Version"
Você está executando uma versão antiga do Java. Instale o Java 21 ou uma versão mais recente.

### O Aplicativo Falha ao Iniciar
Verifique a versão do seu Java:
```bash
java -version
```
Relate quaisquer mensagens de erro juntamente com a versão do seu Java ao desenvolvedor.
