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
### Figure 1: Schema UML du TP1

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

### Figure 2: Schema du cycle de vie d'un Thread

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


## **Compte rendu - Semaine 4 - TP n°3 - Programmation Avancée**

### **Introduction**

Dans ce TP, nous avons appris à utiliser un **moniteur** pour gérer la synchronisation entre plusieurs threads en programmation concurrente. 
Le but était de créer une boîte aux lettres (BAL) partagée, où un **Facteur** dépose des lettres et un **Destinataire** les retire.

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

## **Compte rendu - Semaine 5 - TP n°3 - Programmation Avancée**

### **Compte rendu - Semaine 5 - TP n°3 - Programmation Avancée**

### **Introduction**

Cette semaine, nous avons abordé le **design pattern Producteur-Consommateur** pour gérer les échanges d’objets entre threads. L'objectif était de faire fonctionner et comprendre le code fourni sur le blog de Pomard, qui utilise l'interface **BlockingQueue** pour implémenter une file d’attente partagée.

### **Code de Pomard et modifications**

Nous avons utilisé le code de Pomard sur les files d’attente concurrentes comme base. Cependant, certaines initialisations manquaient pour que les classes soient fonctionnelles :
- **Dans la classe `Boulanger`** : Nous avons ajouté l'initialisation de la `Boulangerie` pour permettre l’ajout de pains.
- **Dans la classe `Mangeur`** : Nous avons ajouté l'initialisation de `Boulangerie` ainsi qu’une variable `rand` pour générer des intervalles aléatoires entre les consommations de pain.

### **Le Design Pattern Producteur-Consommateur et `BlockingQueue`**

Dans ce TP, `BlockingQueue` a permis de mettre en œuvre le pattern Producteur-Consommateur de manière très simple et efficace :

1. **Producteurs (Boulangers)** : Les threads Boulangers utilisent la méthode `offer(pain, 200, TimeUnit.MILLISECONDS)`. Cela leur permet de tenter de déposer un pain, en attendant jusqu’à 200 ms si la file est pleine. Si le dépôt échoue, le Boulanger affiche un message indiquant que la file est pleine.
   
2. **Consommateurs (Mangeurs)** : Les threads Mangeurs utilisent la méthode `poll(200, TimeUnit.MILLISECONDS)`. Cela leur permet de retirer un pain s'il y en a, avec une attente maximale de 200 ms si la file est vide. Si aucun pain n’est disponible après ce délai, un message "j'ai faim" est affiché.

`BlockingQueue` intègre les méthodes `offer()` et `poll()`, qui gèrent le blocage en fonction de la disponibilité de la file. Cela simplifie grandement la gestion des échanges entre producteurs et consommateurs et garantit un accès sécurisé aux ressources partagées.

### **Déroulement du TP**

1. **Mise en place de la Boulangerie** :
   - Nous avons utilisé `ArrayBlockingQueue` (taille limitée à 20) pour stocker les pains.
   - Les méthodes `depose()` et `achete()` utilisent respectivement `offer()` et `poll()` pour gérer les ajouts et les retraits de manière asynchrone, avec des délais d'attente.

2. **Création des threads `Boulanger` et `Mangeur`** :
   - **Boulanger** : Chaque Boulanger ajoute un pain toutes les secondes. Si la file est pleine, un message indique l’échec.
   - **Mangeur** : Les Mangeurs consomment des pains à intervalles aléatoires (entre 0 et 1 seconde). En cas de succès, ils affichent un message "miam miam"; sinon, ils affichent "j’ai faim".

3. **Simulation** :
   - Nous avons lancé 5 threads Boulangers et 2 threads Mangeurs. Cette configuration nous a permis de tester le pattern Producteur-Consommateur, avec plusieurs Boulangers ajoutant et des Mangeurs retirant des pains sans conflits.

### **Observations**

Le pattern Producteur-Consommateur, en s'appuyant sur `BlockingQueue`, permet de simplifier la synchronisation des threads. Plutôt que d’utiliser explicitement des mécanismes de synchronisation comme `synchronized`, `wait()`, ou `notify()`, `BlockingQueue` gère automatiquement le blocage et la reprise des threads en fonction de l'état de la file d'attente.


J'ai utilisé ChatGPT pour corriger des fautes et aussi pour la mise en page en Markdown des 2 premieres semaines du compte-rendu parce que je l'avais fais sur un google doc
Je vous prie de m'excuser d'avoir fait ce commit en retard j'ai oublié de push mon compte-rendu de la semaine 5, la semaine dernière quand nous étions en I21 et je n'ai pas eu l'occasion de le faire quand je m'y suis apperçu parce que j'était absent le mercredi et le jeudi pour cause de maladie et ce vendredi je n'ai pas eu le temps d'aller le faire 


   

