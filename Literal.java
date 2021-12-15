public class Literal implements Expression{
    private String _data;
    private CompoundExpression _parent;

    public Literal(String data) {
        _data = data;
    }

    public CompoundExpression getParent() {
        return _parent;
    }
    @Override
    public void setParent(CompoundExpression parent) {
        _parent = parent;
    }

    public Expression deepCopy() {
        Literal l = new Literal(_data);
        l.setParent(_parent);
        return l;
    }

    // a literal will never have sub expressions
    public void flatten() {

    }


    @Override
    public double evaluate(double x) {
        if(_data.equals("x")){
            return x;
        }else{
            return Double.valueOf(_data);
        }

    }

    public String convertToString(int indentLevel) {
        StringBuilder s = new StringBuilder();
        Expression.indent(s, indentLevel);
        String l = _data;
        char[] chars = l.toCharArray();
        for(int ascii : chars) {
            // [0-9]
            if(ascii >= 48 && ascii <= 57){
                l += ".0";
                break;
            }
        }
        s.append(l);
        return s.toString();
    }
}