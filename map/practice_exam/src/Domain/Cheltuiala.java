package Domain;

import java.util.Date;

public class Cheltuiala {
    private TipCheltuiala tip;
    private float suma;
    private String desc;
    private String efectuataDe;
    private Date data;

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

    public String getEfectuataDe() {
        return efectuataDe;
    }

    public void setEfectuataDe(String efectuataDe) {
        this.efectuataDe = efectuataDe;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
