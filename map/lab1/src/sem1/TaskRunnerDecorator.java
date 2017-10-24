package sem1;

public abstract class TaskRunnerDecorator implements TaskRunner {
    protected TaskRunner decoratedTaskRunner; //decoratedTaskRunner

    public TaskRunnerDecorator(TaskRunner runner) {
        this.decoratedTaskRunner = runner;
    }

    @Override
    public abstract void executeOneTask();

//    @Override
//    public void executeOneTask(){ runner.executeOneTask();}

    @Override
    public void executeAll() {
        while(decoratedTaskRunner.hasTask())
        {
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        decoratedTaskRunner.addTask(t);
    }

    @Override
    public boolean hasTask() {
        return decoratedTaskRunner.hasTask();
    }
}
