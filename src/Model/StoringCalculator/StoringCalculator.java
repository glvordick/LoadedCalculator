package Model.StoringCalculator;

import Model.SimpleDouble.ISimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculator;
import java.util.Stack;

/**
 * An implementation of the {@link IStoringCalculator} interface. This implementation uses a stack
 * to hold the answers to previous calculations. Repeated calls to getAns can be used to get
 * previous answers, but anything that is passed over will be lost.
 */
public class StoringCalculator implements IStoringCalculator {

  private final Stack<String> answers;
  private final ISimpleDoubleCalculator sDC;

  /**
   * Default constructor.
   */
  public StoringCalculator() {
    this.answers = new Stack<>();
    this.sDC = new SimpleDoubleCalculator();
  }

  /**
   * Constructor to set the delegate.
   *
   * @param sdc A {@link ISimpleDoubleCalculator} delegate.
   */
  public StoringCalculator(ISimpleDoubleCalculator sdc) {
    this.answers = new Stack<>();
    this.sDC = sdc;
  }

  @Override
  public String getAns() {
    return this.answers.empty() ? "" : this.answers.pop();
  }

  @Override
  public double add(double x, double y) {
    double ans = this.sDC.add(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double sub(double x, double y) {
    double ans = this.sDC.sub(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double multi(double x, double y) {
    double ans = this.sDC.multi(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    double ans = this.sDC.divide(x, y);
    answers.push(String.valueOf(ans));
    return ans;
  }
}
