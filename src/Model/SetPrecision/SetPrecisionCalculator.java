package Model.SetPrecision;

import Model.SimpleDouble.SimpleDoubleCalculator;

/**
 * This interface is designed for calculators that only store doubles up to a certain precision. A
 * precision of 5 would be equivalent to 5 digits after the decimal point, excluding trailing
 * zeros. It extends {@link SimpleDoubleCalculator} as this calculator should also be able to use
 * the four operations on doubles that the interface lays out.
 */
public interface SetPrecisionCalculator extends SimpleDoubleCalculator {

  /**
   * Sets the precision of the doubles that are stored and printed.
   *
   * @param p The precision.
   * @throws IllegalArgumentException if p is negative.
   */
  void setPrecision(int p) throws IllegalArgumentException;

}
