package Test;

import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Game gameMode = new Game();
        System.out.print("Please choose your game mode (1 = Currency converter, 2 = First Letter separator, 3 = Guess The Number Game): ");

        gameMode.gameId = Integer.parseInt(input.nextLine());

        switch (gameMode.gameId){
            case 1:
            String amount;
            System.out.print("Please Enter a Currency (USD, EUR, JPY, GBP): ");
            String currency = input.nextLine();
            System.out.print("Please Enter An Amount: ");
            amount = input.nextLine();
            if (currency.isEmpty() && amount.isEmpty()){
                System.out.println("The Currency And Amount Are Empty!");
            } else if (currency.isEmpty()) {
                System.out.println("The Currency is Empty!");
            } else if(amount.isEmpty()){
                System.out.println("The Amount is Empty!");
            }
            else {
                gameMode.currencyConvertor(Double.parseDouble(amount), currency);
            }
            break;
            case 2:
                System.out.print("Please Enter Your Name: ");
                String name = input.nextLine();
                if(name.isEmpty()){
                    System.out.println("The Name Provided is Empty");
                }
                else {
                    gameMode.upperFirst(name);
                }
                break;
            case 3:
                gameMode.guessTheNumber();
                break;
            default:
                System.out.println("Wrong Game Mode Selected!");
        }

    }
}
