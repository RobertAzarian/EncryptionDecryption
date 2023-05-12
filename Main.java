package encryptdecrypt;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        String operation = "enc";
        int key = 0;
        String message = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> operation = "dec".equals(args[i + 1]) ? "dec" : "enc";
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> message = args[i + 1];
            }
        }

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