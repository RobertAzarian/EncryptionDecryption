package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String message = "we found a treasure!";
        StringBuilder ciphertext = new StringBuilder();

        char[] chars = message.toCharArray();
        for(char ch : chars) {
            int iCh = ch;
            if (iCh >= 97 && iCh <= 122) {    // 97-109___110-122
                if (iCh <= 109) {
                    iCh += (109 - iCh) * 2 + 1;
                } else {
                    iCh -= (iCh - 110) * 2 + 1;
                }
                ciphertext.append((char) iCh);
            } else {
                ciphertext.append(ch);
            }
        }
        System.out.println(ciphertext);
    }
}
