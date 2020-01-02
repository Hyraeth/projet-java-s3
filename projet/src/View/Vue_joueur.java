package View;

import java.awt.*;

import javax.swing.*;

import Model.Plateau;

public class Vue_joueur extends JPanel {
    public Plateau model;
    public JPanel plateau;
    public JPanel lm_mur;
    public JPanel lm;
    public JPanel mur;
    public JPanel plancher_score;
    public JPanel plancher;
    public JLabel tuile;
    public JLabel score;

    public Vue_joueur(Plateau m) {
        this.setLayout(new GridBagLayout());
        score = new JLabel("score");
        plancher = new JPanel(new GridLayout(1,7));
        lm = new JPanel(new GridLayout(5,1));
        mur = new JPanel(new GridLayout(5,5));
        lm.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        plancher.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        score.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.8;
        c.gridy = 0;

        lm_mur = new JPanel(new GridLayout(1,2));
        lm_mur.add(lm);
        lm_mur.add(mur);
        lm_mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));

        c.weighty = 0.2;
        c.gridy = 1;

        JPanel plancher_score = new JPanel(new GridLayout(1,8));

        plancher_score.add(plancher);
        plancher_score.add(score);
        plancher_score.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        this.add(lm_mur, c);//ligne motif et mur
        this.add(plancher_score);
    }
}