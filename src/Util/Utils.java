package Util;

import Model.SimpleDouble.ISimpleDoubleCalculator;

public class Utils {

  public static int[] complexFromString(String complex) throws NumberFormatException {
    complex = removeAllChar(complex, ' ');
    int locANew = !complex.contains("+") ?
        complex.substring(1).indexOf("-") + 1 :
        complex.indexOf("+");
    int aNew = locANew < 1 ? 0 : Integer.parseInt(complex.substring(0, locANew));

    int locBNew = complex.indexOf("i");
    int bNew = locBNew < 0 ? 0 : Integer.parseInt(complex.substring(locANew + 1, locBNew));
    bNew = complex.indexOf("-") > 0 ? -1 * bNew : bNew;

    return new int[]{aNew, bNew};
  }

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

  public static double equationSolver(String equation, ISimpleDoubleCalculator calc) {
    if (equation.length() == 0) {
      return 0.0;
    }
    try {
      double ans = Double.parseDouble(equation);
      return calc.add(0, ans);
    } catch (NumberFormatException e) {
      if ((equation.indexOf(".") != equation.lastIndexOf("."))
          && !equation.contains("×") && equation.contains("+")
          && equation.contains("-") && equation.contains("÷")) {
        throw new IllegalArgumentException("Invalid Equation given!");
      }
    }
    if (equation.contains(",")
        || (equation.contains("(") && !equation.contains(")"))
        || (!equation.contains("(") && equation.contains(")"))
        || (equation.indexOf(")") < equation.indexOf("("))
        || equation.charAt(0) == '+' || equation.charAt(0) == '×' || equation.charAt(0) == '÷'
        || equation.charAt(equation.length() - 1) == '+'
        || equation.charAt(equation.length() - 1) == '×'
        || equation.charAt(equation.length() - 1) == '÷'
        || equation.charAt(equation.length() - 1) == '-') {
      throw new IllegalArgumentException("Invalid Equation given!");
    } else if (equation.contains("(")) {
      return equationSolver(equation.substring(0, equation.indexOf("("))
          + equationSolver(equation.substring(equation.indexOf("(") + 1,
          equation.indexOf(")")), calc)
          + equation.substring(equation.indexOf(")") + 1), calc);
    } else if (equation.contains("×")) {
      return calc.multi(equationSolver(equation.substring(0, equation.indexOf("×")), calc),
          equationSolver(equation.substring(equation.indexOf("×") + 1), calc));
    } else if (equation.contains("÷")) {
      return calc.divide(equationSolver(equation.substring(0, equation.indexOf("÷")), calc),
          equationSolver(equation.substring(equation.indexOf("÷") + 1), calc));
    } else if (equation.contains("+")) {
      return calc.add(equationSolver(equation.substring(0, equation.indexOf("+")), calc),
          equationSolver(equation.substring(equation.indexOf("+") + 1), calc));
    } else if (equation.contains("-")) {
      return calc.sub(equationSolver(equation.substring(0, equation.indexOf("-")), calc),
          equationSolver(equation.substring(equation.indexOf("-") + 1), calc));
    } else {
      return 0.0;
    }
  }

}
