package Main;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusTextField extends JTextField {
    {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                FocusTextField.this.select(0, getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                FocusTextField.this.select(0, 0);
            }
        });
    }

    public FocusTextField(int columns) {
        super(columns);
    }
}