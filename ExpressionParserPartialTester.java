import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 4; you should definitely add more tests!
 */
public class ExpressionParserPartialTester {
	private ExpressionParser _parser;

	@BeforeEach
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {
		_parser = new SimpleExpressionParser();
	}

	@Test
	/**
	 * Verifies that a specific expression is parsed into the correct parse tree.
	 */
	public void testExpression1 () throws ExpressionParseException {
		final String expressionStr = "x+x";
		final String parseTreeStr = "+\n\tx\n\tx\n";
		assertEquals(parseTreeStr, _parser.parse(expressionStr).convertToString(0));
	}

	@Test
	/**
	 * Verifies that a specific expression is parsed into the correct parse tree.
	 */
	public void testExpression2 () throws ExpressionParseException {
		final String expressionStr = "13*x";
		final String parseTreeStr = "*\n\t13.0\n\tx\n";
		assertEquals(parseTreeStr, _parser.parse(expressionStr).convertToString(0));
	}

	@Test
	/**
	 * Verifies that a specific expression is parsed into the correct parse tree.
	 */
	public void testExpression3 () throws ExpressionParseException {
		final String expressionStr = "4*(x-5*x)";
		final String parseTreeStr = "*\n\t4.0\n\t()\n\t\t-\n\t\t\tx\n\t\t\t*\n\t\t\t\t5.0\n\t\t\t\tx\n";
		assertEquals(parseTreeStr, _parser.parse(expressionStr).convertToString(0));
	}

        @Test
        /**
         * Verifies that a specific expression is evaluated correctly.
         */
        public void testEvaluate1 () throws ExpressionParseException {
                final String expressionStr = "4*(x+5*x)";
                assertEquals(72, (int) _parser.parse(expressionStr).evaluate(3));
        }

        @Test
        /**
         * Verifies that a specific expression is evaluated correctly.
         */
        public void testEvaluate2 () throws ExpressionParseException {
                final String expressionStr = "x";
                assertEquals(1, (int) _parser.parse(expressionStr).evaluate(1));
        }

        @Test
        /**
         * Verifies that a specific expression is evaluated correctly.
         */
        public void testEvaluate3 () throws ExpressionParseException {
                final String expressionStr = "x^2";
                assertEquals(1, (int) _parser.parse(expressionStr).evaluate(1));
        }

        @Test
        /**
         * Verifies that a specific expression is evaluated correctly.
         */
        public void testEvaluate4 () throws ExpressionParseException {
                final String expressionStr = "2^x";
                assertEquals(8, (int) _parser.parse(expressionStr).evaluate(3));
        }

        @Test
        /**
         * Verifies that a specific expression is evaluated correctly.
         */
        public void testEvaluate5 () throws ExpressionParseException {
                final String expressionStr = "x^3^2";
                assertEquals(262144, (int) _parser.parse(expressionStr).evaluate(4));
        }
}
