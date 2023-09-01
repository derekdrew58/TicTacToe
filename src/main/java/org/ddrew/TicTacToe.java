package org.ddrew;

import org.ddrew.components.Game;
import org.ddrew.components.UserInput;

import java.util.Random;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        UserInput userInput = new UserInput();
        boolean playing = true;

        while (playing) {

            System.out.println("Welcome to Derek's TicTacToe game!");
            userInput.chooseSymbol(2); // 2 attempts to provide valid input
            System.out.println("Great, let's get started.");

            Random random = new Random();
            boolean playerOneFirst = random.nextBoolean();
            if (playerOneFirst) {
                System.out.println("You're up to bat first!");
            } else {
                System.out.println("We're gonna let the computer play first, just to be fair.");
            }

            Game game = new Game(playerOneFirst, userInput);
            while (!game.isOver()) {
                game.play();
            }

            Character result = game.getResult();
            if (result == 'P') {
                System.out.println("Kudos and congrats, you won!");
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
                System.out.println("Would you like to play again? Please type 'Y' or 'N'");
                playing = userInput.playAgain(2); // 2 attempts to provide valid input
            }
        }

        System.out.println("Thank you for playing!");
        userInput.getScanner().close();
    }
}