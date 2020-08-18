package Model.StoringCalculator;

import Model.SimpleDouble.SimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculatorImpl;
import java.util.Stack;

/**
 * An implementation of the {@link StoringCalculator} interface. This implementation uses a stack to
 * hold the answers to previous calculations. Repeated calls to getAns can be used to get previous
 * answers, but anything that is passed over will be lost.
 */
public class StoringCalculatorImpl extends SimpleDoubleCalculatorImpl
    implements StoringCalculator {

  private final Stack<Double> answers;

  /**
   * Default constructor.
   */
  public StoringCalculatorImpl() {
    answers = new Stack<>();
  }

  @Override
  public double getAns() {
    return this.answers.empty() ? 0.0 : this.answers.pop();
  }

}
