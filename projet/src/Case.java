public class Case {

    private Tuile tuile;
    public Tuile getTuile() { return this.tuile; }

    public Case() {
        this.tuile = null;
    }

    public void addTuile(Tuile t) {
        this.tuile = t;
    }

    public void clear() {
        tuile = null;
    }

    public boolean isEmpty() {
        if(tuile == null) return true;
        return false;
    }

}