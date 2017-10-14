package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public class StrategyTaskRunner implements TaskRunner {
    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        this.container = new TaskContainerFactory().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        Task task = container.remove();
        if(task != null)
            task.execute();
    }

    @Override
    public void executeAll() {
        while(hasTask())
        {
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
