package com.hafizRSoftDev.classes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

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
	static Scanner input = new Scanner(System.in);
	static Random dice = new Random();
	static String[][] seascape = new String[10][10];
	static String[][] playerSeascape = new String[10][10];
	static String[][] computerSeascape = new String[10][10];
	static char []axis = {'x','y'};
	static byte[] coordinates = new byte [2];
	static int[] score = {0,0};
	
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
		System.out.println("\nOcean Map - Player Seascape");
		System.out.println("---------------------------");
		
// x-axis of BattleShip Grid(TOP)!
		print_X_axis();
		System.out.println();
		
//	10 x 10 BattleShip Grid
		for (int row = 0; row < playerSeascape.length; row++) {
			System.out.print(row + "|");
			for (int col = 0; col < playerSeascape[row].length; col++) {
				if (playerSeascape[row][col] == null)
					System.out.print(" ");
				else
					System.out.print(playerSeascape[row][col]);
			}
			System.out.println("|" + row);
		}
		
// x-axis of BattleShip Grid(BOT)!
		print_X_axis();
		System.out.println("\n\n");
	}

	public static void print_X_axis() {
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) 
				System.out.print("  " + colIndicator);
			else if (colIndicator == 9)
				System.out.print(colIndicator + "  ");
			else
				System.out.print(colIndicator);
		}
	}
	
	public static void deployPlayerShips(){
		System.out.println("Deploy your ships:");
		System.out.println("------------------");
		for(int i = 1; i <= 5; i++) {
    		while(true){
    			selectCoordinates(" coordinate: for your " + i + ". ship: ");

    			if(seascape[coordinates[0]][coordinates[1]] == null) {
            		seascape[coordinates[0]][coordinates[1]] = "1";
            		playerSeascape[coordinates[0]][coordinates[1]] = "@";
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
				coordinates[0] =  (byte) dice.nextInt(10);
				coordinates[1] =  (byte) dice.nextInt(10);
				if (seascape[coordinates[0]][coordinates[1]] == null) {
					computerSeascape[coordinates[0]][coordinates[1]] = "2";
					seascape[coordinates[0]][coordinates[1]] = "2";
					System.out.println(i + ". ship DEPLOYED");
					break;
				}
			}	
		}
		printHorizontalLine();
	}

	public static void shipCount() {
		int player = 0;
		int computer = 0;
		
		for (int row = 0; row < playerSeascape.length; row++) {
			for (int col = 0; col < playerSeascape[row].length; col++) {
				if (playerSeascape[row][col] == "@")
					player++;
				else if (computerSeascape[row][col] == "2")
					computer++;
			}
		}
		score[0] = player;
		score[1] = computer;
	}
	
	private static void printScore() {
		System.out.println("-----------------------------------");
		System.out.println("|Your ships: " + Array.get(score, 0) 
						+ " | Computer ships: " + Array.get(score, 1) + "|");
		System.out.println("-----------------------------------");
		printHorizontalLine();
	}

	public static void battlePhase() {
		System.out.println("\nYOUR TURN");
		System.out.println("---------\n");
		while(true) {
			selectCoordinates(" coordinate: ");
			if(playerSeascape[coordinates[0]][coordinates[1]] != null) {
				if(!(playerSeascape[coordinates[0]][coordinates[1]] == "@")) {
					System.out.println("Exposed coordinate selected;"
									+ " please select new coordinate!");
					continue;
				}else {
					System.out.println("Oh no, you sunk your own ship :(\n");
					playerSeascape[coordinates[0]][coordinates[1]] = "x";
					computerSeascape[coordinates[0]][coordinates[1]] = "x";
					break;
				}
			}else if(seascape[coordinates[0]][coordinates[1]] == "2") {
				System.out.println("Boom! You sunk the ship!\n");
				playerSeascape[coordinates[0]][coordinates[1]] = "!";
				computerSeascape[coordinates[0]][coordinates[1]] = "!";
				break;
			}else {
				System.out.println("Sorry, you missed\n");
				playerSeascape[coordinates[0]][coordinates[1]] = "-";				
				break;	
			}			
		}
				
		System.out.println("COMPUTER TURN");
		while(true) {
			coordinates[0] = (byte)dice.nextInt(10);
			coordinates[1] = (byte)dice.nextInt(10);
			if(seascape[coordinates[0]][coordinates[1]] == null) {
				if(computerSeascape[coordinates[0]][coordinates[1]] != null)
					continue;
				else{
					System.out.println("Computer missed\n");
					computerSeascape[coordinates[0]][coordinates[1]] = "-";
					break;
				}
			} else if(seascape[coordinates[0]][coordinates[1]] == "1") { 
				System.out.println("The Computer sunk one of your ships!\n");
				playerSeascape[coordinates[0]][coordinates[1]] = "x";
				computerSeascape[coordinates[0]][coordinates[1]] = "x";
				break;
					
			} else if(seascape[coordinates[0]][coordinates[1]] == "2") { 
				System.out.println("The Computer sunk one of its own ships\n");
				computerSeascape[coordinates[0]][coordinates[1]] = "!";
				playerSeascape[coordinates[0]][coordinates[1]] = "!";
				break;
			}
		}
	}

	public static void selectCoordinates(String message) {
//		Player provides input for X/Y Coordinate => byte[]coordinates = {0,1}
		for(int j = 0; j < 2; j++) {
			System.out.print("\nEnter " + axis[j] + message);
			
			while(true) {
				if(!input.hasNextByte()) {
					System.out.println("Please enter a numerical value(Min 0 - Max 9)!");
					System.out.print("Re-Enter " + axis[j] + message); 
					input.next();
					continue;
				}else
					coordinates[j] = input.nextByte();	
				
				if(coordinates[j] >= 0 && coordinates[j] <=9)
					break;
				else {	
					System.out.println("Please select coordinate within 10 by 10 grid!");
					System.out.print("Re-Enter " + axis[j] + message);
				}	
			}
		}
	}
			
	public static void commenceGame() {
		int playerScore = score[0];
		int computerScore = score[1];
		
	//	GAME PHASE - START GAME
		while (playerScore != 0 && computerScore != 0) {
			battlePhase();
			generateSeascape();
			shipCount();
			printScore();
			playerScore = score[0];
			computerScore = score[1];
		}
	//	GAME PHASE - END GAME
		if (playerScore == 0)
			System.out.println("\nYOU LOST!");
		else
			System.out.println("\nHOORAY! YOU WIN THE BATTLE!!! :)");
	}

	public static void printHorizontalLine() {
		for (int i = 0; i <= 100; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void generateComputerOcean() {
//	X-axis of BattleShip Grid(Top)
		System.out.println();
		System.out.println("Computer Ocean");
		print_X_axis();
		System.out.println();
		
//	10 x 10 BattleShip Grid
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
		
//	X-axis of BattleShip Grid(Bottom)
		print_X_axis();
		System.out.println("\n\n");
	}

}
	
