package Model.SetPrecision;

import Model.SimpleDouble.ISimpleDoubleCalculator;

/**
 * This interface is designed for calculators that only store doubles up to a certain precision. A
 * precision of 5 would be equivalent to 5 digits after the decimal point, excluding trailing zeros.
 * It extends {@link ISimpleDoubleCalculator} as this calculator should also be able to use the four
 * operations on doubles that the interface lays out.
 */
public interface ISetPrecisionCalculator extends ISimpleDoubleCalculator {

  /**
   * Sets the precision of the doubles that are stored and printed.
   *
   * @param p The precision.
   * @throws IllegalArgumentException if p is negative.
   */
  void setPrecision(int p) throws IllegalArgumentException;

  /**
   * Gets the current precision of the calculator.
   *
   * @return the precision of the calculator at this moment in time.
   */
  int getPrecision();

  /**
   * Rounds the given number to the precision that this object is set to.
   *
   * @param d the number to be rounded.
   * @return the rounded number.
   */
  double round(double d);

}
