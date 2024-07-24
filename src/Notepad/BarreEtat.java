package Notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class BarreEtat extends JPanel {

    private PanneauPrincipal panneauPrincipal;
    private JLabel zoom;
    private JLabel nbCaracteres;
    public BarreEtat(PanneauPrincipal panneauPrincipal) {

        setLayout(new BorderLayout());
        this.panneauPrincipal = panneauPrincipal;
        zoom = new JLabel("100% ");

        nbCaracteres = new JLabel(String.valueOf(" " + panneauPrincipal.getTextArea().getText().length()) +
                " caractères");

        add(nbCaracteres, BorderLayout.WEST);
        add(zoom, BorderLayout.EAST);

        panneauPrincipal.getTextArea().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //pas utilise
            }
        });
    }

    private void updateNbCaracteres() {

        int length = panneauPrincipal.getTextArea().getDocument().getLength();
        nbCaracteres.setText(" " + length + " caractères");
    }


    public JLabel getZoom() {

        return zoom;
    }
}
