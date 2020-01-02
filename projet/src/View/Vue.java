package View;

import javax.swing.*;

import Model.Case;
import Model.Jeu;

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


    public Vue(Jeu m, Controleur con) {

        model = m;
        controleur = con;

        int nbjoueurs = model.getNombreJoueurs();
        //Initialisation
        j = new JFrame();
        zoneCentre = new JPanel();
        zoneCentre.setBorder(BorderFactory.createTitledBorder("Centre"));
        zoneFabrique = new JPanel();
        zoneJoueurs = new JPanel();

        joueurs = new JPanel[nbjoueurs];
        fabriques = new JPanel[nbjoueurs*2+1];
        for (int i=0; i<nbjoueurs; i++) {
            joueurs[i] = new JPanel(new GridBagLayout());
            joueurs[i].setBorder(BorderFactory.createTitledBorder("Joueur N°"+i));
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 0.8;
            c.gridy = 0;
            JPanel lm_mur = new JPanel(new GridLayout(1,2));
            lm_mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            JPanel lm = new JPanel(new GridLayout(5,1));
            lm.setBorder(BorderFactory.createTitledBorder("ligne motif"));
            JPanel mur = new JPanel(new GridLayout(5,5));
            mur.setBorder(BorderFactory.createTitledBorder("mur"));
            lm_mur.add(lm);
            lm_mur.add(mur);
            joueurs[i].add(lm_mur, c);//ligne motif et mur
            c.weighty = 0.2;
            c.gridy = 1;
            JPanel plancher = new JPanel(new GridLayout(1,8));
            for (int j = 0; j < 7; j++) {
                JLabel tuile = new JLabel(j+"");
                if(j<2) tuile.setBorder(BorderFactory.createTitledBorder("-"+1));
                else if(j>4) tuile.setBorder(BorderFactory.createTitledBorder("-"+3));
                else tuile.setBorder(BorderFactory.createTitledBorder("-"+2));
                plancher.add(tuile);
            }
            JLabel score = new JLabel("score");
            score.setBorder(BorderFactory.createTitledBorder("score"));
            plancher.add(score);
            plancher.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            joueurs[i].add(plancher, c);//plancher
        }
        for (int i=0; i<fabriques.length; i++) {
            fabriques[i] = new JPanel(new GridLayout(2,2));
            for (Case c : m.getFabrique(i).getFabrique()) {
                JLabel tuile = new JLabel(c.getTuile().getColor());
                tuile.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                if(!c.isEmpty()) fabriques[i].add(tuile);
            }
            
            fabriques[i].setBorder(BorderFactory.createTitledBorder("Fabrique N°"+i));
        }

        //Parametre fenetre
        j.setSize(4000, 2000);
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
        Jeu j = new Jeu(nom, 0);
        Vue v = new Vue(j, new Controleur());
    }

}