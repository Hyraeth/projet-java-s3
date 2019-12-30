package Project;

public class Plateau {
    private Case[] plancher;
    private Case[][] ligne_motif;
    private Case[][] mur;

    public Plateau() {
        plancher = new Case[7];
        for (int i=0; i<plancher.length; i++) {
            plancher[i] = new Case();
        }

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

        mur = new Case[5][5];
        for (int i=0; i<mur.length; i++) {
            for (int j = 0; j < mur[i].length; j++) {  
                mur[i][j] = new Case();
            }
        }
    }
}