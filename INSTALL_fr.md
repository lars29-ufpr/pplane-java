# Guide d'Installation — pplaneX

## Prérequis
* **Java 17** ou supérieur (OpenJDK recommandé).
* **Apache Maven** (pour la compilation).

## Compilation
Clonez le dépôt et compilez en utilisant Maven :
```bash
git clone https://github.com/lars29-ufpr/pplane-java.git
cd pplane-java
mvn clean package
```

## Exécution
Exécutez le fichier JAR généré dans le dossier `target` :
```bash
java -jar target/pplaneX.jar
```
Pour forcer l'exécution dans une langue spécifique (ex: Français) :
```bash
java -Duser.language=fr -Duser.country=FR -jar target/pplaneX.jar
```
