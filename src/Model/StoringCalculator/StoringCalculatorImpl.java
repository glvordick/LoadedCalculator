package Model.StoringCalculator;

import Model.SimpleDouble.SimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculatorImpl;
import java.util.Stack;

/**
 * An implementation of the {@link StoringCalculator} interface. This implementation uses a stack to
 * hold the answers to previous calculations. Repeated calls to getAns can be used to get previous
 * answers, but anything that is passed over will be lost.
 */
public class StoringCalculatorImpl implements StoringCalculator {

  private final Stack<Double> answers;
  private final SimpleDoubleCalculator sDC;

  /**
   * Default constructor.
   */
  public StoringCalculatorImpl() {
    this.answers = new Stack<>();
    this.sDC = new SimpleDoubleCalculatorImpl();
  }

  /**
   * Constructor to set the delegate.
   * @param sdc A {@link SimpleDoubleCalculator} delegate.
   */
  public StoringCalculatorImpl(SimpleDoubleCalculator sdc) {
    this.answers = new Stack<>();
    this.sDC = sdc;
  }

  @Override
  public double getAns() {
    return this.answers.empty() ? 0.0 : this.answers.pop();
  }

  @Override
  public double add(double x, double y) {
    double ans = this.sDC.add(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double sub(double x, double y) {
    double ans = this.sDC.sub(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double multi(double x, double y) {
    double ans = this.sDC.multi(x, y);
    answers.push(ans);
    return ans;
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    double ans = this.sDC.divide(x, y);
    answers.push(ans);
    return ans;
  }
}
