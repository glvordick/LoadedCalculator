package View;

import Util.Utils;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;

/**
 * This class in as implementation of the {@link ILoadedCalculatorView} interface. This view is has
 * a graphical user interface with over 20 buttons. The GUI displays the results of calculations
 * made by the calculator. It also contains a catalog of more complicated operations that the user
 * can also take advantage of.
 */
public class LoadedCalculatorView extends JFrame implements ILoadedCalculatorView {

  private JButton number8;
  private JButton number7;
  private JButton number9;
  private JButton addButton;
  private JButton number4;
  private JButton number1;
  private JButton number2;
  private JButton number5;
  private JButton number6;
  private JButton subtractButton;
  private JButton number3;
  private JButton multiplyButton;
  private JButton number0;
  private JButton equalsButton;
  private JButton divideButton;
  private JButton ansButton;
  private JButton deleteButton;
  private JSlider precisionSlider;
  private JPanel mainPanel;
  private JTextArea inputArea;
  private JComboBox<String> modeBox;
  private JButton decimalButton;
  private JButton catalogButton;
  private JButton clearButton;
  private JButton leftParenButton;
  private JButton rightParenButton;
  private JSplitPane splitPane;
  private JTextArea outputArea;
  private JSplitPane deleteSplit;
  private JSplitPane sliderSplit;
  private JButton negButton;

  private Consumer<String> commandCallback;

  //None can be final as LoadedCalculator.form describes the layout, but they aren't added until the
  //pack method is called.

  public LoadedCalculatorView() {
    super("Loaded Calculator");

    this.setContentPane(mainPanel);
    this.setPreferredSize(new Dimension(530, 315));

    this.inputArea.setSelectedTextColor(null);

    this.number0.setActionCommand("0");
    this.number1.setActionCommand("1");
    this.number2.setActionCommand("2");
    this.number3.setActionCommand("3");
    this.number4.setActionCommand("4");
    this.number5.setActionCommand("5");
    this.number6.setActionCommand("6");
    this.number7.setActionCommand("7");
    this.number8.setActionCommand("8");
    this.number9.setActionCommand("9");
    this.leftParenButton.setActionCommand("(");
    this.rightParenButton.setActionCommand(")");
    this.addButton.setActionCommand("+");
    this.subtractButton.setActionCommand("-");
    this.multiplyButton.setActionCommand("×");
    this.divideButton.setActionCommand("÷");
    this.ansButton.setActionCommand("getAns");
    this.negButton.setActionCommand("(-)");

    this.number0.addActionListener(new TextButtonListener());
    this.number1.addActionListener(new TextButtonListener());
    this.number2.addActionListener(new TextButtonListener());
    this.number3.addActionListener(new TextButtonListener());
    this.number4.addActionListener(new TextButtonListener());
    this.number5.addActionListener(new TextButtonListener());
    this.number6.addActionListener(new TextButtonListener());
    this.number7.addActionListener(new TextButtonListener());
    this.number8.addActionListener(new TextButtonListener());
    this.number9.addActionListener(new TextButtonListener());
    this.negButton.addActionListener(new TextButtonListener());
    this.decimalButton.addActionListener(new TextButtonListener());
    leftParenButton.addActionListener(new TextButtonListener());
    rightParenButton.addActionListener(new TextButtonListener());
    this.deleteButton.addActionListener(e -> {
      String s = inputArea.getText();
      if (s.length() > 0) {
        inputArea.setText(s.substring(0, s.length() - 1));
      }
    });
    this.clearButton.addActionListener(e ->
        this.inputArea.setText(""));

    this.ansButton.addActionListener(e -> {
      inputArea.append("ANS");
    });

    //This is very complicated as each option requires a different action to be taken.
    this.catalogButton.addActionListener(e -> {
      Object[] possibilities = {"GCD", "LCM", "Prime", "Exponential", "Modulo", "Log", "Ln",
          "LogBaseN", "Sin",
          "Cos", "Tan", "Complex Addition", "Complex Subtraction", "Complex Multiplication",
          "Complex Division",
          "Complex Remainder", "Complex Norm", "exit", "Linear Combination", "Absolute Value"};
      Arrays.sort(possibilities);
      String s = (String) JOptionPane.showInputDialog(
          this,
          "Choose the operation you are looking for:",
          "Pick an Operation",
          JOptionPane.PLAIN_MESSAGE,
          null,
          possibilities,
          "");
      if (s == null) {
        s = "";
      }
      switch (s) {
        case "GCD": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 7,12)",
              "GCD",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "GCD(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("gcd " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "LCM": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 7,12)",
              "LCM",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "LCM(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("lcm " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Prime": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a positive integer: ",
              "Prime",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "Prime(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("prime " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Exponential": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two non-negative integers separated by a comma: (ex. 2,3) \n"
                  + "Entering 2,3 would return 2^3.",
              "Exponential",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "Exp(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("exp " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Modulo": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two integers separated by a comma: (ex. 15,4)\n"
                  + "Entering 15,4 would return 15 mod 4.",
              "Mod",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "Mod(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("mod " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "LogBaseN": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 2,8) \n"
                  + "Entering 2,8 would return log2(8) or log base 2 of 8.",
              "Log Base N",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "LogBaseN(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("logBaseN " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Log": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a positive integer: \n"
                  + "Returns the base 10 log.",
              "Log Base 10",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "log(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("log " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Ln": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a positive integer: "
                  + "\nReturns the natural log of the give number.",
              "Natural Log",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "ln(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("ln " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Sin": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex()).substring(0, 3);
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a value in " + mode + ": ",
              "Sin",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "sin(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("sin " + Utils.removeAllChar(params, ' ') + " " + mode);
          break;
        }
        case "Cos": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex()).substring(0, 3);
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a value in " + mode + ": ",
              "Cos",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cos(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cos " + Utils.removeAllChar(params, ' ') + " " + mode);
          break;
        }
        case "Tan": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex()).substring(0, 3);
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a value in " + mode + ": ",
              "Tan",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "tan(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("tan " + Utils.removeAllChar(params, ' ') + " " + mode);
          break;
        }
        case "Complex Addition": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two complex numbers separated by a comma: (ex. 3+4i,2-i)",
              "Complex Addition",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cAdd(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cAdd " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Complex Subtraction": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two complex numbers separated by a comma: (ex. 3+4i,2-i)",
              "Complex Subtraction",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cSub(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cSub " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Complex Multiplication": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two complex numbers separated by a comma: (ex. 3+4i,2-i)",
              "Complex Multiplication",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cMultiply(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cMultiply " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Complex Division": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two complex numbers separated by a comma: (ex. 3+4i,2-i)",
              "Complex Division",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cDivide(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cDivide " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Complex Remainder": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two complex numbers separated by a comma: (ex. 3+4i,2-i)",
              "Complex Remainder",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cRemainder(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cRemainder " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Complex Norm": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter one complex number: (ex. 3+4i)",
              "Complex Norm",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "cNorm(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("cNorm " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Linear Combination": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter three numbers separated by commas, with the first being between 0 and 1, inclusive: (ex. 0.5,1,10)\n"
                  + "Computes the linear combination with the given ratio between the other two values.\n"
                  + "Entering 0.5,0,10 would yield 5 as it is 50% of the way from 0->10.",
              "Linear Combination",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "linCom(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("linCom " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "Absolute Value": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a number: \n"
                  + "Entering 7 will return 7, while entering -6 will return 6.",
              "Absolute Value",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "abs(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(25 - str.length()));
          callbackHelper("abs " + Utils.removeAllChar(params, ' '));
          break;
        }
        case "exit": {
          return;
        }
        default:
      }
    });

    this.addButton.addActionListener(new TextButtonListener());
    this.subtractButton.addActionListener(new TextButtonListener());
    this.multiplyButton.addActionListener(new TextButtonListener());
    this.divideButton.addActionListener(new TextButtonListener());

    this.equalsButton.addActionListener(e -> {
      String equation = inputArea.getText();
      inputArea.setText("");
      outputArea.setText(equation);
      outputArea.append(" ".repeat(25 - equation.length()));
      callbackHelper("equal " + equation);
    });

    this.precisionSlider.addChangeListener(l ->
        callbackHelper("precision " + precisionSlider.getValue()));

    this.pack();
    this.setVisible(true);

    splitPane.setDividerLocation(splitPane.getWidth() / 2);
    splitPane.setBorder(new CompoundBorder());
    double d = (double) (64 * (deleteSplit.getWidth() / 2)) / 60;
    deleteSplit.setDividerLocation((int) d);
    deleteSplit.setBorder(new CompoundBorder());

  }

  @Override
  public void run() {
    this.setResizable(false);
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,
        error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }

  @Override
  public void acceptResult(String result) {
    if (result.contains("precision")) {
      Scanner s = new Scanner(result);
      s.next();
      int p = s.nextInt();
      this.precisionSlider.setValue(p);
      return;
    }
    if (result.endsWith(".0")) {
      result = result.substring(0, result.length() - 2);
    }
    String a = " ".repeat(25 - result.length());
    outputArea.append(a);
    outputArea.append(result);
  }

  class TextButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      inputArea.append(e.getActionCommand());
    }
  }

  private void callbackHelper(String cmd) {
    if (this.commandCallback != null) {
      commandCallback.accept(cmd);
    }
  }
}