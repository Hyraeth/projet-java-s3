package View;

import Model.Jeu;
import Model.Joueur;
import Model.Tuile;
import Model.ZoneCommune;

public class Controleur {

    public Jeu model;
    public Vue vue;
    public LastPicked mem;

    public Controleur(Jeu j) {
        model = j;
    }

    public void sauvegarder(Tuile t, ZoneCommune zc) {
        if(t != null)
        mem = new LastPicked(t, zc);
    }

    public void ajouterLigne(int ligne, int id) {
        if(!mem.isEmpty()) {
            model.getJoueur(id).ajouterTuile(mem.origine, mem.tuile, ligne);
            model.foutre_au_centre();
        }
        //bug mur le plateau de chaque joueur n'a pas été mise a jour
        if(model.mancheFinie()) {
            model.nouvelleManche();
            System.out.println(model.getJoueur(0));
            vue.GROSSE_MAJ();
        }
        vue.GROSSE_MAJ();
        mem.clear();
    }

    public void ajouterPlancher(int id) {
        if(!mem.isEmpty()) {
            model.getJoueur(id).ajouterTuilePlancher(mem.origine, mem.tuile);
            model.foutre_au_centre();
        }
        //bug mur le plateau de chaque joueur n'a pas été mise a jour
        if(model.mancheFinie()) {
            model.nouvelleManche();
            System.out.println(model.getJoueur(0));
            vue.GROSSE_MAJ();
        }
        if(model.partieFinie()) {
            System.out.println(model.getGagnant());
        }
        vue.GROSSE_MAJ();
        mem.clear();
    }
}