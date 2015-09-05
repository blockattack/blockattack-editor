package gui;

import javax.swing.*;

import model.ModelInterface;

public class SidePanel extends JPanel {

    public SidePanel(ModelInterface mi, EditorPanel ep) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new FilePanel(mi, ep));
        add(new ColorPanel(mi, ep));
        add(new MovePanel(mi, ep));
    }
}
