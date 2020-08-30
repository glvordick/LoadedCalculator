import static org.junit.Assert.*;

import Model.SetPrecision.ISetPrecisionCalculator;
import Model.SetPrecision.SetPrecisionCalculator;
import Model.SimpleDouble.ISimpleDoubleCalculator;
import Model.SimpleDouble.SimpleDoubleCalculator;
import org.junit.Before;
import org.junit.Test;

public class SetPrecisionCalculatorImplTest {
  ISetPrecisionCalculator spc1;
  ISetPrecisionCalculator spc2;
  ISetPrecisionCalculator spc3;
  ISetPrecisionCalculator spc4;

  /**
   * Showcasing all four constructors for the {@link SetPrecisionCalculator} class.
   */
  @Before
  public void init() {
    spc1 = new SetPrecisionCalculator();
    spc2 = new SetPrecisionCalculator(3);
    spc3 =  new SetPrecisionCalculator(new ISimpleDoubleCalculator() {
      @Override
      public double add(double x, double y) {
        return 42;
      }

      @Override
      public double sub(double x, double y) {
        return 42;
      }

      @Override
      public double multi(double x, double y) {
        return 42;
      }

      @Override
      public double divide(double x, double y) throws IllegalArgumentException {
        return 42;
      }
    });
    spc4 = new SetPrecisionCalculator( 7, new SimpleDoubleCalculator());
  }

  /**
   * Tests for the setPrecision method.
   */
  @Test
  public void testSetPrecision() {
    assertEquals(spc1.add(1.105014, 2.0000001), 3.10501, 0.000000001);
    spc1.setPrecision(7);
    assertEquals(spc1.add(1.105014, 2.0000001), 3.1050141, 0.000000001);
    spc1.setPrecision(0);
    assertEquals(spc1.add(1.105014, 2.0000001), 3, 0.000000001);
    spc1.setPrecision(10);
    //No trailing zeros
    assertEquals(spc1.add(1.105014, 2.0000001), 3.1050141, 0.000000001);

  }

  @Test
  public void testGetPrecision() {
    assertEquals(spc1.getPrecision(), 5);
    assertEquals(spc4.getPrecision(), 7);
  }

  /**
   * Tests for the add method.
   */
  @Test
  public void testAdd() {
    assertEquals(spc1.add(1.105014, 2.0000001), 3.10501, 0.000000001);
    assertEquals(spc2.add(1.105014, 2.0000001), 3.105, 0.000000001);

    assertEquals(spc1.add(2.204901, 7.510037), 9.71494, 0.000000001);
    assertEquals(spc2.add(2.204901, 7.510037), 9.715, 0.000000001);
  }

  /**
   * Tests for the sub method.
   */
  @Test
  public void testSub() {
    assertEquals(spc1.sub( 3.10501, 2.0000001), 1.10501, 0.000000001);
    assertEquals(spc2.sub( 3.10501, 2.0000001), 1.105, 0.000000001);

    assertEquals(spc1.sub( 9.71494, 7.510037), 2.2049, 0.000000001);
    assertEquals(spc2.sub( 9.71494, 7.510037), 2.205, 0.000000001);
  }

  /**
   * Tests for the multi method.
   */
  @Test
  public void testMulti() {
    assertEquals(spc1.multi( 3.10501, 2.0000001), 6.21002, 0.000000001);
    assertEquals(spc2.multi( 3.10501, 2.0000001), 6.21, 0.000000001);

    assertEquals(spc1.multi( 9.71494, 7.510037), 72.95956, 0.000000001);
    assertEquals(spc2.multi( 9.71494, 7.510037), 72.96, 0.000000001);
  }

  /**
   * Tests for the divide method.
   */
  @Test
  public void testDivide() {
    assertEquals(spc1.divide( 3.10501, 2.0000001), 1.5525, 0.000000001);
    assertEquals(spc2.divide( 3.10501, 2.0000001), 1.553, 0.000000001);

    assertEquals(spc1.divide( 9.71494, 7.510037), 1.29359, 0.000000001);
    assertEquals(spc2.divide( 9.71494, 7.510037), 1.294, 0.000000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByNegative() {
    spc1.divide(16, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativePrecision() {
    new SetPrecisionCalculator(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLargePrecision() {
    new SetPrecisionCalculator(436);
  }

}