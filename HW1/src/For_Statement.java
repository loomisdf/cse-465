import java.util.ArrayList;

/**
 * Created by Daltin-Desk on 1/30/2017.
 */
public class For_Statement extends Statement{
    ArrayList<Token> tokens;

    public For_Statement(ArrayList<Token> toks, String val) {
        super(val);
        tokens = toks;
    }
}
