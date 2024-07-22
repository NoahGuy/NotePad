package Notepad;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JPanel {
    private JTextArea text;
    private JScrollPane scroll;

    public PanneauPrincipal() {
        initComposants();
    }

    private void initComposants() {
        setLayout(new BorderLayout());
        text = new JTextArea();
        scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, BorderLayout.CENTER);
    }

    public JTextArea getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void append(String s) {
        text.append(s);
    }
}
