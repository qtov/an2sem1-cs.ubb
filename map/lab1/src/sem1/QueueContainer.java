package sem1;

import java.util.ArrayList;

public class QueueContainer implements Container {
    private TaskArray tasks = new TaskArray(new ArrayList<Task>());

    @Override
    public Task remove() {
        Task task = tasks.get(0);
        this.tasks.delete(0);
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
