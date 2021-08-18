package main.java.components;

import java.util.Scanner;

public class UserInput {
	
	private Scanner scanner = new Scanner(System.in);
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	public String askForXorO() {
		System.out.println("Please choose whether you'd like to be X or O:");
    	String playerOneMark = "";
    	
    	try { // give them one chance to give the correct input format. if invalid, send them into a loop
    		playerOneMark = scanner.nextLine().trim().toUpperCase();
    	}
    	catch (Exception e) {
    		playerOneMark = "";
    	}
    	int pOneMarkAttempts = 0;
    	while (!isValidXOrO(pOneMarkAttempts, playerOneMark)) {
    		pOneMarkAttempts++;
    		try {
        		playerOneMark = scanner.nextLine().trim().toUpperCase();
        	}
        	catch (Exception e) {
        		playerOneMark = "";
        	}           		
    	}
    	if (pOneMarkAttempts == 2) {
    		System.out.println("I'm sorry but that's not a valid option either. Please restart the game and try again");
    		return null;
    	}
    	else {
    		return playerOneMark;
    	}
	}
	
	private static boolean isValidXOrO(int pOneMarkAttempts, String playerOneMark) {
		if (pOneMarkAttempts < 2 && playerOneMark.compareTo("X") != 0 && playerOneMark.compareTo("O") != 0) {
			System.out.println("I'm sorry but that's not a valid option. Please choose whether you'd like to be X or O:");
			return false;
		}
		else {
			return true;
		}	
	}
	
	public boolean askForYesOrNo() {
		String yOrN = "";
    	try { // give them one chance to give the correct input format. if invalid, send them into a loop
    		yOrN = scanner.nextLine().trim().toLowerCase();
    	}
    	catch (Exception e) {
    		yOrN = "";
    	}
    	int yOrNoAttempts = 0;
    	while (!isValidYesOrNo(yOrNoAttempts, yOrN)) {
    		yOrNoAttempts++;
        	try {
        		yOrN = scanner.nextLine().trim().toLowerCase();
        	}
        	catch (Exception e) {
        		yOrN = "";
        	}
    	}
    	if (yOrNoAttempts == 2) {
    		System.out.println("I'm sorry but that's not a valid option either. Please restart the game and try again");
    		return false;
    	}
    	else {
    		return (yOrN.compareTo("y") == 0)  ? true : false;
    	}
	}
	
	private static boolean isValidYesOrNo(int yOrNoAttempts, String yOrN) {
		if (yOrNoAttempts < 2 && yOrN.compareTo("n") != 0 && yOrN.compareTo("y") != 0) {
			System.out.println("I'm sorry but that's not a valid option. Please enter y or n: ");
			return false;
		}
		else {
			return true;
		}	
	}
	
	public String askForCoordinate(int[][] grid) {
		Integer x = null, y = null;
		try { // give them one chance to enter correct input. if invalid loop
        	String input = scanner.nextLine();
        	System.out.println(input);
        	if (input.contains(",")) {
     			x = Integer.parseInt(input.split(",")[0].trim());
                y = Integer.parseInt(input.split(",")[1].trim());
        	}
        	else {
        		x = y = null;
        	}
        }
        catch (Exception e) {
       		x = y = null;
        }
        
        int invalidAttempts = 0;
     	while (!isValidCoordinate(invalidAttempts, x, y, grid)) {           
     		invalidAttempts++;
     		try {
     			String input = scanner.nextLine();
     			System.out.println(input);
     			if (input.contains(",")) {
         			x = Integer.parseInt(input.split(",")[0].trim());
                    y = Integer.parseInt(input.split(",")[1].trim());
            	}
            	else {
            		x = y = null;
            	}
            }
            catch (Exception e) {
           		x = y = null;
            }
     	}
    
     	if (invalidAttempts == 10) { // force close because too many invalid attempts
     		System.out.println("I'm sorry but that's not a valid x,y coordinate either. Please restart the game and try again");
       		return null;
     	}
     	else {
     		return x + "," + y;
     	}

	}
	
	 private static boolean isValidCoordinate(int invalidAttempts, Integer x, Integer y, int[][] grid) {
			if (invalidAttempts < 10 && 
					( x == null || y == null || (x < 0 || x > 2) || (y < 0 || y > 2 || grid[x][y] != 0) ) ) {
				System.out.println("I'm sorry but either that spot is taken or it's not a valid x,y coordinate.");
	     		System.out.println("Please enter the x,y place seperated by a comma you'd like to play. For example, 0,1");
				return false;
			}
			else {
				return true;
			}	
	}
	 
}
