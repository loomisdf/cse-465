import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class hw1 {
    private static HashMap<String, Object> vars;
    private static String[] reservedWords = {"PRINT", "FOR", "ENDFOR"};

    private static Scanner fileInput;
    private static int lineNumber;

    public static void assignment(Token lhs, Token rhs) {
        if(rhs.type == Type.VAR) {
            // if rhs is a variable
            if(!vars.containsKey(rhs.value)) {
                System.err.printf("RUNTIME ERROR: line %d, variable %s has no value\n", lineNumber, rhs);
                System.exit(0);
            }
            else {
                vars.put(lhs.value.toString(), vars.get(rhs.value));
            }
        }
        else {
            // if rhs is a string literal or integer
            vars.put(lhs.value.toString(), rhs.value);
        }
    }

    public static void addition(String lhs, String rhs) {
    }

    public static void subtraction(String lhs, String rhs) {
    }

    public static void multiplication(String lhs, String rhs) {
    }

    public static void print(Token var) {
        System.out.println(vars.get(var.value));
    }

    public static void parseLine(String line) {
        ArrayList<String> strings = new ArrayList<>();
        String value = "";
        boolean stringFound = false;
        char curr_char = ' ';
        char prev_char = ' ';
        for(int i = 0; i < line.length(); i++) {
            curr_char = line.charAt(i);
            if(stringFound) {
                value += curr_char;
                if(prev_char != '\\' && curr_char == '"') {
                    strings.add(value);
                    stringFound = false;
                    value = "";
                }
            }
            else {
                if (curr_char == '"') {
                    stringFound = true;
                }
                if (curr_char == ' ') {
                    if(!value.equals(""))
                        strings.add(value);
                    value = "";
                }
                else if(curr_char == ';') {
                    strings.add(";");
                }
                else {
                    value += curr_char;
                }
            }
            prev_char = curr_char;
        }

        ArrayList<Token> tokens = new ArrayList<>();
        for(String str : strings) {
            tokens.add(new Token(str));
        }

        if(tokens.get(0).value.equals("print")) {
            print(tokens.get(1));
        }
        else if(tokens.get(0).value.equals("endfor")) {

        }
        else if(tokens.get(1).type == Type.ASSIGN) {
            assignment(tokens.get(0), tokens.get(2));
        }
        System.out.println();
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