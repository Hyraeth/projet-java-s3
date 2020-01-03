package View;

import javax.swing.*;

import Model.Case;
import Model.Jeu;
import Model.Joueur;
import Model.Tuile;

import java.awt.*;
import java.util.ArrayList;

public class Vue extends JFrame {

    public Jeu model;
    public Controleur controleur;

    public JFrame j;

    public JPanel zoneCentre;
    public ArrayList<JButton> Tuiles_centre;

    public JPanel zoneFabrique;
    public JPanel[] fabriques;
    public JButton[][] Tuiles_fabrique;

    public JPanel zoneJoueurs;
    public JPanelJoueur[] joueurs;

    public Vue(Jeu m, Controleur con) {

        model = m;
        controleur = con;

        int nbjoueurs = model.getNombreJoueurs();
        // Initialisation
        j = new JFrame();
        zoneCentre = new JPanel();
        zoneCentre.setBorder(BorderFactory.createTitledBorder("Centre"));
        zoneFabrique = new JPanel();
        zoneJoueurs = new JPanel();

        joueurs = new JPanelJoueur[nbjoueurs];
        fabriques = new JPanel[nbjoueurs * 2 + 1];

        // Creation des zones pour chaque joueur

        for (int i = 0; i < nbjoueurs; i++) {
            joueurs[i] = new JPanelJoueur(m, con, m.getJoueur(i).getNom(), i);
        }

        //Fin joueur

        // Remplissage du centre
        Tuiles_centre = new ArrayList<>();
        for (int i = 0; i < m.getCentre().getContenuCentre().size(); i++) {
            Tuile color = m.getCentre().getTuileCentre(i);
            ImageIcon image = new ImageIcon("projet\\src\\img\\"+color+".png");
            JButton jl = new JButton(image);
            jl.addActionListener((event) -> controleur.sauvegarder(color, m.getCentre()));
            Tuiles_centre.add(jl);
        }

        for (JButton JButton : Tuiles_centre) {
            zoneCentre.add(JButton);
        }

        //Remplissage des fabriques
        Tuiles_fabrique = new JButton[nbjoueurs * 2 + 1][4];
        MAJ_Fabrique();

        for (int i=0; i<fabriques.length; i++) {
            fabriques[i] = new JPanel(new GridLayout(2,2));
            for (JButton JButton : Tuiles_fabrique[i]) {
                fabriques[i].add(JButton);
            }
            
            fabriques[i].setBorder(BorderFactory.createTitledBorder("Fabrique N°"+i));
        }

        //Parametre fenetre
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setTitle("Azul");
        j.setLayout(new GridBagLayout());
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Repartition des zones principales
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridy = 0;
        j.add(zoneCentre, c);
        c.weighty = 0.1;
        c.gridy = 1;
        j.add(zoneFabrique, c);
        c.weighty = 0.8;
        c.gridy = 2;
        j.add(zoneJoueurs, c);

        //disposition des joueurs
        zoneJoueurs.setLayout(new GridLayout(1,2));
        if(nbjoueurs >= 3) zoneJoueurs.setLayout(new GridLayout(2,2));    
        zoneFabrique.setLayout(new GridLayout(1, fabriques.length));  

        //ajout dans les zones
        for (JPanel jPanel : joueurs) {
            zoneJoueurs.add(jPanel);
        }

        for (JPanel jPanel : fabriques) {
            zoneFabrique.add(jPanel);
        }
    }

    public void MAJ_Fabrique() {
        for (int i = 0; i < Tuiles_fabrique.length; i++) {
            int j = 0;
            for (Case c : model.getFabrique(i).getFabrique()) {
                if(!c.isEmpty()) {
                    Tuile color = c.getTuile();
                    int numfab = i;
                    ImageIcon image = new ImageIcon("projet\\src\\img\\"+c.getTuile().getColor()+".png");
                    Tuiles_fabrique[i][j] = new JButton();
                    Tuiles_fabrique[i][j].setIcon(image);
                    Tuiles_fabrique[i][j].addActionListener((event) -> controleur.sauvegarder(color, model.getFabrique(numfab)));
                    Tuiles_fabrique[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                } else {
                    Tuiles_fabrique[i][j].setIcon(null);
                    Tuiles_fabrique[i][j].setEnabled(false);
                    Tuiles_fabrique[i][j].setBackground(Color.gray);
                    Tuiles_fabrique[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                }
                j++;
            }
        }
    }

    public void MAJ_Centre() {
        Tuiles_centre.clear();
        for (int i = 0; i < model.getCentre().getContenuCentre().size(); i++) {
            Tuile color = model.getCentre().getTuileCentre(i);
            ImageIcon image = new ImageIcon("projet\\src\\img\\"+color+".png");
            JButton jl = new JButton(image);
            jl.addActionListener((event) -> controleur.sauvegarder(color, model.getCentre()));
            Tuiles_centre.add(jl);
        }
    }

    public void MAJ() {
        MAJ_Centre();
        MAJ_Fabrique();
    }

    public void GROSSE_MAJ() {
        MAJ();
        for (JPanelJoueur jpj : joueurs) {
            jpj.MAJ_Joueur(jpj.id);
        }
    }

}