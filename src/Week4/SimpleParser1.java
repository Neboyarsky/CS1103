package Week4;


/*
    This program evaluates fully parenthesized expressions typed in
    by the user.  The expressions can use positive real numbers and
    the binary operators +, -, *, and /.  The expressions are
    defined by the BNF rules:

            <expression>  ::=  <number>  |
                                  "(" <expression> <operator> <expression> ")"
            <operator>  ::=  "+" | "-" | "*" | "/"

    A number must begin with a digit (i.e., not a decimal point).
    A line of input must contain exactly one such expression.  If extra
    data is found on a line after an expression has been read, it is
    considered an error.
 */

import Week3._Samples.TextIO;

public class SimpleParser1 {


    /**
     * An object of type ParseError represents a syntax error found in
     * the user's input.
     */
    private static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    } // end nested class ParseError


    public static void main(String[] args) {

        while (true) {
            Week3._Samples.TextIO.putln("\n\nEnter a fully parenthesized expression,");
            Week3._Samples.TextIO.putln("or press return to end.");
            Week3._Samples.TextIO.put("\n?  ");
            Week3._Samples.TextIO.skipBlanks();
            if ( Week3._Samples.TextIO.peek() == '\n' )
                break;
            try {
                double val = expressionValue();
                Week3._Samples.TextIO.skipBlanks();
                if ( Week3._Samples.TextIO.peek() != '\n' )
                    throw new ParseError("Extra data after end of expression.");
                Week3._Samples.TextIO.getln();
                Week3._Samples.TextIO.putln("\nValue is " + val);
            }
            catch (ParseError e) {
                Week3._Samples.TextIO.putln("\n*** Error in input:    " + e.getMessage());
                Week3._Samples.TextIO.putln("*** Discarding input:  " + Week3._Samples.TextIO.getln());
            }
        }

        Week3._Samples.TextIO.putln("\n\nDone.");

    } // end main()


    /**
     * Read an expression from the current line of input and return its value.
     * @throws ParseError if the input contains a syntax error
     */
    private static double expressionValue() throws ParseError {
        Week3._Samples.TextIO.skipBlanks();
        if ( Character.isDigit(Week3._Samples.TextIO.peek()) ) {
            // The next item in input is a number, so the expression
            // must consist of just that number.  Read and return
            // the number.
            return Week3._Samples.TextIO.getDouble();
        }
        else if ( Week3._Samples.TextIO.peek() == '(' ) {
            // The expression must be of the form
            //         "(" <expression> <operator> <expression> ")"
            // Read all these items, perform the operation, and
            // return the result.
            Week3._Samples.TextIO.getAnyChar();  // Read the "("
            double leftVal = expressionValue();  // Read and evaluate first operand.
            char op = getOperator();             // Read the operator.
            double rightVal = expressionValue(); // Read and evaluate second operand.
            Week3._Samples.TextIO.skipBlanks();
            if ( Week3._Samples.TextIO.peek() != ')' ) {
                // According to the rule, there must be a ")" here.
                // Since it's missing, throw a ParseError.
                throw new ParseError("Missing right parenthesis.");
            }
            Week3._Samples.TextIO.getAnyChar();  // Read the ")"
            switch (op) {   //  Apply the operator and return the result.
                case '+':  return leftVal + rightVal;
                case '-':  return leftVal - rightVal;
                case '*':  return leftVal * rightVal;
                case '/':  return leftVal / rightVal;
                default:   return 0;  // Can't occur since op is one of the above.
                // (But Java syntax requires a return value.)
            }
        }
        else {
            throw new ParseError("Encountered unexpected character, \"" +
                    Week3._Samples.TextIO.peek() + "\" in input.");
        }
    } // end expressionValue()


    /**
     * If the next character in input is one of the legal operators,
     * read it and return it.  Otherwise, throw a ParseError.
     */
    static char getOperator() throws ParseError {
        Week3._Samples.TextIO.skipBlanks();
        char op = Week3._Samples.TextIO.peek();
        if ( op == '+' || op == '-' || op == '*' || op == '/' ) {
            TextIO.getAnyChar();
            return op;
        }
        else if (op == '\n')
            throw new ParseError("Missing operator at end of line.");
        else
            throw new ParseError("Missing operator.  Found \"" +
                    op + "\" instead of +, -, *, or /.");
    } // end getOperator()


} // end class SimpleParser1