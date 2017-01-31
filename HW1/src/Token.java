/**
 * Created by Daltin-Desk on 1/24/2017.
 * Token Class
 */
public class Token {
    public Type type;
    public Object value;

    public Token(Type t, Object val) {
        this.type = t;
        this.value = val;
    }

    public Token(String str) {
        if(str.equals("FOR")) {
            this.type = Type.FOR;
            this.value = "for";
        }
        else if(str.equals("PRINT")) {
            this.type = Type.PRINT;
            this.value = "print";
        }
        else if(str.equals("ENDFOR")) {
            this.type = Type.ENDFOR;
            this.value = "endfor";
        }
        else if(str.equals("=")) {
            this.type = Type.ASSIGN;
            this.value = "=";
        }
        else if(str.equals("+=")) {
            this.type = Type.ADD;
            this.value = "+=";
        }
        else if(str.equals("-=")) {
            this.type = Type.SUBTRACT;
            this.value = "-=";
        }
        else if(str.equals("*=")) {
            this.type = Type.MULTIPLY;
            this.value = "*=";
        }
        else if(str.equals(";")) {
            this.type = Type.SEMICOLON;
            this.value = ";";
        }
        else {
            // token is either a variable name, a string literal, or an integer
            if(str.startsWith("\"")) {
                this.type = Type.STRING;
                this.value = str;
            }
            else if(str.matches("^-?\\d+$")){
                this.type = Type.INTEGER;
                this.value = Integer.parseInt(str);
            }
            else {
                this.type = Type.VAR;
                this.value = str;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        Token t = (Token)other;
        if(this.type == t.type) {
            if(this.type == Type.STRING) {
                if(this.value.toString().equals(t.value.toString()))
                    return true;
            }
            if(this.type == Type.ENDFOR && t.type == Type.ENDFOR) {
                return true;
            }
            else {
                if((int)this.value == (int)t.value)
                    return true;
            }
        }
        return false;
    }
}
