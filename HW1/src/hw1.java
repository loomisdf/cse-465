import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class hw1 {
    private static HashMap<String, Object> vars;
    private static String[] reservedWords = {"PRINT", "FOR", "ENDFOR"};

    private static Scanner fileInput;
    private static int lineNumber;

    //TODO handle string assignment
    public static void assignment(String lhs, String rhs) {
        if(rhs.matches("^-?\\d+$")) {
            // rhs is just an integer
            vars.put(lhs, Integer.parseInt(rhs));
        }
        else {
            // rhs is another variable
            if(!vars.containsKey(rhs)) {
                System.err.printf("RUNTIME ERROR: line %d, variable %s does not exist\n", lineNumber, rhs);
                System.exit(0);
            }
            vars.put(lhs, vars.get(rhs));
        }
    }

    public static void addition(String lhs, String rhs) {
        if(rhs.matches("^-?\\d+$")) {
            // rhs is just an integer
            vars.put(lhs, (int)vars.get(lhs) + Integer.parseInt(rhs));
        }
        else if(!vars.containsKey(rhs)) {
            System.err.printf("RUNTIME ERROR: line %d, variable %s does not exist\n", lineNumber, rhs);
            System.exit(0);
        }
        else {
            vars.put(lhs, (int)vars.get(lhs) + (int)vars.get(rhs));
        }
    }

    public static void subtraction(String lhs, String rhs) {
        if(rhs.matches("^-?\\d+$")) {
            // rhs is just an integer
            vars.put(lhs, (int)vars.get(lhs) - Integer.parseInt(rhs));
        }
        else if(!vars.containsKey(rhs)) {
            System.err.printf("RUNTIME ERROR: line %d, variable %s does not exist\n", lineNumber, rhs);
            System.exit(0);
        }
        else {
            vars.put(lhs, (int)vars.get(lhs) - (int)vars.get(rhs));
        }
    }

    public static void multiplication(String lhs, String rhs) {
        if(rhs.matches("^-?\\d+$")) {
            // rhs is just an integer
            vars.put(lhs, (int)vars.get(lhs) * Integer.parseInt(rhs));
        }
        else if(!vars.containsKey(rhs)) {
            System.err.printf("RUNTIME ERROR: line %d, variable %s does not exist\n", lineNumber, rhs);
            System.exit(0);
        }
        else {
            vars.put(lhs, (int)vars.get(lhs) * (int)vars.get(rhs));
        }
    }

    public static void print(String var) {
        System.out.println(vars.get(var));
    }

    public static void parseLine(String line) {
        String[] tokens = line.split(" ");
        // check if the first token is a reserved word
        if(Arrays.asList(reservedWords).contains(tokens[0])) {
            // token is a reserved word
            switch(tokens[0]) {
                case "PRINT":
                    print(tokens[1]);
                    break;
            }
        }
        else {
            // token is a custom value
            if(tokens[1].equals("=")) {
                assignment(tokens[0], tokens[2]);
            }
            if(tokens[1].equals("+=")) {
                addition(tokens[0], tokens[2]);
            }
            if(tokens[1].equals("-=")) {
                subtraction(tokens[0], tokens[2]);
            }
            if(tokens[1].equals("*=")) {
                multiplication(tokens[0], tokens[2]);
            }
        }
    }

    public static void main(String[] args) {
        vars = new HashMap<>();
        lineNumber = 1;

        try {
            fileInput = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            parseLine(line);
            lineNumber++;
        }
        System.out.println("END PROGRAM");
    }
}