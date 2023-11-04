import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton decimalButton, equalButton, deleteButton, clearButton;
    private JPanel panel;

    private double num1, num2, result;
    private String operator;

    private boolean isDecimalClicked = false;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);
        frame.setTitle("calculator");

        textField = new JTextField();
        textField.setBounds(30, 30, 240, 30);
        frame.add(textField);
	
        numberButtons = new JButton[10];
        functionButtons = new JButton[8];

        panel = new JPanel();
        panel.setBounds(30, 70, 240, 240);
        panel.setLayout(new GridLayout(5, 4));

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalButton;
        functionButtons[6] = deleteButton;
        functionButtons[7] = clearButton;

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().equals("Error")) {
                        textField.setText("");
                        isDecimalClicked = false;
                    }
                    textField.setText(textField.getText().concat(e.getActionCommand()));
                }
            });
        }

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if (command.equals("+")) {
                        handleArithmeticOperation("+");
                    } else if (command.equals("-")) {
                        handleArithmeticOperation("-");
                    } else if (command.equals("*")) {
                        handleArithmeticOperation("*");
                    } else if (command.equals("/")) {
                        handleArithmeticOperation("/");
                    } else if (command.equals("=")) {
                        handleEqualButton();
                    } else if (command.equals("Clear")) {
                        textField.setText("");
                        isDecimalClicked = false;
                    } else if (command.equals("Delete")) {
                        if (textField.getText().equals("Error")) {
                            textField.setText("");
                            isDecimalClicked = false;
                        } else {
                            String currentText = textField.getText();
                            if (currentText.length() > 0) {
                                textField.setText(currentText.substring(0, currentText.length() - 1));
                                if (currentText.charAt(currentText.length() - 1) == '.') {
                                    isDecimalClicked = false;
                                }
                            }
                        }
                    } else if (command.equals(".")) {
                        handleDecimalButton();
                    }
                }
            });
        }

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalButton);
        panel.add(divideButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        frame.add(panel);
        frame.setVisible(true);
	frame.setLocationRelativeTo(null);	
    }

    private void handleArithmeticOperation(String operation) {
        if (textField.getText().isEmpty()) {
            showErrorMessage("Input cannot be empty");
        } else if (!isInputValid()) {
            showErrorMessage("Invalid input");
        } else if (operation.equals("/") && Double.parseDouble(textField.getText()) == 0) {
            showErrorMessage("Division by zero is not allowed");
        } else {
            num1 = Double.parseDouble(textField.getText());
            operator = operation;
            textField.setText("");
            isDecimalClicked = false;
        }
    }

    private void handleEqualButton() {
        if (textField.getText().isEmpty()) {
            showErrorMessage("Input cannot be empty");
        } else if (!isInputValid()) {
            showErrorMessage("Invalid input ");
        } else {
            num2 = Double.parseDouble(textField.getText());
            
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            
            textField.setText(String.valueOf(result));
            isDecimalClicked = false;
        }
    }

    private void handleDecimalButton() {
        if (!isDecimalClicked) {
            if (textField.getText().isEmpty()) {
                textField.setText("0.");
            } else {
                textField.setText(textField.getText().concat("."));
            }
            isDecimalClicked = true;
        }
    }

    private boolean isInputValid() {
        try {
            Double.parseDouble(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showErrorMessage(String message) {
        textField.setText("Error ");
        JOptionPane.showMessageDialog(frame, message, "Error ", JOptionPane.ERROR_MESSAGE);
    }

}


