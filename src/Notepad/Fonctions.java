package Notepad;

import Recherche.Cadre;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.text.BadLocationException;
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
    private String chaineARemplacer;
    private int dernierePositionTrouve;
    private JRadioButton directionArriere;
    private int positionCurseurCourante;

    public Fonctions(CadreGUI cadre, JTextPane textPane, BarreEtat barreEtat) {

        this.textPane = textPane;

        this.barreEtat = barreEtat;
        this.cadre = cadre;
    }

    public void nouveauFichier() {
        textPane.setText("");
        cadre.setTitle("Nouvelle page");
        nomFichier = null;
        adresseFichier = null;
    }

    public void ouvrirFichier() {

        FileDialog fileDialog = new FileDialog((Frame) null, "Select a File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (fileName != null && directory != null) {

            cadre.setTitle(fileName);
            nomFichier = fileName;
            File file = new File(directory, fileName);
            try {

                String content = new String(Files.readAllBytes(file.toPath()));
                textPane.setText(content);

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(null, "Error reading the file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void save() {
        if (nomFichier == null) {

            saveAs();
        } else {

            try {

                FileWriter fw = new FileWriter(adresseFichier + nomFichier);
                fw.write(textPane.getText());
                cadre.setTitle(nomFichier);
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

            nomFichier = fd.getFile();
            adresseFichier = fd.getDirectory();
            cadre.setTitle(this.nomFichier);
        }

        try {

            FileWriter fw = new FileWriter(adresseFichier + nomFichier);
            fw.write(textPane.getText());
            fw.close();
        } catch (Exception var3) {

            Exception e = var3;
            System.out.println(e);
        }

    }

    public void quitter() {

        int option = JOptionPane.showConfirmDialog(cadre,
                "Voulez-vous quitter et sauvegarder ?",
                "Quitter",
                JOptionPane.YES_NO_CANCEL_OPTION);

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

            float newSize = (float) currentFont.getSize() + 2.0F;

            int pourcentageAugmentation = calculerPourcentageZoom(newSize);

            textPane.setFont(currentFont.deriveFont(newSize));
            barreEtat.getZoom().setText(String.valueOf(pourcentageAugmentation) + "% ");
        }

    }

    public void zoomOut() {

        Font currentFont = textPane.getFont();

        if (currentFont.getSize() > MIN_FONT_SIZE) {

            float newSize = (float) currentFont.getSize() - 2.0F;

            int pourcentageDiminution = calculerPourcentageZoom(newSize);

            textPane.setFont(currentFont.deriveFont(newSize));
            barreEtat.getZoom().setText(String.valueOf(pourcentageDiminution) + "% ");
        }

    }

    private int calculerPourcentageZoom(float newSize) {

        return (int) (100.0F * (newSize / 12.0F));
    }

    public void ouvrirPanneauRecherche(Fonctions fonctions) {

        Cadre cadre = new Cadre("Rechercher/Remplacer", fonctions);
        SwingUtilities.invokeLater(cadre);
    }

    public void enleverRemettreBarreEtat() {

        barreEtat.setVisible(!barreEtat.isVisible());
    }

    public void rechercher(String chaineARechercher, JCheckBox caseSensibleCasse, JRadioButton directionArriere) {

        this.directionArriere = directionArriere;
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

        if (positionCurseur != positionCurseurCourante) {
            dernierePositionTrouve = positionCurseur;
            positionCurseurCourante = positionCurseur;
        }


        if (dernierePositionTrouve == 0) {

            dernierePositionTrouve = positionCurseur;
        }
        //dernierePositionTrouve = positionCurseur;

        // apres une nouvelle recherche, on remet le texte à son style par
        // defaut
        enleverSurlignage();

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

        if (!directionArriere.isSelected()) {

            // cherche la position de l'occurence après le curseur
            int index = texte.indexOf(chaineARechercher, dernierePositionTrouve);

            // si on retrouve plus l'occurence on cherche à partir du premier
            // caractère
            if (index < 0) {

                dernierePositionTrouve = 0;
                index = texte.indexOf(chaineARechercher, dernierePositionTrouve);
            }

            // on cherche la prochaine occurence et on incrémente la position
            // de l'index
            if (index >= 0) {

                doc.setCharacterAttributes(index, chaineARechercher.length(), vert, true);
                dernierePositionTrouve = index + chaineARechercher.length();
            }
        } else {

            // cherche la derniere occurence avant le curseur
            int index = texte.lastIndexOf(chaineARechercher, dernierePositionTrouve);

            // si aucune occurence est trouvee avant le curseur, on cherche la derniere occurence de tout le texte
            if (index < 0) {

                dernierePositionTrouve = texte.length();
                index = texte.lastIndexOf(chaineARechercher, dernierePositionTrouve);
            }

            // on cherche l'occurence precedente et on decremente la position de l'index
            if (index >= 0) {

                doc.setCharacterAttributes(index, chaineARechercher.length(), vert, true);
                dernierePositionTrouve = index - chaineARechercher.length();
            }
        }

    }

    private void surlignerEnJaune() {

        // on transforme le texte ecris en String et remplace les \n par du
        // vide (car doc.setCharacterAttributes ne les detecte pas)
        texte = texte.replace("\n", "");

        int index = 0;

        // La boucle continue tant qu'une occurrence du mot recherché est
        // trouvee
        while ((index = texte.indexOf(chaineARechercher, index)) >= 0) {

            // on surligne le caractère recherchee
            doc.setCharacterAttributes(index, chaineARechercher.length(), jaune, true);

            // on passe à prochaine occurence
            index += chaineARechercher.length()-1;

        }
    }

    public void setDernierePositionTrouve(int dernierePositionTrouve) {

        this.dernierePositionTrouve = dernierePositionTrouve;
    }

    public void enleverSurlignage() {

        doc.setCharacterAttributes(0, doc.getLength(), defaut, true);
    }

    /**
     * Remplace tout les occurence par la chaine de caractère à remplacer
     *
     * @param chaineARechercher
     * @param chaineARemplacer
     * @param caseSensibleCasse
     * @param directionArriere
     */
    public void remplacer(String chaineARechercher,
                                    String chaineARemplacer,
                                    JCheckBox caseSensibleCasse,
                                    JRadioButton directionArriere) {

        texte = textPane.getText();

        this.chaineARechercher = chaineARechercher;
        this.chaineARemplacer = chaineARemplacer;
        this.directionArriere = directionArriere;

        // pour les case sensible
        if (!caseSensibleCasse.isSelected()) {

            chaineARechercher = chaineARechercher.toLowerCase();
            texte = texte.toLowerCase();
        }


        try {

            remplacerTout();
        } catch (BadLocationException ex) {

            throw new RuntimeException(ex);
        }


    }


    /**
     * fonction qui permet la recherche de tout les occurence et remplace par
     * une autre chaine de caractere
     *
     * @throws BadLocationException
     */
    private void remplacerTout() throws BadLocationException {


        // on transforme le texte ecris en String et remplace les \n par du
        // vide (car doc.setCharacterAttributes ne les detecte pas)
        texte = texte.replace("\n","");

        // l'index représente la position où ce trouve
        // "chaineCaratereRecherchee"
        int index = texte.indexOf(chaineARechercher);




        // on cherche les occurences du mot rechercher
        // (chaineCaratereRecherchee)
        while (index >= 0) {

            // on eneleve les occurence du mot rechercher
            // (chaineCaratereRecherchee) à partir de leur index
            doc.remove(index,chaineARechercher.length());

            // on insert la chaine de caractere "chaineCaratereRemplacement"
            // à partir de l'index
            doc.insertString(index,chaineARemplacer, null);


            // on va à la position de l'occurence suivante
            index = texte.indexOf(chaineARechercher, index +
                                                    chaineARemplacer.length());


        }

    }

    /**
     *
     * permet de remplacer une seul occurence et surligne en vert l'occurence
     * suivante
     *
     * @param chaineARechercher
     * @param chaineARemplacer
     * @param caseSensibleCasse
     * @param directionArriere
     */
    public void remplacerOccurrenceSurligneeEnVert(String chaineARechercher,
                                                   String chaineARemplacer,
                                                   JCheckBox caseSensibleCasse,
                                                  JRadioButton directionArriere)
    {
        this.chaineARechercher = chaineARechercher;
        this.chaineARemplacer = chaineARemplacer;
        this.directionArriere = directionArriere;
        this.texte = textPane.getText();


        // verifie l'option de la case sensible
        if (!caseSensibleCasse.isSelected()) {
            chaineARechercher = chaineARechercher.toLowerCase();
            texte = texte.toLowerCase();
        }

        try {

            texte = texte.replace("\n", "");

            // on prend l'index de l'occurence
            int index = chercheProchaineOccurence();


            if (index >= 0) {

                // on remplace l'occurence par la chaine de caractere a
                // remplacer
                doc.remove(index, chaineARechercher.length());
                doc.insertString(index, chaineARemplacer, defaut);

            }

        } catch (BadLocationException ex) {

            throw new RuntimeException(ex);
        }
    }

    /**
     * Cherche l'index de l'occurence selon la selection de la direction
     *
     * @return l'index de l'occurence
     */
    private int chercheProchaineOccurence() {

        return directionArriere.isSelected() ?
                texte.indexOf(chaineARechercher, dernierePositionTrouve)
                : texte.lastIndexOf(chaineARechercher, dernierePositionTrouve);
    }

}
