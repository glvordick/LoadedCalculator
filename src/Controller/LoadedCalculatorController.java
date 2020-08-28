package Controller;

import Model.LoadedCalculator.ILoadedCalculatorModel;
import Util.Utils;
import View.ILoadedCalculatorView;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

public class LoadedCalculatorController implements IController, Consumer<String> {

  private final ILoadedCalculatorView view;
  private final ILoadedCalculatorModel model;

  public LoadedCalculatorController(ILoadedCalculatorView view,
      ILoadedCalculatorModel model) {
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
  }
  @Override
  public void processCommand(String s, String args) {
    Map<String, Runnable> commands = new SupportedOperations().getOperations(args);

    Runnable command = commands.get(s); // we get an empty runnable if no command found


    try {
      command.run();
    } catch (RuntimeException e) { //because it could return one of many runtime exceptions, of
      //which we would simply like to relay that message to the user.
      view.showErrorMessage(e.getMessage());
    }
  }

  @Override
  public void run() {
    view.setCommandCallback(this);
    view.run();
  }

  @Override
  public void accept(String s) {
    Scanner scan = new Scanner(s);

    String cmd = scan.next();

    StringBuilder args = new StringBuilder();

    while (scan.hasNext()) {
      args.append(scan.next());
    }

    processCommand(cmd, args.toString());
  }


  //holds all of the supported commands for the edit controller.
  private class SupportedOperations {

    //returns a map of all the supported commands
    public Map<String, Runnable> getOperations(String args) {
      Map<String, Runnable> commands = new HashMap<>();

      commands.put("gcd", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          int ans = model.gcd(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("lcm", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          int ans = model.lcm(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("prime", () -> {
        try {
          int a = Integer.parseInt(args);
          boolean ans = model.prime(a);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid argument given!");
        }
      });
      commands.put("mod", () -> {
        try {
          int a = Integer.parseInt(args.substring(0, args.indexOf(",")));
          int b = Integer.parseInt(args.substring(args.indexOf(",") + 1));
          int ans = model.mod(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("exp", () -> {
        try {
          double a = Double.parseDouble(args.substring(0, args.indexOf(",")));
          double b = Double.parseDouble(args.substring(args.indexOf(",") + 1));
          double ans = model.exp(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("log", () -> {
        try {
          double a = Double.parseDouble(args);
          double ans = model.log(a);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("ln", () -> {
        try {
          double a = Double.parseDouble(args);
          double ans = model.ln(a);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("logBaseN", () -> {
        try {
          double a = Double.parseDouble(args.substring(0, args.indexOf(",")));
          double b = Double.parseDouble(args.substring(args.indexOf(",") + 1));
          double ans = model.logBaseN(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("sin", () -> {
        try {
          double a = Double.parseDouble(args.substring(0, args.indexOf(",")));
          boolean b = args.substring(args.indexOf(",") + 1).equals("Radians");
          double ans = model.sin(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cos", () -> {
        try {
          double a = Double.parseDouble(args.substring(0, args.indexOf(",")));
          boolean b = args.substring(args.indexOf(",") + 1).equals("Radians");
          double ans = model.cos(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("tan", () -> {
        try {
          double a = Double.parseDouble(args.substring(0, args.indexOf(",")));
          boolean b = args.substring(args.indexOf(",") + 1).equals("Radians");
          double ans = model.tan(a, b);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
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
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cSubtract", () -> {
        try {
          String a = args.substring(0, args.indexOf(","));
          String b = args.substring(args.indexOf(",") + 1);
          int[] complex1 = Utils.complexFromString(a);
          int[] complex2 = Utils.complexFromString(b);
          String ans = model.complexSub(complex1[0], complex1[1], complex2[0], complex2[1]);
          view.acceptResult(ans);
        } catch(StringIndexOutOfBoundsException e) {
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
        } catch(StringIndexOutOfBoundsException e) {
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
        } catch(StringIndexOutOfBoundsException e) {
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
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("cNorm", () -> {
        try {
          int[] complex1 = Utils.complexFromString(args);
          int ans = model.complexNorm(complex1[0], complex1[1]);
          view.acceptResult(String.valueOf(ans));
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("precision", () -> {
        try {
        int p = Integer.parseInt(args);
        model.setPrecision(p);
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("getAns", () -> {
        try {
          String ans = model.getAns();
          view.acceptAnswer(ans);
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      commands.put("equal", () -> {
        try {
          double ans = Utils.equationSolver(args, model);
          String answer = String.valueOf(ans);
          if (answer.endsWith(".0")) {
            view.acceptResult(answer.substring(0, answer.length() - 2));
          } else {
            view.acceptResult(answer);
          }
        } catch(StringIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("Invalid arguments given!");
        }
      });
      return commands;
    }
  }
}
