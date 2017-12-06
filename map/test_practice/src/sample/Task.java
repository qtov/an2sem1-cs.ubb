package sample;

public class Task {
    private int id;
    private TaskE status;
    private String denumire;
    private String user;

    public Task(int id, TaskE status, String den, String user) {
        this.id = id;
        this.status = status;
        this.denumire = den;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskE getStatus() {
        return status;
    }

    public void setStatus(TaskE status) {
        this.status = status;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "" + id + " " + status + " " + denumire + " " + user;
    }
}
