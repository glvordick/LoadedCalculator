package Model.LoadedCalculator;

import Model.SetPrecision.ISetPrecisionCalculator;
import Model.StoringCalculator.IStoringCalculator;

/**
 * An interface that represents a Calculator that contains all of the usual operations, but also has
 * a multitude of other operations that it can use. This interface extends both the {@link
 * IStoringCalculator} interface and the {@link ISetPrecisionCalculator} interface. This calculator
 * interface is the most verbose, and has the most capabilities.
 */
public interface ILoadedCalculatorModel
    extends IStoringCalculator, ISetPrecisionCalculator {

  /**
   * Computes the greatest common denominator of the two integers.
   *
   * @param a positive integer a.
   * @param b positive integer b.
   * @return the gcd (greatest common denominator) of two integers.
   * @throws IllegalArgumentException If either a or b are less than 1.
   */
  int gcd(int a, int b) throws IllegalArgumentException;

  /**
   * Computes the least common multiple of the two integers.
   *
   * @param a positive integer a.
   * @param b positive integer b.
   * @return the lcm (least common multiple) of two integers.
   * @throws IllegalArgumentException If either a or b are less than 1.
   */
  long lcm(int a, int b) throws IllegalArgumentException;

  /**
   * Computes whether or not the given number is prime.
   *
   * @param p the number in question.
   * @return true if its prime, false if not.
   * @throws IllegalArgumentException if p is negative.
   */
  boolean prime(int p) throws IllegalArgumentException;

  /**
   * Computes a modulo b.
   *
   * @param a the dividend.
   * @param b the divisor.
   * @return returns a % b.
   */
  int mod(int a, int b);

  /**
   * Computes a to the b power.
   *
   * @param a The base of the exponent.
   * @param b The power that a is being raised to.
   * @return a^b.
   */
  double exp(double a, double b);

  /**
   * Computes the base 10 logarithm of the number given.
   *
   * @param a the number that the log is being taken of.
   * @return log10(a).
   * @throws IllegalArgumentException if a is not a positive integer.
   */
  double log(double a) throws IllegalArgumentException;

  /**
   * Computes the natural or base e logarithm of the number given.
   *
   * @param a the number that the log is being taken of.
   * @return ln(a).
   * @throws IllegalArgumentException if a is not a positive integer.
   */
  double ln(double a) throws IllegalArgumentException;

  /**
   * Computes the base N logarithm of the number given.
   *
   * @param a the number that the log is being taken of.
   * @param n the base of the logarithm.
   * @return logN(a).
   * @throws IllegalArgumentException if a or n are not a positive integers.
   */
  double logBaseN(double n, double a) throws IllegalArgumentException;

  /**
   * Computes the sin of the given angle.
   *
   * @param a         the angle that the sin is being computed of.
   * @param inRadians is the given value in radians?
   * @return the sin of the given angle.
   */
  double sin(double a, boolean inRadians);

  /**
   * Computes the cos of the given angle.
   *
   * @param a         the angle that the cos is being computed of.
   * @param inRadians is the given value in radians?
   * @return the cos of the given angle.
   */
  double cos(double a, boolean inRadians);

  /**
   * Computes the tan of the given angle.
   *
   * @param a         the angle that the tan is being computed of.
   * @param inRadians is the given value in radians?
   * @return the tan of the given angle.
   * @throws IllegalArgumentException if a is in the form pi/2 + piK where k is an integer.
   */
  double tan(double a, boolean inRadians) throws IllegalArgumentException;

  /**
   * Adds two complex numbers together. Complex numbers are in the form a + bi.
   *
   * @param a1 the a value of the first complex number.
   * @param b1 the b value of the first complex number.
   * @param a2 the a value of the second complex number.
   * @param b2 the b value of the second complex number.
   * @return The sum of two complex numbers stored as a string.
   */
  String complexAdd(int a1, int b1, int a2, int b2);

  /**
   * Subtracts two complex numbers together. Complex numbers are in the form a + bi.
   *
   * @param a1 the a value of the first complex number.
   * @param b1 the b value of the first complex number.
   * @param a2 the a value of the second complex number.
   * @param b2 the b value of the second complex number.
   * @return The difference of two complex numbers stored as a string.
   */
  String complexSub(int a1, int b1, int a2, int b2);

  /**
   * Multiplies two complex numbers together. Complex numbers are in the form a + bi.
   *
   * @param a1 the a value of the first complex number.
   * @param b1 the b value of the first complex number.
   * @param a2 the a value of the second complex number.
   * @param b2 the b value of the second complex number.
   * @return The product of two complex numbers stored as a string.
   */
  String complexMulti(int a1, int b1, int a2, int b2);

  /**
   * Divides two complex numbers together. Complex numbers are in the form a + bi.
   *
   * @param a1 the a value of the first complex number.
   * @param b1 the b value of the first complex number.
   * @param a2 the a value of the second complex number.
   * @param b2 the b value of the second complex number.
   * @return The quotient of two complex numbers stored as a string.
   */
  String complexDivide(int a1, int b1, int a2, int b2);

  /**
   * Divides two complex numbers together and computes their remainder. Complex numbers are in the
   * form a + bi.
   *
   * @param a1 the a value of the first complex number.
   * @param b1 the b value of the first complex number.
   * @param a2 the a value of the second complex number.
   * @param b2 the b value of the second complex number.
   * @return The remainder of the division of two complex numbers stored as a string.
   */
  String complexRemainder(int a1, int b1, int a2, int b2);

  /**
   * Computes the norm of a complex number. The norm is the distance from the origin in a cartesian
   * coordinate system.
   *
   * @param a the a value of the first complex number.
   * @param b the b value of the first complex number.
   * @return sqrt(a ^ 2 + b ^ 2)
   */
  double complexNorm(int a, int b);

  /**
   * Computes a linear combination between a and b, with a weight on a and b defined by the ratio.
   *
   * @param ratio the weight between a and b
   * @param a     the first number
   * @param b     the second number
   * @return a linear combination between a and b, with a ratio of 0 being towards a, and 1 being
   * towards b.
   * @throws IllegalArgumentException if ratio is not between 0 and 1.
   */
  double linearCombination(double ratio, double a, double b) throws IllegalArgumentException;

  /**
   * Computes the absolute value of the given double.
   *
   * @param d the number that the absolute value is being taken of.
   * @return The absolute value of d.
   */
  double abs(double d);

}
