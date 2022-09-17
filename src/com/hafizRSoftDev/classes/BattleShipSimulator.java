package com.hafizRSoftDev.classes;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class BattleShipSimulator {
	public static String[][] ocean = new String[10][10];
	public static String[][] playerOcean = new String[10][10];
	public static String[][] computerOcean = new String[10][10];
	public static byte ship_X_Coord = 0;
	public static byte ship_Y_Coord = 0;
	public static byte targetX = 0;
	public static byte targetY = 0;
	public static Scanner input = new Scanner(System.in);
	public static Random dice = new Random();

	public static void main(String[] args) {

		System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty");

		generateOcean();
		deployPlayerShips();
		generateOcean();
		deployComputerShips();
		generateOcean();
		generateEvilOcean();
		shipCount(0, 0);
		var score = shipCount(0, 0);
		System.out.println("Your ships: " + Array.get(score, 0) + " | Computer ships: " + Array.get(score, 1));
		battlePhase();
		generateOcean();
		generateEvilOcean();
		shipCount(0, 0);
		score = shipCount(0, 0);
		int player = (int) Array.get(score, 0);
		int computer = (int) Array.get(score, 1);
		System.out.println("Your ships: " + Array.get(score, 0) + " | Computer ships: " + Array.get(score, 1));

		while ((player != 0) && (computer != 0)) {
			battlePhase();
			generateOcean();
			generateEvilOcean();
			shipCount(0, 0);
			score = shipCount(0, 0);
			player = (int) Array.get(score, 0);
			computer = (int) Array.get(score, 1);
			System.out.println("Your ships: " + Array.get(score, 0) + " | Computer ships: " + Array.get(score, 1));
		}
		if (player == 0) {
			System.out.println("YOU LOST!");
		} else
			System.out.println("Hooray! You win the battle :)");
	}

	public static void generateOcean() {
		// x-axis of BattleShip Grid(TOP)!
		System.out.println();
		System.out.println("Player Ocean");
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) {
				System.out.print("  " + colIndicator);
			} else if (colIndicator == 9) {
				System.out.print(colIndicator + "  ");
			} else
				System.out.print(colIndicator);
		}
		System.out.println();
//    10 x 10 BattleShip Grid
		int row;
		int col;
		for (row = 0; row < playerOcean.length; row++) {
			System.out.print(row + "|");
			for (col = 0; col < playerOcean[row].length; col++) {
				if (playerOcean[row][col] == null) {
					System.out.print(" ");
				} else {
					System.out.print(playerOcean[row][col]);
				}
			}
			System.out.println("|" + row);
		}
//                x-axis of BattleShip Grid(BOT)!
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) {
				System.out.print("  " + colIndicator);
			} else if (colIndicator == 9) {
				System.out.print(colIndicator + "  ");
			} else
				System.out.print(colIndicator);
		}
		System.out.println();
		System.out.println("\n");
	}

	public static void generateEvilOcean() {
		// x-axis of BattleShip Grid(TOP)!
		System.out.println();
		System.out.println("Computer Ocean");
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) {
				System.out.print("  " + colIndicator);
			} else if (colIndicator == 9) {
				System.out.print(colIndicator + "  ");
			} else
				System.out.print(colIndicator);
		}
		System.out.println();
//    10 x 10 BattleShip Grid
		int row;
		int col;
		for (row = 0; row < computerOcean.length; row++) {
			System.out.print(row + "|");
			for (col = 0; col < computerOcean[row].length; col++) {
				if (computerOcean[row][col] == null) {
					System.out.print(" ");
				} else {
					System.out.print(computerOcean[row][col]);
				}
			}
			System.out.println("|" + row);
		}
//                x-axis of BattleShip Grid(BOT)!
		for (int colIndicator = 0; colIndicator <= 9; colIndicator++) {
			if (colIndicator == 0) {
				System.out.print("  " + colIndicator);
			} else if (colIndicator == 9) {
				System.out.print(colIndicator + "  ");
			} else
				System.out.print(colIndicator);
		}
		System.out.println();
		System.out.println("\n");
	}

	public static void deployPlayerShips(){
		System.out.println("Deploy your ships:");
		
    	for(int i = 1; i <= 5; i++) {
        	while(true) {
        		System.out.print("Enter X coordinate for your " + i + ". ship: ");
            	ship_X_Coord = input.nextByte();
            	if(ship_X_Coord < 0 || ship_X_Coord > 9) {
            		System.out.println("\nPlease select coordinate within 10 by 10 grid!");
            		continue;
            	}
            	while(true) {
	            	System.out.print("Enter Y coordinate for your " + i + ". ship: ");
	            	ship_Y_Coord = input.nextByte();
	            	if(ship_Y_Coord >= 0 && ship_Y_Coord <= 9)
	            		break;
	            	System.out.println("Please select coordinate within 10 by 10 grid!");
	            }
            	if(!Objects.equals(ocean[ship_X_Coord][ship_Y_Coord], "1")) {
            		ocean[ship_X_Coord][ship_Y_Coord] = "1";
            		playerOcean[ship_X_Coord][ship_Y_Coord] = "@";
            		break;
            	}
            	System.out.println("Unable to place two or more ships on the same location!\n");
            }
    	}
    }
   
	public static void deployComputerShips() {
		System.out.println("Computer is deploying ships");

		for(int i = 1; i <= 5; i++) {
			while(true) {
				ship_X_Coord =  (byte) dice.nextInt(10);
				ship_Y_Coord =  (byte) dice.nextInt(10);
				if (Objects.equals(ocean[ship_X_Coord][ship_Y_Coord], null)) {
					computerOcean[ship_X_Coord][ship_Y_Coord] = "2";
					ocean[ship_X_Coord][ship_Y_Coord] = "2";
					System.out.println(i + ". ship DEPLOYED");
					break;
					}
			}	
		}
		for (int i = 0; i <= 100; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public static void battlePhase() {
		
		System.out.println("YOUR TURN");
		while(true) {
			System.out.println("Enter X coordinate:");
			targetX = input.nextByte();
			if(targetX < 0 || targetX > 9) {
				System.out.println("Please select coordinate within 10 by 10 grid!");
				continue;
			}
			while(true) {
				System.out.println("Enter Y coordinate:");
				targetY = input.nextByte();
				if(targetY >= 0 && targetY <= 9) {
					break;
				}
				System.out.println("Please select coordinate within 10 by 10 grid!");
			}
			
			if(ocean[targetX][targetY] == null) {
				if(playerOcean[targetX][targetY] != null) {
					System.out.println("Exposed coordinate selected; please select new coordinate!");
					continue;
				}else{
					System.out.println("Sorry, you missed\n");
					playerOcean[targetX][targetY] = "-";
					break;
				}
			}else if(Objects.equals(ocean[targetX][targetY], "1") && Objects.equals(playerOcean[targetX][targetY], "@")) {
				System.out.println("Oh no, you sunk your own ship :(\n");
				playerOcean[targetX][targetY] = "x";
				computerOcean[targetX][targetY] = "x";
				break;
				
			} else if (Objects.equals(ocean[targetX][targetY], "2") && Objects.equals(playerOcean[targetX][targetY], null)) {
				System.out.println("Boom! You sunk the ship!\n");
				playerOcean[targetX][targetY] = "!";
				computerOcean[targetX][targetY] = "!";
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
			if(ocean[targetX][targetY] == null) {
				if(computerOcean[targetX][targetY] != null)
					continue;
				else{
					System.out.println("Computer missed\n");
					computerOcean[targetX][targetY] = "-";
					break;
				}
			}else if(Objects.equals(ocean[targetX][targetY], "1")) {
				System.out.println("The Computer sunk one of your ships!\n");
				playerOcean[targetX][targetY] = "x";
				computerOcean[targetX][targetY] = "x";
				break;
					
			}else if (Objects.equals(ocean[targetX][targetY], "2")) {
				System.out.println("The Computer sunk one of its own ships\n");
				computerOcean[targetX][targetY] = "!";
				playerOcean[targetX][targetY] = "!";
				break;
			}
		}
	}
		
	public static int[] shipCount(int player, int computer) {
		int row;
		int col;
		for (row = 0; row < playerOcean.length; row++) {
			for (col = 0; col < playerOcean[row].length; col++) {
				if (Objects.equals(playerOcean[row][col], "@")) {
					player++;
				} else if (Objects.equals(computerOcean[row][col], "2")) {
					computer++;
				}
			}
		}
		return new int[] { player, computer };
	}
}
