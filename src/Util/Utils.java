package Util;

import Model.LoadedCalculator.ILoadedCalculatorModel;
import java.io.IOException;
import java.util.Objects;

/**
 * This class holds methods that are used in multiple places by multiple classes.
 */
public class Utils {

  //private constructor preventing instantiation.
  private Utils() {
  }

  /**
   * Given a string, returns the two coefficients of a complex number in an array.
   *
   * @param complex The string representation of a complex number (a+bi).
   * @return An array where the first element is the a value, and the second is the b
   * @throws NumberFormatException If the string is not a complex number.
   */
  public static int[] complexFromString(String complex) throws NumberFormatException {
    if (complex.equals("")) {
      throw new NumberFormatException("Invalid input given!");
    }
    complex = removeAllChar(complex, ' ');
    if (complex.contains("i") && complex.charAt(complex.length() - 1) != 'i') {
      int locationOfSymbol = !complex.contains("+")
          ? complex.substring(1).indexOf("-") + 1
          : complex.indexOf("+");

      String newComplex = complex.substring(locationOfSymbol + 1)
          + complex.charAt(locationOfSymbol)
          + complex.substring(0, locationOfSymbol);

      if (newComplex.charAt(newComplex.length() - 1) == 'i') {
        return complexFromString(newComplex);
      } else {
        throw new NumberFormatException("Invalid input given!");
      }
    }
    int locANew = !complex.contains("+") ?
        complex.substring(1).indexOf("-") :
        complex.indexOf("+");
    int aNew = locANew < 0 ? (!complex.contains("i") ? Integer.parseInt(complex) : 0)
        : (!complex.contains("i") || !complex.contains("-")
            || (complex.indexOf("-") == 0 && complex.contains("+"))
            ? Integer.parseInt(complex.substring(0, locANew))
            : Integer.parseInt(complex.substring(0, locANew + 1)));

    int locBNew = complex.indexOf("i");
    if (locBNew < 0) {
      if (complex.contains("+")) {
        throw new NumberFormatException("Invalid input given!");
      }
      return new int[]{aNew, 0};
    } else if (locBNew == 0
        || complex.charAt(locBNew - 1) == '+') {
      return new int[]{aNew, 1};
    } else if (complex.charAt(locBNew - 1) == '-') {
      return new int[]{aNew, -1};
    } else {
      return new int[]{aNew, Integer.parseInt(complex.substring(locANew + 1, locBNew))};
    }
  }

  /**
   * Removes all occurrences of the given character within the string.
   *
   * @param str The string that is being edited.
   * @param c   The character being removed.
   * @return A copy of the string with the given characters removed.
   */
  public static String removeAllChar(String str, char c) {
    String ans = str;
    for (int i = 0; i < ans.length() - 1; i++) {
      if (ans.charAt(i) == c) {
        ans = ans.substring(0, i) + ans.substring(i + 1);
        i--;
      }
    }
    return ans;
  }

  /**
   * A method that solves equations that come as strings, and then the math is done with a
   * calculator.
   *
   * @param equation The given equation that is being solved.
   * @param calc     The calculator that does the calculations
   * @return The result of the equation that is in string form.
   */
  public static double equationSolver(String equation, ILoadedCalculatorModel calc) {
    if (equation.length() == 0) {
      return 0.0;
    }
    try {
      return adjustedParseDouble(equation);
    } catch (NumberFormatException e) {
      if (((equation.indexOf(".") != equation.lastIndexOf("."))
          && !equation.contains("×") && equation.contains("+")
          && equation.contains("-") && equation.contains("÷"))
          || equation.indexOf("(-)") == equation.length()-3) {
        throw new IllegalArgumentException("Invalid Equation given!");
      }
      if (equation.charAt(0) == '[' && equation.charAt(equation.length() - 1) == ']') {
        return equationSolver(equation.substring(1, equation.length() - 1), calc);
      }
    }
    equation = equation.replace("×", "*").replace("÷", "/");
    equation = applyNegative(equation);
    int freeNeg = 0;
    if ((equation.contains("(") && !equation.contains(")"))
        || (!equation.contains("(") && equation.contains(")"))
        || (equation.indexOf(")") < equation.indexOf("("))
        || equation.charAt(0) == '×' || equation.charAt(0) == '÷'
        || equation.charAt(0) == '*' || equation.charAt(0) == '/'
        || equation.charAt(equation.length() - 1) == '+'
        || equation.charAt(equation.length() - 1) == '×'
        || equation.charAt(equation.length() - 1) == '÷'
        || equation.charAt(equation.length() - 1) == '*'
        || equation.charAt(equation.length() - 1) == '/'
        || equation.charAt(equation.length() - 1) == '-') {
      throw new IllegalArgumentException("Invalid Equation given!");
    }
    if (equation.contains("(")) {
      String eq = equation.substring(0, equation.indexOf("("))
          + equationSolver(equation.substring(equation.indexOf("(") + 1,
          rightParenFinder(equation, equation.indexOf("("))), calc)
          + equation.substring(rightParenFinder(equation, equation.indexOf("(")) + 1);
      return equationSolver(eq, calc);
    }
    try {
      freeNeg = freeNegIndex(equation);
    } catch (StringIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid input given!");
    }
    if ( freeNeg > 0) {
      if (!equation.contains("+") || equation.lastIndexOf("+") < freeNeg) {
        if (equation.contains("[") || equation.contains("]")) {
          equation = negativeVsMinusProblem(equation, '-', calc);
          return equationSolver(equation, calc);
        }
        return calc.sub(equationSolver(equation.substring(0, equation.indexOf("-")), calc),
            equationSolver(equation.substring(equation.indexOf("-") + 1), calc));
      } else {
        return calc.add(equationSolver(equation.substring(0, equation.indexOf("+")), calc),
            equationSolver(equation.substring(equation.indexOf("+") + 1), calc));
      }
    }
    if (equation.contains("+")) {
      if (equation.contains("[") || equation.contains("]")) {
        equation = negativeVsMinusProblem(equation, '+', calc);
        return equationSolver(equation, calc);
      }
      return calc.add(equationSolver(equation.substring(0, equation.indexOf("+")), calc),
          equationSolver(equation.substring(equation.indexOf("+") + 1), calc));
    }
    if (equation.contains("*")) {
      if (equation.contains("[") || equation.contains("]")) {
        equation = negativeVsMinusProblem(equation, '*', calc);
        return equationSolver(equation, calc);
      }

      return calc.multi(equationSolver(equation.substring(0, equation.indexOf("*")), calc),
          equationSolver(equation.substring(equation.indexOf("*") + 1), calc));

    }
    if (equation.contains("/")) {
      if (equation.contains("[") || equation.contains("]")) {
        equation = negativeVsMinusProblem(equation, '/', calc);
        return equationSolver(equation, calc);
      }
      return calc.divide(equationSolver(equation.substring(0, equation.indexOf("/")), calc),
          equationSolver(equation.substring(equation.indexOf("/") + 1), calc));
    }
    throw new IllegalArgumentException("Invalid Equation given!");
  }

  private static int freeNegIndex(String s) {
    for(int i = 0; i < s.length()-1; i++) {
      if(s.charAt(i) == '-' && s.charAt(i-1) != '[') {
        return i;
      }
    }
    return -1;
  }

  private static String negativeVsMinusProblem(String string, char symbol,
      ILoadedCalculatorModel calc) {
    String equation = string;
    int index = symbol == '-' ? freeNegIndex(string) : equation.indexOf(symbol);
    if (equation.charAt(index - 1) == ']' && equation.charAt(index + 1) == '[') {
      int loc = equation.substring(index).indexOf(']') + index;
      int locOfFirstBracket = equation.substring(0, index).lastIndexOf('[');

      String part1 = equation.substring(0, locOfFirstBracket + 1);
      double part2 = equationSolver(
          equation.substring(
              locOfFirstBracket + 2,
              equation.substring(0, index).lastIndexOf(']'))
              + symbol + equation.substring(
              equation.substring(index).indexOf('[') + 2 + index,
              loc), calc);

      if (symbol == '+' || symbol == '-') {
        part2 *= -1;
      }
      String part3 = equation.substring(loc);

      equation = part1 + part2 + part3;

      return equation;
    } else if (equation.charAt(index - 1) == ']') {
      String temp = equation.substring(index);
      int locOfNextSymbol = 0;
      for (int i = 1; i < temp.length(); i++) {
        if (temp.charAt(i) == '-' || temp.charAt(i) == '+' || temp.charAt(i) == '*'
            || temp.charAt(i) == '/' || temp.charAt(i) == '[' || temp.charAt(i) == ']') {
          locOfNextSymbol = i + index;
          i = temp.length();
        }
      }
      if (locOfNextSymbol == 0) {
        locOfNextSymbol = equation.length();
      }
      int locOfFirstBracket = equation.substring(0, index).lastIndexOf('[');

      String part1 = equation.substring(
          locOfFirstBracket + 2,
          equation.substring(0, index).lastIndexOf(']'));
      String part2 = equation.substring(index + 1, locOfNextSymbol);
      double result = 0.0;
      if (symbol == '+') {
        result = equationSolver(part2 + '-' + part1, calc);
      } else if(symbol == '-') {
        result = -1 * equationSolver(part2 + '+' + part1, calc);
      } else {
        result = -1 * equationSolver(part1 + symbol + part2, calc);
      }

      equation = equation.substring(0, locOfFirstBracket + 1) + result +
          "]" + equation.substring(locOfNextSymbol);

      return equation;
    } else if (equation.charAt(index + 1) == '[') {
      String temp = equation.substring(0, index);
      int locOfPrevSymbol = temp.length();
      for (int i = temp.length() - 1; i >= 0; i--) {
        if (temp.charAt(i) == '-' || temp.charAt(i) == '+' || temp.charAt(i) == '*'
            || temp.charAt(i) == '/' || temp.charAt(i) == '[' || temp.charAt(i) == ']') {
          locOfPrevSymbol = i + index;
          i = -1;
        }
      }
      if (locOfPrevSymbol == temp.length()) {
        locOfPrevSymbol = -1;
      }

      int loc = equation.substring(index).indexOf(']') + index;


      String part1 = equation.substring(
          locOfPrevSymbol + 1,
          index);
      String part2 = equation.substring(
          equation.substring(index).indexOf('[') + 2 + index,
          loc);
      double result = 0.0;
      if (symbol == '+') {
        result = equationSolver(part1 + '-' + part2, calc);
      } else if (symbol == '-') {
        result = equationSolver(part1 + '+' + part2, calc);
      }else {
        result = -1 * equationSolver(part1 + symbol + part2, calc);
      }
      equation = equation.substring(0, locOfPrevSymbol + 1) + "["
          + result
          + equation.substring(loc);

      return equation;
    }

    return string;
  }

  //This method changes (-) -> [-...] where every digit of the
  // number being negated is inside the brackets.
  private static String applyNegative(String equation) {
    String answer = equation;
    answer = answer.replace("(-)", "[-]");
    while (answer.contains("[-]")) {
      for (int i = answer.indexOf("[-]") + 3; i < answer.length(); i++) {
        try {
          char c = answer.charAt(i);
          int digit = 0;
          if (c != '.') {
            digit = Integer.parseInt(String.valueOf(c));
          }
          answer = answer.substring(0, i - 1) + digit + "]" + answer.substring(i + 1);
        } catch (NumberFormatException nfe) {
          i = answer.length();
        }
      }
    }
    return answer;
  }

  /**
   * Finds the right parenthesis associated with the left parenthesis in the equation.
   *
   * @param string   The equation containing the parentheses.
   * @param location The location of the left parenthesis in question.
   * @return the location in the string of the right parenthesis. -1 if there is no associated right
   * paren.
   * @throws IllegalArgumentException if there is no left paren at the given location.
   */
  public static int rightParenFinder(String string, int location) throws IllegalArgumentException {
    if (string.charAt(location) != '(') {
      throw new IllegalArgumentException("Invalid String or location!");
    }
    int count = 0;
    for (int i = location + 1; i < string.length(); i++) {
      if (string.charAt(i) == ')') {
        if (count == 0) {
          return i;
        } else {
          count--;
        }
      } else if (string.charAt(i) == '(') {
        count++;
      }
    }
    return -1;
  }

  /**
   * Catches an IOException when calling append on appendable. This allows for append to be called
   * without writing a try catch for every call.
   *
   * @param appendable the appendable to be appended to.
   * @param s          the string to be appended.
   * @throws NullPointerException  if s or appendable are null.
   * @throws IllegalStateException if the append fails.
   */
  public static void safeAppend(Appendable appendable, String s) throws NullPointerException,
      IllegalStateException {
    Objects.requireNonNull(appendable);
    Objects.requireNonNull(s);
    try {
      appendable.append(s);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed.");
    }
  }

  public static double adjustedParseDouble(String str) throws NumberFormatException {
    if (str.charAt(0) == '+') {
      throw new NumberFormatException("Invalid Character!");
    } else {
      return Double.parseDouble(str);
    }
  }


}
