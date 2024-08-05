package Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int gameId;

    public void currencyConvertor(double amount, String currency){
            switch (currency.toUpperCase()) {
                case "USD":
                    System.out.println("The Amount Converted to: " + (amount / 3.8) + " " + currency.toUpperCase());
                    break;
                case "EUR":
                    System.out.println("The Amount Converted to: " + (amount / 4.15) + " " + currency.toUpperCase());
                    break;
                case "JPY":
                    System.out.println("The Amount Converted to: " + (amount / 0.026) + " " + currency.toUpperCase());
                    break;
                case "GBP":
                    System.out.println("The Amount Converted to: " + (amount / 4.87) + " " + currency.toUpperCase());
                    break;
                default:
                    System.out.println("Currency Not Supported!");
                    break;
            }

    }

    public void upperFirst(String fullName){

            String[] words = fullName.split(" ");
            String result="";

            for(String word : words){
                result += Character.toUpperCase(word.charAt(0)) + "."; // I admit I've used stackoverflow for this one
            }

            result = result.substring(0, result.length()-1);  //remove the last dot

            System.out.println(result);

    }

    public void guessTheNumber(){
        Random rand =new Random();
        int min = 1, max = 10, tries=2;
        String guess;
        int randomNumber = rand.nextInt((max - min) + 1) + min;


        while (tries >= 0) {
            Scanner input = new Scanner(System.in);
            System.out.print("Please Guess a number between 1-10: ");
            guess = input.nextLine();
            if (guess.isEmpty()) {
                System.out.println("No Guess Provided Please Try Again!");
                continue;
            } else {
                if (Integer.parseInt(guess) == randomNumber) {
                    System.out.println("You Win!");
                    break;
                } else {
                    if (Integer.parseInt(guess) < randomNumber) {
                        System.out.print("The Guess Is Too Low!");
                        if (tries > 0) {
                            System.out.println("\tTry Again.\nYou have: " + tries + " Tries Left");
                        } else {
                            System.out.println("\n");
                        }
                    } else {
                        System.out.print("The Guess IS Too High!");
                        if (tries > 0) {
                            System.out.println("\tTry Again.\nYou have: " + tries + " Tries Left");
                        } else {
                            System.out.println("\n");
                        }
                    }
                }
                tries--;
            }
        }
        if (tries < 0) {
            System.out.println("Too Bad Your Out Of Tries...\tGame Over!\nBetter Luck Next Time =)");
            System.out.println("The Correct Answer Was: " + randomNumber);
        }

    }
}
