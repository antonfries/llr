package Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class CloseAction extends AbstractAction {

    private JFrame jFrame;

    public CloseAction(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));

    }
}
