

package Notepad;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BarreEtat extends JPanel {
    private PanneauPrincipal panneauPrincipal;
    private JLabel zoom;
    private JLabel nbCaracteres;

    public BarreEtat(PanneauPrincipal panneauPrincipal) {
        this.setLayout(new BorderLayout());
        this.panneauPrincipal = panneauPrincipal;
        this.zoom = new JLabel("100% ");
        this.nbCaracteres = new JLabel(String.valueOf(" " + panneauPrincipal.getTextArea().getText().length()) + " caractères");
        this.add(this.nbCaracteres, "West");
        this.add(this.zoom, "East");
        panneauPrincipal.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                BarreEtat.this.updateNbCaracteres();
            }

            public void removeUpdate(DocumentEvent e) {
                BarreEtat.this.updateNbCaracteres();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void updateNbCaracteres() {
        int length = this.panneauPrincipal.getTextArea().getDocument().getLength();
        this.nbCaracteres.setText(" " + length + " caractères");
    }

    public JLabel getZoom() {
        return this.zoom;
    }
}
