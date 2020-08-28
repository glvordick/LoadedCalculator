package Model.LoadedCalculator;

import Model.SetPrecision.ISetPrecisionCalculator;
import Model.SetPrecision.SetPrecisionCalculator;
import Util.Utils;
import java.util.Stack;

public class LoadedCalculatorModel implements ILoadedCalculatorModel {


  private final Stack<String> answers;
  private final ISetPrecisionCalculator calc;

  public LoadedCalculatorModel() {
    this.answers = new Stack<>();
    this.calc = new SetPrecisionCalculator();
  }

  public LoadedCalculatorModel(int precision) {
    this.answers = new Stack<>();
    this.calc = new SetPrecisionCalculator(precision);
  }

  @Override
  public int gcd(int a, int b) {
    if (a < 1 || b < 1) {
      throw new IllegalArgumentException("a and b must be greater than 0.");
    }
    int ans = gcdHelp(a, b);
    answers.push(String.valueOf(ans));
    return ans;
  }

  private int gcdHelp(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcdHelp(b, a % b);
  }

  @Override
  public int lcm(int a, int b) {
    if (a < 1 || b < 1) {
      throw new IllegalArgumentException("a and b must be greater than 0.");
    }
    int ans = a * b / this.gcd(a, b);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public boolean prime(int p) {
    if (p < 1) {
      throw new IllegalArgumentException("p must be greater than 0");
    } else if (p < 2) {
      return false;
    } else if (p == 2) {
      return true;
    }

    for (int i = 2; i < (int) Math.sqrt(p); i++) {
      if (p % i == 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int mod(int a, int b) {
    int ans = a % b;
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double exp(double a, double b) {
    double ans = Math.pow(a, b);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double log(double a) {
    return Math.log10(a);
  }

  @Override
  public double ln(double a) {
    return Math.log(a);
  }

  @Override
  public double logBaseN(double n, double a) {
    double ans = this.divide(Math.log(a), Math.log(n));
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double sin(double a, boolean inRadians) {
    double ans = Math.sin(inRadians ? a : Math.toRadians(a));
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double cos(double a, boolean inRadians) {
    double ans = Math.cos(inRadians ? a : Math.toRadians(a));
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double tan(double a, boolean inRadians) {
    double ans = Math.tan(inRadians ? a : Math.toRadians(a));
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public String complexAdd(int a1, int b1, int a2, int b2) {
    int bNew = b1 + b2;
    String ans = (a1 + a2) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
    answers.push(ans);
    return ans;
  }

  @Override
  public String complexSub(int a1, int b1, int a2, int b2) {
    int bNew = b1 - b2;
    String ans = (a1 - a2) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
    answers.push(ans);
    return ans;
  }

  @Override
  public String complexMulti(int a1, int b1, int a2, int b2) {
    int bNew = ((a1 * b2) + (a2 * b1));
    String ans = ((a1 * a2) - (b1 * b2)) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
    answers.push(ans);
    return ans;
  }

  @Override
  public String complexDivide(int a1, int b1, int a2, int b2) {
    String complimentAfterMulti = this.complexMulti(a1, b1, a2, -1 * b2);
    int[] hold = Utils.complexFromString(complimentAfterMulti);

    int aFinal = hold[0];
    int bFinal = hold[1];

    int aNew = (int) Math.round((aFinal + 0.0) / this.complexNorm(a2, b2));
    int bNew = (int) Math.round((bFinal + 0.0) / this.complexNorm(a2, b2));

    String ans = aNew + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
    answers.push(ans);
    return ans;
  }

  @Override
  public String complexRemainder(int a1, int b1, int a2, int b2) {

    String ans = this.complexDivide(a1, b1, a2, b2);

    int[] hold = Utils.complexFromString(ans);

    int aNew = hold[0];
    int bNew = hold[1];

    String antiRemainder = this.complexMulti(a2, b2, aNew, bNew);
    hold = Utils.complexFromString(antiRemainder);

    int aRemainder = hold[0];
    int bRemainder = hold[1];

    String remainder = this.complexSub(a1, b1, aRemainder, bRemainder);
    answers.push(remainder);
    return ans + " remainder: " + remainder;
  }

  @Override
  public int complexNorm(int a, int b) {
    int ans = a * a + b * b;
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public void setPrecision(int p) throws IllegalArgumentException {
    this.calc.setPrecision(p);
  }

  @Override
  public String getAns() {
    return this.answers.empty() ? "" : this.answers.pop();
  }

  @Override
  public double add(double x, double y) {
    double ans = this.calc.add(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double sub(double x, double y) {
    double ans = this.calc.sub(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double multi(double x, double y) {
    double ans = this.calc.multi(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    double ans = this.calc.divide(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }
}
