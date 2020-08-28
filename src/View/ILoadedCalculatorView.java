package View;

import java.util.function.Consumer;

public interface ILoadedCalculatorView {

  /**
   * Runs the view, displays the calculator.
   */
  void run();

  /**
   * Show an error message in the calculator.
   *
   * @param error the error message to be displayed
   */
  void showErrorMessage(String error);

  /**
   * Sets the callbacks for the commands that the buttons call.
   *
   * @param callback the consumer which processes the commands.
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Takes in the answer to be added to the output area.
   *
   * @param result the result of a calculation.
   */
  void acceptResult(String result);

  /**
   * Takes in the answer to be added to the output area.
   *
   * @param answer the result of a calculation.
   */
  void acceptAnswer(String answer);


}
