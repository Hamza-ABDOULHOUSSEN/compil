# Projet de compilation (2021-2022)

## Auteurs :
* BOULECHFAR Sami (IAMD)
* KHATIB Maha (SIE)
* ABDOULHOUSSEN Hamza (IAMD)


## Contenu du répertoire
* [sujet du projet](sujet-Projet-2021-22.pdf)
* dossier Code : contenant le code
* dossier Rapports : contenant les rapports d'activité

## Script test
Les tests sont placés dans le dossier `Code/examples`

### Le fichier launch.sh permet de lancer la génération d'arbre syntaxique pur, de l'AST, de la TDS ou de lancer tous les tests
Dans Code
éxécuter  
```
./launch.sh
```

## Génération d'arbre syntaxique pur, de l'AST ou de la TDS

* Il faut d'abord choisir l'objet généré (insérer 0 1 ou 2)

```
0 : complete tree
1 : ast
2 : tds
3 : test all complete tree
4 : generate all ast
5 : generate all tds
6 : syntax error detection
7 : semantics error detection
Which option [0/1/2/3/4/5/6/7] : 

```

* Ensuite insérer le nom du fichier test dans `examples`

```
test files :
Test_Complet/test.exp
Test_Complet/test_min.exp
Test_Complet/test1.exp
Test_Complet/test3.exp
Test_Complet/test2.exp
Test_Complet/test6.exp
Test_Complet/test7.exp
Test_Complet/test5.exp
Test_Complet/test4.exp
...

Which test from examples (ex Test_Complet/test) : 
```

* Puis, pour l'AST ou la TDS choisir le nom des fichiers générés dans out 

```
Which name for the ast :
```

* Enfin, si l'AST ou la TDS existe déjà, choisir d'écraser le fichier ou non
```
file already exists
overwrite [y/n] : 
```

## Lancement de tous les tests
* L'option 3 permet de verifier la syntaxe sur tous les tests présents dans examples

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

* L'option 4 permet de générer tous les ast pour les tests valides présents dans `examples`

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

* L'option 5 permet de générer tous les ast pour les tests sémantiquement correctes présents dans `examples`

```
=================== Test_Semantique ====================

=========== TDS creation : annuaire ===========
[+] TDS file dot generation
[+] TDS file svg generation
### ✅ ✅ ✅ : Done, files are in out ###

=========== TDS creation : codestruct ===========
[+] TDS file dot generation
[+] TDS file svg generation
### ✅ ✅ ✅ : Done, files are in out ###
```


* L'option 6 lance les exemples avec erreurs pour vérifier leurs détectitons

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

* L'option 7 lance les exemples avec erreurs sémantiques pour vérifier leurs détectitons

```
=================== Test_Erreur_Semantique ====================

=========== Semantics check : cond1Y ===========
[+] TDS file dot generation
Erreur condition : == au lieu de =
### ✅ ✅ ✅ : The error was detected ###

=========== Semantics check : divis_zero1Y ===========
[+] TDS file dot generation
Erreur division par zéro
### ✅ ✅ ✅ : The error was detected ###

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
./launch.sh 2 Test_Complet/test tree
```

Génère la TDS pour le fichier test.exp dans les fichiers tree.dot et tree.svg.  
La commande écrase les fichiers déjà présents.

```
./launch.sh 3
```
Verifie la lecture des tests sans erreur par la grammaire

```
./launch.sh 4
```
Génère l'AST pour tous les tests sans erreur

```
./launch.sh 5
```
Génère la TDS pour tous les tests sans erreur

```
./launch.sh 6
```
Vérifie la détection d'erreur syntaxique pour les tests avec erreurs

```
./launch.sh 7
```
Vérifie la détection d'erreur sémantique pour les tests avec erreurs

## Tester si le code fonctionne en C
Pour tester si le code fonctionne ou pour voir l'erreur donnée
il faut utiliser le script test_C_code.sh avec le nom du test en paramètre

```
./test_C_code.sh Test_Semantique/codestruct 
```

On peut aussi lancer le script et indiquer ensuite le test :

```
./test_C_code.sh 
```

affiche

```
Which test from examples (ex Test_Semantique/codestruct) : 
```
