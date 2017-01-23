import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class hw1 {
    private static HashMap<String, Object> vars;

    private static Scanner fileInput;

    public static void main(String[] args) {
        try {
            fileInput = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            String[] tokens = line.split(" ");
            System.out.println(fileInput.nextLine());
        }
    }
}