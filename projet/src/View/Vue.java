package View;

import javax.swing.*;

import Model.Case;
import Model.Jeu;
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
    public JPanel[] joueurs;

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

        joueurs = new JPanel[nbjoueurs];
        fabriques = new JPanel[nbjoueurs * 2 + 1];

        // Creation des zones pour chaque joueur

        for (int i = 0; i < nbjoueurs; i++) {
            joueurs[i] = new JPanelJoueur(m, con, m.getJoueur(i).getNom());
        }

        //Fin joueur

        // Remplissage du centre
        Tuiles_centre = new ArrayList<>();
        for (int i = 0; i < m.getCentre().getContenuCentre().size(); i++) {
            Tuile color = m.getCentre().getTuileCentre(i);
            ImageIcon image = new ImageIcon("projet\\src\\img\\"+color+".png");
            JButton jl = new JButton(image);
            jl.addActionListener((event) -> controleur.eleverTuileCentre(color));
            Tuiles_centre.add(jl);
        }

        for (JButton JButton : Tuiles_centre) {
            zoneCentre.add(JButton);
        }

        //Remplissage des fabriques
        Tuiles_fabrique = new JButton[nbjoueurs * 2 + 1][4];
        for (int i = 0; i < Tuiles_fabrique.length; i++) {
            int j = 0;
            for (Case c : m.getFabrique(i).getFabrique()) {
                if(!c.isEmpty()) {
                    Tuile color = c.getTuile();
                    int numfab = i;
                    ImageIcon image = new ImageIcon("projet\\src\\img\\"+c.getTuile().getColor()+".png");
                    JButton tuile = new JButton(image);
                    tuile.addActionListener((event) -> controleur.enleverTuileFabrique(color, numfab));
                    tuile.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                    Tuiles_fabrique[i][j] = tuile;
                } else {
                    JButton tuile = new JButton();
                    tuile.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                    Tuiles_fabrique[i][j] = tuile;                
                }
                j++;
            }
        }

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
                    ImageIcon image = new ImageIcon("projet\\src\\img\\"+c.getTuile().getColor()+".png");
                    Tuiles_fabrique[i][j].setIcon(image);
                } else {
                    Tuiles_fabrique[i][j].setIcon(null);                
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
            jl.addActionListener((event) -> controleur.eleverTuileCentre(color));
            Tuiles_centre.add(jl);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> nom = new ArrayList<>();
        nom.add("pipi");
        nom.add("popo");
        nom.add("pupu");
        nom.add("pepe");
        Jeu j = new Jeu(nom, 0);
        Controleur c = new Controleur(j);
        Vue v = new Vue(j, c);
        c.vue=v;
    }

}