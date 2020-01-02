package View;

import javax.swing.*;

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
        zoneCentre.setBackground(Color.blue);
        zoneFabrique = new JPanel();
        zoneFabrique.setBackground(Color.green);
        zoneJoueurs = new JPanel();
        zoneJoueurs.setBackground(Color.red);

        joueurs = new JPanel[nbjoueurs];
        fabriques = new JPanel[nbjoueurs*2+1];
        for (int i=0; i<nbjoueurs; i++) {
            joueurs[i] = new JPanel();
        }
        for (int i=0; i<fabriques.length; i++) {
            fabriques[i] = new JPanel();
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
        zoneFabrique.setLayout(new GridLayout(fabriques.length, 1));  
        
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