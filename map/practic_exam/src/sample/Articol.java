package sample;

public class Articol {
    private String cod;
    private Domeniu domeniu;
    private String titlu;
    private String autori;
    private String keywords;

    public Articol(String cod, Domeniu domeniu, String titlu, String autori, String keywords) {
        this.cod = cod;
        this.domeniu = domeniu;
        this.titlu = titlu;
        this.autori = autori;
        this.keywords = keywords;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Domeniu getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(Domeniu domeniu) {
        this.domeniu = domeniu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public String getAutori() {
        return autori;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return cod + "\n" +
                titlu + "\n" +
                domeniu + "\n" +
                autori + "\n" +
                keywords + "\n";
    }
}
