import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class hw1 {
    private static HashMap<String, Variable> vars;
    private static String[] reservedWords = {"PRINT", "FOR", "ENDFOR"};
    private static String[] COMPOUND_ASSIGNMENTS = {"+=", "-=", "*="};

    private static Scanner fileInput;
    private static int lineNumber;

    public static Variable getVar(Token tok) {
        if(!vars.containsKey(tok.value)) {
            System.err.printf("RUNTIME ERROR: line %d, variable %s does not exist\n", lineNumber, tok);
            System.exit(0);
        }
        return vars.get(tok.value.toString());
    }

    public static void assignment(Token lhs, Token rhs) {
        if(rhs.type == Type.VAR) {
            vars.put(lhs.value.toString(), getVar(rhs));
        }
        else {
            // if rhs is a string literal or integer
            // create a variable
            vars.put(lhs.value.toString(), new Variable(rhs.type, lhs.value.toString(), rhs.value));
        }
    }

    public static void compound_assign(Token lhs, Token rhs, char operator) {
        Variable lhs_var = getVar(lhs);
        if(rhs.type == Type.VAR) {
            Variable rhs_var = getVar(rhs);
            if(lhs_var.type != rhs_var.type) {
                System.err.printf("RUNTIME ERROR: line %d, Type mismatch\n", lineNumber);
                System.exit(0);
            }
            else {
                if (lhs_var.type == Type.INTEGER) {
                    switch(operator) {
                        case '+':
                            lhs_var.value = (int) lhs_var.value + (int) rhs_var.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;

                        case '-':
                            lhs_var.value = (int) lhs_var.value - (int) rhs_var.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;

                        case '*':
                            lhs_var.value = (int) lhs_var.value * (int) rhs_var.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;
                    }
                } else if (lhs_var.type == Type.STRING) {
                    switch (operator) {
                        case '+':
                            lhs_var.value = lhs_var.value + (String) rhs_var.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;
                        default:
                            System.err.printf("RUNTIME ERROR: line %d, Cannot use %c with strings\n", lineNumber, operator);
                            System.exit(0);
                            break;
                    }

                }
            }
        }
        else {
            if(lhs_var.type != rhs.type) {
                System.err.printf("RUNTIME ERROR: line %d, Type mismatch\n", lineNumber);
                System.exit(0);
            }
            else {
                if (lhs_var.type == Type.INTEGER) {
                    switch(operator) {
                        case '+':
                            lhs_var.value = (int) lhs_var.value + (int) rhs.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;

                        case '-':
                            lhs_var.value = (int) lhs_var.value - (int) rhs.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;

                        case '*':
                            lhs_var.value = (int) lhs_var.value * (int) rhs.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;
                    }
                }
                else if (lhs_var.type == Type.STRING) {
                    switch(operator) {
                        case '+':
                            lhs_var.value = lhs_var.value + (String) rhs.value;
                            vars.replace(lhs.value.toString(), lhs_var);
                            break;

                        default:
                            System.err.printf("RUNTIME ERROR: line %d, Cannot use %c with strings\n", lineNumber, operator);
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }

    public static void print(Token tok) {
        Variable v = getVar(tok);
        System.out.println(v.name + "=" + v.value);
    }

    public static ArrayList<Token> getTokens(String line) {
        ArrayList<Token> tokens = new ArrayList<>();
        String value = "";
        boolean stringFound = false;
        char curr_char;
        char prev_char = ' ';
        for(int i = 0; i < line.length(); i++) {
            curr_char = line.charAt(i);
            if(stringFound) {
                value += curr_char;
                if(prev_char != '\\' && curr_char == '"') {
                    value = value.substring(1, value.length() - 1);
                    tokens.add(new Token(Type.STRING, value));
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
                        tokens.add(new Token(value));
                    value = "";
                }
                else if(curr_char == ';') {
                    tokens.add(new Token(Type.SEMICOLON, ";"));
                }
                else {
                    value += curr_char;
                }
            }
            prev_char = curr_char;
        }

        return tokens;
    }

    public static void parseLine(String line) {
        ArrayList<Token> tokens = getTokens(line);

        if(tokens.get(0).value.equals("print")) {
            print(tokens.get(1));
        }
        else if(tokens.get(0).value.equals("endfor")) {

        }
        else if(tokens.get(1).type == Type.ASSIGN) {
            assignment(tokens.get(0), tokens.get(2));
        }
        else if(Arrays.asList(COMPOUND_ASSIGNMENTS).contains(tokens.get(1).value.toString())) {
            compound_assign(tokens.get(0), tokens.get(2), tokens.get(1).value.toString().charAt(0));
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