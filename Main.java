package encryptdecrypt;

import java.io.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        String operation = "enc";
        int key = 0;
        String message = "";
        File fileOutput = null;
        File fileInput = null;
        String algorithm = "shift";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> operation = "dec".equals(args[i + 1]) ? "dec" : "enc";
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> message = args[i + 1];
                case "-in" -> fileInput = new File(args[i + 1]);
                case "-out" -> fileOutput = new File(args[i + 1]);
                case "-alg" -> algorithm = "unicode".equals(args[i + 1]) ? "unicode" : "shift";
            }
        }

        if (fileInput != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
                message = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        AlgorithmsFactory algorithmChoose = AlgorithmChoice.getAlgorithm(algorithm);
        String result = algorithmChoose.operation(operation, message, key);

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
}