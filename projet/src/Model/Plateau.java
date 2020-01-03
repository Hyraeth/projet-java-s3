package Model;

import java.util.ArrayList;

public class Plateau {
    private Plancher plancher;
    public Plancher getPlancher() { return plancher; }
    private LigneMotif ligne_motif;
    public LigneMotif getLigneMotif() { return ligne_motif; }
    private Mur mur;
    public Mur getMur() { return this.mur; }

    public Plateau() {
        plancher = new Plancher();

        ligne_motif = new LigneMotif();

        mur = new Mur();
    }

    public boolean ajoutable(int ligne, Tuile t) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("bleu");
        colors.add("jaune");
        colors.add("rouge");
        colors.add("noir");
        colors.add("blanc");
        if(ligne_motif.isRemplie(ligne)) return false;
        if(!colors.contains(t.getColor())) return false;
        if(mur.contains(ligne, t)) return false;
        if(!ligne_motif.isEmpty(ligne)) {
            Case[][] lm = ligne_motif.getLigne();
            if(!lm[ligne][0].getTuile().equals(t)) return false;
            return true;
        }
        return true;
    }

    public boolean addTuile(int ligne, int nombre, Tuile t) {
        int size_prev = ligne_motif.size(ligne);
        if(ajoutable(ligne, t) && !(t.getColor().equals("Premier Joueur"))) {
            ligne_motif.add(ligne, nombre, t);
            int size_after = ligne_motif.size(ligne);
            plancher.add(nombre-(size_after-size_prev), t);
            return true;
        }
        return false;
    }

    public int updateWall() {
        int sc = 0;
        boolean[] b = ligne_motif.lignesRemplies();
        for (int i = 0; i < b.length; i++) {
            System.out.println("ligne remple?");
            if(ligne_motif.isRemplie(i)) {
                System.out.println(i+" cette ligne est remplie");
                sc += mur.ajouter(ligne_motif.getColorLine(i), i);
            }
        }
        return sc;
    }

    public String toString() {
        return "Ligne motif :\n"+ligne_motif.toString()+"\nPlancher\n"+plancher.toString()+"\nMur\n"+mur.toString();
    }

	public void addTuilePlancher(int n, Tuile t) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("bleu");
        colors.add("jaune");
        colors.add("rouge");
        colors.add("noir");
        colors.add("blanc");
        colors.add("Premier joueur");
        if(!colors.contains(t.getColor())) return;
        plancher.add(n, t);
	}

	public void moveToDefausse() {
        ligne_motif.moveToDefausse();
        plancher.moveToDefausse();
	}

}