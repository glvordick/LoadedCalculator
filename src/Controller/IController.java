package Controller;

/**
 * The controller interface for animate program. It can process commands which are then applied to
 * the proper model and view.
 */
public interface IController {

  /**
   * Takes in a command as a string and a list of possible arguments for the command, and runs the
   * runnable object associated with that string. The list of valid commands is:
   *
   * <p>
   * "gcd" <br> "lcm" <br> "prime" <br> "mod" <br> "exp" <br> "log" <br> "ln" <br> "logBaseN" <br>
   * "sin" <br> "cos" <br> "tan"
   * <br> "cAdd" <br> "cSub" <br> "cMultiply" <br> "cDivide" <br> "cRemainder" <br> "cNorm"
   * <br> "precision" <br> "getAns" <br>
   * </p>
   *
   * @param s    the name of the command.
   * @param args the arguments contained in a string.
   */
  void processCommand(String s, String args);

  /**
   * Runs the program on the correct model and view.
   */
  void run();
}