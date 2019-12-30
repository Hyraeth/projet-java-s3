package Project;

import java.util.*;

public class Jeu {

    private Sac sac;
    private Defausse defausse;
    private Fabrique[] fabriques;
    private Centre centre;
    private ArrayList<Joueur> joueurs;
    
    public Jeu(ArrayList<String> noms) {
        this.sac = new Sac();
        this.defausse = new Defausse();
        this.fabriques = new Fabrique[(noms.size())*2+1];
        for (int i = 0; i < fabriques.length; i++) {
            this.fabriques[i] = new Fabrique();
        }
        this.centre = new Centre();
        this.joueurs = new ArrayList<Joueur>();
        for (String nom : noms) {
            this.joueurs.add(new Joueur(nom));
        }
    }
}