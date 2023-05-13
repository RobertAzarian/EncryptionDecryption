package encryptdecrypt;

import java.io.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        String operation = "enc";
        int key = 0;
        String message = "";
        File fileOutput = null;
        File fileInput = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> operation = "dec".equals(args[i + 1]) ? "dec" : "enc";
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> message = args[i + 1];
                case "-in" -> fileInput = new File(args[i + 1]);
                case "-out" -> fileOutput = new File(args[i + 1]);
            }
        }
        if (fileInput != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
                message = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String result = operation(operation, message, key);

        if (fileOutput != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutput))) {
                bw.write(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(result);
        }
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