package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;

public class PanneauPrincipal extends JPanel {
    private JTextPane text;
    private JScrollPane scroll;
    private BarreEtat barreEtat;

    public PanneauPrincipal() {
        this.initComposants();
    }

    private void initComposants() {
        this.setLayout(new BorderLayout());
        this.text = new JTextPane();
        this.scroll = new JScrollPane(this.text, 20, 30);
        this.scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(this.scroll, "Center");
        this.barreEtat = new BarreEtat(this);
        this.add(this.barreEtat, "South");

       // SimpleAttributeSet.SetFontStyle(..., "monospaced");
    }

    public JTextPane getTextArea() {
        return this.text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

//    public void append(String s) {
//        this.text.append(s);
//    }

    public BarreEtat getBarreEtat() {
        return this.barreEtat;
    }
}
