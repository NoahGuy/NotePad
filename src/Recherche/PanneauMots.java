package Recherche;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauMots</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve notre mot à remplacer et son
		          remplacement.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauMots extends JPanel{
	
	//Le titre de la barre de recherche du mot à remplacer
	private JLabel trouver;
	
	//Le titre de la barre de recherche du mot remplaçant
	private JLabel remplacer;
	
	//La barre de recherche dans laquelle l'utilisateur écrivera le mot
	//à remplacer
	private JComboBox saisieTrouver;
	
	//L'historique des mots à remplacer recherchés pas l'utilisateur (lié au 
	//JComboBox "saisieTrouver")
	private ArrayList<String> historiqueRecherche;
	
	//La barre de recherche dans laquelle l'utilisateur écrivera le mot
	//remplaçant
	private JComboBox saisieRemplacer;
	
	//L'historique des mots de remplacement recherchés pas l'utilisateur (lié au 
	//JComboBox "saisieRemplacer")
	private ArrayList<String> historiqueRemplace;
	
	//Panneau dans lequel se retrouve le JLabel trouver et le
	//JComboBox saisieTrouver
	private JPanel panneauTrouver;
	
	//Panneau dans lequel se retrouve le JLabel remplacer et le
	//JComboBox saisieRemplacer
	private JPanel panneauRemplacer;
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private JPanel panneauPrincipal;
	
	/**
	 * Construit le panneau de mots où on insère notre mot à remplacer et son
	 * remplacement.
	 *
	 * @param panneauPrincipal le panneau principal où on ajoute ce panneau.
	 */
	public PanneauMots(JPanel panneauPrincipal) {
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;
		
		// met box layout pour que les panneaux imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		// initialise les composants
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de mots en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {
		
		creerPanneau();
		
		creerEtiq();
		
		creerHistorique();
		creerSaisie();
		
		ajouterComposants();
		
		initialiserComposants();
		
	}
	
	/**
	 * Créé les deux panneaux enfants du panneau de mots. On utilise cette
	 * technique afin d'avoir l'affichage souhaitée; les panneaux sont un par 
	 * dessu l'autre (méthode setLayout) et les composants de chaque panneau
	 * sont un à côter de l'autre (affichage par défaut).
	 */
	private void creerPanneau() {
		
		panneauTrouver = nouveauPanneau(panneauTrouver);
		panneauRemplacer = nouveauPanneau(panneauRemplacer);
	}
	
	/**
	 * Construit un nouveau panneau et l'assigne à celui reçu en paramètre
	 *
	 * @param panneau le panneau auquel le panneau créé sera assigné.
	 * 
	 * @return un JPanel associé à celui reçu en paramètre.
	 */
	private JPanel nouveauPanneau(JPanel panneau) {
		
		panneau = new JPanel();
		
		return panneau;
	}
	
	/**
	 * Créé les deux étiquettes qui comporteront le titre des barres de 
	 * recherche de mot et de son remplaçant.
	 */
	private void creerEtiq() {
		
		trouver = nouvelleEtiq(trouver);
		remplacer = nouvelleEtiq(remplacer);
	}
	
	/**
	 * Construit une nouvelle étiquette et l'assigne à celle reçue en paramètre
	 *
	 * @param etiq l'étiquette à laquelle l'étiquette crée sera assignée.
	 * 
	 * @return un JLabel associé à celui reçu en paramètre.
	 */
	private JLabel nouvelleEtiq(JLabel etiq) {
		
		etiq = new JLabel();
		
		return etiq;
	}
	
	/**
	 * Créé les deux historiques qui comporteront respectivement
	 * l'historique des mots à remplacer recherchés par l'utilisateur 
	 * et l'historique des mots remplaçants recherchés par l'utilisateur.
	 */
	private void creerHistorique() {
		
		historiqueRecherche = nouvelHistorique(historiqueRecherche);
		
		historiqueRemplace = nouvelHistorique(historiqueRemplace);
	}
	
	/**
	 * Construit une nouvelle historique et l'assigne à celle reçue en paramètre.
	 * L'historique est une ArrayList<String> afin de pouvoir la modifier
	 * dynamiquement.
	 *
	 * @param historique l'historique à laquelle l'historique crée sera assignée.
	 * 
	 * @return une ArrayList<String> associée à celle reçue en paramètre.
	 */
	private ArrayList<String> nouvelHistorique(ArrayList<String> historique) {
		
		historique = new ArrayList<String>();
		
		return historique;
	}
	
	/**
	 * Créé les deux saisie qui comporteront respectivement le mot à remplacer
	 * recherché par l'utilisateur et son remplaçant. Elles comportent aussi
	 * une historique de recherche.
	 */
	private void creerSaisie() {
		
		//AJOUT D'UN BOUTON TEMPORAIRE À ENLEVER PLUS TARD!!!
		JButton btn = new JButton();
		btn.setText("temporaire");
		add(btn);
		
		saisieTrouver = nouvelleSaisie(saisieTrouver, historiqueRecherche, btn);
		saisieRemplacer = nouvelleSaisie(saisieRemplacer, historiqueRemplace, btn);
		
	}
	
	/**
	 * Construit une nouvelle saisie (JComboBox) et l'assigne à celle 
	 * reçue en paramètre
	 *
	 * @param saisie la saisie à laquelle la saisie crée sera assignée,
	 * @param historique l'historique des mots entrés par l'utilisateur dans
	 * 					 le passé.
	 * 
	 * @return un JComboBox associé à celui reçu en paramètre.
	 */
	private static JComboBox nouvelleSaisie(JComboBox<String> saisie, 
			ArrayList<String> historique, JButton btn) {
		
		//Comme JComboBox n'est pas compatible avec ArrayList, on se doit
		//d'utiliser un modèle (DefaultComboBoxModel) afin de transformer
		//l'ArrayList en String[].
		DefaultComboBoxModel<String> modele = 
				new DefaultComboBoxModel<>(historique.toArray(new String[0]));
		saisie = new JComboBox(modele);
		
		// on défini les dimensions voulus
		saisie.setPreferredSize(new Dimension(250, 40));
		
		// on rend possible la saisie de l'utilisateur
		saisie.setEditable(true);
		
		//On met l'écouteur dans une autre méthode, car on ne peut pas 
		//sauvegarder la saisie de l'utilisateur dans le même méthode que 
		//celle où on a créé le JComboBox. Autrement, le JComboBox devrait
		//être "final" ce qui n'est pas possible dans notre cas. C'est pour 
		//cette raison que la méthode activerHistorique est static.
		activerHistorique(saisie, historique, btn, modele);
		
		return saisie;
	}
	
	/**
	 * On enregistre la saisie de l'utilisateur dans l'historique lorsqu'il
	 * clique sur le bouton.
	 */
	private static void activerHistorique(JComboBox<String> saisie, 
			ArrayList<String> historique, JButton btn, 
			DefaultComboBoxModel<String> modele){
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//On sauvegarde la saisie de l'utilisateur dans 
            	//une chaîne de caractères
                String motSaisi = (String) saisie.getEditor().getItem();
                
                //On ne rajoute rien si l'utilisateur clique sur le bouton sans 
                //n'avoir rien saisi
                if (motSaisi != null) {
                	
                	//Si le mot saisi est déjà présent dans l'historique, on l'enlève
                	//et on le rajoute afin qu'il soit au sommet de la liste
                	if(historique.contains(motSaisi)) {
                		historique.remove(motSaisi);
                		modele.removeElement(motSaisi);
                	}
                	
                	//Quand on ajoute un mot à l'historique, on s'assure que celui-ci 
                	//se retrouve au sommet de la liste
                    historique.add(0, motSaisi); // Ajouter à l'ArrayList
                    modele.insertElementAt(motSaisi, 0); // Ajouter au modèle
                    
                   //JE NE SAIS PAS SI LA LIGNE SUIVANTE EST NÉCESSAIRE!!!
                   //saisie.setSelectedItem(newItem);
                }
            }
        });
	}
	
	/**
	 * On ajoute les étiquettes et les saisies à leur panneau respectif et
	 * on ajoute les panneaux créés au panneau de mots.
	 */
	private void ajouterComposants() {
		
		add(panneauTrouver);
		panneauTrouver.add(trouver);
		panneauTrouver.add(saisieTrouver);
		
		add(panneauRemplacer);
		panneauRemplacer.add(remplacer);
		panneauRemplacer.add(saisieRemplacer);
	}
	
	/**
	 * On ajoute le titre de chaque barre de saisie à leur étiquette 
	 * respective.
	 */
	private void initialiserComposants() {
		
		trouver.setText("Trouver:");
		
		remplacer.setText("Remplacer avec:");
	}
}
