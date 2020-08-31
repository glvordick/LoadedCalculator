package Model.SetPrecision;

import Model.SimpleDouble.ISimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculator;
import java.math.BigDecimal;
import org.apache.commons.math3.util.Precision;

/**
 * An implementation of the {@link ISetPrecisionCalculator} interface. This class has a default
 * precision of 5 with a minimum of 0 and max of 10. This class uses a {@link
 * ISimpleDoubleCalculator} as a delegate as the interface that {@link ISetPrecisionCalculator}
 * extends is @link ISimpleDoubleCalculator}. This way the delegate is used to calculate and the
 * answers are rounded to the specifications of the given precision.
 */
public class SetPrecisionCalculator implements ISetPrecisionCalculator {

  private final ISimpleDoubleCalculator sDC;
  private int precision;

  /**
   * Default constructor.
   */
  public SetPrecisionCalculator() {
    this.sDC = new SimpleDoubleCalculator();
    this.precision = 5;
  }

  /**
   * Constructor that creates an object with a given precision in range.
   *
   * @param p the precision in number of digits after the decimal point.
   */
  public SetPrecisionCalculator(int p) {
    this.sDC = new SimpleDoubleCalculator();
    this.setPrecision(p);
  }

  /**
   * Constructor to set the {@link ISimpleDoubleCalculator} delegate.
   *
   * @param sdc The delegate given by the user.
   */
  public SetPrecisionCalculator(ISimpleDoubleCalculator sdc) {
    this.sDC = sdc;
    this.precision = 5;
  }

  /**
   * Constructor that creates an object with a given precision in range and sets the delegate.
   *
   * @param p   the precision in number of digits after the decimal point.
   * @param sdc The delegate given by the user.
   */
  public SetPrecisionCalculator(int p, ISimpleDoubleCalculator sdc) {
    this.sDC = sdc;
    this.setPrecision(p);
  }

  @Override
  public void setPrecision(int p) throws IllegalArgumentException {
    if (p < 0 || p > 10) {
      throw new IllegalArgumentException("The precision "
          + "must be a non-negative integer less than 11");
    }
    this.precision = p;
  }

  @Override
  public int getPrecision() {
    return this.precision;
  }

  @Override
  public double round(double d) {
    return Precision.round(d, this.precision, BigDecimal.ROUND_HALF_UP);
  }

  @Override
  public double add(double x, double y) {
    return this.round(this.sDC.add(x, y));
  }

  @Override
  public double sub(double x, double y) {
    return this.round(this.sDC.sub(x, y));
  }

  @Override
  public double multi(double x, double y) {
    return this.round(this.sDC.multi(x, y));
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    if (Math.abs(y) < Math.pow(10, -1 * precision)) {
      throw new IllegalArgumentException("Cannot divide by Zero.");
    } else {
      return this.round(this.sDC.divide(x, y));
    }
  }
}
