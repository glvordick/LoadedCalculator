package Model.SimpleDouble;

/**
 * An interface that represents a calculator with 4 simple operations on doubles.
 */
public interface SimpleDoubleCalculator {

  /**
   * Adds two doubles x + y.
   *
   * @param x double to be added.
   * @param y double to be added.
   * @return The sum of x and y.
   */
  double add(double x, double y);

  /**
   * Subtracts two doubles x - y.
   *
   * @param x double being subtracted from.
   * @param y double subtracting from the other.
   * @return The difference between x and y.
   */
  double sub(double x, double y);

  /**
   * Multiplies two doubles x * y.
   *
   * @param x double to be multiplied;
   * @param y double to be multiplied.
   * @return The product of x and y.
   */
  double multi(double x, double y);

  /**
   * Divides two doubles x / y.
   *
   * @param x the dividend
   * @param y the divisor
   * @return The quotient of x and y.
   * @throws IllegalArgumentException if y is equal to 0.
   */
  double divide(double x, double y) throws IllegalArgumentException;

}
