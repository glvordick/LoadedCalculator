package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LoadedCalculatorView extends JFrame implements ILoadedCalculatorView {

  private JButton number8; //
  private JButton number7; //
  private JButton number9; //
  private JButton addButton; //
  private JButton number4; //
  private JButton number1; //
  private JButton number2; //
  private JButton number5; //
  private JButton number6; //
  private JButton subtractButton; //
  private JButton number3; //
  private JButton multiplyButton; //
  private JButton number0; //
  private JButton equalsButton; //
  private JButton divideButton; //
  private JButton ansButton; //
  private JButton deleteButton; //
  private JSlider precisionSlider; //
  private JPanel mainPanel; //
  private JTextArea inputArea; //
  private JComboBox<String> modeBox; //
  private JButton decimalButton; //
  private JButton catalogButton; //
  private JButton commaButton; //
  private JButton leftParenButton; //
  private JButton rightParenButton; //
  private JSplitPane splitPane; //
  private JTextArea outputArea; //

  private Consumer<String> commandCallback;

  public LoadedCalculatorView() {
    super("Calculator");
    this.setContentPane(mainPanel);
    this.setPreferredSize(new Dimension(500, 320));

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
    this.commaButton.addActionListener(new TextButtonListener());
    this.decimalButton.addActionListener(new TextButtonListener());
    leftParenButton.addActionListener(new TextButtonListener());
    rightParenButton.addActionListener(new TextButtonListener());
    this.deleteButton.addActionListener(e -> {
      String s = inputArea.getText();
      if (s.length() > 0) {
        inputArea.setText(s.substring(0, s.length() - 1));
      }
    });

    this.ansButton.addActionListener(new ActionButtonListener());
    this.modeBox.addActionListener(e ->
        callbackHelper(modeBox.getItemAt(modeBox.getSelectedIndex())));

    this.catalogButton.addActionListener(e -> {
      Object[] possibilities = {"GCD", "LCM", "Prime", "Exponential", "Modulo", "log", "ln",
          "LogBaseN", "Sin",
          "Cos", "Tan", "complexAdd", "complexSubtract", "complexMultiply", "complexDivide",
          "complexRemainder", "complexNorm", "Exit"};
      String s = (String) JOptionPane.showInputDialog(
          this,
          "Choose the operation you are looking for:",
          "Pick an Operation",
          JOptionPane.PLAIN_MESSAGE,
          null,
          possibilities,
          "GCD");
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("gcd " + params);
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("lcm " + params);
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("prime " + params);
          break;
        }
        case "Exponential": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 7,12)",
              "Exponential",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "Exp(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("exp " + params);
          break;
        }
        case "Modulo": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 7,12)",
              "Mod",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "Mod(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("mod " + params);
          break;
        }
        case "LogBaseN": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter two positive integers separated by a comma: (ex. 7,12)",
              "Log Base N",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "LogBaseN(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("logBaseN " + params);
          break;
        }
        case "log": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a positive integer: ",
              "Log Base 10",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "log(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("log " + params);
          break;
        }
        case "ln": {
          String params = (String) JOptionPane.showInputDialog(
              this,
              "Please enter a positive integer: ",
              "Natural Log",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
          String str = "ln(" + params + ")";
          outputArea.setText(str);
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("ln " + params);
          break;
        }
        case "Sin": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex());
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("sin " + params + " " + mode);
          break;
        }
        case "Cos": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex());
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cos " + params + " " + mode);
          break;
        }
        case "Tan": {
          String mode = modeBox.getItemAt(modeBox.getSelectedIndex());
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("tan " + params + " " + mode);
          break;
        }
        case "complexAdd": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cAdd " + params);
          break;
        }
        case "complexSubtract": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cSub " + params);
          break;
        }
        case "complexMultiply": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cMultiply " + params);
          break;
        }
        case "complexDivide": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cDivide " + params);
          break;
        }
        case "complexRemainder": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cRemainder " + params);
          break;
        }
        case "complexNorm": {
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
          outputArea.append(" ".repeat(40-str.length()));
          callbackHelper("cNorm " + params);
          break;
        }
        case "Exit": {
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
      outputArea.append(" ".repeat(40 - equation.length()));
      callbackHelper("equal " + equation);
    });

    this.precisionSlider.addChangeListener(l -> {
      callbackHelper("precision " + precisionSlider.getValue());
    });

    this.pack();
    this.setVisible(true);

    splitPane.setDividerLocation(splitPane.getWidth() / 2);
    splitPane.setBorder(new CompoundBorder());

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
    String a = " ".repeat(40 - result.length());
    outputArea.append(a);
    outputArea.append(result);
  }

  @Override
  public void acceptAnswer(String answer) {
    inputArea.append(answer);
  }

  class TextButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      inputArea.append(e.getActionCommand());
    }
  }

  class ActionButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      callbackHelper(actionEvent.getActionCommand());
    }
  }

  private void callbackHelper(String cmd) {
    if (this.commandCallback != null) {
      commandCallback.accept(cmd);
    }
  }
}

