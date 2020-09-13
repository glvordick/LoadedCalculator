package View;

import Util.Utils;
import java.util.Scanner;
import java.util.function.Consumer;

public class TextualView implements ILoadedCalculatorView {

  private final Appendable ap;
  private final Readable rd;

  public TextualView(Appendable ap, Readable rd) {
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
      Double.parseDouble(result);
      Utils.safeAppend(ap, " = " + result + "\n");
    } catch (NumberFormatException e) {
      try {
        Utils.complexFromString(result);
        Utils.safeAppend(ap, " = " + result + "\n");
      } catch (IllegalArgumentException iae) {
        if(result.contains("(x")) {
          Utils.safeAppend(ap, " = " + result + "\n");
        }
        else {
          Utils.safeAppend(ap, result);
          if (result.contains("precision")) {
            Utils.safeAppend(ap, "\n");
          }
        }
      }
    }
  }
}
