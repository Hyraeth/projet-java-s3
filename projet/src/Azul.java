import java.awt.EventQueue;
import java.util.ArrayList;

import Model.Jeu;
import View.Controleur;
import View.Vue;

public class Azul {
    public static void main(String[] args) {
        ArrayList<String> nom = new ArrayList<>();
        nom.add("pipi");
        nom.add("popo");
        nom.add("pupu");
        nom.add("pepe");
        EventQueue.invokeLater(() -> 
        {
            Jeu j = new Jeu(nom, 0);
            Controleur c = new Controleur(j);
            Vue v = new Vue(j, c);
            c.vue=v;
        });
        
    }
}