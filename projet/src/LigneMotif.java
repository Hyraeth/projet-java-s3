public class LigneMotif {
    private Case[][] ligne_motif;

    public LigneMotif() {
        ligne_motif = new Case[5][];
        ligne_motif[0] = new Case[1];
        ligne_motif[1] = new Case[2];
        ligne_motif[2] = new Case[3];
        ligne_motif[3] = new Case[4];
        ligne_motif[4] = new Case[5];
        for (int i=0; i<ligne_motif.length; i++) {
            for (int j = 0; j < ligne_motif[i].length; j++) {  
                ligne_motif[i][j] = new Case();
            }
        }
    }

    public int size(int ligne) {
        int c = 0;
        for (Case cases : ligne_motif[ligne]) {
            if(!cases.isEmpty()) c++;
        }
        return c;
    }

    public boolean isEmpty(int ligne) {
        for (Case cases : ligne_motif[ligne]) {
            if(!cases.isEmpty()) return false;
        }
        return true;
    }

    public boolean ajoutable(int ligne, Tuile t) {
        if(!isEmpty(ligne)) {
            if(!ligne_motif[ligne][0].getTuile().equals(t)) return false;
            return true;
        }
        return true;
    }
    
    public boolean add(int ligne, int nombre, Tuile t) {
        if(ajoutable(ligne, t)) {
            int c = 0;
            for (int i = 0; i < ligne_motif[ligne].length; i++) {
                if(ligne_motif[ligne][i].isEmpty()) ligne_motif[ligne][i].addTuile(t);
                c++;
                if(c==nombre) break;
            }
            return true;
        } return false;
    }

    public String toString() {
        String s = "";
        for (Case[] cases : ligne_motif) {
            for (Case case1 : cases) {
                s += case1.toString()+ "|";
            }
            s+= "\n";
        }
        return s;
    }
}