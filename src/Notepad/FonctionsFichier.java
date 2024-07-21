package Notepad;

public class FonctionsFichier {
    private CadreGUI cadre;

    public FonctionsFichier(CadreGUI cadre) {
        this.cadre = cadre;
    }

    public void nouveauFichier() {
        cadre.getPanneauPrincipal().getText().setText("");
        cadre.setTitle("Nouvelle page");
    }

    // Ajouter les méthodes de sauvegarde ici
}
