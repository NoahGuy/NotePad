

package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BarreEtat extends JPanel {

    private JLabel zoom;
    private JLabel nbCaracteres;
    private CustomJTextPane textPane;

    public BarreEtat(CustomJTextPane textPane) {

        setLayout(new BorderLayout());

        this.textPane = textPane;

        zoom = new JLabel("100% ");
        nbCaracteres = new JLabel(String.valueOf(" " + textPane.getText().length()) + " caractères");

        add(this.nbCaracteres, "West");
        add(this.zoom, "East");

        textPane.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            public void removeUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            public void changedUpdate(DocumentEvent e) {

                // pas utilisé
            }
        });
    }

    private void updateNbCaracteres() {

        int length = textPane.getDocument().getLength();
        nbCaracteres.setText(" " + length + " caractères");
    }

    public JLabel getZoom() {

        return zoom;
    }
}
