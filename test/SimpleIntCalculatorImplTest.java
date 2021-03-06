import static org.junit.Assert.assertEquals;

import Model.SimpleInt.SimpleIntCalculator;
import Model.SimpleInt.SimpleIntCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

public class SimpleIntCalculatorImplTest {

  SimpleIntCalculator calc;

  @Before
  public void init() {
    this.calc = new SimpleIntCalculatorImpl();
  }

  @Test
  public void testAdd() {
    assertEquals(calc.add(1, 3), 4);
    assertEquals(calc.add(-2, -1), -3);
    assertEquals(calc.add(0, 3), 3);
    assertEquals(calc.add(3, 0), 3);
    assertEquals(calc.add(-1, 3), 2);
    assertEquals(calc.add(-4, 3), -1);
    assertEquals(calc.add(3, -1), 2);
    assertEquals(calc.add(3, -4), -1);

  }

  @Test
  public void testSub() {
    assertEquals(calc.sub(1, 3), -2);
    assertEquals(calc.sub(3, 1), 2);
    assertEquals(calc.sub(-2, -1), -1);
    assertEquals(calc.sub(0, 3), -3);
    assertEquals(calc.sub(3, 0), 3);
    assertEquals(calc.sub(0, 0), 0);
    assertEquals(calc.sub(-1, 3), -4);
    assertEquals(calc.sub(-4, 3), -7);
    assertEquals(calc.sub(3, -1), 4);
    assertEquals(calc.sub(3, -4), 7);
  }

  @Test
  public void testMulti() {
    assertEquals(calc.multi(1, 3), 3);
    assertEquals(calc.multi(3, 1), 3);
    assertEquals(calc.multi(3, 4), 12);
    assertEquals(calc.multi(-2, -1), 2);
    assertEquals(calc.multi(0, 3), 0);
    assertEquals(calc.multi(3, 0), 0);
    assertEquals(calc.multi(0, 0), 0);
    assertEquals(calc.multi(-1, 3), -3);
    assertEquals(calc.multi(-4, 3), -12);
    assertEquals(calc.multi(3, -1), -3);
    assertEquals(calc.multi(3, -4), -12);
  }

  @Test
  public void testDivide() {
    assertEquals(calc.divide(1, 3), 0);
    assertEquals(calc.divide(3, 1), 3);
    assertEquals(calc.divide(3, 4), 0);
    assertEquals(calc.divide(-2, -1), 2);
    assertEquals(calc.divide(0, 3), 0);
    assertEquals(calc.divide(-1, 3), 0);
    assertEquals(calc.divide(-4, 3), -1);
    assertEquals(calc.divide(3, -1), -3);
    assertEquals(calc.divide(3, -4), 0);
    assertEquals(calc.divide(20, -4), -5);
    assertEquals(calc.divide(88, 4), 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByNegative() {
    calc.divide(16, 0);
  }
}