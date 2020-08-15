package Model;

import Model.SimpleDouble.SimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculatorImpl;
import java.text.DecimalFormat;

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
  private final DecimalFormat df;

  public SetPrecisionCalculatorImpl() {
    this.sDC = new SimpleDoubleCalculatorImpl();
    this.precision = 5;
    this.df = new DecimalFormat();
    this.updateFormatter();
  }

  public SetPrecisionCalculatorImpl(int p) {
    this.sDC = new SimpleDoubleCalculatorImpl();
    this.setPrecision(p);
    this.df = new DecimalFormat();
    this.updateFormatter();
  }

  @Override
  public void setPrecision(int p) throws IllegalArgumentException {
    if (p < 0 || p > 10) {
      throw new IllegalArgumentException("The precision "
          + "must be a non-negative integer less than 11");
    }
    this.precision = p;
    this.updateFormatter();
  }

  private void updateFormatter() {
    StringBuilder pattern = new StringBuilder("#.");
    for (int i = 0; i < precision; i++) {
      pattern.append("#");
    }
    df.applyPattern(pattern.toString());
  }

  @Override
  public double add(double x, double y) {
    return Double.parseDouble(df.format(this.sDC.add(x, y)));
  }

  @Override
  public double sub(double x, double y) {
    return Double.parseDouble(df.format(this.sDC.sub(x, y)));
  }

  @Override
  public double multi(double x, double y) {
    return Double.parseDouble(df.format(this.sDC.multi(x, y)));
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    if (Math.abs(y) < Math.pow(10, -1 * precision)) {
      throw new IllegalArgumentException("Cannot divide by Zero.");
    } else {
      return Double.parseDouble(df.format(x / y));
    }
  }
}
