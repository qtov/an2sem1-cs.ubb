package sem1;
import java.util.ArrayList;

public class TaskArray implements TaskArrayI {
    private ArrayList<Task> vectTask;

    TaskArray (ArrayList<Task> vectTaskParam) {
        this.vectTask = new ArrayList<Task>(vectTaskParam);
    }

    @Override
    public Task get(int pos) {
        return vectTask.get(pos);
    }

    @Override
    public void add(Task elem) {
        this.vectTask.add(elem);
    }

    @Override
    public void add(int index, Task elem) {
        this.vectTask.add(index, elem);
    }

    @Override
    public int size() {
        return this.vectTask.size();
    }

    @Override
    public void delete(int pos) {
        this.vectTask.remove(pos);
    }
}
