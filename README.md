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

### Le fichier launch.sh permet de lancer la génération d'arbre syntaxique pur ou de l'AST
Dans Code
éxécuter  `./launch.sh `

* Il faut d'abord choisir l'arbre (insérer 0 ou 1) : 

```
0 : complete tree
1 : ast
Generate complete tree or ast [0/1] : 
```

* Ensuite insérer le nom du fichier test dans examples 

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

## Génération AST pour tout les fichiers test
Il est possible de générer l'AST pout tout les fichiers test sans erreur syntaxique (sans X) dans `examples`.  
Si les AST existent, ils sont écrasés.

Pour cela, il faut éxécuter  
```
./bash/launchalltree.sh
```
