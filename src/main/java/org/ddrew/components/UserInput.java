package org.ddrew.components;

import java.util.Scanner;

public class UserInput {
    private final Scanner scanner = new Scanner(System.in);

    private String playerSymbol;

    private void setPlayerSymbol(String character) {
        this.playerSymbol = character;
    }

    public String getPlayerSymbol() {
        return this.playerSymbol;
    }
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * ask user to choose X or O
     * @param  attemptsRemaining   the amount of attempts a user is given to choose a symbol
     */
    public void chooseSymbol(Integer attemptsRemaining) throws Exception {
        System.out.println("Please choose whether you'd like to be X or O:");
        String input;

        try {
            input = scanner.nextLine().trim().toUpperCase();
        } catch (Exception e) {
            input = "";
        }
        attemptsRemaining--;
        boolean isValid = isValidXOrO(input);
        if (isValid) {
            setPlayerSymbol(input);
        } else if (attemptsRemaining > 0) {
            System.out.println("I'm sorry but that's not a valid option. Remaining attempts to provide input: " + attemptsRemaining);
            chooseSymbol(attemptsRemaining);
        } else {
            System.out.println("I'm sorry but that's not a valid option either. Please restart the game and try again");
            throw new Exception("Too many invalid input attempts");
        }
    }

    private static boolean isValidXOrO(String input) {
        return input.equals("X") || input.equals("O");
    }

    public String askForCoordinate(int[][] grid, Integer attemptsRemaining) throws Exception {
        int x = -1, y = -1;
        try {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.contains(",")) {
                x = Integer.parseInt(input.split(",")[0].trim());
                y = Integer.parseInt(input.split(",")[1].trim());
            }
        } catch (Exception e) {
            // continue
        }
        attemptsRemaining--;
        boolean isValid = isValidCoordinate(x, y, grid);
        if (isValid) {
            return x + "," + y;
        } else if (attemptsRemaining > 0) {
            System.out.println("Please enter the x,y place separated by a comma you'd like to play. For example: 0,1. x" +
                    "Remaining attempts to provide input: \" + attemptsRemaining");
            return askForCoordinate(grid, attemptsRemaining);
        }
        else {
            System.out.println("There have been too many invalid attempts. The game will end now. Thanks for playing!");
            throw new Exception("Too many invalid input attempts");
        }
    }

    private static boolean isValidCoordinate(Integer x, Integer y, int[][] grid) {
        if ((x < 0 || y < 0) || (x > 2 || y > 2)) {
            System.out.println("I'm sorry but either that is not a valid x,y coordinate.");
        } else if (grid[x][y] != 0) {
            System.out.println("I'm sorry but that space has already been used.");
        } else {
            return true;
        }
        return false;
    }

    public boolean playAgain(Integer attemptsRemaining) throws Exception {
        String input;
        try {
            input = scanner.nextLine().trim().toUpperCase();
        } catch (Exception e) {
            input = "";
        }
        attemptsRemaining--;
        boolean isValid = isValidYorN(input);
        if (isValid) {
            return input.equals("Y");
        } else if (attemptsRemaining > 0) {
            System.out.println("I'm sorry but that's not a valid option. Remaining attempts to provide input: " + attemptsRemaining);
            return playAgain(attemptsRemaining);
        } else {
            System.out.println("I'm sorry but that's not a valid option either. The game will end now. Thanks for playing!");
            throw new Exception("Too many invalid input attempts");
        }
    }

    private static boolean isValidYorN(String input) {
        return input.equals("Y") || input.equals("N");
    }

}
