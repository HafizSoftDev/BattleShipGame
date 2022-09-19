# BattleShipGame
 <b>Introduction</b>
 
 A Java Console Application of a Battle Ship Game. Project was undertaken to practice mutli-dimensional array; 2D array.
 - Procedural programming paradigm employed for this project.
 
 
 <b>Project Brief</b>
 
 <u>1. Purpose</u>
 
 Develop a higher level of aptitude in utilizing arrays in JAVA. 
 
 <u>2. Objectives</u>
 
 	i) Recreate the game of battleships. 
 
 <u>3. Requirements</u>
 
 	i)	A player will place 5 of their ships on a 10 by 10 grid. 
 	ii)	The computer and the player will deploy five ships on the same grid. 
 	iii)	Once the game starts the player and computer take turns, trying to sink each other's ships by guessing the coordinates to "attack". 
 	iv)	The game ends when either the player or computer has no ships left
 
 <u>4. Phases in application</u>
 
 	i)	Step 1 – Create the ocean map
			- ocean map is represented by a 10 by 10 grid of different characters. 
			- The grid is managed by a 2D array; saving the following values:
				i)  where user and computer decide to place their ships
				ii) when someone tries to attack a location and misses. 
			- 2D Array empty at start of game and will change what is stored at each index of the array according to game phases.
 
 	ii)	Step 2 – Deploy player’s ships
			- Java Scanner class to be employed in allowing the user to enter in input.
			- Player should deploy 5 ships; each ship individually stored in a single index of the array as a special character.
			- Scanner object input validation:
				Re-prompt user for valid input until legal coordinates chosen for the ship.
				i)  Cannot place two or more ships on the same location.
				ii) Cannot place ships outside the 10 by 10 grid.
			- Player's ships within the OceanMap are stored as '1' & printed as '@' symbol.
 
 	iii)	Step 3 – Deploy computer’s ships
			- Computer will deploy 5 ships by randomly picking X and Y coordinates.
			- Computer input validation:
				Regenerate random coordinates until all ships are placed appropriately.
				i)  Cannot place ship on a location that is already taken by another existing player/computer ship.
				ii) Cannot place ships outside the 10 by 10 grid.
			- Computer's ships within the OceanMap are stored as '2' & and should be invisible on the ocean map.
			- Generate each line of output each time you successfully place a ship based on random coordinates.
 
 	iv)	Step 4 – Battle
 		
			Player and computer will take turns guessing X and Y coordinates of the opponent’s ships. 
			Every coordinate guessed should be marked so they players know not to guess there again.
			After the player guesses a coordinate it's the computer's turn to guess.				
			Battle phase will continue to run until one of the players is out of ships.
 
 		- Player Turn
 			i) Re-prompt user to enter valid X/Y coordinates; not guessed by player yet and with 10 by 10 grid.
 			ii) Valid guess to evaluate 3 possible results:
 				• Player correctly guessed coordinates of computer’s ship (computer loses ship).
 					Inform user "Boom! You sunk the ship!" & mark this as a hit when printing the map as a "!".
 				• Player entered coordinates of his/her own ship (player loses ship).
 					Inform user "Oh no, you sunk your own ship :(" & mark this as an "x" when printing the map, replacing the "@"
 				• Player missed. No ship on the entered coordinates.
 					Inform user "Sorry, you missed" & mark this as an "-" when printing the map.
 		
 		- Computer Turn
 			i) Computer keep generating random numbers until a valid guess; not guessed by computer yet and with 10 by 10 grid.
 			ii) Valid guess to evaluate 3 possible results:
 				• Computer guessed coordinates of the player’s ship (player loses ship).
 					Inform user "The Computer sunk one of your ships!" & mark this as an "x" when printing the map.
 				• Computer guessed coordinates of its own ship (computer loses ship).
 					Inform user "The Computer sunk one of its own ships" & mark this as a "!" when printing the map.
 				• Computer missed. No ship on guessed coordinates.
 					Inform user "Computer missed" & store information in your map ensuring computer doesn't duplicate guesses.
 
 	v)	Step 5 - Game Over
 		- The game is over when one player or computer has no ship left.
