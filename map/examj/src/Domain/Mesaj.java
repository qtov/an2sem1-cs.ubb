package Domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Mesaj implements HasId<String> {
    private String id;
    private String user;
    private String mesaj;
    private LocalDateTime data;
    private String toWho;

    public Mesaj(String user, String mesaj, LocalDateTime data, String toWho) {
        id = user + data;
        this.user = user;
        this.mesaj = mesaj;
        this.data = data;
        this.toWho = toWho;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        id = s;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    @Override
    public String toString() {
        return user + ";" + mesaj + ";" + data + ";" + toWho;
    }

}
