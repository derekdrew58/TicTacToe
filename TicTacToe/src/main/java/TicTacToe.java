package main.java;

import java.util.Random;

import main.java.components.Game;
import main.java.components.UserInput;

public class TicTacToe {
	
	public static void main(String[ ] args) {
        
        UserInput userInput = new UserInput();
        boolean playing = true;
        
        while (playing) {

        	System.out.println("Welcome to Derek's TicTacToe game!");   	
        	String playerOneMark = userInput.askForXorO();
        	
        	if (playerOneMark == null) { return; } // too many invalid input attempts. close game
        	System.out.println("Great, let's get started.");
        	
        	Random random = new Random();
        	boolean playerOneFirst = random.nextBoolean();  	
        	if (playerOneFirst) {
        		System.out.println("You're up to bat first!");
        	}
        	else {
        		System.out.println("We're gonna let the computer play first, just to be fair.");
        	}
        	
            Game game = new Game(playerOneMark, playerOneFirst, userInput);
            while (!game.isOver()) {
            	game.play();
            }
            
            Character result = game.getResult();
            if (result == 'P') {
            	System.out.println("Kudos and congrats, you won! Did you cheat? :)");
            }
            else if (result == 'C') {
            	System.out.println("Uh oh, the computer got lucky and beat you this time...");
            }
            else if (result == 'T') {
            	System.out.println("Ugh! A tie? Those are the worst.");
            }           
            
            if (result == 'I') { // invalid input, end game and make them restart
            	playing = false;
            }
            else {    
            	System.out.println("Would you like to play again?");
            	playing = userInput.askForYesOrNo();
            }
        }
        
        System.out.println("Thank you for playing!");
        userInput.getScanner().close();
     
    }
	
	
}
