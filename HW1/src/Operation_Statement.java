/**
 * Created by Daltin-Desk on 1/30/2017.
 */
public class Operation_Statement extends Statement {
    public Token lhs;
    public Token rhs;
    public String operation;

    public Operation_Statement(Token lhs, Token rhs, String operation, String val) {
        super(val);
        this.lhs = lhs;
        this.rhs = rhs;
        this.operation = operation;
    }
}
