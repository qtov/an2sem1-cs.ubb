package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public class MessageTask extends Task{

    private String mesaj;

    public MessageTask(String taskId, String descriere, String mesaj) {
        super(taskId, descriere);
        this.mesaj = mesaj;
    }

    @Override
    public void execute() {
        System.out.println(mesaj);
    }

    @Override
    public String toString() {
        return super.toString() + " " + mesaj;
    }
}
