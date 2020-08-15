package Model.SimpleInt;

/**
 * An interface that represents a calculator with 4 simple operations on integers.
 */
public interface SimpleIntCalculator {

  /**
   * Adds two integers x + y.
   *
   * @param x integer to be added.
   * @param y integer to be added.
   * @return The sum of x and y.
   */
  int add(int x, int y);

  /**
   * Subtracts two integers x - y.
   *
   * @param x integer being subtracted from.
   * @param y integer subtracting from the other.
   * @return The difference between x and y.
   */
  int sub(int x, int y);

  /**
   * Multiplies two integers x * y.
   *
   * @param x integer to be multiplied;
   * @param y integer to be multiplied.
   * @return The product of x and y.
   */
  int multi(int x, int y);

  /**
   * Divides two integers x / y. Truncates the answer (integer division).
   *
   * @param x the dividend
   * @param y the divisor
   * @return The quotient of x and y.
   * @throws IllegalArgumentException if y is equal to 0.
   */
  int divide(int x, int y) throws IllegalArgumentException;

}
