package View;

import javax.swing.*;

import Model.Case;
import Model.Jeu;
import Model.Tuile;

import java.awt.*;
import java.util.ArrayList;

public class JPanelJoueur extends JPanel {

    public String nom;
    public int id;
    Jeu model;
    Controleur controleur;

    public JPanel plancher;
    public JPanel lm_mur;
    public JPanel lm;
    public JPanel[] lignes;
    public JPanel mur;

    public JButton[][] Tuiles_Ligne;
    public JLabel[][] Tuiles_Mur;
    public JButton[] Tuiles_Plancher;
    public JLabel score;

    public JPanelJoueur(Jeu m, Controleur con, String nom, int id) {
        super(new GridBagLayout());
        super.setBorder(BorderFactory.createTitledBorder(nom));
        this.nom = nom;
        model = m;
        controleur = con;
        this.id = id;
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
        lignes = new JPanel[5];
        Tuiles_Ligne = new JButton[5][];
        for (int j = 0; j < 5; j++) {
            lignes[j] = new JPanel();
            lignes[j].setLayout(new GridLayout());
            Tuiles_Ligne[j] = new JButton[j+1];
            for (int k = 0; k <= j; k++) {
                int ligne = j;
                Tuiles_Ligne[j][k] = new JButton();
                Tuiles_Ligne[j][k].addActionListener((event) -> con.ajouterLigne(ligne, id));
                Tuiles_Ligne[j][k].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
                lignes[j].add(Tuiles_Ligne[j][k]);
            }
            lm.add(lignes[j]);
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
        Tuiles_Plancher = new JButton[7];
        for (int j = 0; j < 7; j++) {
            Tuiles_Plancher[j] = new JButton();
            Tuiles_Plancher[j].addActionListener((event) -> con.ajouterPlancher(id));
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

    public void MAJ_Mur() {
        Case[][] c = model.getJoueur(id).getPlateau().getMur().getCasesMur();
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 5; k++) {
                if(!c[i][k].isEmpty()) {
                    Tuiles_Mur[i][k].setIcon(new ImageIcon("projet\\src\\img\\"+c[i][k].getTuile()+".png"));
                }                
                else Tuiles_Ligne[i][k].setIcon(null);
            }
        }
    }

    public void MAJ_LM() {
        Case[][] c = model.getJoueur(id).getPlateau().getLigneMotif().getLigne();
        for (int i = 0; i < c.length; i++) {
            for (int k = 0; k < c[i].length; k++) {
                if(!c[i][k].isEmpty()) Tuiles_Ligne[i][k].setIcon(new ImageIcon("projet\\src\\img\\"+c[i][k].getTuile().getColor()+".png"));
                else Tuiles_Ligne[i][k].setIcon(null);
            }
        }
    }

    public void MAJ_Plancher() {
        for (int i = 0; i < model.getJoueur(id).getPlateau().getPlancher().getPlancher().size(); i++) {
            ArrayList<Tuile> a = model.getJoueur(id).getPlateau().getPlancher().getPlancher();
            if(i<7) Tuiles_Plancher[i].setIcon(new ImageIcon("projet\\src\\img\\"+a.get(i).getColor()+".png"));
        }
    }

    public void MAJ_Score() {
        score.setText(model.getJoueur(id).getScore()+"");
    }

    public void MAJ_Joueur() {
        MAJ_Mur();
        MAJ_LM();
        MAJ_Plancher();
        MAJ_Score();
    }
    
}