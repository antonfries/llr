package Gui;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class GuiProgress extends JFrame {
    public static final String TITEL = "Rechen-Operationen";
    public static final int WIDTH = 300;
    public static final int HEIGHT = 100;

    private JProgressBar jProgressBar;

    public GuiProgress(int progressLength) {
        setTitle(TITEL);
        setSize(WIDTH, HEIGHT);
        jProgressBar = new JProgressBar(0, progressLength);
        jProgressBar.setStringPainted(true);
        add(jProgressBar);
        setVisible(true);
    }

    public void setValueOfProgressBar(int value) {
        jProgressBar.setValue(value);
    }

    public void closeProgressBar() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
