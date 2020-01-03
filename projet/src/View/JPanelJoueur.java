package View;

import javax.swing.*;

import Model.Case;
import Model.Jeu;
import Model.Joueur;
import Model.Tuile;

import java.awt.*;
import java.util.ArrayList;

public class JPanelJoueur extends JPanel {

    public JPanel plancher;
    public JPanel lm_mur;
    public JPanel lm;
    public JPanel mur;

    public JLabel[][] Tuiles_Mur;
    public JLabel[] Tuiles_Plancher;
    public JLabel score;

    public JPanelJoueur(Jeu m, Controleur con, String nom) {
        super(new GridBagLayout());
        super.setBorder(BorderFactory.createTitledBorder(nom));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.8;
        c.gridy = 0;

        //ligne motif et mur

        lm_mur = new JPanel(new GridLayout(1, 2));
        lm_mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        lm = new JPanel(new GridLayout(5, 1));
        lm.setBorder(BorderFactory.createTitledBorder("Lignes motif"));
        mur = new JPanel(new GridLayout(5, 5));
        mur.setBorder(BorderFactory.createTitledBorder("Mur"));

        //init ligne motif

        for (int j = 0; j < 5; j++) {
            JPanel ligne = new JPanel();
            ligne.setLayout(new GridLayout());
            for (int k = 0; k <= j; k++) {
                JLabel emplacement_tuile = new JLabel();
                emplacement_tuile.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                ligne.add(emplacement_tuile);
            }
            lm.add(ligne);
        }

        //Remplissage du mur de fausse icone
        Tuiles_Mur = new JLabel[5][5];
        for (int j = 0; j < 25; j++) {
            ImageIcon icon;
            if (j == 0 || j == 6 || j == 12 || j == 18 || j == 24)
                icon = new ImageIcon("projet\\src\\img\\bleufake.png");
            else if (j == 1 || j == 7 || j == 13 || j == 19 || j == 20)
                icon = new ImageIcon("projet\\src\\img\\jaunefake.png");
            else if (j == 2 || j == 8 || j == 14 || j == 15 || j == 21)
                icon = new ImageIcon("projet\\src\\img\\rougefake.png");
            else if (j == 3 || j == 9 || j == 10 || j == 16 || j == 22)
                icon = new ImageIcon("projet\\src\\img\\noirfake.png");
            else
                icon = new ImageIcon("projet\\src\\img\\blancfake.png");

            Tuiles_Mur[j%5][j%5] = new JLabel(icon);
            Tuiles_Mur[j%5][j%5].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            mur.add(Tuiles_Mur[j%5][j%5]);
        }

        //Ajout au lm_mur du lm et mur
        lm_mur.add(lm);
        lm_mur.add(mur);
        //Ajout dans la zone joueur du lm et mur
        super.add(lm_mur, c);


        //plancher et score
        c.weighty = 0.2;
        c.gridy = 1;
        plancher = new JPanel(new GridLayout(1, 8));
        Tuiles_Plancher = new JLabel[7];
        for (int j = 0; j < 7; j++) {
            Tuiles_Plancher[j] = new JLabel();
            if (j < 2)
                Tuiles_Plancher[j].setBorder(BorderFactory.createTitledBorder("-" + 1));
            else if (j > 4)
                Tuiles_Plancher[j].setBorder(BorderFactory.createTitledBorder("-" + 3));
            else
                Tuiles_Plancher[j].setBorder(BorderFactory.createTitledBorder("-" + 2));
            plancher.add(Tuiles_Plancher[j]);
        }
        //ajout du score dans la zone plancher du joueur
        score = new JLabel(0+"");
        score.setBorder(BorderFactory.createTitledBorder("score"));
        plancher.add(score);
        plancher.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        super.add(plancher, c);// plancher
    }

    public void MAJ_Mur(Joueur j) {
        Case[][] c = j.getPlateau().getMur().getCasesMur();
        for (int i = 0; i < c.length; i++) {
            for (int k = 0; k < c[i].length; k++) {
                if(!c[i][k].isEmpty()) Tuiles_Mur[i][k].setIcon(new ImageIcon("projet\\src\\img\\"+c[i][k].getTuile().getColor()+"ake.png"));
            }
        }
    }

    public void MAJ_LM() {

    }

    public void MAJ_Plancher(Joueur j) {
        for (int i = 0; i < j.getPlateau().getPlancher().getPlancher().size(); i++) {
            ArrayList<Tuile> a = j.getPlateau().getPlancher().getPlancher();
            if(i<7) Tuiles_Plancher[i].setIcon(new ImageIcon("projet\\src\\img\\"+a.get(i).getColor()+"fake.png"));
        }
    }

    public void MAJ_Score(Joueur j) {
        score.setText(j.getScore()+"");
    }
    
}