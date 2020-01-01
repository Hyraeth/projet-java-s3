public class Mur {
    private Case[][] mur;

    public Mur() {
        mur = new Case[5][5];
        for (int i=0; i<mur.length; i++) {
            for (int j = 0; j < mur[i].length; j++) {  
                mur[i][j] = new Case();
            }
        }
    }

    public String toString() {
        String s = "";
        for (Case[] cases : mur) {
            for (Case case1 : cases) {
                s += case1.toString() + "|";
            }
            s += "\n";
        }
        return s;
    }

    public boolean ligneRemplie() {
        for (Case[] cases : mur) {
            boolean rempli = true;
            for (Case case1 : cases) {
                if(case1.isEmpty()) rempli = false;
            }
            if(rempli) return true;
        }
        return false;
    }

    public boolean contains(int ligne, Tuile t) {
        for (Case cases : mur[ligne]) {
            if(!cases.isEmpty() && cases.getTuile().equals(t)) return true;
        }
        return false;
    }

    public int comptePoints(int i, int j) {
        int pts = 0;
        for (int l = 0; l < mur.length; l++) {
            if(!mur[i][l].isEmpty()) pts++;
        }
        for (int k = 0; k < mur.length; k++) {
            if(!mur[k][j].isEmpty()) pts++;
        }
        return pts;
    }

    public int ajouter(Tuile t, int ligne) {
        int pts = 0;
        switch (t.getColor()) {
            case "bleu":
                mur[ligne][ligne].addTuile(t);
                pts = comptePoints(ligne, ligne);
                break;
            case "jaune":
                mur[ligne][(ligne+1)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+1)%5);
                break;
            case "rouge":
                mur[ligne][(ligne+2)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+1)%5);
                break;
            case "noir":
                mur[ligne][(ligne+3)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+1)%5);
                break;
            case "blanc":
                mur[ligne][(ligne+4)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+1)%5);
                break;
            default:
                break;
        }
        return pts;
    }
}