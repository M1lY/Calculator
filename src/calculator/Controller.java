package calculator;


import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Controller {
    public TextField input;
    public Text output;

    public void typing() {
        input.setText(input.getText().replace("k", ""));
        input.setText(input.getText().replace("p", ""));
        input.setText(input.getText().replace("c", ""));
        input.positionCaret(input.getLength());
        String x = input.getText();
        if (x.isEmpty()) {
            output.setText("");
            return;
        }
        String operation = input.getText();
        operation = operation.replaceAll(",", ".");
        operation = operation.replaceAll("√", "sqrt");
        operation = operation.replaceAll("²", "^2");
        try {
            Expression e = new ExpressionBuilder(operation).build();
            output.setText(String.valueOf(e.evaluate()));
        } catch (IllegalArgumentException ex) {
            output.setText("Invalid operation");
        }
    }

    public void key(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (output.getText().equals("Invalid operation")) return;
            input.setText(output.getText());
            input.positionCaret(input.getLength());
            output.setText("");
        }
        if (keyEvent.getCode() == KeyCode.P) {
            input.setText("\u221A(" + input.getText() + ")");
        }
        if (keyEvent.getCode() == KeyCode.K) {
            input.setText("(" + input.getText() + ")\u00B2");
        }
        if (keyEvent.getCode() == KeyCode.C) {
            input.clear();
            output.setText("");
        }
    }
}

