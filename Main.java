import java.util.Scanner;

public class Main {
    private static boolean checkContainOnlyZeros(String inputString){
        for (int i = 0; i < inputString.length(); i++) {
            return inputString.matches("^[0]+$");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        int n = 2;

        String[] tmpSubStrings = input.split(" ");

        final int newArrLength = tmpSubStrings.length / n;

        // 3° check not valid encoded messages
        if (tmpSubStrings.length % n != 0) {
            System.out.print("Encoded string is not valid.");
            return;
        }

        String[] subStrings = new String[newArrLength];

        boolean isZeros = false;
        boolean isContainingOnlyZeros = false;

        for (int i = 0; i < tmpSubStrings.length; i++) {

            // 1° check not valid encoded messages
            isContainingOnlyZeros = checkContainOnlyZeros(tmpSubStrings[i]);
            if (!isContainingOnlyZeros) {
                System.out.print("Encoded string is not valid.");
                return;
            }

            if (i % n == 0) {

                // 2° check not valid encoded messages
                if (!tmpSubStrings[i].equals("00") && !tmpSubStrings[i].equals("0")) {
                    System.out.print("Encoded string is not valid.");
                    return;
                }

                if (tmpSubStrings[i].equals("00")) {
                    isZeros = true;
                } else {
                    isZeros = false;
                }


            } else {
                if (isZeros){
                    subStrings[i/n] = tmpSubStrings[i];
                } else {
                    subStrings[i/n] = tmpSubStrings[i].replaceAll("0", "1");
                }
            }


        }

        String strBinaryDigit = "";
        for (String substring : subStrings) {
            strBinaryDigit += substring;
        }

        // 4° check not valid encoded messages
        if (strBinaryDigit.length() % 7 != 0) {
            System.out.print("Encoded string is not valid.");
            return;
        }

        String[] binaryDigits = strBinaryDigit.split("(?<=\\G.{" + 7 + "})");

        System.out.println("Decoded string:");
        int decimalValue = 0;
        for (String binaryDigit : binaryDigits) {
            decimalValue = Integer.parseInt(binaryDigit,2);
            char character = (char) decimalValue;
            System.out.print(character);
        }
    }
}