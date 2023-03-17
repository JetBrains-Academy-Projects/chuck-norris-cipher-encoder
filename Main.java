import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input string:");
        String input = scanner.nextLine();

        System.out.println("The result:");
        for(int i = 0; i < input.length(); i++){
            System.out.println(input.charAt(i) + " = " + String.format("%07d", Integer.parseInt(Integer.toBinaryString(input.charAt(i)))));
        }
    }
}