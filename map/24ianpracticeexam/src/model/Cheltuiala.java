package model;

public class Cheltuiala implements HasId<Integer> {
    private Integer id;
    private TipCheltuiala tip;
    private float suma;
    private String desc;
    private User efecDe;

    public Cheltuiala(Integer id, TipCheltuiala tip, float suma, String desc, User efecDe) {
        this.id = id;
        this.tip = tip;
        this.suma = suma;
        this.desc = desc;
        this.efecDe = efecDe;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public TipCheltuiala getTip() {
        return tip;
    }

    public void setTip(TipCheltuiala tip) {
        this.tip = tip;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getEfecDe() {
        return efecDe;
    }

    public void setEfecDe(User efecDe) {
        this.efecDe = efecDe;
    }
}
