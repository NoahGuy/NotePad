package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class PanneauPrincipal extends JPanel {
    private JTextPaneCtrlFYZ textPane;
    private JScrollPane scroll;


    public PanneauPrincipal(CadreGUI cadreGUI) {

        initComposants();
    }

    private void initComposants() {

        setLayout(new BorderLayout());



       // SimpleAttributeSet.SetFontStyle(..., "monospaced");
    }

}
