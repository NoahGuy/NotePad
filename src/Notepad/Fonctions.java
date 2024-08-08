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

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’.</p>
 *
 * <p>Classe	: </p>
 *
 * <p>Desc		: </p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */

public class Fonctions {
    private static final int MAX_FONT_SIZE = 60;
    private static final int MIN_FONT_SIZE = 2;
    private CadreGUI cadre;
    protected String nomFichier;
    private String adresseFichier;
    private JTextPane textPane;
    private BarreEtat barreEtat;

    //pour la recherche

    // Attribut pour le surlignage
    private SimpleAttributeSet rouge;
    private SimpleAttributeSet vert;
    private SimpleAttributeSet jaune;
    private SimpleAttributeSet defaut;
    private StyledDocument doc;

    // Variable qui prend le Texte du JTextPane
    private String texte;

    // Text ecris des barre de recherche et barre pour le remplacement d
    private String chaineARechercher;
    private String chaineARemplacer;

    // Position du surlignage en vert
    private int dernierePositionTrouve;

    // Option de la recherche
    private JRadioButton directionArriere;

    // Position du Curseur courant
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

    /**
     * Fonction qui surligne en rouge où se situe le curseur et qui surligne
     * en jaune l'occurence de la chaine à rechercher et qui surligne en vert
     * l'occurence qui est situé après la position du curseur
     *
     * @param chaineARechercher qui est écrit dans le panneau de recherche
     *                          ("Trouver")
     * @param caseSensibleCasse un JCheckBox qu'on peut selecetionner pour la
     *                          recherche à case sensible
     * @param directionArriere  un JRadioButton qui demande vers quelle
     *                          direction l'utilisateur veut faire sa recherche
     */
    public void rechercher(String chaineARechercher,
                           JCheckBox caseSensibleCasse,
                           JRadioButton directionArriere) {

        // on prend l'option de la recherche
        this.directionArriere = directionArriere;

        // on prend la chaine de caractaire qu'on veut rechercher
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

        // le style par Defaut
        defaut = new SimpleAttributeSet();


        // on obtient la position du curseur actuel
        int positionCurseur = textPane.getCaretPosition();

        // on verifie si la position du curseur à changer
        if (positionCurseur != positionCurseurCourante) {

            // si c'est le cas alors on fait la recherche à partir de la
            // position courante du curseur
            dernierePositionTrouve = positionCurseur;

            // on change la valeur de la position du curseur courante
            positionCurseurCourante = positionCurseur;
        }

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

    /**
     *
     * Sous-programme qui permet au surlignage vert
     *
     */
    private void surlignerEnVert() {

        if (!directionArriere.isSelected()) {

            // cherche la position de l'occurence après le curseur
            int index = texte.indexOf(chaineARechercher, dernierePositionTrouve);

            // si on retrouve plus l'occurence on cherche à partir du premier
            // caractère
            if (!occurenceExiste(index)) {

                dernierePositionTrouve = 0;
                index = texte.indexOf(chaineARechercher, dernierePositionTrouve);
            }

            // on cherche la prochaine occurence et on incrémente la position
            // de l'index
            if (occurenceExiste(index)) {

                doc.setCharacterAttributes(index, chaineARechercher.length(), vert, true);
                dernierePositionTrouve = index + chaineARechercher.length();
            }
        } else {

            // cherche la derniere occurence avant le curseur
            int index = texte.lastIndexOf(chaineARechercher, dernierePositionTrouve);

            // si aucune occurence est trouvee avant le curseur, on cherche la
            // derniere occurence de tout le texte
            if (!occurenceExiste(index)) {

                dernierePositionTrouve = texte.length();
                
                index = texte.lastIndexOf(chaineARechercher,
                                            dernierePositionTrouve);
            }

            // on cherche l'occurence precedente et on decremente la position de
            // l'index
            if (occurenceExiste(index)) {

                doc.setCharacterAttributes(index, chaineARechercher.length(),
                                           vert, true);
                dernierePositionTrouve = index - chaineARechercher.length();
            }
        }

    }

    /**
     * Sous-programme qui surligne en jaune toutes les occurrences
     *
     */
    private void surlignerEnJaune() {

        // on transforme le texte ecris en String et remplace les \n par du
        // vide (car doc.setCharacterAttributes ne les detecte pas)
        texte = texte.replace("\n", "");

        // on initialise l'index à 0
        int index = 0;

        // La boucle continue tant qu'une occurrence du mot recherché est
        // trouvee
        while (occurenceExiste(index = texte.indexOf(chaineARechercher, index))){

            // on surligne le caractère recherchee
            doc.setCharacterAttributes(index, chaineARechercher.length(), jaune,
                                                                    true);

            // on passe à prochaine occurence
            index += chaineARechercher.length();

        }
    }

    /**
     * Fonction qui met qui la derniere position trouve à une certaine position
     *
     * @param position qu'on veut set à "dernierePositionTrouve"
     */
    public void setDernierePositionTrouve(int position) {

        this.dernierePositionTrouve = position;
    }

    /**
     * Fonction qui enleve tout les surlignage du JTextPane
     *
     */
    public void enleverSurlignage() {

        doc.setCharacterAttributes(0, doc.getLength(), defaut, true);
    }

    /**
     * Remplace toutes les occurence par la chaine de caractère à remplacer
     *
     * @param chaineARechercher qui est écrit dans le panneau de recherche
     *                          dans la barre de recherche ("Trouver")
     *
     * @param chaineARemplacer  qui est écrit dans le panneau de recherche dans
     *                          la barre de remplacement ("Remplacer")
     *
     * @param caseSensibleCasse un JCheckBox qu'on peut selecetionner pour la
     *                          recherche à case sensible
     *
     * @param directionArriere  un JRadioButton qui demande vers quelle
     *                          direction l'utilisateur veut faire sa recherche
     */
    public void remplacer(String chaineARechercher,
                                    String chaineARemplacer,
                                    JCheckBox caseSensibleCasse,
                                    JRadioButton directionArriere) {

        texte = textPane.getText();

        this.chaineARechercher = chaineARechercher;
        this.chaineARemplacer = chaineARemplacer;
        this.directionArriere = directionArriere;

        // On verifie que l'utilisateur à ecris dans la barre de remplacement
        if(chaineARemplacer != null){

            // pour les case sensible
            if (!caseSensibleCasse.isSelected()) {

                chaineARechercher = chaineARechercher.toLowerCase();
                texte = texte.toLowerCase();
            }


            try {

                // on remplace toutes les occurrences trouvées par
                // chaineARemplacer
                remplacerTout();
            } catch (BadLocationException ex) {

                throw new RuntimeException(ex);
            }
        }
    }


    /**
     * Fonction qui permet la recherche de tout les occurence et remplace par
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
        while (occurenceExiste(index)) {

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
     * Permet de remplacer une seule occurrence et surligne en vert l'occurrence
     * suivante
     *
     * @param chaineARechercher qui est écrit dans le panneau de recherche
     *                          dans la barre de recherche ("Trouver")
     *
     * @param chaineARemplacer  qui est écrit dans le panneau de recherche dans
     *                          la barre de remplacement ("Remplacer")
     *
     * @param caseSensibleCasse un JCheckBox qu'on peut selecetionner pour la
     *                          recherche à case sensible
     *
     * @param directionArriere  un JRadioButton qui demande vers quelle
     *                          direction l'utilisateur veut faire sa recherche
     */
    public void remplacerOccurrenceSurligneeEnVert(String chaineARechercher,
                                                   String chaineARemplacer,
                                                   JCheckBox caseSensibleCasse,
                                                  JRadioButton directionArriere)
    {
        this.chaineARechercher = chaineARechercher;
        this.chaineARemplacer = chaineARemplacer;
        this.directionArriere = directionArriere;
        texte = textPane.getText();


        // verifie l'option de la case sensible
        if (!caseSensibleCasse.isSelected()) {
            chaineARechercher = chaineARechercher.toLowerCase();
            texte = texte.toLowerCase();
        }

        try {

            // on remplacce les \n par le vide pour facilité la recherche
            texte = texte.replace("\n", "");

            // on prend l'index de l'occurence si elle existe sinon elle vaut -1
            int index = chercheProchaineOccurence();

            // on vérifie que l'occurrence existe
            if (occurenceExiste(index)) {

                // on remplace l'occurrence par la chaine de caractere à
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
     * @return l'index de l'occurence sinon retourne -1 si elle n'existe pas
     */
    private int chercheProchaineOccurence() {

        return directionArriere.isSelected() ?
                texte.indexOf(chaineARechercher, dernierePositionTrouve)
                : texte.lastIndexOf(chaineARechercher, dernierePositionTrouve);
    }

    /**
     * Sous-programme qui verifie si l'index de l'occurences existe
     *
     * @param index qu'on verifie
     * @return une boolean qui indique l'existance de l'occurence
     */
    private boolean occurenceExiste(int index){

        return index != -1;
    }



}
