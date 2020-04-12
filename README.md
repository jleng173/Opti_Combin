# Opti_Combin

Stratégie Glouton 

Pour implémenter l’algorithme glouton nous avons choisi une approche “duale”, donc se concentrant sur le choix de la base de données jusqu’à couvrir toutes les entreprises.
L’heuristique que nous avons retenue se fait sur le prix d’une base de données, c’est à dire que le choix de la base lors du parcours se fera sur le prix le plus bas d’une base parmis toutes les bases à dispositions.

Notre algorithme va donc se dérouler comme ceci : 
On va récupérer la liste des bases données présentes dans un fichier “ListeBase”.
On choisi la base de donnée la moins chère et on récupère le prix de celle-ci.
On retire la base de la liste des bases et on réitère l’action précédente jusqu’à avoir parcouru toutes les entreprises voulu.
On affiche le prix final ainsi que toute les bases de données achetées.

En faisant tourner l’algorithme on voit très vite les limites de celui-ci. Par exemple les bases retournées ne contiennent pas forcément une des entreprises voulues, ce qui fait que plusieurs bases peuvent être achetées sans utiliser la moindre information à l’intérieur de celle-ci.

