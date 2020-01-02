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
    public JPanel zoneFabrique;
    public JPanel zoneJoueurs;

    public JPanel[] joueurs;
    public JPanel[] fabriques;
    public JLabel[] centre;

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
            joueurs[i] = new JPanel(new GridBagLayout());
            joueurs[i].setBorder(BorderFactory.createTitledBorder("Joueur N°" + i));
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 0.8;
            c.gridy = 0;

            //ligne motif et mur

            JPanel lm_mur = new JPanel(new GridLayout(1, 2));
            lm_mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            JPanel lm = new JPanel(new GridLayout(5, 1));
            lm.setBorder(BorderFactory.createTitledBorder("ligne motif"));
            JPanel mur = new JPanel(new GridLayout(5, 5));
            mur.setBorder(BorderFactory.createTitledBorder("mur"));
            for (int j = 0; j < 25; j++) {
                JLabel jl;
                if (j == 0 || j == 6 || j == 12 || j == 18 || j == 24)
                    jl = new JLabel("bleu");
                else if (j == 1 || j == 7 || j == 13 || j == 19 || j == 20)
                    jl = new JLabel("jaune");
                else if (j == 2 || j == 8 || j == 14 || j == 15 || j == 21)
                    jl = new JLabel("rouge");
                else if (j == 3 || j == 9 || j == 10 || j == 16 || j == 22)
                    jl = new JLabel("noir");
                else
                    jl = new JLabel("blanc");
                jl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                mur.add(jl);
            }
            lm_mur.add(lm);
            lm_mur.add(mur);
            joueurs[i].add(lm_mur, c);


            //plancher et score
            c.weighty = 0.2;
            c.gridy = 1;
            JPanel plancher = new JPanel(new GridLayout(1, 8));
            for (int j = 0; j < 7; j++) {
                JLabel tuile = new JLabel(j + "");
                if (j < 2)
                    tuile.setBorder(BorderFactory.createTitledBorder("-" + 1));
                else if (j > 4)
                    tuile.setBorder(BorderFactory.createTitledBorder("-" + 3));
                else
                    tuile.setBorder(BorderFactory.createTitledBorder("-" + 2));
                plancher.add(tuile);
            }
            JLabel score = new JLabel("score");
            score.setBorder(BorderFactory.createTitledBorder("score"));
            plancher.add(score);
            plancher.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            joueurs[i].add(plancher, c);// plancher
        }

        // Remplissage du centre

        for (int i = 0; i < m.getCentre().getContenuCentre().size(); i++) {
            ImageIcon image = new ImageIcon("projet\\src\\img\\"+m.getCentre().getTuileCentre(i)+".png");
            zoneCentre.add(new JLabel(image));
        }

        //Remplissage des fabriques

        for (int i=0; i<fabriques.length; i++) {
            fabriques[i] = new JPanel(new GridLayout(2,2));
            for (Case c : m.getFabrique(i).getFabrique()) {
                ImageIcon image = new ImageIcon("projet\\src\\img\\"+c.getTuile().getColor()+".png");
                JLabel tuile = new JLabel(image);
                tuile.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                if(!c.isEmpty()) fabriques[i].add(tuile);
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

    public static void main(String[] args) {
        ArrayList<String> nom = new ArrayList<>();
        nom.add("pipi");
        nom.add("popo");
        nom.add("pupu");
        Jeu j = new Jeu(nom, 0);
        Vue v = new Vue(j, new Controleur());
    }

}