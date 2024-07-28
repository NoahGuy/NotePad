package Notepad;

import Recherche.Cadre;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import static java.awt.FileDialog.SAVE;

public class Fonctions {
    private static final int MAX_FONT_SIZE = 60;
    private static final int MIN_FONT_SIZE = 2;
    private CadreGUI cadre;
    protected String nomFichier;
    private String adresseFichier;
    private JTextPane textPane;
    private BarreEtat barreEtat;

    //pour la recherche
    private SimpleAttributeSet rouge;
    private SimpleAttributeSet vert;
    private SimpleAttributeSet jaune;
    private SimpleAttributeSet defaut;
    private StyledDocument doc;
    private String texte;
    private String chaineARechercher;
    private int dernierePositionTrouve;

    public Fonctions(CadreGUI cadre, JTextPane textPane, BarreEtat barreEtat) {

        this.textPane = textPane;

        this.barreEtat = barreEtat;
        this.cadre = cadre;
    }

    public void nouveauFichier() {
        textPane.setText("");
        this.cadre.setTitle("Nouvelle page");
        this.nomFichier = null;
        this.adresseFichier = null;
    }

    public void ouvrirFichier() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select a File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (fileName != null && directory != null) {
            File file = new File(directory, fileName);
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textPane.setText(content);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public void save() {
        if (this.nomFichier == null) {
            this.saveAs();
        } else {
            try {
                FileWriter fw = new FileWriter(this.adresseFichier + this.nomFichier);
                fw.write(textPane.getText());
                this.cadre.setTitle(this.nomFichier);
                fw.close();
            } catch (Exception var2) {
                Exception e = var2;
                System.out.println(e);
            }
        }

    }

    public void saveAs() {

        FileDialog fd = new FileDialog(this.cadre, "Sauvegarder en tant que", SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            this.nomFichier = fd.getFile();
            this.adresseFichier = fd.getDirectory();
            this.cadre.setTitle(this.nomFichier);
        }

        try {
            FileWriter fw = new FileWriter(this.adresseFichier + this.nomFichier);
            fw.write(textPane.getText());
            fw.close();
        } catch (Exception var3) {
            Exception e = var3;
            System.out.println(e);
        }

    }

    public void quitter() {
        int option = JOptionPane.showConfirmDialog(cadre, "Voulez-vous quitter et sauvegarder ?", "Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            saveAs();
            System.exit(0);
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0);
        }

    }

    public void zoomIn() {

        Font currentFont = textPane.getFont();

        if (currentFont.getSize() < MAX_FONT_SIZE) {

            float newSize = (float)currentFont.getSize() + 2.0F;

            int pourcentageAugmentation = calculerPourcentageZoom(newSize);

            textPane.setFont(currentFont.deriveFont(newSize));
            barreEtat.getZoom().setText(String.valueOf(pourcentageAugmentation) + "% ");
        }

    }

    public void zoomOut() {

        Font currentFont = textPane.getFont();

        if (currentFont.getSize() > MIN_FONT_SIZE) {

            float newSize = (float)currentFont.getSize() - 2.0F;

            int pourcentageDiminution = calculerPourcentageZoom(newSize);

            textPane.setFont(currentFont.deriveFont(newSize));
            barreEtat.getZoom().setText(String.valueOf(pourcentageDiminution) + "% ");
        }

    }

    private int calculerPourcentageZoom(float newSize) {

        return (int)(100.0F * (newSize / 12.0F));
    }

    public void ouvrirPanneauRecherche(Fonctions fonctions) {

        Cadre cadre = new Cadre("Rechercher/Remplacer", fonctions);
        SwingUtilities.invokeLater(cadre);
    }

    public void enleverRemettreBarreEtat() {

        barreEtat.setVisible(!barreEtat.isVisible());
    }

    public void rechercher(String chaineARechercher, JCheckBox caseSensibleCasse) {

        this.chaineARechercher = chaineARechercher;
        // on prend Le texte qu'on a ecris dans le JTextPane en String
        //JTextPane leText = cadre.getPanneauPrincipal().getTextPane();
        texte = textPane.getText();
        doc = textPane.getStyledDocument();

        // le style pour surligner le JTextPane en rouge
        rouge = new SimpleAttributeSet();
        StyleConstants.setBackground(rouge, Color.RED);

        // le style pour surligner le JTextPane en vert
        vert = new SimpleAttributeSet();
        StyleConstants.setBackground(vert, Color.GREEN);

        // le style pour surligner le JTextPane en jaune
        jaune = new SimpleAttributeSet();
        StyleConstants.setBackground(jaune, Color.YELLOW);

        defaut = new SimpleAttributeSet();

        // on prend ce qu'on a ecris dans la barre de recherche en
        // String
        //chaineCaratereRecherchee = barRecherche.getText();

        // on obtient la position du curseur actuel
        int positionCurseur = textPane.getCaretPosition();
        dernierePositionTrouve = positionCurseur;

        // apres une nouvelle recherche, on remet le texte à son style par defaut
        doc.setCharacterAttributes(0, doc.getLength(), defaut, true);





        // si le check box pour case sensitive n'est pas coché alors
        // on transforme le text et la chaine de caractère recherchee
        // tout en minuscule
        if (!caseSensibleCasse.isSelected()) {

            chaineARechercher = chaineARechercher.toLowerCase();
            texte = texte.toLowerCase();
        }
        // on surligne en jaune tous les occurences
        surlignerEnJaune();

        // on surligne en vert selon la position du curseur
        surlignerEnVert();

        // on surligne en Rouge où se trouve le curseur
        doc.setCharacterAttributes(positionCurseur, 1, rouge, true);
    }

    private void surlignerEnVert() {

        // cherche la position de l'occurence après le curseur
        int index = texte.indexOf(chaineARechercher, dernierePositionTrouve);
        doc.setCharacterAttributes(index, chaineARechercher.length(), vert, true);

        // si on retrouve plus l'occurence on cherche à partir du premier
        // caractère
        if (index < 0){

            dernierePositionTrouve = 0;
            index = texte.indexOf(chaineARechercher, dernierePositionTrouve);
        }

        // sinon, on cherche la prochaine occurence et on incrémente la position
        // du curseur (dernierePositionTrouve)
        if (index >= 0) {

            doc.setCharacterAttributes(index, chaineARechercher.length(), vert, true);
            dernierePositionTrouve = index + chaineARechercher.length();
        }
    }

    private void surlignerEnJaune() {

        // on transforme le texte ecris en String et remplace les \n par du
        // vide (car doc.setCharacterAttributes ne les detecte pas)
        texte = texte.replace("\n","");

        int index = 0;

        // La boucle continue tant qu'une occurrence du mot recherché est
        // trouvee
        while ((index = texte.indexOf(chaineARechercher, index)) >= 0) {

            // on surligne le caractère recherchee
            doc.setCharacterAttributes(index, chaineARechercher.length(), jaune, true);

            // on passe à prochaine occurence
            index += chaineARechercher.length();

        }
    }
}
