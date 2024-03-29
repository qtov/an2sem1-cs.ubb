package sem1;

import java.util.ArrayList;

/**
 * Created by camelia on 10/5/2017.
 */
public class StackContainer implements Container {
    private TaskArray tasks = new TaskArray(new ArrayList<Task>());

    @Override
    public Task remove() {
        Task task = tasks.get(tasks.size() - 1);
        this.tasks.delete(tasks.size() - 1);
        return task;
    }

    @Override
    public void add(Task task) {
        this.tasks.add(task);
    }

    @Override
    public int size() {
       return this.tasks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }
}
