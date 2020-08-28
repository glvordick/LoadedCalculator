package Model.LoadedCalculator;

import Model.SetPrecision.ISetPrecisionCalculator;
import Model.StoringCalculator.IStoringCalculator;

public interface ILoadedCalculatorModel
    extends IStoringCalculator, ISetPrecisionCalculator {

  int gcd(int a, int b) throws IllegalArgumentException;

  int lcm(int a, int b) throws IllegalArgumentException;

  boolean prime(int p) throws IllegalArgumentException;

  int mod(int a, int b);

  double exp(double a, double b);

  double log(double a);

  double ln(double a);

  double logBaseN(double a, double n);

  double sin(double a, boolean inRadians);

  double cos(double a, boolean inRadians);

  double tan(double a, boolean inRadians);

  String complexAdd(int a1, int b1, int a2, int b2);

  String complexSub(int a1, int b1, int a2, int b2);

  String complexMulti(int a1, int b1, int a2, int b2);

  String complexDivide(int a1, int b1, int a2, int b2);

  String complexRemainder(int a1, int b1, int a2, int b2);

  int complexNorm(int a, int b);

}
