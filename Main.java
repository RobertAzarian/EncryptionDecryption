package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        char[] chars = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            int iCh = ch;
            if (iCh >= 97 && iCh <= 122) {
                if (iCh + key > 122) {
                    int k = key;
                    while (iCh + k > 122) {
                        k -= 122 - iCh + 1;
                        iCh = 97;
                    }
                    iCh += k;
                } else {
                    iCh += key;
                }
                sb.append((char) iCh);
            } else {
                sb.append(ch);
            }
        }
        System.out.println(sb);
    }
}