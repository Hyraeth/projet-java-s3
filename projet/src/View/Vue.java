package View;

import javax.swing.*;

import Model.Jeu;

import java.awt.*;

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
        zoneFabrique = new JPanel();
        zoneJoueurs = new JPanel();

        joueurs = new JPanel[nbjoueurs];
        fabriques = new JPanel[nbjoueurs*2+1];
        for (JPanel jPanel : joueurs) {
            jPanel = new JPanel();
        }
        for (JPanel jPanel : fabriques) {
            jPanel = new JPanel();
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
        c.weighty = 0.2;
        c.gridy = 0;
        j.add(zoneCentre, c);
        c.weighty = 0.2;
        c.gridy = 1;
        j.add(zoneFabrique, c);
        c.weighty = 0.6;
        c.gridy = 2;
        j.add(zoneJoueurs, c);

        //disposition des joueurs
        zoneJoueurs.setLayout(new GridLayout(1,2));
        if(nbjoueurs >= 3) zoneJoueurs.setLayout(new GridLayout(2,2));

        for (JPanel jPanel : joueurs) {
            zoneJoueurs.add(jPanel);
        }

        for (JPanel jPanel : fabriques) {
            zoneFabrique.add(jPanel);
        }
    }

}