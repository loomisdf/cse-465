/**
 * Created by Daltin-Desk on 1/25/2017.
 * Variable class
 */
public class Variable {
    public Type type;
    public String name;
    public Object value;

    public Variable(Type t, String n, Object val) {
        this.type = t;
        this.name = n;
        this.value = val;
    }

    @Override
    public String toString() {
        return "Name = " + name + "; Type = " + type + "; value = " + value.toString();
    }
}
