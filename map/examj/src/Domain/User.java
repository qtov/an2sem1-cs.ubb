package Domain;

public class User implements HasId<String> {
    private String nume;
    private Rol rol;
    private Stare stare;

    public User(String nume, Rol rol) {
        this.nume = nume;
        this.rol = rol;
        this.stare = Stare.ACTIV;
    }

    @Override
    public String getId() {
        return nume;
    }

    @Override
    public void setId(String s) {
        this.nume = s;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", rol=" + rol +
                ", stare=" + stare +
                '}';
    }
}
