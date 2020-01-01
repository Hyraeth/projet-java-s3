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
}