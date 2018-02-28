package model;

public class User implements HasId<String> {
    private String nume;
    private TipUser tip;
    private float vtf;

    public User(String nume, TipUser tip, float vtf) {
        this.nume = nume;
        this.tip = tip;
        this.vtf = vtf;
    }

    @Override
    public String getId() {
        return nume;
    }

    @Override
    public void setId(String id) {
        this.nume = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public TipUser getTip() {
        return tip;
    }

    public void setTip(TipUser tip) {
        this.tip = tip;
    }

    public float getVtf() {
        return vtf;
    }

    public void setVtf(float vtf) {
        this.vtf = vtf;
    }
}
