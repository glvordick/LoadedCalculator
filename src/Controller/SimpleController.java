package Controller;

import Model.LoadedCalculator.ILoadedCalculatorModel;
import View.ILoadedCalculatorView;

/**
 * A simple controller which deals with IViews. If given an IEditableView, will display it but does
 * not support events.
 */
public class SimpleController implements IController {

  private final ILoadedCalculatorModel model;
  private final ILoadedCalculatorView view;

  /**
   * Construct a SimpleController with a given model and view. Will delegate to them as needed.
   *
   * @param model the model to be associated.
   * @param view  the view to be associated.
   */
  public SimpleController(ILoadedCalculatorModel model, ILoadedCalculatorView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void processCommand(String s, String args) {
    throw new UnsupportedOperationException("No commands to be processed");
  }

  @Override
  public void run() {
    view.run();
  }
}
