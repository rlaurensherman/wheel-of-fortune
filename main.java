/* Lauren Sherman */

package com.company;

import java.util.Scanner;

public class Main {
    public static int playGame(String contestant, String challenger){
        int i = 0;
        char currChar = '_';
        String expr = "";
        StringBuffer hiddenExpr = new StringBuffer ();
        int numGuesses = 0;
        String guessExpr = "";
        Scanner scnr = new Scanner(System.in);

        System.out.println(contestant + ": Turn away");
        System.out.println(challenger + ": Enter an expression");

        expr = scnr.nextLine();

        makeHiddenExpression(expr, hiddenExpr); //calls makeHiddenExpression method
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(hiddenExpr);

        while (numGuesses < 26) {
            System.out.println(contestant + ": Guess a letter");
            char guess = scnr.nextLine().toCharArray()[0];
            ++numGuesses;
            boolean containsCharacter = checkForCharacter(expr, guess); //calls checkForCharacter method
            if (containsCharacter == false) { //If the guessed letter is not in the expression
                System.out.println("That character is not in the expression.");
            }
            else { //If the guessed letter is in the expression
                for (i = 0; i < expr.length(); ++i) { //This loop will go through every character of the expression
                    if (guess == expr.charAt(i)) { //This will put the letter into the hidden expression everywhere it appears
                        hiddenExpr.setCharAt(i, guess);
                    }
                }
                System.out.println("That character is in the expression!");
                System.out.println(hiddenExpr); //This displays the expression with the letter filled in
                System.out.println("Try to guess the expression"); //This allows the contestant to guess the whole expression
                guessExpr = scnr.nextLine();
                if (guessExpr.equals(expr)){
                    System.out.println("You are correct!"); //If the contestant guessed correctly, his/her turn is over
                    if (numGuesses == 1) {
                        System.out.println("It took you " + numGuesses + " try to guess the expression."); //Makes "try" singular if it was guessed in one turn.
                    }
                    else {
                        System.out.println("It took you " + numGuesses + " tries to guess the expression.");
                    }
                    System.out.println("Your score is " + numGuesses + ".");
                    break;
                }
                else { //If not, the contestant guesses another letter
                    System.out.println("Incorrect. That is not the expression.");
                }
            }
        }
        return numGuesses;
    }

    public static void makeHiddenExpression(String expr, StringBuffer hiddenExpr){
        int i = 0;
        char currChar = '_';
        for (i = 0; i < expr.length(); ++i) { //This converts the expression to a hidden form, converting non-apostrophe and non-space characters to asterisks.
            currChar = expr.charAt(i);
            if (currChar == ' '){
                hiddenExpr.append(currChar);
            } else if (currChar == '\'') {
                hiddenExpr.append(currChar);
            } else {
                hiddenExpr.append('*');
            }
        }
        return;
    }
    public static boolean checkForCharacter(String expr, char guess){
        if (expr.indexOf(guess) == -1) { //If the guessed letter is not in the expression, returns false
            return false;
        }
        else { //If the guessed letter is in the expression, returns true
            return true;
        }
    }

    public static void main (String [] args) {

        String player1name = "";
        String player2name = "";
        int numGuesses1 = 0;
        int numGuesses2 = 0;
        Scanner scnr = new Scanner(System.in);

        System.out.println("Player 1: Enter your name");
        player1name = scnr.nextLine();
        System.out.println("Player 2: Enter your name");
        player2name = scnr.nextLine();
        System.out.println(player2name + " will be the challenger first, and " + player1name + " will be the contestant.");
        System.out.println("The challenger will enter a well-known expression, between 20 and 50 characters, and the contestant will guess letters that may be in the expression, until the contestant can guess the expression.");
        System.out.println("Then the players will switch roles. Whoever guesses the other expression in fewer tries wins. Each person's score will be the number of tries, so the lower score wins.");
        System.out.print("\n");

        numGuesses1 = playGame(player1name, player2name); //player 1's score

        System.out.println("Now it is " + player2name + "'s turn to be the contestant.");

        numGuesses2 = playGame(player2name, player1name); //player 2's score

        System.out.println(player1name + " scored " + numGuesses1 + " and " + player2name + " scored " + numGuesses2 + "!");
        System.out.println("\n");
        if (numGuesses1 < numGuesses2) {
            System.out.println(player1name + " wins! Congratulations!"); //If Player 1 wins
        }
        else if (numGuesses1 > numGuesses2) {
            System.out.println(player2name + " wins! Congratulations!"); //If Player 2 wins
        }
        else if (numGuesses1 == numGuesses2) {
            System.out.println("Congratulations " + player1name + " and " + player2name + ", you have tied!"); //If they tie
        }
        return;
    }
};
