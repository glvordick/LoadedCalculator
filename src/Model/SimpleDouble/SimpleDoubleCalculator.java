package Model.SimpleDouble;

/**
 * An implementation of the {@link ISimpleDoubleCalculator} interface. It implements all four
 * methods. The class can do operations on doubles.
 */
public class SimpleDoubleCalculator implements ISimpleDoubleCalculator {

  @Override
  public double add(double x, double y) {
    return x + y;
  }

  @Override
  public double sub(double x, double y) {
    return x - y;
  }

  @Override
  public double multi(double x, double y) {
    return x * y;
  }

  @Override
  public double divide(double x, double y) throws IllegalArgumentException {
    if (Math.abs(y) < 0.00000000001) {
      throw new IllegalArgumentException("Cannot divide by Zero.");
    } else {
      return x / y;
    }
  }
}
