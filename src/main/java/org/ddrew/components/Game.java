package org.ddrew.components;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private boolean gameIsComplete = false;
    private Character result;
    private int[][] grid = new int[3][3]; // value 0 = empty space, 1 = playerOne space, 2 = computer space
    private String playerSymbol;
    private String computerSymbol;
    private boolean playerTurn;
    private int openSpaces = 9;
    UserInput userInput;

    public Game(boolean playerTurn, UserInput userInput) {
        this.userInput = userInput;
        this.playerTurn = playerTurn;

        playerSymbol = userInput.getPlayerSymbol();
        if (playerSymbol.equals("X")) {
            this.computerSymbol = "O";
        }
        else {
            this.computerSymbol = "X";
        }
    }

    public Character getResult() {
        return this.result;
    }

    public boolean isOver() {
        return this.gameIsComplete;
    }

    public void displayGrid() {
        for (int column = 0; column < 3; column++) {
            if (column == 0) {
                System.out.println("\n      x");
                System.out.println("      0      1      2");
                System.out.println("      _______________");
                System.out.print("y 0  |");
            }
            else {
                System.out.print("  " + column + "  |");
            }
            for (int row = 0; row < 3; row++) {
                if (grid[row][column] == 0) { // empty space
                    System.out.print("  ");
                }
                else if (grid[row][column] == 1) { // player space
                    System.out.print(" " + playerSymbol );
                }
                else if (grid[row][column] == 2) { // computer space
                    System.out.print(" " + computerSymbol);
                }
                System.out.print("  |");
            }
            System.out.print("\n");
        }
        System.out.println("      _______________\n");

    }

    public void play() throws Exception {

        if (openSpaces <= 0) { // no open spaces left, it's a tie
            gameIsComplete = true;
            result = 'T';
            this.displayGrid();
            return;
        }

        this.displayGrid();
        if (playerTurn) {

            System.out.println("Please enter the x,y place seperated by a comma you'd like to play. For example: 0,1");
            //Integer x = null, y = null;

            String validCoordinate = userInput.askForCoordinate(grid, 2);
//            if (validCoordinate == null) {
//                gameIsComplete = true;
//                result = 'I';
//                return;
//            }

            int x = Integer.parseInt(validCoordinate.split(",")[0]);
            int y = Integer.parseInt(validCoordinate.split(",")[1]);


            grid[x][y] = 1;
            openSpaces--;
            if (checkIfWin('P')) { // check if player won
                gameIsComplete = true;
                result = 'P';
                this.displayGrid();
            }
            playerTurn = false;
        }
        else {
            System.out.println("Computer will play");
            int x = ThreadLocalRandom.current().nextInt(0, 3);
            int y = ThreadLocalRandom.current().nextInt(0, 3);
            while (grid[x][y] != 0) { // while current space is not empty
                x = ThreadLocalRandom.current().nextInt(0, 3);
                y = ThreadLocalRandom.current().nextInt(0, 3);
            }
            grid[x][y] = 2;
            openSpaces--;
            if (checkIfWin('C')) { // check if computer won
                gameIsComplete = true;
                result = 'C';
                this.displayGrid();
            }
            playerTurn = true;
        }
    }

    public boolean checkIfWin(Character c) {
        int val = c == 'P' ? 1 : 2; // player = 1, computer = 2
        boolean wonByColumn = (grid[0][0] == val && grid[0][1] == val && grid[0][2] == val) ||
                (grid[1][0] == val && grid[1][1] == val && grid[1][2] == val) ||
                (grid[2][0] == val && grid[2][1] == val && grid[2][2] == val);

        boolean wonByRow = (grid[0][0] ==val && grid[1][0] == 1 && grid[2][0] == 1) ||
                (grid[0][1] == val && grid[1][1] == val && grid[2][1] == val) ||
                (grid[0][2] == val && grid[1][2] == val && grid[2][2] == val);

        boolean wonByDiag = (grid[0][0] == val && grid[1][1] == val && grid[2][2] == val) ||
                (grid[2][0] == val && grid[1][1] == val && grid[0][2] == val);

        return (wonByColumn || wonByRow || wonByDiag);
    }
}
