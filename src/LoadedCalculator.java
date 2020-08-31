import Controller.ILoadedCalculatorController;
import Controller.LoadedCalculatorController;
import Model.LoadedCalculator.LoadedCalculatorModel;
import View.LoadedCalculatorView;

public class LoadedCalculator {

  public static void main(String[] args) {
    ILoadedCalculatorController controller = new LoadedCalculatorController(new LoadedCalculatorView(),
        new LoadedCalculatorModel());
    controller.run();

    //System.out.println(Utils.complexFromString("4")[0] + " " + Utils.complexFromString("4")[1]);

  }
}
