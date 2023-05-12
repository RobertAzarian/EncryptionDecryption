package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        System.out.println(operation(operation, message, key));
    }

    public static String operation(String operation, String message, int key) {
        char[] chars = message.toCharArray();
        StringBuilder sb = new StringBuilder();


        switch (operation) {
            case "enc" -> {
                for (char ch : chars) {
                    int iCh = ch + key;
                    sb.append((char) iCh);
                }
            }
            case "dec" -> {
                for (char ch : chars) {
                    int iCh = ch - key;
                    sb.append((char) iCh);
                }
            }
            default -> sb.append("Wrong operation input");
        }
        return sb.toString();
    }
}