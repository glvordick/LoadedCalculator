package Model.SetPrecision;

import Model.SimpleDouble.SimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculatorImpl;
import java.math.BigDecimal;
import org.apache.commons.math3.util.Precision;

/**
 * An implementation of the {@link SetPrecisionCalculator} interface. This class has a default
 * precision of 5 with a minimum of 0 and max of 10. This class uses a
 * {@link SimpleDoubleCalculator} as a delegate as the interface that {@link SetPrecisionCalculator}
 * extends is @link SimpleDoubleCalculator}. This way the delegate is used to calculate and the
 * answers are rounded to the specifications of the given precision.
 */
public class SetPrecisionCalculatorImpl implements SetPrecisionCalculator {

  private final SimpleDoubleCalculator sDC;
  private int precision;

  /**
   * Default constructor.
   */
  public SetPrecisionCalculatorImpl() {
    this.sDC = new SimpleDoubleCalculatorImpl();
    this.precision = 5;
  }

  /**
   * Constructor that creates an object with a given precision in range.
   * @param p the precision in number of digits after the decimal point.
   */
  public SetPrecisionCalculatorImpl(int p) {
    this.sDC = new SimpleDoubleCalculatorImpl();
    this.setPrecision(p);
  }

  /**
   * Constructor to set the {@link SimpleDoubleCalculator} delegate.
   * @param sdc The delegate given by the user.
   */
  public SetPrecisionCalculatorImpl(SimpleDoubleCalculator sdc) {
    this.sDC = sdc;
    this.precision = 5;
  }

  /**
   * Constructor that creates an object with a given precision in range and sets the delegate.
   * @param p the precision in number of digits after the decimal point.
   * @param sdc The delegate given by the user.
   */
  public SetPrecisionCalculatorImpl(int p, SimpleDoubleCalculator sdc) {
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
  public double add(double x, double y) {
    return Precision.round(this.sDC.add(x, y),
        this.precision,
        BigDecimal.ROUND_HALF_UP);
  }

  @Override
  public double sub(double x, double y) {
    return Precision.round(this.sDC.sub(x, y),
        this.precision,
        BigDecimal.ROUND_HALF_UP);
  }

  @Override
  public double multi(double x, double y) {
    return Precision.round(this.sDC.multi(x, y),
        this.precision,
        BigDecimal.ROUND_HALF_UP);
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    if (Math.abs(y) < Math.pow(10, -1 * precision)) {
      throw new IllegalArgumentException("Cannot divide by Zero.");
    } else {
      return Precision.round(this.sDC.divide(x, y),
          this.precision,
          BigDecimal.ROUND_HALF_UP);
    }
  }
}
