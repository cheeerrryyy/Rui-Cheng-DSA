package oy.tol.tra;

/**
 * Uses the StackInterface implementation to check that parentheses in text
 * files
 * match.
 * <p>
 * NOTE: The Person.json test file has an error, but the tests expect that. So
 * the test will
 * not fail with that file -- the erroneus json file is _expected_.
 * <p>
 * Note that you do not have to instantiate this class anywhere. The
 * ParenthesisTests:
 * <ul>
 * <li>Constructs your implementation of the
 * {@code StackImplementation<Character>}, and
 * <li>Calls ParenthesisChecker.checkParentheses.
 * </ul>
 * So your job is just to have a working StackImplementation and implement the
 * ParenthesisChecker.checkParentheses.
 * Then execute the ParenthesisTests.
 */
public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * <code>Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...</code>,
    * <p>
    * which can be found for example in Java source code and JSON structures.
    * <p>
    * If the string has issues with parentheses, you should throw a
    * {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you
    * in the ParenthesesException
    * class to be used.
    * <p>
    * What is to be tested:
    * <ul>
    * <li>when an opening parenthesis is found in the string, it is successfully
    * pushed to the stack.
    * <li>when a closing parenthesis is found in the string, a matching opening
    * parenthesis is popped from the stack.
    * <li>when popping a parenthesis from the stack, it must not be null. Otherwise
    * string has too many closing parentheses.
    * <li>when the string has been handled, and if the stack still has parentheses,
    * there are too few closing parentheses.
    * </ul>
    * 
    * @param stack      The stack object used in checking the parentheses from the
    *                   string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both
    *         opening and closing).
    * @throws ParenthesesException     if the parentheses did not match as
    *                                  intended.
    * @throws StackAllocationException If the stack cannot be allocated or
    *                                  reallocated if necessary.
    */
   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      int matchCount = 0;
      for (int i = 0; i < fromString.length(); i++) {
         char currentChar = fromString.charAt(i);

         if (isOpeningParenthesis(currentChar)) {
            stack.push(currentChar);
         } else if (isClosingParenthesis(currentChar)) {
            if (stack.isEmpty()) {
               throw new ParenthesesException("Invalid parenthesis in file",
                     ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }

            char poppedChar = stack.pop();
            if (!matchingParentheses(poppedChar, currentChar)) {
               throw new ParenthesesException("Invalid parenthesis in file",
                     ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            } else {
               matchCount++;
            }
         }
      }

      if (!stack.isEmpty()) {
         throw new ParenthesesException("Invalid parenthesis in file",
               ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
      }
      int totalCount = matchCount * 2;
      return totalCount;

   }

   private static boolean isOpeningParenthesis(char c) {
      return c == '(' || c == '[' || c == '{';
   }

   private static boolean isClosingParenthesis(char c) {
      return c == ')' || c == ']' || c == '}';
   }

   private static boolean matchingParentheses(char opening, char closing) {
      return (opening == '(' && closing == ')') ||
            (opening == '[' && closing == ']') ||
            (opening == '{' && closing == '}');
   }
}