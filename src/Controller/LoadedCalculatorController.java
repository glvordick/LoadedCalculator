package Controller;

import Model.LoadedCalculator.ILoadedCalculatorModel;
import Util.Utils;
import View.ILoadedCalculatorView;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * An implementation of the {@link ILoadedCalculatorController} interface. This implementation is
 * for a calculator that has a view and model that need to be communicated with. This class
 * implements the Consumer interface in order to receive information from the view and act on it.
 * This, in essence, makes this class a listener for the View.
 */
public class LoadedCalculatorController implements ILoadedCalculatorController, Consumer<String> {

  private final ILoadedCalculatorView view;
  private final ILoadedCalculatorModel model;

  /**
   * Default constructor for the class.
   *
   * @param view  a {@link ILoadedCalculatorView} object that the controller will listen to.
   * @param model a {@link ILoadedCalculatorModel} object that the controller will talk to.
   */
  public LoadedCalculatorController(ILoadedCalculatorView view,
      ILoadedCalculatorModel model) {
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
    this.accept("precision " + model.getPrecision());
  }

  @Override
  public void processCommand(String s, String args) {
    Map<String, Runnable> commands = new SupportedOperations().getOperations(args);
    //System.out.println(args);

    Runnable command = commands.get(s); // we get an empty runnable if no command found

    //command.run();
    try {
      command.run();
    } catch (RuntimeException e) { // because it could return one of many runtime exceptions, of
      // which we would simply like to relay that message to the user.
      view.showErrorMessage(e.getMessage());
      view.acceptResult("ERROR");
    }
  }


  @Override
  public void run() {
    view.setCommandCallback(this); //Sets this object as the listener for the view.
    view.run();
  }

  @Override
  public void accept(String s) {
    Scanner scan = new Scanner(s);

    String cmd = scan.next();

    StringBuilder args = new StringBuilder();

    boolean b = false;
    while (scan.hasNext()) {
      b = true;
      args.append(scan.next().toLowerCase()).append(" ");
    }

    if (b) {
      args.delete(args.length() - 1, args.length()); // removes trailing space
    }

    String params = args.toString()
        .replace("pi", String.valueOf(Math.PI))
        .replace("e", String.valueOf(Math.E));
    if (params.contains("ans")) {

      String ans = String.valueOf(this.model.getAns());

      for (int i = 0; i < params.length() - 2; i++) {
        String str = params.substring(i, i + 3);
        if (str.equals("ans")) {
          if (i > 0) {
            try {
              Double.parseDouble(String.valueOf(params.charAt(i - 1)));
            } catch (NumberFormatException e) {
              try {
                Double.parseDouble(String.valueOf(params.charAt(i + 3)));
              } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
                params = params.substring(0, i) + ans + params.substring(i + 3);
              }
            }
          } else {
            try {
              Double.parseDouble(String.valueOf(params.charAt(i + 3)));
            } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
              params = params.substring(0, i) + ans + params.substring(i + 3);
            }
          }
        }
      }

      //params = params.replace("ans", String.valueOf(this.model.getAns()));
    }
    processCommand(cmd, params);
  }


  //holds all of the supported commands for the edit controller.
  private class SupportedOperations {

    //returns a map of all the supported commands
    public Map<String, Runnable> getOperations(String args) {
      Map<String, Runnable> commands = new HashMap<>();

      //Every command is surrounded with a try catch to help give more specific error codes

      commands.put("gcd", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          int ans = model.gcd(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("lcm", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          long ans = model.lcm(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("prime", () -> {
        try {
          int a = Integer.parseInt(args);
          boolean ans = model.prime(a);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid argument given!");
        }
      });
      commands.put("mod", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          int ans = model.mod(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("exp", () -> {
        try {
          double a = Utils.equationSolver(args.substring(0, args.indexOf(",")), model);
          double b = Utils.equationSolver(args.substring(args.indexOf(",") + 1), model);
          double ans = model.exp(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("log", () -> {
        try {
          double a = Utils.equationSolver(args, model);
          double ans = model.log(a);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("ln", () -> {
        try {
          double a = Utils.equationSolver(args, model);
          double ans = model.ln(a);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("logBaseN", () -> {
        try {
          double a = Utils.equationSolver(args.substring(0, args.indexOf(",")), model);
          double b = Utils.equationSolver(args.substring(args.indexOf(",") + 1), model);
          double ans = model.logBaseN(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("sin", () -> {
        try {
          double a = Utils.equationSolver(args.substring(0, args.indexOf(" ")), model);
          boolean b = args.toLowerCase().contains("rad");
          double ans = model.sin(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cos", () -> {
        try {
          double a = Utils.equationSolver(args.substring(0, args.indexOf(" ")), model);
          boolean b = args.toLowerCase().contains("rad");
          double ans = model.cos(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("tan", () -> {
        try {
          double a = Utils.equationSolver(args.substring(0, args.indexOf(" ")), model);
          boolean b = args.toLowerCase().contains("rad");
          double ans = model.tan(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cAdd", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexAdd(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cSub", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexSub(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cMultiply", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexMulti(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cDivide", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexDivide(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cRemainder", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexRemainder(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cNorm", () -> {
        try {
          int[] complex1 = Utils.complexFromString(args);
          double ans = model.complexNorm(complex1[0], complex1[1]);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("linCom", () -> {
        try {
          double ratio = Utils.equationSolver(args.substring(0, args.indexOf(",")), model);
          double a = Utils.equationSolver(
              args.substring(args.indexOf(",") + 1, args.lastIndexOf(",")), model);
          double b = Utils.equationSolver(args.substring(args.lastIndexOf(",") + 1), model);
          double ans = model.linearCombination(ratio, a, b);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });

      commands.put("abs", () -> {
        try {
          double d = Utils.equationSolver(args, model);
          double ans = model.abs(d);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("precision", () -> {
        try {
          int p = Integer.parseInt(args);
          model.setPrecision(p);
          view.acceptResult("precision " + p);
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("equal", () -> {
        try {
          double ans = Utils.equationSolver(args, model);
          view.acceptResult(String.valueOf(ans));
        } catch (StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      return commands;
    }
  }
}
