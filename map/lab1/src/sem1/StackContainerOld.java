package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public class StackContainerOld implements Container {

    private Task tasks[] = new  Task[10];
    private int size = 0;

    @Override
    public Task remove() {
        if(size==0) return null;
        size--;
        return tasks[size];
    }

    private void resize(){
        Task tasksAux[] = new Task[tasks.length*2];
        System.arraycopy(tasks,0,tasksAux,0,size);
        tasks = tasksAux;

    }

    @Override
    public void add(Task task) {
        if(size == tasks.length) resize();
        tasks[this.size] = task;
        this.size++;
    }

    @Override
    public int size() {
       return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
