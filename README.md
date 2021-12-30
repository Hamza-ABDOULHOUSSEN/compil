# Projet de compilation (2021-2022)

## Auteurs :
* BOULECHFAR Sami (IAMD)
* KHATIB Maha (SIE)
* ABDOULHOUSSEN Hamza (IAMD)


## Contenu du répertoire
* [sujet du projet](sujet-Projet-2021-22.pdf)
* dossier Code : contenant le code

## Script test
Les tests sont placés dans le dossier `Code/examples`

### Le fichier launch.sh permet de lancer la génération d'arbre syntaxique pur ou de l'AST ou de lancer tous les tests
Dans Code
éxécuter  
```
./launch.sh
```

## Génération d'arbre syntaxique pur ou de l'AST

* Il faut d'abord choisir l'arbre (insérer 0 ou 1)

```
0 : complete tree
1 : ast
2 : test all complete tree
3 : generate all ast
4 : syntax error detection
Which option [0/1/2/3/4] : 
```

* Ensuite insérer le nom du fichier test dans `examples`

```
test files :
Test_Complet/test.exp
Test_Complet/test_min.exp
Test_Unaire/expr.exp
Test_Unaire/decl_typ.exp
Test_Unaire/struct.exp
Test_Unaire/decl_fct.exp
codestruct.exp
tdsTest.exp

Which test from examples (ex Test_Complet/test) : 
```

* Puis, pour l'AST choisir le nom des fichiers générés dans out 

```
Which name for the ast :
```

* Enfin, si l'AST existe déjà, choisir d'écraser le fichier ou non
```
file already exists
overwrite [y/n] : 
```

## Lancement de tous les tests
* L'option 2 `check` permet de lancer tous les tests présents dans `examples`

```
=========== Analyse : test1 ===========
[+] Creation parser
[+] Compilation
[+] Analyse
### ✅ ✅ ✅ : test passed ###

=========== Analyse : test2 ===========
[+] Creation parser
[+] Compilation
[+] Analyse
### ❌ ❌ ❌ : test failed ###
```

* L'option 3 permet de générer tous les ast pour les tests valides présents dans `examples`

```
========================================================== Ast creation test ==========================================================

=========== Creation arbre : test1 ===========
[+] Creation parser
[+] Compilation
[+] Generation ast file dot
[+] Generation ast file svg
### ✅ ✅ ✅ : Done, files are in out ###

=========== Creation arbre : test2 ===========
[+] Creation parser
[+] Compilation
[+] Generation ast file dot
### ❌ ❌ ❌ : ERROR ###
```

* L'option 4 lance les exemples avec erreurs pour vérifier leurs détectitons

```
========================================================== Syntax error detection ==========================================================

=========== Creation arbre : test1X ===========
[+] Creation parser
[+] Compilation
[+] Generation ast file dot
### ✅ ✅ ✅ : The error was detected ###

=========== Creation arbre : test2X ===========
[+] Creation parser
[+] Compilation
[+] Generation ast file dot
[+] Generation ast file svg
### ❌ ❌ ❌ : The error was not seen ###
### files are in out ###
```

### Raccourci launch.sh
* Les paramètres peuvent être donnés directement à launch.sh

Exemple :  
```
./launch.sh 0 test
``` 

Renvoie l'arbre syntaxique pur pour le fichier test.exp

```
./launch.sh 1 Test_Complet/test tree
```

Génère l'AST pour le fichier test.exp dans les fichiers tree.dot et tree.svg.  
La commande écrase les fichiers déjà présents.

```
./launch.sh 2
```
Verifie la lecture des tests sans erreur par la grammaire

```
./launch.sh 3
```
Génère l'AST pour tous les tests sans erreur

```
./launch.sh 4
```
Vérifie la détection d'erreur syntaxique pour les tests avec erreurs

