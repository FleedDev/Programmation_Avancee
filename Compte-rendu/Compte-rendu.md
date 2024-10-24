# **Compte rendu** 

## **semaine 1 TP n°1 - Programmation Avancée**

Lors de ce premier TP, l'objectif était de créer un programme
permettant à un mobile de se déplacer de gauche à droite à l'écran. 
Pour cela, nous avons utilisé une classe qui **implémente JPanel** et qui **étend l'interface Runnable**.

### Qu’est-ce que **Runnable** ?
L'interface **Runnable** en Java permet à une classe de devenir une tâche qui peut être exécutée par un thread. 
Un **thread** est un processus léger permettant de gérer plusieurs actions en parallèle au sein d'une application.
Lorsqu'une classe étend **Runnable**, elle doit nécessairement implémenter la méthode **run()**, 
qui contient le code à exécuter de manière concurrente.

### Le rôle de la méthode **run()**
La méthode **run()** est l'endroit où le comportement du thread est défini.
Dans notre cas, c'est ici que l'on programme le déplacement du mobile de gauche à droite.
Le principe est de mettre à jour la position du mobile à chaque itération d’une boucle dans **run()**,
créant ainsi l'illusion de mouvement fluide à l'écran.

### Pourquoi intégrer le thread dans la classe fenêtre ?
Le **thread** est intégré dans la classe **fenêtre** qui initialise le mobile et permet une première présentation de celui-ci à l'écran.
Le fait de gérer le thread directement dans cette classe permet de mieux organiser le code et d'éviter que la classe du mobile ait trop de responsabilités.
Cela permet aussi de synchroniser le rafraîchissement de l'interface avec l’animation du mobile, en séparant la gestion de l'affichage de celle du mouvement.

 <img src="https://cdn.discordapp.com/attachments/1245914491284226080/1296785980485009408/image.png?ex=67138d8e&is=67123c0e&hm=f577c0010db8de79c9069cfdf2cbc22008934742962d8dd10d472a6cf94e678d&">

Ce diagramme de classes UML montre comment les différentes parties de notre programme interagissent pour créer le mouvement des mobiles dans une fenêtre.

1. **`UneFenetre`** : C'est la classe principale qui crée la fenêtre (en héritant de `JFrame`). Elle initialise les objets `UnMobile` et démarre un thread pour chacun d’eux.

2. **`UnMobile`** : C'est la classe qui représente chaque mobile. Elle hérite de `JPanel` (pour l'affichage) et implémente `Runnable` (pour pouvoir être exécutée dans un thread). Son rôle est de dessiner et de déplacer le mobile dans la fenêtre.

3. **`Runnable`** : Une interface qui oblige `UnMobile` à définir une méthode `run()`. C'est cette méthode qui contient la logique de mouvement du mobile.

4. **`Thread`** : Chaque mobile est lancé dans un thread séparé, ce qui permet à plusieurs mobiles de bouger en même temps.

5. **`TpMobile`** : C'est le point de départ du programme. La méthode `main()` crée la fenêtre et lance l'application.


## **Semaine 2 - TP n°1 : Programmation Avancée**

### Objectif de la séance

Pendant cette séance, on a travaillé sur la gestion des threads en Java, notamment avec l'utilisation de méthodes pour arrêter et reprendre les threads, comme `resume()` et `suspend()`, ainsi que l'alternative avec `wait()`.

### 1. Utilisation de `suspend()` et `resume()`

Au début, on a essayé de contrôler l'exécution des threads avec les méthodes **suspend()** et **resume()** pour stopper et relancer le déplacement du mobile. 
Mais ces méthodes sont **deprecated**, ce qui veut dire qu'elles sont plus recommandées parce qu'elles peuvent causer des problèmes, java nous empêche complètement de les utiliser et envois un message d'erreur s'il on essaye leur activation.

### 2. Pourquoi `wait()` marche dans `run()`, mais pas dans `UneFenetre`

Ensuite, on a testé une autre solution en utilisant les méthodes **wait()**  pour gérer l'exécution des threads. 
Cette fois, on a placé le code directement dans la méthode `run()` de la classe `UnMobile`, et là ça a fonctionné. 
En fait, **`wait()` suspend le thread**, mais ça ne peut marcher que si le thread est déjà en train d'exécuter son code.
Dans notre programme, `UneFenetre` se contente de démarrer le thread avec `start()`, mais ce n'est pas elle qui contrôle directement son exécution.
C’est la méthode `run()` dans `UnMobile` qui gère tout le déroulement du thread, et donc c’est là qu’on peut utiliser `wait()` pour le mettre en pause.

### 3. Cycle de vie d’un thread

Le thread passe par différents états : **Prêt à l'exécution**, **En exécution**, et **Bloqué**. Quand on appelle `wait()`, 
le thread passe en état bloqué jusqu'à ce qu’il soit réveillé par un appel à `notify()`. C'est donc une manière plus efficace de gérer les threads que `suspend()` et `resume()`, 
qui peuvent poser problème.

<img src="https://cdn.discordapp.com/attachments/1245914491284226080/1296791586113589300/image.png?ex=671392c7&is=67124147&hm=328af3e71d26ea96e3f77bb4c92a911d61a6a317fda240fb23caba75bc3ce346&">

## **Compte rendu - Semaine 3 - TP n°1/2 - Programmation Avancée**

### Partie 1 : TP2 - Synchronisation avec `synchronized` et Sémaphore Binaire

Cette semaine, on a travaillé sur la gestion de la **synchronisation des threads** en utilisant deux méthodes principales : d'abord avec le mot-clé `synchronized`, puis avec des **sémaphores binaires**. 
L'objectif de cet exercice était de bien gérer l'accès à une portion critique du code pour éviter que plusieurs threads n'entrent en conflit.

#### Utilisation de `synchronized` avec une Classe Vide `Exclusion`

Dans un premier temps, on a utilisé le mot-clé `synchronized` pour contrôler l'accès à la section critique.
Pour ce faire, on a créé une classe vide qu'on a appelée `Exclusion`, qui servait de verrou. Chaque thread devait obtenir ce verrou avant d'accéder à la section critique (c'est-à-dire la partie du code où il imprimait des lettres).
La classe `Exclusion` ne contenait aucune donnée, mais elle était utile pour appliquer le `synchronized` et ainsi empêcher plusieurs threads d'exécuter cette portion de code en même temps. 
Cela permettait d'éviter que les affichages se chevauchent, ce qui aurait donné un résultat incohérent.
On a compris que la section critique était la partie qui affichait les lettres, et c’est cette portion qu’on a protégée avec le `synchronized`.

#### Sémaphores et Sémaphores Binaires

Ensuite, on a exploré une autre méthode de synchronisation : les **sémaphores**, plus particulièrement les **sémaphores binaires**. 
Un sémaphore est un mécanisme qui permet de contrôler l'accès à une ressource partagée. Un sémaphore binaire fonctionne un peu comme un verrou, où une seule unité (ou thread) peut accéder à la ressource à la fois.
Contrairement à `synchronized`, qui verrouille directement une section de code, les sémaphores offrent une approche plus flexible. 
Ils peuvent être initialisés avec une valeur (0 ou 1), et ils bloquent l'accès tant que la valeur est à zéro. 
Cela nous a permis de gérer plus précisément l’accès à la section critique.
On a utilisé les méthodes `syncWait()` pour demander l'accès à la ressource et `syncSignal()` pour la libérer une fois qu'on a terminé. 
Cela a permis à plusieurs threads d'afficher des lettres successivement, tout en respectant l'ordre et en évitant les interférences.

### Partie 2 : Reprise du TP1 avec Sémaphores et Ressource Critique

Dans cette partie, on a repris le TP1 pour intégrer la gestion des sémaphores dans le contexte de la simulation de mouvements graphiques de "mobiles".
L’idée était assez similaire : plusieurs objets (mobiles) se déplacent sur l’interface graphique, et il fallait s’assurer que leurs mouvements ne se bloquent pas ou ne se chevauchent pas de manière incorrecte.
Chaque mobile correspondait à un thread indépendant, et chaque thread devait accéder à une ressource partagée : l’espace graphique. On a donc identifié une portion critique dans le code, 
où chaque mobile traversait une partie de l’écran qui devait être exclusive à lui pendant son passage, 
un peu comme si chaque mobile empruntait un "couloir".
Le sémaphore était utilisé pour verrouiller cet espace pendant que le mobile y était, afin qu'un autre mobile ne puisse pas y entrer en même temps.

#### Problèmes de Synchronisation et Sémaphores Générales

On a rencontré plusieurs problèmes de synchronisation, notamment avec les ressources multiples. 
On avait une ressource partagée (l’espace d’affichage) qui était divisée en plusieurs sections, et chaque mobile devait traverser ces sections successivement. 
Pour gérer cela, on a utilisé un **sémaphore général**, qui permettait de contrôler plusieurs ressources simultanément, au lieu d'un simple verrou binaire.
L'objectif était d'assurer que plusieurs threads puissent avancer, tout en se synchronisant correctement dans les zones critiques pour éviter des blocages ou des interruptions mutuelles. 
Cela nécessitait d’ajuster les valeurs du sémaphore pour que chaque mobile sache quand il pouvait entrer dans la zone critique et quand il devait attendre.
L’un des défis majeurs était de bien gérer les threads dans une boucle et d’identifier les moments où il fallait utiliser `wait()` et `signal()`. 
Des erreurs de synchronisation pouvaient entraîner des situations où certains mobiles ne franchissaient jamais la zone critique. 


## **Compte rendu - Semaine 4 - TP n°4 - Programmation Avancée**

### **Introduction**

Dans ce TP, nous avons appris à utiliser un **moniteur** pour gérer la synchronisation entre plusieurs threads en programmation concurrente. 
Le but était de créer une boîte aux lettres (BAL) partagée, où un **Facteur** dépose des lettres et un **Destinataire** les retire.
### **Objectif du TP**

Le but du TP était de comprendre comment un moniteur fonctionne et de l'utiliser pour permettre à plusieurs threads de communiquer entre eux sans créer de conflits. 
En gros, on devait s'assurer que le Facteur puisse déposer des lettres dans la BAL et que le Destinataire puisse les retirer, mais de façon organisée.

### **Qu'est-ce qu'un moniteur ?**

Un **moniteur** est un outil qui sert à **contrôler l'accès** des threads à des ressources partagées (comme des variables ou des objets). 
Il permet à un seul thread d'accéder à la ressource à la fois, pour éviter les erreurs. 
Par exemple, si deux threads essaient de modifier la même donnée en même temps, cela pourrait causer des résultats incorrects. Le moniteur garantit que ça n'arrive pas.

### **Déroulement du TP**

1. **Création de la BAL** :
   Nous avons d'abord créé une classe `BAL` (Boîte Aux Lettres) qui contient une liste de lettres. Cette classe utilise un moniteur pour gérer l'attente entre le Facteur (qui dépose les lettres) et le Destinataire (qui les retire).

2. **Utilisation du Moniteur** :
   La classe `BAL` agit comme un moniteur. Nous avons utilisé les méthodes `synchronized`, `wait()` et `notify()` pour s'assurer que :
    - **`deposer()`** : Le Facteur dépose une lettre et "réveille" le Destinataire pour qu'il sache qu'il y a une lettre à récupérer.
    - **`retirer()`** : Le Destinataire attend que le Facteur dépose une lettre avant de la retirer.

3. **Threads Facteur et Destinataire** :
   Nous avons ensuite créé deux threads, un pour le Facteur qui dépose des lettres toutes les secondes, et un pour le Destinataire qui les retire de façon aléatoire. Le programme s'arrête quand le Destinataire reçoit la lettre "Q".

