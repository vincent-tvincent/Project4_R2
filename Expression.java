interface Expression {
	/**
	 * Given the value of the dependent variable x, evaluate this expression.
	 * @param x the given value of x
	 * @return the value of the expression
	 */
	double evaluate (double x);

        /**
         * Creates a String representation by recursively printing out (using indentation) the
         * tree represented by this expression, starting at the specified indentation level.
         * @param indentLevel the indentation level (number of tabs from the left margin) at which to start
         * @return a String representation of the expression tree.
         */
        String convertToString (int indentLevel);

	/**
	 * Static helper method to indent a specified number of times from the left margin, by
	 * appending tab characters to the specified StringBuilder.
	 * @param sb the StringBuilder to which to append tab characters.
	 * @param indentLevel the number of tabs to append.
	 */
	public static void indent (StringBuilder sb, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			sb.append('\t');
		}
	}

	void flatten();

	void setParent(CompoundExpression parent);
}
