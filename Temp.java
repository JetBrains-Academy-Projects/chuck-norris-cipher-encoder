import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input string:");
        String input = scanner.nextLine();

        StringBuilder sbBinary = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            sbBinary.append(String.format("%7s", Integer.toBinaryString(ch)).replace(" ", "0"));
        }

        char flag = '.';
        for (char ch : sbBinary.toString().toCharArray()) {
            if (ch != flag) {
                flag = ch;
                result.append(" ");
                switch (ch) {
                    case '1' -> result.append("0");
                    case '0' -> result.append("00");
                }
                result.append(" ");
            }
            result.append("0");
        }

        System.out.println("The result:");
        System.out.println(result.toString().trim());
    }
}