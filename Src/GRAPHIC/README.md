# WeatherClick Graphic

Auteurs :
* Jean-Gabriel MASSICOT <jgmassicot@gmail.com>
* Axel DEVAUX <devaux25@gmail.com>
Github :
* massicotjgab
* DvAx
edit : 05/04/2020

## Résumé

Cet élément graphique est codé en java. Il a été conçu avec eclipse sous windows.
Toutes les informations ci-dessous sont à remettre en question pour les utilisateurs de Linux.

Cette application sert à représenter les informations envoyées par le module WeatherClick installé sur le shield de développement STM32f4 Dicovery Shield.
La carte main est une STM32F407.
Actuellement la lecture série n'est pas implémentée. La partie graphique lit dans un fichier les données au format suivant :

Temperature:Humidite:Pression

Avec :
* Temperature en degré Celsius	(°C)
* Humidité en pourcentage	(%)
* Pression en HectoPascal	(hPa)

* Ces valeurs sont situées dans le repertoire :
  * WeatherClick/Src/GRAPHIC/WeatherClick/bin
* Dans le fichier
  * Values.txt
* Ces valeurs sont des doubles
  * Vous pouvez donc entrer des nombres à virgule
  * Cela sera tronqué au deuxième chiffre après la virgule pour l'affichage
  * Attention, la virgule doit être représentée par un "."

## Préparation

### Raccourci launcher
Un raccourci java est présent au chemin
* WeatherClick/Src/GRAPHIC/WeatherClick

Il permet de lancer l'application java. Celui ci est susceptible de ne pas pointer au bon endroit en fonction de votre dépot git local.
Il faut donc faire :
* Click droit sur le raccourci
* Propriétés
* Modifier le champ "Cible"
  * Sélectionnez l'ensemble du chemin et tappez "javaw Affiche_Data"
* Modifier le champ "Démarrer dans"
  * Mettez le chemin complet du répertoire
    * WeatherClick/Src/GRAPHIC/WeatherClick/bin

### Raccourci txt
Un raccourci vers le fichier de valeurs est présent au chemin
* WeatherClick/Src/GRAPHIC/WeatherClick

Il pointe vers
* WeatherClick/Src/GRAPHIC/WeatherClick/bin/Values.txt

Il permet d'accéder directement au fichier. Celui ci est susceptible de ne pas pointer au bon endroit en fonction de votre dépot git local.
Il faut donc faire :
* Click droit sur le raccourci
* Propriétés
* Modifier le champ "Cible"
  * Mettez le chemin complet du fichier
    * WeatherClick/Src/GRAPHIC/WeatherClick/bin/Values.txt
* Modifier le champ "Démarrer dans"
  * Mettez le chemin complet du répertoire
    * WeatherClick/Src/GRAPHIC/WeatherClick/bin

## Lancement

Pour lancer il suffit de :
* Double cliquer sur le raccourci
* Modifier en direct les valeurs par défaut
* Enregistrer le fichier
* Les valeurs se modifient automatiquement sur l'affichage

Le projet peut aussi être lancé par L'ide Eclipse.