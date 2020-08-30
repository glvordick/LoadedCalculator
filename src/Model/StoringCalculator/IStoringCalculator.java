package Model.StoringCalculator;

import Model.SimpleDouble.ISimpleDoubleCalculator;

/**
 * An interface that represents a calculator that stores the results of the calculations it makes.
 */
public interface IStoringCalculator extends ISimpleDoubleCalculator {

  /**
   * Returns the answer of the previous calculation.
   *
   * @return a string that was the result of a prior calculation.
   */
  double getAns();

}
