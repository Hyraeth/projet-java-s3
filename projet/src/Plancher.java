public class Plancher {
    private int minus;
    private Case[] plancher;

    public Plancher() {
        minus = 0;
        plancher = new Case[7];
        for (int i = 0; i < plancher.length; i++) {
            plancher[i] = new Case();
        }
    }

    public int size() {
        int n = 7;
        for (Case c : plancher) {
           if(c.isEmpty()) n--; 
        }
        return n;
    }

    public void refreshMinus() {
        int s = size();
        if(s <= 2) minus = 1;
        if(s > 5) minus = 3;
        else minus = 2;
    }

    public void add(int n, Tuile t) {
        if(n>7-size()) {
            for (Case c : plancher) {
                if(c.isEmpty()) c.addTuile(t);
            }
            Defausse.ajouterTuile(n-7, t);
        } else {
            for (Case c : plancher) {
                if(c.isEmpty()) c.addTuile(t);
            }  
        }
        refreshMinus();
    }
}