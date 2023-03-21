import java.io.IOException;
import java.util.Scanner;

public class Main {
    static public String encode(String input){
        StringBuilder binaries = new StringBuilder();

        for(char c: input.toCharArray()){
            binaries.append(String.format("%7s", Integer.toBinaryString(c)).replace(" ", "0"));
        }

        // converting the binary chunks to encodings
        StringBuilder output = new StringBuilder();
        char flag = '.';
        for(char c: binaries.toString().toCharArray()){
            if(c != flag){
                flag = c;
                output.append(" ");

                switch (c){
                    case '1' -> output.append("0");
                    case '0' -> output.append("00");
                }
                output.append(" ");
            }
            output.append("0");
        }

        return output.toString().trim();
    }

    static public String decode(String input){
        try {
            // validation no. 1: The encoded message includes characters other than 0 or spaces
            for(char c: input.toCharArray()){
                if(c != '0' && c != ' '){
                    throw new IOException("Encoded string is not valid.");
                }
            }

            String[] inputs = input.split(" ");

            // validation no. 3: The number of blocks is odd
            if(inputs.length % 2 != 0){
                throw new IOException("Encoded string is not valid.");
            }

            // convert the decoded items to binary first
            StringBuilder inputBinary = new StringBuilder();
            for(int i = 0; i<inputs.length; i+=2){

                // validation no. 2: The first block of each sequence is not 0 or 00
                if(!inputs[i].equals("0") && !inputs[i].equals("00")){
                    throw new IOException("Encoded string is not valid.");
                }

                switch (inputs[i]) {
                    case "0" -> inputBinary.append("1".repeat(inputs[i + 1].length()));
                    case "00" -> inputBinary.append("0".repeat(inputs[i + 1].length()));
                }
            }

            // validation no. 4: The length of the decoded binary string is not a multiple of 7
            if(inputBinary.length() % 7 != 0){
                throw new IOException("Encoded string is not valid.");
            }

            // splitting the array into equal chunks of length 7
            String[] binaryChars = inputBinary.toString().split("(?<=\\G.{7})");

            // converting the binary strings ino ASCII characters
            StringBuilder output = new StringBuilder();
            for(String i: binaryChars){
                i = Integer.toString(Integer.parseInt(i)); // removing extra zeros in case of 6 digit binary string
                output.append((char) Integer.parseInt(i,2));
            }

            return output.toString();
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
            return "";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while(running){
            System.out.println("Please input operation (encode/decode/exit):");
            String input = scanner.nextLine();
            String output;

            switch (input) {
                case "encode" -> {
                    System.out.println("Input string:");
                    input = scanner.nextLine();
                    output = encode(input);
                    System.out.println("Encoded string:");
                    System.out.println(output + "\n");
                }
                case "decode" -> {
                    System.out.println("Input encoded string:");
                    input = scanner.nextLine();
                    output = decode(input);
                    if (!output.equals("")) {
                        System.out.println("Decoded string:");
                        System.out.println(output + "\n");
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!" + "\n");
                    running = false;
                }
                default -> System.out.println("There is no '" + input + "' operation" + "\n");
            }
        }
    }
}