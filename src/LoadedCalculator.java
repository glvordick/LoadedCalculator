import Controller.IController;
import Controller.LoadedCalculatorController;
import Model.LoadedCalculator.LoadedCalculatorModel;
import View.LoadedCalculatorView;

public class LoadedCalculator {

  public static void main(String[] args) {
    IController controller = new LoadedCalculatorController(new LoadedCalculatorView(),
        new LoadedCalculatorModel());
    controller.run();
  }
}
