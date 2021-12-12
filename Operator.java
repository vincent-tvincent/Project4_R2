import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Operator implements CompoundExpression {
    private String _identifier;
    private Operator _parent;
    private List<Expression> _subexpresions;

    public Operator (String identifier) {
        _identifier = identifier;
        _subexpresions = new ArrayList<>();
    }

    public String getIdentifier() {
        return _identifier;
    }

    @Override
    public void addSubexpression(Expression subexpression) {
        _subexpresions.add(subexpression);
    }

    public void removeSubexpression(Expression subexpression) {
        _subexpresions.remove(subexpression);
    }

    public CompoundExpression getParent() {
        return _parent;
    }

    @Override
    public void setParent(CompoundExpression parent) {
        _parent = (Operator) parent;
    }

    public Expression deepCopy() {
        Operator op = new Operator(_identifier);
        op.setParent(_parent);

        Expression[] subArray = new Expression[_subexpresions.size()];
        _subexpresions.toArray(subArray);

        for (int i = 0; i < subArray.length; i++) {
            op.addSubexpression(subArray[i]);
        }
        return op;
    }

    /*
     * removes redundant operators
     */
    public void flatten() {
        Expression[] subArray = new Expression[_subexpresions.size()];
        _subexpresions.toArray(subArray);

        for (int i = 0; i < subArray.length; i++) {
            subArray[i].flatten();
        }

        if(_parent != null && _parent.getIdentifier().equals(_identifier)) {
            for (int i = 0; i < subArray.length; i++) {
                _parent.addSubexpression(subArray[i]);
                subArray[i].setParent(_parent);

                _parent.removeSubexpression(this);
            }
        }
    }


    @Override
    public double evaluate(double x) {
        flatten();
        double result = _subexpresions.get(0).evaluate(x);
        for(int i = 1; i < _subexpresions.size(); i++) {
            switch (_identifier) {
                case "+" :
                    result += _subexpresions.get(i).evaluate(x);
                    break;
                case "-" :
                    result -= _subexpresions.get(i).evaluate(x);
                    break;
                case "*" :
                    result *= _subexpresions.get(i).evaluate(x);
                    break;
                case "/" :
                    result /= _subexpresions.get(i).evaluate(x);
                case "^" :
                    result = Math.pow(result, _subexpresions.get(i).evaluate(x));
                    break;
            }
        }
        return result;
    }

    /*
     * Flattens the tree then prints it
     */
    public String convertToString(int indentLevel) {
        // flatten before convert
        flatten();
        StringBuilder s = new StringBuilder();
        Expression.indent(s, indentLevel);
        s.append(_identifier);

        indentLevel += 1;
        for(Expression e : _subexpresions) {
            s.append("\n");
            s.append(e.convertToString(indentLevel));
        }
        if(_parent == null) {
            s.append("\n");
        }
        return s.toString();
    }
}