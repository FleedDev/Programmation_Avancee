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

## **semaine 2 TP n°1 - Programmation Avancée**

### Objectif de la séance

L'accent a été mis sur la gestion des threads, notamment avec les méthodes resume() et suspend() des threads.

### 1. Utilisation de `suspend()` et `resume()`

Ensuite, on a essayé d'utiliser les méthodes **suspend** et **resume** pour contrôler l'exécution des threads, 
en particulier pour stopper et reprendre le déplacement du mobile. Comme ces méthodes sont **déprécated**, ça n'a donc pas fonctionné.
### 2. Pourquoi `wait()` marche sur `run()` mais pas sur `UneFenetre`

Ce qui a bien marché, c'est quand on a utilisé les threads directement dans la méthode `run()` de la classe `UnMobile` en utilisant la methode `wait()`.
La raison, c'est que le cycle de vie du thread, comme on le voit sur le schéma, se déroule à travers les étapes d'exécution (`run()`), de blocage (`wait()`), et de déblocage (`notify()`).
Donc `UneFenetre` initialise le thread mais `wait()` intervient sur le Runnable 



### 3. Cycle de vie du thread

On voit bien que tout passe par les états **Prêt à l'exécution**, **Exécute**, et **Bloque**. Le thread passe par ces étapes en fonction des actions qu’on lui demande (comme `wait()` et `notify()`)

<img src="https://cdn.discordapp.com/attachments/1245914491284226080/1296791586113589300/image.png?ex=671392c7&is=67124147&hm=328af3e71d26ea96e3f77bb4c92a911d61a6a317fda240fb23caba75bc3ce346&">

## **semaine 3  TP n°1/2 - Programmation Avancée**