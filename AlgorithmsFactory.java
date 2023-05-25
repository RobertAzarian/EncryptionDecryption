package encryptdecrypt;

abstract class AlgorithmsFactory {

    abstract String operation(String operation, String message, int key);
}

class AlgorithmChoice {

    public static AlgorithmsFactory getAlgorithm(String algorithm) {
        if ("unicode".equals(algorithm)) {
            return new Unicode();
        } else {
            return new Shift();
        }
    }
}

class Shift extends AlgorithmsFactory {

    String operation(String operation, String message, int key) {
        char[] chars = message.toCharArray();
        StringBuilder sb = new StringBuilder();

        switch (operation) {
            case "enc" -> {
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
                    } else if (iCh >= 65 && iCh <= 90) {
                        if (iCh + key > 90) {
                            int k = key;
                            while (iCh + k > 90) {
                                k -= 90 - iCh + 1;
                                iCh = 65;
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
            }

            case "dec" -> {
                for (char ch : chars) {
                    int iCh = ch;
                    if (iCh >= 97 && iCh <= 122) {
                        if (iCh - key < 97) {
                            int k = key;
                            while (iCh - k < 97) {
                                k -= iCh - 97 + 1;
                                iCh = 122;
                            }
                            iCh -= k;
                        } else {
                            iCh -= key;
                        }
                        sb.append((char) iCh);
                    } else if (iCh >= 65 && iCh <= 90) {
                        if (iCh - key < 65) {
                            int k = key;
                            while (iCh - k < 65) {
                                k -= iCh - 65 + 1;
                                iCh = 90;
                            }
                            iCh -= k;
                        } else {
                            iCh -= key;
                        }
                        sb.append((char) iCh);
                    } else {
                        sb.append(ch);
                    }
                }
            }

            default -> sb.append("Wrong operation input");
        }
        return sb.toString();
    }
}

class Unicode extends AlgorithmsFactory {

    String operation(String operation, String message, int key) {
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
