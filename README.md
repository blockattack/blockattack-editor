# blockattack-editor
Editor for puzzles to Block Attack - Rise of the Blocks

The game can be found at http://blockattack.sourceforge.net/

##Screen shot
![Block Attack - Rise of the Blocks puzzle level editor v2](/extra/screenshots/blockattack_editor.png?raw=true "Screen shot of a single level")

##Dependencies
  * JDK 7+ (Open or Oracle)
  * maven

##Building
While standing in the base directory (with the pom.xml) type:
```
mvn package
```
If all dependencies are installed it should compile right away.
The result is: target/blockattack_editor-1.0-SNAPSHOT.jar.

It can be run with: 
```
java -jar target/blockattack_editor-1.0-SNAPSHOT.jar
```

##Licence 
The Editor is licensed under GPL v2 or later like the game
