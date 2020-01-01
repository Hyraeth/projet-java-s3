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
        if(!ligne_motif.isEmpty(ligne)) {
            Case[][] lm = ligne_motif.getLigne();
            if(!lm[ligne][0].getTuile().equals(t) || mur.contains(ligne, t)) return false;
            return true;
        }
        return true;
    }

    public boolean addTuile(int ligne, int nombre, Tuile t) {
        int size_prev = ligne_motif.size(ligne);
        if(ajoutable(ligne, t)) {
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
            if(b[i]) sc += mur.ajouter(ligne_motif.getColorLine(i), i);
        }
        return sc;
    }

    public String toString() {
        return ligne_motif.toString()+"\n"+plancher.toString()+"\n\n"+mur.toString();
    }

	public void addTuilePlancher(int n, Tuile t) {
        plancher.add(n, t);
	}

}