package atomcode.utils;

import java.util.Scanner;

public class userInput {
    private final static Scanner scanner = new Scanner(System.in);

    public static int readInt () {
        while (true) {
            try {
                int userInput = scanner.nextInt();
                scanner.nextLine();
                return userInput;
            } catch (Exception e) {
                System.out.println("Invalid user input. Try again.");
                scanner.nextLine();
            }
        }
    }
}
