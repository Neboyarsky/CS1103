package Week4;


import Week3._Samples.TextIO;

/**
    This program evaluates standard expressions typed in
    by the user.  The expressions can use positive real numbers and
    the binary operators +, -, *, and /.  The unary minus operation
    is supported.  The expressions are defined by the BNF rules:

            <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ]...

            <term>  ::=  <factor> [ [ "*" | "/" ] <factor> ]...

            <factor>  ::=  <number>  |  "(" <expression> ")"

    A number must begin with a digit (i.e., not a decimal point).
    A line of input must contain exactly one such expression.  If extra
    data is found on a line after an expression has been read, it is
    considered an error.
 */

public class SimpleParser2 {


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
            Week3._Samples.TextIO.putln("\n\nEnter an expression, or press return to end.");
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
        boolean negative;  // True if there is a leading minus sign.
        negative = false;
        if (Week3._Samples.TextIO.peek() == '-') {
            Week3._Samples.TextIO.getAnyChar();
            negative = true;
        }
        double val;  // Value of the expression.
        val = termValue();
        if (negative)
            val = -val;
        Week3._Samples.TextIO.skipBlanks();
        while ( Week3._Samples.TextIO.peek() == '+' || Week3._Samples.TextIO.peek() == '-' ) {
            // Read the next term and add it to or subtract it from
            // the value of previous terms in the expression.
            char op = Week3._Samples.TextIO.getAnyChar();
            double nextVal = termValue();
            if (op == '+')
                val += nextVal;
            else
                val -= nextVal;
            Week3._Samples.TextIO.skipBlanks();
        }
        return val;
    } // end expressionValue()


    /**
     * Read a term from the current line of input and return its value.
     * @throws ParseError if the input contains a syntax error
     */
    private static double termValue() throws ParseError {
        Week3._Samples.TextIO.skipBlanks();
        double val;
        val = factorValue();
        Week3._Samples.TextIO.skipBlanks();
        while ( Week3._Samples.TextIO.peek() == '*' || Week3._Samples.TextIO.peek() == '/' ) {
            // Read the next factor, and multiply or divide
            // the value-so-far by the value of this factor.
            char op = Week3._Samples.TextIO.getAnyChar();
            double nextVal = factorValue();
            if (op == '*')
                val *= nextVal;
            else
                val /= nextVal;
            Week3._Samples.TextIO.skipBlanks();
        }
        return val;
    } // end termValue()


    /**
     * Read a factor from the current line of input and return its value.
     * @throws ParseError if the input contains a syntax error
     */
    private static double factorValue() throws ParseError {
        Week3._Samples.TextIO.skipBlanks();
        char ch = Week3._Samples.TextIO.peek();
        if ( Character.isDigit(ch) ) {
            // The factor is a number.
            return Week3._Samples.TextIO.getDouble();
        }
        else if ( ch == '(' ) {
            // The factor is an expression in parentheses.
            Week3._Samples.TextIO.getAnyChar();  // Read the "("
            double val = expressionValue();
            Week3._Samples.TextIO.skipBlanks();
            if ( Week3._Samples.TextIO.peek() != ')' )
                throw new ParseError("Missing right parenthesis.");
            TextIO.getAnyChar();  // Read the ")"
            return val;
        }
        else if ( ch == '\n' )
            throw new ParseError("End-of-line encountered in the middle of an expression.");
        else if ( ch == ')' )
            throw new ParseError("Extra right parenthesis.");
        else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
            throw new ParseError("Misplaced operator.");
        else
            throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
    }


} // end class SimpleParser2