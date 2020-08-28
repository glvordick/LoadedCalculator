import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Model.SimpleDouble.*;

public class SimpleDoubleCalculatorImplTest {

  ISimpleDoubleCalculator calc;

  @Before
  public void init() {
    this.calc = new SimpleDoubleCalculator();
  }

  @Test
  public void testAdd() {
    assertEquals(calc.add(1.2, 2.8), 4.0, 0.001);
    assertEquals(calc.add(-2, -1), -3, 0.001);
    assertEquals(calc.add(0, 3.12), 3.12, 0.001);
    assertEquals(calc.add(3, 0), 3, 0.001);
    assertEquals(calc.add(-1.041, 3.041), 2, 0.001);
    assertEquals(calc.add(-4, 3.631), -0.369, 0.001);
    assertEquals(calc.add(3, -1), 2, 0.001);
    assertEquals(calc.add(3, -4), -1, 0.001);

  }

  @Test
  public void testSub() {
    assertEquals(calc.sub(1, 3.5), -2.5, 0.001);
    assertEquals(calc.sub(3.5, 1), 2.5, 0.001);
    assertEquals(calc.sub(-2, -1), -1, 0.001);
    assertEquals(calc.sub(3, 0.001), 2.999, 0.001);
    assertEquals(calc.sub(0, 0), 0, 0.001);
    assertEquals(calc.sub(-1, 3), -4, 0.001);
    assertEquals(calc.sub(3, -1), 4, 0.001);
    assertEquals(calc.sub(3.123, -4.643), 7.766, 0.001);
  }

  @Test
  public void testMulti() {
    assertEquals(calc.multi(1, 3.2376458), 3.2376458, 0.001);
    assertEquals(calc.multi(3, 1.5), 4.5, 0.001);
    assertEquals(calc.multi(3, 4), 12, 0.001);
    assertEquals(calc.multi(-2, -1.75), 3.5, 0.001);
    assertEquals(calc.multi(0, 3), 0, 0.001);
    assertEquals(calc.multi(3, 0), 0, 0.001);
    assertEquals(calc.multi(0, 0), 0, 0.001);
    assertEquals(calc.multi(-1.5, 3), -4.5, 0.001);
    assertEquals(calc.multi(-4, 3), -12, 0.001);
    assertEquals(calc.multi(3, -1), -3, 0.001);
    assertEquals(calc.multi(3, -4), -12, 0.001);
  }

  @Test
  public void testDivide() {
    assertEquals(calc.divide(1, 3), 0.3333333, 0.001);
    assertEquals(calc.divide(3, 4), 0.75, 0.001);
    assertEquals(calc.divide(-2, -1), 2, 0.001);
    assertEquals(calc.divide(0, 3), 0, 0.001);
    assertEquals(calc.divide(-1, 3), -0.333333, 0.001);
    assertEquals(calc.divide(-4, 3), -1.333333, 0.001);
    assertEquals(calc.divide(3, -1), -3, 0.001);
    assertEquals(calc.divide(3, -4), -0.75, 0.001);
    assertEquals(calc.divide(20, -4), -5, 0.001);
    assertEquals(calc.divide(88, 4), 22, 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByNegative() {
    calc.divide(16, 0);
  }
}