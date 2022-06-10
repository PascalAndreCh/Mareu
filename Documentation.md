# OpenClassrooms


## Application **MA REUNION**

Les listes des collaborateur et des salles sont fixées par avance.

Il y a 20 collaborateurs avec comme information, le nom, l'adresse mail, le service où il officient, leur qualification.
 
Il y a 10 Salles dont les information données sont : le numéro de salle, le nom de la salle, l'étage où elle se trouve et le nombre maximum de personnes qu'elle peut accueillir.


## Tour d'horizon de l'application

pour lancer l'application, cliquer sur son icone
  
![](images/Iconedel'application.png) 

Au lancement de l'application, les meetings obsolètes seront supprimés avant l'affichage de la liste  


A l'ouverture de l'application, la liste des meetings déjà créés et non obsolètes s'affichent.  

![](images/EcranAccueil.png)

Vous pouvez filtrer les meetings par date ou par salle  

![](images/Selectiondesfiltres.png)  

Filtré par date  

![](images/Filtrepardate(12-07-22).png) 

Filtré par salle  

Choisissez la salle et validez  

![](images/EcrandesélectiondufiltreRoom.png)  
![](images/ListedechoixdelaSallepourfiltre.png)  
![](images/AffichagedelalistefiltréeparSalle(Sicile).png)  

Reset pour revenir à l'écran initial avec tous les meetings d'affichés.  

![](images/EcranAccueil.png)  


Pour supprimer un meeting, cliquer sur la corbeille et répondez oui à la question  

![](images/Suppressiond'unmeeting.png)  


Pour consulter ou modifier un meeting, cliquer sur la ligne du meeting
L'écran de consultation modification apparait

Chaque zone est modifiable  

* Le titre
* La description détaillée
* la date
* l'heure de départ
* la durée
  * (l'heure de fin n'est pas accéssible, elle est le résultat de l'heure de départ et de la durée)
* la liste des participants (vous pouvez en rajouter, en enlever, en changer)
* la salle  


cliquer sur save meeting pour valider les modifications ou sur la flèche retour pour abandonner  

![](images/EcranConsultationModification.png)  


La validation vous ramène dans l'écran principal  


Pour créer un meeting, cliquer sur le bouton +  

![](images/EcranAccueil.png)  


Vous êtes alors dans l'écran de création  

![](images/EcrandeCréation.png)  


Saisisser les différentes données  


* Le titre vous disposez d'une ligne

* La description, 4 lignes

* La date ne peut être inférieure à la date du jour

* La durée ne peut être suppérieure à 4 heures

* la sélection des participants se fait dans un écran spécifique.  

![](images/EcranSélectiondesParticipants.png)  

* Les participants pouvant être sélectionnés sont indiqués avec une icone grise
* Les participants déjà sélectionnés pour ce meetings ont une icone verte
* Les participants avec une icone rouge ne sont pas disponible, car déjà pris par un autre meeting à la même période (il sagit d'une indication, non bloquante)
* Si vous saisissez plus de 10 participants, vous aures un message d'alerte (non bloquant)  


La sélection de la salle se fait aussi dans un écran à part  

![](images/EcrandeSélectiondelaSalle.png)  

* Les salles sélectionnables ont une icone grise
* La salle sélectiionnée a une icone verte
* Une icone rouge indique que la salle n'est pas disponible (B pour buzy) car déjà prise à la même période par une autre réunion
* ou indique que la salle est trop petite pour le nombre de participants (S pour small). BS signifie qu'elle est trop petite et déjà utilisée.
* après validation, des message vous informent d'éventuels problèmes. L'un d'eux, vous indique si un meilleur choix est possible pour votre réunion
* Par exemple, vous être 4, vous avez choisi une salle pour 10 et il existe un salle pour 5 de libre, l'appli vous proposera cette salle, sans vous l'imposer.  

![](images/EcranCréationavantValidation.png)  


Il n'est pas possible de valider une création si la saisie est incomplète.  





