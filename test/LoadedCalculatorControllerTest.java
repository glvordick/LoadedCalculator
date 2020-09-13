import static org.junit.Assert.*;

import Controller.ILoadedCalculatorController;
import Controller.LoadedCalculatorController;
import Model.LoadedCalculator.ILoadedCalculatorModel;
import Model.LoadedCalculator.LoadedCalculatorModel;
import View.ILoadedCalculatorView;
import View.TextualView;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

public class LoadedCalculatorControllerTest {

  private ILoadedCalculatorController cont;
  private ILoadedCalculatorModel model;
  private ILoadedCalculatorView view;

  private StringBuilder sb;
  private StringReader sr;

  @Before
  public void setUp() {
    model = new LoadedCalculatorModel();
    sb = new StringBuilder();
  }

  @Test
  public void testRun() {
    view = new TextualView(sb, new StringReader(""));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\n");
  }

  @Test
  public void testProcessGCD() {
    view = new TextualView(sb, new StringReader("gcd 20,75"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ngcd 20,75 = 5\n");
  }

  @Test
  public void testProcessLCM() {
    view = new TextualView(sb, new StringReader("lcm 20,75"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nlcm 20,75 = 300\n");
  }

  @Test
  public void testProcessPrime() {
    view = new TextualView(sb, new StringReader("prime 193"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nprime 193true");
  }

  @Test
  public void testProcessMod() {
    view = new TextualView(sb, new StringReader("mod 15,4"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nmod 15,4 = 3\n");
  }

  @Test
  public void testProcessExp() {
    view = new TextualView(sb, new StringReader("exp 2,7"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nexp 2,7 = 128.0\n");
  }

  @Test
  public void testProcessLog() {
    view = new TextualView(sb, new StringReader("log 10000"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nlog 10000 = 4.0\n");
  }

  @Test
  public void testProcessLn() {
    view = new TextualView(sb, new StringReader("ln e*e"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nln e*e = 2.0\n");
  }

  @Test
  public void testProcessLogBaseN() {
    view = new TextualView(sb, new StringReader("logBaseN 2,8"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nlogBaseN 2,8 = 3.0\n");
  }

  @Test
  public void testProcessSin() {
    view = new TextualView(sb, new StringReader("sin pi/2 radians"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nsin pi/2 radians = 1.0\n");
  }

  @Test
  public void testProcessCos() {
    view = new TextualView(sb, new StringReader("cos pi radians"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncos pi radians = -1.0\n");
  }

  @Test
  public void testProcessTan() {
    view = new TextualView(sb, new StringReader("tan pi/4 radians"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ntan pi/4 radians = 1.0\n");
  }

  @Test
  public void testProcessCAdd1() {
    view = new TextualView(sb, new StringReader("cAdd 3+4i,2-i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd 3+4i,2-i = 5+3i\n");
  }

  @Test
  public void testProcessCAdd2() {
    view = new TextualView(sb, new StringReader("cAdd 3,4"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd 3,4 = 7+0i\n");
  }

  @Test
  public void testProcessCAdd3() {
    view = new TextualView(sb, new StringReader("cAdd i,i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd i,i = 0+2i\n");
  }

  @Test
  public void testProcessCAdd4() {
    view = new TextualView(sb, new StringReader("cAdd 3i,4i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd 3i,4i = 0+7i\n");
  }

  @Test
  public void testProcessCAdd5() {
    view = new TextualView(sb, new StringReader("cAdd i,-i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd i,-i = 0+0i\n");
  }

  @Test
  public void testProcessCAdd6() {
    view = new TextualView(sb, new StringReader("cAdd 123-123i,100+100i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd 123-123i,100+100i = 223-23i\n");
  }

  @Test
  public void testProcessCAdd7() {
    view = new TextualView(sb, new StringReader("cAdd -3-4i,-6-4i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd -3-4i,-6-4i = -9-8i\n");
  }

  @Test
  public void testProcessCAdd8() {
    view = new TextualView(sb, new StringReader("cAdd i,-2"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncAdd i,-2 = -2+1i\n");
  }



  @Test
  public void testProcessCSub() {
    view = new TextualView(sb, new StringReader("cSub 3+4i,2-i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncSub 3+4i,2-i = 1+5i\n");
  }

  @Test
  public void testProcessCMulti() {
    view = new TextualView(sb, new StringReader("cMultiply 3+4i,2-i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncMultiply 3+4i,2-i = 10+5i\n");
  }

  @Test
  public void testProcessCDivide() {
    view = new TextualView(sb, new StringReader("cDivide 32-13i,2+3i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncDivide 32-13i,2+3i = 2-9i\n");
  }

  @Test
  public void testProcessCRemainder() {
    view = new TextualView(sb, new StringReader("cRemainder 32-13i,2+3i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(),
        "precision 5\nBegin!\ncRemainder 32-13i,2+3i = 2-9i remainder: 1-1i\n");
  }

  @Test
  public void testProcessCNorm() {
    view = new TextualView(sb, new StringReader("cNorm 3+4i"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\ncNorm 3+4i = 5.0\n");
  }

  @Test
  public void testProcessLinCom() {
    view = new TextualView(sb, new StringReader("linCom 0.123456789,0,100"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nlinCom 0.123456789,0,100 = 12.34568\n");
  }

  @Test
  public void testProcessAbs() {
    view = new TextualView(sb, new StringReader("abs -17.38"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nabs -17.38 = 17.38\n");
  }

  @Test
  public void testProcessPrecision() {
    view = new TextualView(sb, new StringReader("precision 7"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nprecision 7\nprecision 7\n");
  }

  @Test
  public void testProcessEqual1() {
    view = new TextualView(sb, new StringReader("equal (20-(8+2))/8"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nequal (20-(8+2))/8 = 1.25\n");
  }

  @Test
  public void testProcessEqual2() {
    view = new TextualView(sb, new StringReader("equal (-)18.0+6"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\nBegin!\nequal (-)18.0+6 = -12.0\n");
  }

  @Test
  public void testProcessQuadFact() {
    view = new TextualView(sb, new StringReader("quadFact 3,(-)5,4"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\n"
        + "Begin!\n"
        + "quadFact 3,(-)5,4 = 3.0(x-1.63264i)(x-1.63264i)\n");
  }

  @Test
  public void testProcessQuadFact2() {
    view = new TextualView(sb, new StringReader("quadFact 17,(-)8,21"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\n"
        + "Begin!\n"
        + "quadFact 17,(-)8,21 = 17.0(x+0.85095i)(x-1.32154i)\n");
  }

  @Test
  public void testProcessFact() {
    view = new TextualView(sb, new StringReader("fact 17"));
    cont = new LoadedCalculatorController(view, model);

    cont.run();
    assertEquals(sb.toString(), "precision 5\n"
        + "Begin!\n"
        + "fact 17 = 355687428096000\n");
  }

}