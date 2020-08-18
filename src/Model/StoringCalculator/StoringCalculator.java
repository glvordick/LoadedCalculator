package Model.StoringCalculator;

import Model.SimpleDouble.SimpleDoubleCalculator;
import Model.SimpleInt.SimpleIntCalculator;

/**
 * An interface that represents a calculator that stores the results of the calculations it makes.
 */
public interface StoringCalculator extends SimpleDoubleCalculator {

  /**
   * Returns the answer of the previous calculation.
   * @return a double that was the result of a prior calculation.
   */
  double getAns();

}
