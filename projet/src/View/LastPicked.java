package View;

import Model.*;

public class LastPicked {
    public Tuile tuile;
    public int nombre;
    public ZoneCommune origine;

    public LastPicked(Tuile t, ZoneCommune zc) {
        tuile = t;
        nombre = zc.count(t);
        origine = zc;
    }

    public void clear() {
        tuile = null;
        nombre = 0;
        origine = null;
    }

    public boolean isEmpty() {
        if(tuile==null && nombre==0 && origine == null) return true;
        return false;
    }
}