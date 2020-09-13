import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import Model.LoadedCalculator.ILoadedCalculatorModel;
import Model.LoadedCalculator.LoadedCalculatorModel;
import Util.Utils;
import org.junit.Test;

public class UtilsTest {

  ILoadedCalculatorModel c = new LoadedCalculatorModel();

  @Test
  public void testComplexFromString() {
    assertArrayEquals(Utils.complexFromString("3+4i"), new int[]{3, 4});
    assertArrayEquals(Utils.complexFromString("0+0i"), new int[]{0, 0});
    assertArrayEquals(Utils.complexFromString("3+4i"), new int[]{3, 4});
    assertArrayEquals(Utils.complexFromString("2"), new int[]{2, 0});
    assertArrayEquals(Utils.complexFromString("4i"), new int[]{0, 4});
    assertArrayEquals(Utils.complexFromString("-2+3i"), new int[]{-2, 3});
    assertArrayEquals(Utils.complexFromString("3-4i"), new int[]{3, -4});
    assertArrayEquals(Utils.complexFromString("-7-12642i"), new int[]{-7, -12642});
    assertArrayEquals(Utils.complexFromString("i"), new int[]{0, 1});
    assertArrayEquals(Utils.complexFromString("12++3i"), new int[]{12, 3});
    assertArrayEquals(Utils.complexFromString("4i + 5"), new int[]{5, 4});

  }

  @Test
  public void testRemoveAllChar() {
    assertEquals(Utils.removeAllChar("abbbcbdbebfbgbhbibjbkblbmbnbobpbqbrbsbtbubvbwbxbybz", 'b'),
        "acdefghijklmnopqrstuvwxyz");
    assertEquals(Utils.removeAllChar("", 'b'),
        "");
    assertEquals(Utils.removeAllChar("a a a a a a a a a a a", ' '),
        "aaaaaaaaaaa");
    assertEquals(Utils.removeAllChar("a a a a a a a a a a a", 'b'),
        "a a a a a a a a a a a");
  }

  @Test
  public void testEquationSolver() {
    assertEquals(Utils.equationSolver("3+4-7", c), 0, 0.00001);
    assertEquals(Utils.equationSolver("(20-(8+2))/8", c), 1.25, 0.00001);
    assertEquals(Utils.equationSolver("3.14 - 2.5", c), 0.64, 0.00001);
    assertEquals(Utils.equationSolver("3-6*5+27", c), 0, 0.00001);
    assertEquals(Utils.equationSolver("((((3+4)×5)÷7)*20)-30.3031", c), 69.6969, 0.00001);
    assertEquals(Utils.equationSolver("3+6*2-3+8/2", c), 16, 0.00001);
    assertEquals(Utils.equationSolver("3*3-4/2*5", c), -1, 0.00001);
    assertEquals(Utils.equationSolver("2*(-)3", c), -6, 0.00001);
    assertEquals(Utils.equationSolver("(-)2*(-)3", c), 6, 0.00001);
    assertEquals(Utils.equationSolver("(-)2*3", c), -6, 0.00001);

    assertEquals(Utils.equationSolver("18/(-)3", c), -6, 0.00001);
    assertEquals(Utils.equationSolver("(-)18/(-)3", c), 6, 0.00001);
    assertEquals(Utils.equationSolver("(-)18/3", c), -6, 0.00001);

    assertEquals(Utils.equationSolver("18+(-)3", c), 15, 0.00001);
    assertEquals(Utils.equationSolver("(-)18+(-)3", c), -21, 0.00001);
    assertEquals(Utils.equationSolver("(-)18+3", c), -15, 0.00001);


    assertEquals(Utils.equationSolver("18-(-)3", c), 21, 0.00001);
    assertEquals(Utils.equationSolver("(-)18-(-)3", c), -15, 0.00001);
    assertEquals(Utils.equationSolver("(-)18-3", c), -21, 0.00001);
  }

  @Test
  public void testRightParenFinder() {
    assertEquals(Utils.rightParenFinder("(()()(((())))))", 0), 13);
    assertEquals(Utils.rightParenFinder("(()()(((())))))", 1), 2);
    assertEquals(Utils.rightParenFinder("(()()(((())))))", 5), 12);
    assertEquals(Utils.rightParenFinder("(()(()()(((()))()))", 0), -1);
    assertEquals(Utils.rightParenFinder("(((((((((((((", 7), -1);
    assertEquals(Utils.rightParenFinder("abcdef(helloWorld)fkafa", 6), 17);
  }

  @Test
  public void testAdjustedParseDouble() {
    assertEquals(3.14, Utils.adjustedParseDouble("3.14"), 0.00001);
    assertEquals(-3.14, Utils.adjustedParseDouble("-3.14"), 0.00001);
  }

  @Test (expected = NumberFormatException.class)
  public void testIllegalDouble1() {
    Utils.adjustedParseDouble("+3.14");
  }

  @Test (expected = NumberFormatException.class)
  public void testIllegalDouble2() {
    Utils.adjustedParseDouble("Hello World!");
  }

  @Test(expected = NumberFormatException.class)
  public void testEmptyStringComplex() {
    Utils.complexFromString("");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex1() {
    Utils.complexFromString("3-4h");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex2() {
    Utils.complexFromString("-----3 ----- 3 i");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex3() {
    Utils.complexFromString("3+4");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex4() {
    Utils.complexFromString("15+i randomGarbage");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex5() {
    Utils.complexFromString("12+-38424+3i");
  }

  @Test(expected = NumberFormatException.class)
  public void testInvalidComplex6() {
    Utils.complexFromString("3i + 3i");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoParens() {
    Utils.rightParenFinder("HelloWorld", 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoLeftParen() {
    Utils.rightParenFinder("))))))))))", 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoLeftParenAtLoc() {
    Utils.rightParenFinder("())))))))))", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTwoDecimalPoints() {
    Utils.equationSolver("3.4.5", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadParens() {
    Utils.equationSolver(")3+4( 2 - 5)", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnlyLeft() {
    Utils.equationSolver("(3+4(", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnlyRight() {
    Utils.equationSolver(")3+4)", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiFirst1() {
    Utils.equationSolver("*3-4", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiFirst2() {
    Utils.equationSolver("×3+4", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivFirst1() {
    Utils.equationSolver("/3-4", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivFirst2() {
    Utils.equationSolver("÷3+4", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiLast1() {
    Utils.equationSolver("3-4*", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiLast2() {
    Utils.equationSolver("3+4×", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivLast1() {
    Utils.equationSolver("3+4/", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivLast2() {
    Utils.equationSolver("3+4÷", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLast1() {
    Utils.equationSolver("3-4+", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSubLast1() {
    Utils.equationSolver("3+4-", c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGarbageEquation() {
    Utils.equationSolver("HelloGarbage", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConsecutiveAdd() {
    Utils.equationSolver("3++4", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConsecutiveSub() {
    Utils.equationSolver("3---4", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConsecutiveMulti() {
    Utils.equationSolver("3**4", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConsecutiveDiv() {
    Utils.equationSolver("3//4", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEndingWithNeg() {
    Utils.equationSolver("3*4(-)", c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConsecNeg() {
    Utils.equationSolver("(-)(-)3", c);
  }
}