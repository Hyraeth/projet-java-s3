package View;

import java.awt.*;

import javax.swing.*;

import Model.Plateau;

public class Vue_joueur extends JPanel {
    public Plateau model;
    public JPanel plateau;
    public JPanel lm_mur;
    public JPanel plancher_score;
    public JLabel tuile;
    public JLabel score;

    public Vue_joueur(Plateau m) {
        this.setLayout(new GridBagLayout());
        score = new JLabel("score");
        score.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.8;
        c.gridy = 0;
        lm_mur = new JPanel(new GridLayout(1,2));
        lm_mur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        this.add(lm_mur, c);//ligne motif et mur
        c.weighty = 0.2;
        c.gridy = 1;
        JPanel plancher_score = new JPanel(new GridLayout(1,8));
        for (int i = 0; i < 7; i++) {
            JLabel emplacement = new JLabel();
            emplacement.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
            plancher_score.add(emplacement);
        }
        plancher_score.add(score);
        plancher_score.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
    }
}