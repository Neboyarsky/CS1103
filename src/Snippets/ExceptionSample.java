package Snippets;

//public class ExceptionSample {
//    /**
//     * An object of type ParseError represents a syntax error found in
//     * the user’s input.
//     */
//    private static class ParseError extends Exception {
//        ParseError(String message) {
//            super(message);
//        }
//    } // end nested class ParseError
//
//    /**
//     * If the next character in input is one of the legal operators,
//     * read it and return it. Otherwise, throw a ParseError.
//     */
//    static char getOperator() throws ParseError {
//        TextIO.skipBlanks();
//        char op = TextIO.peek();
//        if ( op == ’+’ || op == ’-’ || op == ’*’ || op == ’/’ ) {
//            TextIO.getAnyChar();
//            return op;
//        }
//        else if (op == ’\n’)
//            throw new ParseError("Missing operator at end of line.");
//        else
//            throw new ParseError("Missing operator. Found \"" +
//                op + "\" instead of +, -, *, or /.");
//    } // end getOperator()
//
//    public static void main(String[] args) {
//        // some routine
//    }
//}
