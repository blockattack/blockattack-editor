package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelInterface;

public class MovePanel extends JPanel {

    private JButton b0, b1, b2, b3;
    private ModelInterface mi;
    private EditorPanel ep;

    public MovePanel(ModelInterface mi, EditorPanel ep) {
        this.mi = mi;
        this.ep = ep;
        setLayout(new GridLayout(2, 2));
        b0 = new JButton("Move down");
        add(b0);
        b1 = new JButton("Move up");
        add(b1);
        b2 = new JButton("Move left");
        add(b2);
        b3 = new JButton("Move right");
        add(b3);

        ButtonListener listener = new ButtonListener();

        b0.addActionListener(listener);
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            Object source = evt.getSource();

            if (source == b0) {
                mi.selectColor(8);
            }
            if (source == b1) {
                mi.selectColor(7);
            }
            if (source == b2) {
                mi.moveLeft();
            }
            if (source == b3) {
                mi.moveRight();
            }
            ep.repaint();
        }
    }
}
