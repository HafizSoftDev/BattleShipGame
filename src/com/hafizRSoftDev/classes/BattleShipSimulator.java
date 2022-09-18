package com.hafizRSoftDev.classes;

import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/*
 * GAME PHASES
 * 
 * PHASE 1 - INTRODUCTION
 * PHASE 2 - DEPLOYMENT
 * PHASE 3 - START GAME
 * PHASE 4 - END GAME
 * 
*/

public class BattleShipSimulator {
	public static String[][] seascape = new String[10][10];
	public static String[][] playerSeascape = new String[10][10];
	public static String[][] computerSeascape = new String[10][10];
	public static byte ship_X_Coord;
	public static byte ship_Y_Coord;
	public static byte targetX = 0;
	public static byte targetY = 0;
	public static int[] score = {0,0};
	public static Scanner input = new Scanner(System.in);
	public static Random dice = new Random();
	public static boolean invalid = true;

	public static void main(String[] args) {	
	//	PHASE 1 - INTRODUCTION    
		System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty");
		generateSeascape();
	//	PHASE 2 - DEPLOYMENT  
		deployPlayerShips();
		generateSeascape();
		deployComputerShips();
		generateSeascape();
		shipCount();
		printScore();
	//	PHASE 3 - COMMENCE GAME  	
		commenceGame();
	}

	public static void generateSeascape() {
		// x-axis of BattleShip Grid(TOP)!
		System.out.println("\nOcean Map - Player Seascape");
		System.out.println("---------------------------");
		print_X_axis();
		System.out.println();
		//	10 x 10 BattleShip Grid
		for (int row = 0; row < playerSeascape.length; row++) {
			System.out.print(row + "|");
			for (int col = 0; col < playerSeascape[row].length; col++) {
				if (playerSeascape[row][col] == null) {
					System.out.print(" ");
				} else {
					System.out.print(playerSeascape[row][col]);
				}
			}
			System.out.println("|" + row);
		}
		// x-axis of BattleShip Grid(BOT)!
		print_X_axis();
		System.out.println("\n\n");
	}

	public static void print_X_axis() {
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) {
				System.out.print("  " + colIndicator);
			} else if (colIndicator == 9) {
				System.out.print(colIndicator + "  ");
			} else
				System.out.print(colIndicator);
		}
	}
	
	public static void deployPlayerShips(){
		System.out.println("Deploy your ships:");
		System.out.println("------------------");
    	for(int i = 1; i <= 5; i++) {
        	while(true) {
        		System.out.print("\nEnter X coordinate for your " + i + ". ship: ");	
        	do {
        		invalid = true;
        		try {
        			input = new Scanner(System.in);
        			ship_X_Coord = input.nextByte();
        		
        			if(ship_X_Coord >= 0 && ship_X_Coord <=9)
        				invalid = false;
        			else {
        				System.out.println("Please select coordinate within 10 by 10 grid!");
        				System.out.print("Re-Enter X coordinate for your " + i + ". ship: ");
        				invalid = true;
        			}
        		}catch (InputMismatchException e) {
        			System.out.println("InputMismatchException => Invalid Input: Please enter a numerical value(Min 0 - Max 9)!");
        			System.out.print("Re-Enter X coordinate for your " + i + ". ship: ");
        			invalid = true;
        		}
        	}while (invalid);
        		
        	System.out.print("Enter Y coordinate for your " + i + ". ship: ");
        	do {
        		invalid = true;
        		try {
        			input = new Scanner(System.in);
        			ship_Y_Coord = input.nextByte();
        			
        			if(ship_Y_Coord >= 0 && ship_Y_Coord <= 9) {
        				invalid = false;
        			}else{
        			System.out.println("Please select coordinate within 10 by 10 grid!");
        			System.out.print("Re-Enter Y coordinate for your " + i + ". ship: ");
        			invalid = true;
        			}
        		}catch(InputMismatchException e) {
        			System.out.println("InputMismatchException => Input: Please enter a numerical value(Min 0 - Max 9)!");
        			System.out.print("Re-Enter Y coordinate for your " + i + ". ship: ");
        			invalid = true;
        		}		
        	}while (invalid);	
	        	
        	if(Objects.equals(seascape[ship_X_Coord][ship_Y_Coord], null)) {
        		seascape[ship_X_Coord][ship_Y_Coord] = "1";
        		playerSeascape[ship_X_Coord][ship_Y_Coord] = "@";
        		break;
        	}else
        		System.out.println("Unable to place two or more ships on the same location! \n");
        	}
    	}
	}
	
	public static void deployComputerShips() {
		System.out.println("Computer is deploying ships");

		for(int i = 1; i <= 5; i++) {
			while(true) {
				ship_X_Coord =  (byte) dice.nextInt(10);
				ship_Y_Coord =  (byte) dice.nextInt(10);
				if (Objects.equals(seascape[ship_X_Coord][ship_Y_Coord], null)) {
					computerSeascape[ship_X_Coord][ship_Y_Coord] = "2";
					seascape[ship_X_Coord][ship_Y_Coord] = "2";
					System.out.println(i + ". ship DEPLOYED");
					break;
					}
			}	
		}
		for (int i = 0; i <= 100; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void shipCount() {
		int player = 0;
		int computer = 0;
		
		for (int row = 0; row < playerSeascape.length; row++) {
			for (int col = 0; col < playerSeascape[row].length; col++) {
				if (Objects.equals(playerSeascape[row][col], "@")) {
					player++;
				} else if (Objects.equals(computerSeascape[row][col], "2")) {
					computer++;
				}
			}
		}
		Array.setInt(score, 0, player);
		Array.setInt(score, 1, computer);
	}
	
	private static void printScore() {
		System.out.println(" --------------------------------- ");
		System.out.println("|Your ships: " + Array.get(score, 0) + " | Computer ships: " + Array.get(score, 1) + "|");
		for (int i = 0; i <= 100; i++)
			System.out.print("-");
		System.out.println();
	}

	public static void battlePhase() {
		
		System.out.println("\nYOUR TURN");
		System.out.println("---------\n");
		while(true) {
			System.out.print("Enter X coordinate: ");
			do {
				invalid = true;
				try {
					input = new Scanner(System.in);
					targetX = input.nextByte();
				if(targetX >=0 && targetX <= 9)
					invalid = false;
				else {
					System.out.println("Please select coordinate within 10 by 10 grid!");
					System.out.print("Re-Enter X coordinate: ");
					invalid = true;
				}
				}catch(InputMismatchException e) {
					System.out.println("InputMismatchException => Input: Please enter a numerical value(Min 0 - Max 9)!");
					System.out.print("\nRe-Enter X coordinate: ");
					invalid = true;
				}
			}while(invalid);
			
			System.out.print("Enter Y coordinate: ");
			do {
				invalid = true;
				try {
					input = new Scanner(System.in);
					targetY = input.nextByte();
					if(targetY >= 0 && targetY <=9)
						invalid = false;
					else {
						System.out.println("Please select coordinate within 10 by 10 grid!");
						System.out.print("Re-Enter Y coordinate: ");
						invalid = true;
					}
				}catch(InputMismatchException e) {
					System.out.println("InputMismatchException => Input: Please enter a numerical value(Min 0 - Max 9)!");
					System.out.print("\nRe-Enter Y coordinate: ");
					invalid = true;
				}
			}while(invalid);
			
			if(seascape[targetX][targetY] == null) {
				if(playerSeascape[targetX][targetY] != null) {
					System.out.println("Exposed coordinate selected; please select new coordinate!");
					continue;
				}else{
					System.out.println("Sorry, you missed\n");
					playerSeascape[targetX][targetY] = "-";
					break;
				}
			} else if(seascape[targetX][targetY] == "1" && playerSeascape[targetX][targetY] == "@") {
				System.out.println("Oh no, you sunk your own ship :(\n");
				playerSeascape[targetX][targetY] = "x";
				computerSeascape[targetX][targetY] = "x";
				break;
				
			} else if(seascape[targetX][targetY] == "2" && playerSeascape[targetX][targetY] == null) {
				System.out.println("Boom! You sunk the ship!\n");
				playerSeascape[targetX][targetY] = "!";
				computerSeascape[targetX][targetY] = "!";
				break;
			}else {
				System.out.println("Exposed coordinate selected; please select new coordinate!");
				continue;
			}
		}
		
		System.out.println("COMPUTER TURN");
		while(true) {
			targetX = (byte)dice.nextInt(10);
			targetY = (byte)dice.nextInt(10);
			if(seascape[targetX][targetY] == null) {
				if(computerSeascape[targetX][targetY] != null)
					continue;
				else{
					System.out.println("Computer missed\n");
					computerSeascape[targetX][targetY] = "-";
					break;
				}
			} else if(seascape[targetX][targetY] == "1") { 
				System.out.println("The Computer sunk one of your ships!\n");
				playerSeascape[targetX][targetY] = "x";
				computerSeascape[targetX][targetY] = "x";
				break;
					
			} else if(seascape[targetX][targetY] == "2") { 
				System.out.println("The Computer sunk one of its own ships\n");
				computerSeascape[targetX][targetY] = "!";
				playerSeascape[targetX][targetY] = "!";
				break;
			}
		}
	}
			
	public static void commenceGame() {
		int playerScore = (int) Array.get(score, 0);
		int computerScore = (int) Array.get(score, 1);	
	//	GAME PHASE - START GAME
		while (playerScore != 0 && computerScore != 0) {
			battlePhase();
			generateSeascape();
			shipCount();
			printScore();
			playerScore = (int) Array.get(score, 0);
			computerScore = (int) Array.get(score, 1);
		}
	//	GAME PHASE - END GAME
		if (playerScore == 0)
			System.out.println("\nYOU LOST!");
		else
			System.out.println("\nHOORAY! YOU WIN THE BATTLE!!! :)");
	}

	public static void generateComputerOcean() {
		// x-axis of BattleShip Grid(TOP)!
		System.out.println();
		System.out.println("Computer Ocean");
		print_X_axis();
		System.out.println();
		int row;
		int col;
		for (row = 0; row < computerSeascape.length; row++) {
			System.out.print(row + "|");
			for (col = 0; col < computerSeascape[row].length; col++) {
				if (computerSeascape[row][col] == null) {
					System.out.print(" ");
				} else {
					System.out.print(computerSeascape[row][col]);
				}
			}
			System.out.println("|" + row);
		}
print_X_axis();
		System.out.println();
		System.out.println("\n");
	}

}
	
