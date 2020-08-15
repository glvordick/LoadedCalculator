package Model.SimpleInt;

/**
 * An implementation of the {@link SimpleIntCalculator} interface. It implements all four methods.
 * The class can do operations on integers.
 */
public class SimpleIntCalculatorImpl implements SimpleIntCalculator {

  @Override
  public int add(int x, int y) {
    return x + y;
  }

  @Override
  public int sub(int x, int y) {
    return x - y;
  }

  @Override
  public int multi(int x, int y) {
    return x * y;
  }

  @Override
  public int divide(int x, int y) {
    if (y == 0) {
      throw new IllegalArgumentException("Cannot divide by Zero.");
    } else {
      return x / y;
    }
  }
}
