package com.company;



public class Main {

    public static void main(String[] args) {

        GameBoard myGame = new GameBoard();
        int whoPlays;
        whoPlays = myGame.whoToPlayWith();
        myGame.gameBlueprint();
        myGame.displayBoard();

        if (whoPlays == 2) {// User vs user
            int counter = 1;

            while (myGame.gameActive()) {
                if (counter % 2 == 0)
                    myGame.askPlayer('O');
                else
                    myGame.askPlayer('X');
                counter++;

                System.out.println();
                myGame.displayBoard();
                myGame.checkForWinner();
            }
        }

        else {// User vs CPU

            while (myGame.gameActive()){

                myGame.askPlayer2('X');
                myGame.displayBoard();
                //myGame.pressEnterKey();

                if (myGame.checkForWinner())// Break out if user wins
                    break;

                myGame.askPlayerCPU('O');
                myGame.displayBoard();
                if (myGame.checkForWinner()) // Break out if cpu wins
                    break;

                if (myGame.gameActive())
                    myGame.checkForWinner();// Incase of tie game

            }
        }


    }// End of Main Method
}