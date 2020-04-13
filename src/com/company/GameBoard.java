package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {

    private char[][]  gameBoard;
    private boolean gameOnGoing = true; // Can be changed to false by checkForWinner() method
    private int position;// User input in number
    int row, col;
    int cpu = 2; // used to know who the user wants to play with.

    public GameBoard(){
        gameBoard = new char[3][3];

        for (int row = 0; row<gameBoard.length; row++){
            Arrays.fill(gameBoard[row], ' ');
        }
    }//end of constructor


    /**
     * Directs the user on how to use the game board.
     */
    public void gameBlueprint(){
        System.out.println();// Give some space between java command and display board to prevent jumbled up look
        System.out.println("Game Blueprint ............");
        System.out.println();
        System.out.println();
        int count = 1;
        for (int row = 0; row<gameBoard.length;row++){
            for (int col = 0; col<gameBoard[0].length; col++){
                System.out.print(gameBoard[row][col]);
                if (col==0||col ==1) {
                    System.out.print(count + "|");
                    count++;
                }
                else {
                    System.out.print(count);
                    count++;
                }
            }
            if(row!=2)System.out.print("\n---------\n");// stops the horizontal line from printing at the bottom of the board.
        }
        System.out.println();// Give some space between the each board display to prevent jumbled up look
        System.out.println();

        pressEnterKey();
    }// End of gameBlueprint() method

    /**
     * This method will display the game board
     */
    public void displayBoard(){
        for (int row = 0; row<gameBoard.length;row++){
            for (int col = 0; col<gameBoard[0].length; col++){
                System.out.print("\t"+gameBoard[row][col]);
                if (col==0||col ==1)
                    System.out.print('|');
            }
            if(row!=2)System.out.print("\n----------------\n");// stops the horizontal line from printing at the bottom of the board.
        }
        System.out.println();// Give some space between the each board display to prevent jumbled up look
        System.out.println();
        System.out.println();
    }//End of displayBoard() method

    /**
     * Sets the row and column of multi dimensional array based on user input
     */
    public void arraySetter(int position) {

        if (position == 1) {
            row = 0;
            col = 0;
        } else if (position == 2) {
            row = 0;
            col = 1;
        } else if (position == 3) {
            row = 0;
            col = 2;
        } else if (position == 4) {
            row = 1;
            col = 0;
        } else if (position == 5) {
            row = 1;
            col = 1;
        } else if (position == 6) {
            row = 1;
            col = 2;
        } else if (position == 7) {
            row = 2;
            col = 0;
        } else if (position == 8) {
            row = 2;
            col = 1;
        } else if (position == 9) {
            row = 2;
            col = 2;
        }

    }// End of placesPiece method

    /**
     * Sets the row and column of multi dimensional array based on CPU input
     */
    public void arraySetter(int position, int cpu) {

        if (cpu == 1) {

            if (position == 1) {
                row = 0;
                col = 0;
            } else if (position == 2) {
                row = 0;
                col = 1;
            } else if (position == 3) {
                row = 0;
                col = 2;
            } else if (position == 4) {
                row = 1;
                col = 0;
            } else if (position == 5) {
                row = 1;
                col = 1;
            } else if (position == 6) {
                row = 1;
                col = 2;
            } else if (position == 7) {
                row = 2;
                col = 0;
            } else if (position == 8) {
                row = 2;
                col = 1;
            } else if (position == 9) {
                row = 2;
                col = 2;
            }
        }
    }// End of arraySetter overload

    /**
     * Called when user wants to play friend
     * Ask the user to pick a spot and call makeMove() to validate the move
     */
    public void askPlayer(char player){
        Scanner scan = new Scanner(System.in);

        do {
            System.out.printf("Player %s Please enter your move based on the blueprint: ", player);

            // Repeat until user enters a number
            while (!scan.hasNextInt()){
                scan.next(); // Read and discard if scan is not a number
                System.out.printf("Invalid!!! Player %s enter a NUMBER based on the blueprint: ", player);
            }// At this point in the code, the user has entered an integer

            position = scan.nextInt();// Get position from the user
            arraySetter(position);

        }while (notValid());// This makes sure user enters a valid number (also free space)

        makeMove(player);// Puts move based on row and column into gameBoard. The negative accounts for arrays beginning their counts with 'O'
    }//End of askPlayer() method.


    /**
     * Called when user wants to play cpu
     * Controls user's turn
     */
    public void askPlayer2(char player){
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Player X Please enter your move based on the blueprint: " );

            // Repeat until user enters a number
            while (!scan.hasNextInt()){
                scan.next(); // Read and discard if scan is not a number
                System.out.println("Invalid!!! Player X Please enter A NUMBER as shown on the blueprint: ");
            }// At this point in the code, the user has entered an integer

            position = scan.nextInt();// Get position from the user
            arraySetter(position);// Converts that position to 'row' and 'col'

        }while (notValid());// This makes sure user enters a valid number (also free space)

        makeMove(player);// Puts move based on row and column into gameBoard.

    }// End of askPlayer2 method

    /**
     * Called when user wants to play cpu
     * Controls cpu's turn
     */
    public void askPlayerCPU(char player){
        System.out.println("CPU's turn");
        System.out.println();
        pressEnterKey();
        do {
            position = cpuMoveEasy(); // Gives cpu position the return value from cpuMoveEasy();
            arraySetter(position, cpu); // Converts that position to 'row' and 'col'

        }while (notValid2());// This makes sure cpu picks a valid number (also free space)
        makeMove(player);
    } // End of askPlayerCPU() method

    /**
     * Check if the players move is allowed.
     * Return true if the move is valid.
     * Puts move into gameBoard.
     */
    public boolean makeMove(char player){
        if (position>0 && position<=9)// checks if move is within our multiDimensional array
        {
            if (gameBoard[row][col] != ' ')// checks to see if the cell is empty or not.
                return false; // Not a valid move
            else {
                gameBoard[row][col] = player; //else it is a valid move and we set the position to whatever the player is (player is an 'X' or '0' char)
                return true;
            }
        }
        return false;
    }// end of makeMove() method



    /**
     * Return true if game is still active
     */
    public boolean gameActive(){

        return gameOnGoing;
    }// End of gameActive() method

    /**
     * @return true if the position is empty , ele return false.
     */
    public boolean isEmpty(){
        if (gameBoard[row][col]== ' ')
            return true;
        else
            return false;
    }// End of isEmpty() method.

    /**
     * Validate if the number entered is between 1 and 9,
     * AND calls isEmpty method to check if the  position is empty
     */
    public boolean notValid(){

        if (position < 0 || position > 9|| !isEmpty()){
            System.out.println("Not a valid move" );
            return true;
        }
        else
            return false;
    }// End of notValid() method

    /**
     * Stops cpu from playing over user's already made move
     */
    public boolean notValid2(){

        if (position < 0 || position > 9|| !isEmpty()){
            System.out.println("CPU is thinking" );
            return true;
        }
        else
            return false;
    }// End of notValid() method


    /**
     * Checks if the board is full
     * @return true if it is
     */
    public boolean checkIfBoardIsFull(){
        int count = 0;
        for (int row = 0; row<gameBoard.length;row++){
            for (int col = 0; col<gameBoard[row].length; col++) {
                if (gameBoard[row][col] !=' ')
                    count++; // For every cell that is not empty, count is added by one
            }
        }
        if (count==9)// We have gone through all 9 cells and could not find an empty cell hence count will be 9
            return true;
        return false;
    }// End of checkIfBoardIsFull() method

    /**
     * Checks for a 3 in a row win
     * Change gameOnGoing variable at the top to false if there is a winner
     */
    public boolean checkForWinner(){
        // Checks each row for a winner
        for (int row=0; row<gameBoard.length; row++){
            if (gameBoard[row][0] == gameBoard [row][1] && gameBoard[row][1] == gameBoard [row][2] && gameBoard[row][0] != ' ') {
                System.out.println("The winner is " + gameBoard[row][0]);
                gameOnGoing = false;
                break;
            }
        }
        // Checks each column for a winner
        for (int col=0; col<gameBoard.length; col++) {
            if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col] && gameBoard[0][col] != ' ') {
                System.out.println("The winner is " + gameBoard[0][col]);
                gameOnGoing = false;
                break;
            }
        }

        // Check diagonals
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != ' ') {// right diagonal
            System.out.println("The winner is player " + gameBoard[0][0]);
            gameOnGoing = false;
            return true;
        }

        if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[0][2] != ' ') { // left diagonal
            System.out.println("The winner is player " + gameBoard[0][2]);
            gameOnGoing = false;
            return true;
        }
        if (checkIfBoardIsFull()){ // Accounts for tie game
            System.out.println("Tie game! ");
            gameOnGoing = false;
            //return true;

        }

        if (gameOnGoing == false)// This bit is done to validate if statement in main code.
            return true;
        return false;
    } // End of checkForWinner() method



    /**
     *Ask the user if he wants to play with cpu or friend
     * @return cpu = 1 if user wises to play with cpu
     */
    public int whoToPlayWith(){
        System.out.println();
        System.out.println();

        do {
            System.out.println("Enter 1 to play with CPU ");
            System.out.println("Enter 2 to play with Friend ");
            Scanner scan = new Scanner(System.in);
            while (!scan.hasNextInt()) {
                scan.next(); // Read and discard if scan is not a number
                System.out.println("Invalid: Enter 1 for cpu. Enter 2 for friend");

            }
            cpu = scan.nextInt();
        } while ((cpu > 0) ^ (cpu < 3)); // Checks to make sure user input is one or two

        System.out.println();

        if (cpu ==1)
            System.out.println("Play with computer selected");
        else
            System.out.println("Play with friend selected");
        //System.out.println(cpu);
        return cpu;
    }//End of whoToPlayWith method

    /**
     * Generates the CPU's position
     */
    public int cpuMoveEasy(){
        Random rand = new Random();
        position = rand.nextInt(9)+1;
        return position;
    }// End of cpuMoveEasy method

    /**
     * Prompts the user to press the enter key so the game can continue
     */
    public void pressEnterKey(){
        String enterKey;
        Scanner scan = new Scanner(System.in);
        do {
            // Keeps asking user until enter key is pressed
            System.out.println("Please press enter key to continue  ");
            enterKey = scan.nextLine();
        }while (!enterKey.equals(""));//Condition is satisfied
    }// End of enter key method

}// End of gameBoard class
