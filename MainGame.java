package Test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainGame {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Game gameMode = new Game();
        gameMode.gameId=0;
        do {
        System.out.print("Please choose your game mode:\n1 = Currency converter\n2 = First Letter separator\n3 = Guess The Number Game\nAny Other Number = Exit: ");

        gameMode.gameId = Integer.parseInt(input.nextLine());

    switch (gameMode.gameId) {
        case 1:
            String amount;
            System.out.print("Please Enter a Currency (USD, EUR, JPY, GBP): ");
            String currency = input.nextLine();
            System.out.print("Please Enter An Amount: ");
            amount = input.nextLine();
            if (currency.isEmpty() && amount.isEmpty()) {
                System.out.println("The Currency And Amount Are Empty!");
            } else if (currency.isEmpty()) {
                System.out.println("The Currency is Empty!");
            } else if (amount.isEmpty()) {
                System.out.println("The Amount is Empty!");
            } else {
                gameMode.currencyConvertor(Double.parseDouble(amount), currency);
            }
            System.out.println("\n");
            break;
        case 2:
            System.out.print("Please Enter Your Name: ");
            String name = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("The Name Provided is Empty");
            } else {
                gameMode.upperFirst(name);
            }
            System.out.println("\n");
            break;
        case 3:
            gameMode.guessTheNumber();
            System.out.println("\n");
            break;
        default:
            System.out.println("Exiting The Program!");
            TimeUnit.SECONDS.sleep(5);
    }
}while (gameMode.gameId<4);

    }
}
