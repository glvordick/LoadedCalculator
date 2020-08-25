import static org.junit.Assert.*;

import Model.SetPrecision.SetPrecisionCalculatorImpl;
import Model.StoringCalculator.StoringCalculator;
import Model.StoringCalculator.StoringCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

public class StoringCalculatorImplTest {

  StoringCalculator sc1;
  StoringCalculator sc2;

  @Before
  public void init() {
    sc1 = new StoringCalculatorImpl();
    sc2 = new StoringCalculatorImpl(new SetPrecisionCalculatorImpl());
  }

  @Test
  public void testGetAns() {
    sc1.add(3, 5);
    assertEquals(sc1.getAns(), 8, 0.001);
    assertEquals(sc1.getAns(), 0, 0.001);
    assertEquals(sc1.getAns(), 0, 0.001);
    //only get 0 if you keep calling getAns

    sc2.multi(3.2144, 3.32487242);
    sc2.divide(24652, 17);
    assertEquals(sc2.getAns(), 1450.11765, 0.000001); //line 29 operation
    assertEquals(sc2.getAns(), 10.68747, 0.000001); //line 28 operation
  }

  /**
   * Tests for the add method.
   */
  @Test
  public void testAdd() {
    assertEquals(sc1.add(1.105014, 2.0000001), 3.1050141, 0.000000001);

    assertEquals(sc2.add(2.204901, 7.510037), 9.71494, 0.000000001);
  }

  /**
   * Tests for the sub method.
   */
  @Test
  public void testSub() {
    assertEquals(sc1.sub( 3.10501, 2.0000001), 1.10501, 0.00001);

    assertEquals(sc2.sub( 9.71494, 7.510037), 2.2049, 0.00001);
  }

  /**
   * Tests for the multi method.
   */
  @Test
  public void testMulti() {
    assertEquals(sc1.multi( 3.10501, 2.0000001), 6.21002, 0.000001);

    assertEquals(sc2.multi( 9.71494, 7.510037), 72.95956, 0.000001);
  }

  /**
   * Tests for the divide method.
   */
  @Test
  public void testDivide() {
    assertEquals(sc1.divide( 3.10501, 2.0000001), 1.5525, 0.00001);

    assertEquals(sc2.divide( 9.71494, 7.510037), 1.29359, 0.00001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByNegative() {
    sc1.divide(16, 0);
  }
}