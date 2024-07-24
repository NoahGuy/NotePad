package Notepad;

import Recherche.Cadre;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;

public class Fonctions {
    private CadreGUI cadre;
    protected String nomFichier;
    private String adresseFichier;
    private PanneauPrincipal panneauPrincipal;

    public Fonctions(CadreGUI cadre) {
        this.cadre = cadre;
        this.panneauPrincipal = cadre.getPanneauPrincipal();
    }

    public void nouveauFichier() {
        this.cadre.getPanneauPrincipal().getTextArea().setText("");
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
                cadre.getPanneauPrincipal().getTextArea().setText(content);
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
                fw.write(this.cadre.getPanneauPrincipal().getTextArea().getText());
                this.cadre.setTitle(this.nomFichier);
                fw.close();
            } catch (Exception var2) {
                Exception e = var2;
                System.out.println(e);
            }
        }

    }

    public void saveAs() {
        FileDialog fd = new FileDialog(this.cadre, "Sauvegarder en tant que", 1);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            this.nomFichier = fd.getFile();
            this.adresseFichier = fd.getDirectory();
            this.cadre.setTitle(this.nomFichier);
        }

        try {
            FileWriter fw = new FileWriter(this.adresseFichier + this.nomFichier);
            fw.write(this.cadre.getPanneauPrincipal().getTextArea().getText());
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
        Font currentFont = this.cadre.getPanneauPrincipal().getTextArea().getFont();
        if (currentFont.getSize() < 60) {
            float newSize = (float)currentFont.getSize() + 2.0F;
            int pourcentageAugmentation = (int)(100.0F * (newSize / 12.0F));
            this.cadre.getPanneauPrincipal().getTextArea().setFont(currentFont.deriveFont(newSize));
            this.cadre.getPanneauPrincipal().getBarreEtat().getZoom().setText(String.valueOf(pourcentageAugmentation) + "% ");
        }

    }

    public void zoomOut() {
        Font currentFont = this.cadre.getPanneauPrincipal().getTextArea().getFont();
        if (currentFont.getSize() > 2) {
            float newSize = (float)currentFont.getSize() - 2.0F;
            int pourcentageDiminution = (int)(100.0F * (newSize / 12.0F));
            this.cadre.getPanneauPrincipal().getTextArea().setFont(currentFont.deriveFont(newSize));
            this.cadre.getPanneauPrincipal().getBarreEtat().getZoom().setText(String.valueOf(pourcentageDiminution) + "% ");
        }

    }

    public void rechercher() {
        Cadre cadre = new Cadre("Rechercher/Remplacer");
        SwingUtilities.invokeLater(cadre);
    }

    public void enleverRemettreBarreEtat() {
        if (this.cadre.getPanneauPrincipal().getBarreEtat().isVisible()) {
            this.cadre.getPanneauPrincipal().getBarreEtat().setVisible(false);
        } else {
            this.cadre.getPanneauPrincipal().getBarreEtat().setVisible(true);
        }

    }
}
