package Model;

import java.util.ArrayList;

public class Plancher {
    private int minus;
    private ArrayList<Tuile> plancher;
    public ArrayList<Tuile> getPlancher() { return plancher; }

    public Plancher() {
        minus = 0;
        plancher = new ArrayList<>();
    }

    public int size() {
        return plancher.size();
    }

    public void clear() {
        minus = 0;
        plancher.clear();
    }

    public void refreshMinus() {
        int s = size();
        if(s <= 2) minus = 1;
        else if(s > 5) minus = 3;
        else minus = 2;
    }

    public void add(int n, Tuile t) {
        for (int i = 0; i < n; i++) {
            plancher.add(t);
        }
        refreshMinus();
    }

    public String toString() {
        String s = "";
        for (Tuile case1 : plancher) {
            s += case1.toString()+";";
        }
        return s;
    }

    public void moveToDefausse() {
        for (Tuile tuile : plancher) {
            if(!(tuile instanceof PremierJoueur)) Defausse.ajouterTuile(tuile);
        }
        clear();
    }
}