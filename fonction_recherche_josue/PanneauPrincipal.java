import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauPrincipal extends JPanel {


    private JTextPane leText;
    private JTextField barRecherche;
    private JButton btnRecherche;
    private SimpleAttributeSet rouge;
    private SimpleAttributeSet vert;
    private SimpleAttributeSet jaune;
    private SimpleAttributeSet defaut;
    private StyledDocument doc;
    private String texte;
    private String chaineCaratereRecherchee;
    private int dernierePositionTrouve;

    private JCheckBox caseSensibleCasse;
    private JCheckBox caseRechercheGeneralisee;



    public PanneauPrincipal() {

        initComposants();
    }

    /**
     * Initialisation des composants
     */
    private void initComposants() {

        // initialisation du espace de texte pour rechercher un mot
        barRecherche = new JTextField();
        barRecherche.setPreferredSize(new Dimension(200, 30));

        leText = new JTextPane();
        leText.setPreferredSize(new Dimension(400, 300));

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

        doc = leText.getStyledDocument();

        dernierePositionTrouve = 0;

        // initie un bouton pour rechercher la chaine de caractère
        btnRecherche = new JButton("Rechercher");

        caseSensibleCasse = new JCheckBox();
        caseSensibleCasse.setText("case sensible");


        /**
         * Bouton qui fait la recherche selon ce qui est écris sur le JTextField
         */
        btnRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // on prend Le texte qu'on a ecris dans le JTextPane en String
                texte = leText.getText();

                // on prend ce qu'on a ecris dans la barre de recherche en
                // String
                chaineCaratereRecherchee = barRecherche.getText();

                // on obtient la position du curseur actuel
                int positionCurseur = leText.getCaretPosition();

                // apres une nouvelle recherche, on remet le texte à son style par defaut
                doc.setCharacterAttributes(0, doc.getLength(), defaut, true);

                // on surligne en Rouge où se trouve le curseur
                doc.setCharacterAttributes(positionCurseur, 1, rouge, true);
                

                // si le check box pour case sensitive n'est pas coché alors
                // on transforme le text et la chaine de caractère recherchee
                // tout en minuscule
                if (!caseSensibleCasse.isSelected()) {

                    chaineCaratereRecherchee = chaineCaratereRecherchee.toLowerCase();
                    texte = texte.toLowerCase();
                }
                // on surligne en jaune tous les occurences
                surlignerEnJaune(chaineCaratereRecherchee);

                // on surligne en vert selon la position du curseur
                surlignerEnVert(chaineCaratereRecherchee);




            }
        });

        // ajoute les composants au panneau principal
        this.add(barRecherche);
        this.add(btnRecherche);
        this.add(leText);
        this.add(caseSensibleCasse);

    }

    /**<
     * Recherche la chaine de caractère
     *
     * @param motRechercher
     */
    private void surlignerEnJaune(String motRechercher){


        // on transforme le texte ecris en String et remplace les \n par du
        // vide (car doc.setCharacterAttributes ne les detecte pas)
        texte = texte.replace("\n","");

        int index = 0;

        // La boucle continue tant qu'une occurrence du mot recherché est
        // trouvee
        while ((index = texte.indexOf(motRechercher, index)) >= 0) {

            // on surligne le caractère recherchee
            doc.setCharacterAttributes(index, motRechercher.length(), jaune, true);

            // on passe à prochaine occurence
            index += motRechercher.length();

        }

    }

    /**
     * surligne dans le JTextPane en vert l'occurence après le curseur
     *
     * @param motRechercher
     */
    private void surlignerEnVert(String motRechercher){

        dernierePositionTrouve =
        // cherche la position de l'occurence après le curseur
        int index = texte.indexOf(motRechercher, dernierePositionTrouve);
        doc.setCharacterAttributes(index, motRechercher.length(), vert, true);

        // si on retrouve plus l'occurence on cherche à partir du premier
        // caractère
        if (index < 0){

            dernierePositionTrouve = 0;
            index = texte.indexOf(motRechercher, dernierePositionTrouve);
        }

        // sinon, on cherche la prochaine occurence et on incrémente la position
        // du curseur (dernierePositionTrouve)
        if (index >= 0) {

            doc.setCharacterAttributes(index, motRechercher.length(), vert, true);
            dernierePositionTrouve = index + motRechercher.length();
        }



    }





}