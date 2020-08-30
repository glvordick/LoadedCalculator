package View;

import Util.Utils;
import java.util.Scanner;
import java.util.function.Consumer;
import org.w3c.dom.Text;

public class TextualView implements ILoadedCalculatorView {

  private final Appendable ap;
  private final Readable rd;

  public TextualView (Appendable ap, Readable rd) {
    this.ap = ap;
    this.rd = rd;
  }

  private Consumer<String> commandCallback;


  @Override
  public void run() {
    Utils.safeAppend(ap, "Begin!\n");
    boolean exit = false;
    Scanner s = new Scanner(rd);
    while (!exit && s.hasNext()) {
      String str = s.nextLine();
      if (str.equalsIgnoreCase("exit")) {
        exit = true;
      } else {
        this.acceptResult(str);
        commandCallback.accept(str);
      }
    }
  }

  @Override
  public void showErrorMessage(String error) {
    Utils.safeAppend(ap, error);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }

  @Override
  public void acceptResult(String result) {
    try {
      double d = Double.parseDouble(result);
      Utils.safeAppend(ap, " = " + result + "\n");
    } catch (NumberFormatException e) {
      try {
        int[] a = Utils.complexFromString(result);
        Utils.safeAppend(ap, " = " + result + "\n");
      } catch (IllegalArgumentException iae) {
        Utils.safeAppend(ap, result);
        if(result.contains("precision")) {
          Utils.safeAppend(ap, "\n");
        }
      }

    }
  }

  @Override
  public void acceptAnswer(String answer) {
    acceptResult(answer);
  }
}
