package Model.LoadedCalculator;

import Model.SetPrecision.SetPrecisionCalculator;
import Model.SetPrecision.SetPrecisionCalculatorImpl;
import java.util.Stack;

public class LoadedCalculatorModelImpl
    implements LoadedCalculatorModel  {


  private final Stack<Double> answers;
  private final SetPrecisionCalculator calc;

  public LoadedCalculatorModelImpl() {
    this.answers = new Stack<>();
    this.calc = new SetPrecisionCalculatorImpl();
  }

  public LoadedCalculatorModelImpl(int precision) {
    this.answers = new Stack<>();
    this.calc = new SetPrecisionCalculatorImpl(precision);
  }

  @Override
  public int gcd(int a, int b) {
    if (a < 1 || b < 1) {
      throw new IllegalArgumentException("a and b must be greater than 0.");
    }

    return gcdHelp(a, b);
  }

  private int gcdHelp(int a, int b) {
    if(b == 0){
      return a;
    }
    return gcdHelp(b, a%b);
  }

  @Override
  public int lcm(int a, int b) {
    if (a < 1 || b < 1) {
      throw new IllegalArgumentException("a and b must be greater than 0.");
    }
    return a * b / this.gcd(a, b);
  }

  @Override
  public boolean prime(int p) {
    if (p < 1) {
      throw new IllegalArgumentException("p must be greater than 0");
    }
    else if (p < 2) {
      return false;
    }
    else if( p == 2) {
      return true;
    }

    for (int i = 2; i < (int)Math.sqrt(p); i++) {
      if (p % i == 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int mod(int a, int b) {
    return a % b;
  }

  @Override
  public double exp(double a, double b) {
    return Math.pow(a, b);
  }

  @Override
  public double logBaseN(double n, double a) {
    return this.divide(Math.log(a), Math.log(n));
  }

  @Override
  public double sin(double a, boolean inRadians) {
    return Math.sin(inRadians ? a : Math.toRadians(a));
  }

  @Override
  public double cos(double a, boolean inRadians) {
    return Math.cos(inRadians ? a : Math.toRadians(a));  }

  @Override
  public double tan(double a, boolean inRadians) {
    return Math.tan(inRadians ? a : Math.toRadians(a));
  }

  @Override
  public String complexAdd(int a1, int b1, int a2, int b2) {
    int bNew = b1 + b2;
    return (a1 + a2) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";  }

  @Override
  public String complexSub(int a1, int b1, int a2, int b2) {
    int bNew = b1 - b2;
    return (a1 - a2) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
  }

  @Override
  public String complexMulti(int a1, int b1, int a2, int b2) {
    int bNew = ((a1 * b2) + (a2 * b1));
    return ((a1 * a2) - (b1 * b2)) + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
  }

  @Override
  public String complexDivide(int a1, int b1, int a2, int b2) {
    String complimentAfterMulti = this.complexMulti(a1, b1, a2, -1 * b2);
    int loc1 = complimentAfterMulti.indexOf(" ");
    int aFinal = Integer.parseInt(complimentAfterMulti.substring(0, loc1));

    int loc2 = complimentAfterMulti.indexOf("i");
    int bFinal = Integer.parseInt(complimentAfterMulti.substring(loc1 + 3, loc2));

    int aNew = (int) Math.round((aFinal + 0.0) / this.complexNorm(a2, b2));
    int bNew = (int) Math.round((bFinal + 0.0) / this.complexNorm(a2, b2));
    bNew = complimentAfterMulti.indexOf("-") > 1 ? -1 * bNew : bNew;

    return aNew + (bNew < 0 ? " - " : " + ") + Math.abs(bNew) + "i";
  }

  @Override
  public String complexDivideWithRemainder(int a1, int b1, int a2, int b2) {

    String ans = this.complexDivide(a1, b1, a2, b2);

    int locANew = ans.indexOf(" ");
    int aNew = Integer.parseInt(ans.substring(0, locANew));

    int locBNew = ans.indexOf("i");
    int bNew = Integer.parseInt(ans.substring(locANew + 3, locBNew));
    bNew = ans.indexOf("-") > 1 ? -1 * bNew : bNew;

    String antiRemainder = this.complexMulti(a2, b2, aNew, bNew);
    int locR1 = antiRemainder.indexOf(" ");
    int aRemainder = Integer.parseInt(antiRemainder.substring(0, locR1));

    int locR2 = antiRemainder.indexOf("i");
    int bRemainder = Integer.parseInt(antiRemainder.substring(locR1 + 3, locR2));
    bRemainder = antiRemainder.indexOf("-") > 1 ? -1 * bRemainder : bRemainder;

    return  ans + " remainder: " +
        this.complexSub(a1, b1, aRemainder, bRemainder);
  }

  @Override
  public int complexNorm(int a, int b) {
    return a * a + b * b;
  }

  @Override
  public void setPrecision(int p) throws IllegalArgumentException {
    this.calc.setPrecision(p);
  }

  @Override
  public double getAns() {
    return this.answers.empty() ? 0.0 : this.answers.pop();
  }

  @Override
  public double add(double x, double y) {
    double ans = this.calc.add(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double sub(double x, double y) {
    double ans = this.calc.sub(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double multi(double x, double y) {
    double ans = this.calc.multi(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    double ans = this.calc.divide(x, y);
    answers.push(ans);
    return ans;
  }
}
