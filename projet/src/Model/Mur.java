package Model;

public class Mur {
    private Case[][] mur;
    public Case[][] getCasesMur() {
        return mur;
    }

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

    public int compteHori(int i, int j) {
        int n = 0;
        for (int k = i+1; k < 5; k++) {
            if(mur[k][j].isEmpty()) break;
            n++;
        }
        for (int k = i-1; k >= 0; k--) {
            if(mur[k][j].isEmpty()) break;
            n++;
        }
        return n;
    }

    public int compteVerti(int i, int j) {
        int n = 0;
        for (int k = j+1; k < 5; k++) {
            if(mur[i][k].isEmpty()) break;
            n++;
        }
        for (int k = j-1; k >= 0; k--) {
            if(mur[i][k].isEmpty()) break;
            n++;
        }
        return n;
    }

    public int comptePoints(int i, int j) {
        return compteHori(i, j) + compteVerti(i, j) + 1;
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
                pts = comptePoints(ligne, (ligne+2)%5);
                break;
            case "noir":
                mur[ligne][(ligne+3)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+3)%5);
                break;
            case "blanc":
                mur[ligne][(ligne+4)%5].addTuile(t);
                pts = comptePoints(ligne, (ligne+4)%5);
                break;
        }
        return pts;
    }
}