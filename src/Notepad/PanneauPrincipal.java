package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class PanneauPrincipal extends JPanel {
    private JTextPane textPane;
    private JScrollPane scroll;
    private BarreEtat barreEtat;

    public PanneauPrincipal() {

        initComposants();
    }

    private void initComposants() {

        setLayout(new BorderLayout());
        textPane = new JTextPane();
        scroll = new JScrollPane(textPane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, "Center");
        barreEtat = new BarreEtat(textPane);
        add(barreEtat, "South");

       // SimpleAttributeSet.SetFontStyle(..., "monospaced");
    }

    public JTextPane getTextPane() {

        return textPane;
    }


    public BarreEtat getBarreEtat() {

        return barreEtat;
    }
}
