package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public abstract class Task {

    private String taskId;
    private String descriere;

    public Task(String taskId, String descriere) {
        this.taskId = taskId;
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "taskId='" + taskId + '\'' +
                ", descriere='" + descriere + '\'';
    }

    public abstract void execute();

    public String getTaskId() {
        return taskId;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
