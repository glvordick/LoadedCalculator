import static org.junit.Assert.*;

import Model.LoadedCalculator.LoadedCalculatorModel;
import Model.LoadedCalculator.LoadedCalculatorModelImpl;
import Model.SetPrecision.SetPrecisionCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

public class LoadedCalculatorModelImplTest {

  LoadedCalculatorModel lcm1;
  LoadedCalculatorModel lcm2;

  @Before
  public void setUp() {
    lcm1 = new LoadedCalculatorModelImpl();
    lcm2 = new LoadedCalculatorModelImpl(3);
  }

  @Test
  public void testGcd() {
    assertEquals(lcm1.gcd(144, 108), 36);
    assertEquals(lcm1.gcd(144, 53), 1);
    assertEquals(lcm1.gcd(144, 972), 36);
  }

  @Test
  public void testLcm() {
    assertEquals(lcm1.lcm(144, 108), 432);
    assertEquals(lcm1.lcm(144, 53), 7632);
    assertEquals(lcm1.lcm(144, 972), 3888);
  }

  @Test
  public void testPrime() {
    assertTrue(lcm1.prime(73));
    assertFalse(lcm1.prime(120));
  }

  @Test
  public void testMod() {
    assertEquals(lcm1.mod(7, 3), 1);
    assertEquals(lcm1.mod(40, 8), 0);
  }

  @Test
  public void testExp() {
    assertEquals(lcm1.exp(3,3), 27,0.001);
    assertEquals(lcm2.exp(2, 0.5), 1.414, 0.001);
  }

  @Test
  public void testLogBaseN() {
    assertEquals(lcm1.logBaseN(2, 8), 3, 0.001);
    assertEquals(lcm2.logBaseN(2,37), 5.209, 0.001);
  }

  @Test
  public void testSin() {
    assertEquals(lcm1.sin(Math.PI, true), 0.0, 0.0001);
    assertEquals(lcm2.sin(Math.PI, false), .055, 0.001);
  }

  @Test
  public void testCos() {
    assertEquals(lcm1.cos(Math.PI, true), -1.0, 0.0001);
    assertEquals(lcm2.cos(Math.PI, false), 0.998, 0.001);
  }

  @Test
  public void testTan() {
    assertEquals(lcm1.tan(Math.PI/6, true), 0.57735, 0.00001);
    assertEquals(lcm2.tan(7 * Math.PI, false), .404, 0.001);
  }

  @Test
  public void testComplexAdd() {
    assertEquals(lcm1.complexAdd(3,4,1,2), "4 + 6i");
    assertEquals(lcm1.complexAdd(3,-4,4,2), "7 - 2i");
  }

  @Test
  public void testComplexSub() {
    assertEquals(lcm1.complexSub(3,4,1,2), "2 + 2i");
    assertEquals(lcm1.complexSub(3,-4,4,2), "-1 - 6i");
  }

  @Test
  public void testComplexMulti() {
    assertEquals(lcm1.complexMulti(3,4,1,2), "-5 + 10i");
    assertEquals(lcm1.complexMulti(3,-4,4,2), "20 - 10i");
  }

  @Test
  public void testComplexDivide() {
    assertEquals(lcm1.complexDivide(73, 28, 6, 8), "7 - 4i");
    assertEquals(lcm1.complexDivide(12, 41, 5, -9), "-3 + 3i");
  }

  @Test
  public void testComplexDivideWithRemainder() {
    assertEquals(lcm1.complexDivideWithRemainder(73, 28, 6, 8),
        "7 - 4i remainder: -1 - 4i");
    assertEquals(lcm1.complexDivideWithRemainder(12, 41, 5, -9),
        "-3 + 3i remainder: 0 - 1i");
  }


  @Test
  public void testSetPrecision() {
    assertEquals(lcm1.add(1.105014, 2.0000001), 3.10501, 0.000000001);
    lcm1.setPrecision(7);
    assertEquals(lcm1.add(1.105014, 2.0000001), 3.1050141, 0.000000001);
    lcm1.setPrecision(0);
    assertEquals(lcm1.add(1.105014, 2.0000001), 3, 0.000000001);
    lcm1.setPrecision(10);
    //No trailing zeros
    assertEquals(lcm1.add(1.105014, 2.0000001), 3.1050141, 0.000000001);
  }

  @Test
  public void testGetAns() {
    lcm1.add(3, 5);
    assertEquals(lcm1.getAns(), 8, 0.001);
    assertEquals(lcm1.getAns(), 0, 0.001);
    assertEquals(lcm1.getAns(), 0, 0.001);
    //only get 0 if you keep calling getAns

    lcm2.multi(3.2144, 3.32487242);
    lcm2.divide(24652, 17);
    assertEquals(lcm2.getAns(), 1450.118, 0.000001); //line 129 operation
    assertEquals(lcm2.getAns(), 10.687, 0.000001); //line 128 operation
  }

  /**
   * Tests for the add method.
   */
  @Test
  public void testAdd() {
    assertEquals(lcm1.add(1.105014, 2.0000001), 3.10501, 0.000000001);
    assertEquals(lcm2.add(1.105014, 2.0000001), 3.105, 0.000000001);

    assertEquals(lcm1.add(2.204901, 7.510037), 9.71494, 0.000000001);
    assertEquals(lcm2.add(2.204901, 7.510037), 9.715, 0.000000001);
  }

  /**
   * Tests for the sub method.
   */
  @Test
  public void testSub() {
    assertEquals(lcm1.sub( 3.10501, 2.0000001), 1.10501, 0.000000001);
    assertEquals(lcm2.sub( 3.10501, 2.0000001), 1.105, 0.000000001);

    assertEquals(lcm1.sub( 9.71494, 7.510037), 2.2049, 0.000000001);
    assertEquals(lcm2.sub( 9.71494, 7.510037), 2.205, 0.000000001);
  }

  /**
   * Tests for the multi method.
   */
  @Test
  public void testMulti() {
    assertEquals(lcm1.multi( 3.10501, 2.0000001), 6.21002, 0.000000001);
    assertEquals(lcm2.multi( 3.10501, 2.0000001), 6.21, 0.000000001);

    assertEquals(lcm1.multi( 9.71494, 7.510037), 72.95956, 0.000000001);
    assertEquals(lcm2.multi( 9.71494, 7.510037), 72.96, 0.000000001);
  }

  /**
   * Tests for the divide method.
   */
  @Test
  public void testDivide() {
    assertEquals(lcm1.divide( 3.10501, 2.0000001), 1.5525, 0.000000001);
    assertEquals(lcm2.divide( 3.10501, 2.0000001), 1.553, 0.000000001);

    assertEquals(lcm1.divide( 9.71494, 7.510037), 1.29359, 0.000000001);
    assertEquals(lcm2.divide( 9.71494, 7.510037), 1.294, 0.000000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByNegative() {
    lcm1.divide(16, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativePrecision() {
    new LoadedCalculatorModelImpl(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLargePrecision() {
    new LoadedCalculatorModelImpl(436);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGCD1() { lcm1.gcd(0, 6);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadGCD2() { lcm1.gcd(-12, -6);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadGCD3() { lcm1.gcd(5, -2763);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadLCM1() { lcm1.lcm(0, 6);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadLCM2() { lcm1.lcm(-12, -6);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadLCM3() { lcm1.lcm(5, -2763);}

  @Test(expected = IllegalArgumentException.class)
  public void testBadPrime1() { lcm1.prime(-2);}

}