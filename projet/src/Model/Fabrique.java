package Model;

public class Fabrique implements ZoneCommune {

    private Case[] fabrique;
    public Case[] getFabrique() { return fabrique;}

    public Fabrique() {
        fabrique = new Case[4];
        for (int i = 0; i < fabrique.length; i++) {
            fabrique[i] = new Case();
        }
    }

    public void remplir(Sac s) {
        if(this.isEmpty()) {
            for (Case c : fabrique) {
                c.addTuile(s.prendreTuile());
            }
        }
    }

    public void remove(Tuile t) {
        if(t != null) {
            for (Case case1 : fabrique) {
                if(case1.getTuile().equals(t)) case1.clear();
            }
        }
    }

    public boolean isEmpty() {
        for (Case c : fabrique) {
            if(!c.isEmpty()) return false;
        }
        return true;
    }

    public boolean Remplie() {
        for (Case c : fabrique) {
            if(c.isEmpty()) return false;
        }
        return true;
        
    }

    public void clear() {
        for (Case c : fabrique) {
            c.clear();
        }
    }

    public int count(Tuile t) {
        int c = 0;
        for (Case case1 : fabrique) {
            if(!case1.isEmpty() && case1.getTuile().equals(t)) c++;
        }
        return c;
    }

    public String toString() {
        String s = "";
        for (Case case1 : fabrique) {
            s+= case1.toString()+" ";
        }
        return s;
    }

}