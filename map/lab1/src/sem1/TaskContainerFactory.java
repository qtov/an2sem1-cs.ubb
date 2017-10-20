package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public final class TaskContainerFactory implements Factory {
    private static final TaskContainerFactory INSTANCE = new TaskContainerFactory();

    private TaskContainerFactory() {

    }

    @Override
    public Container createContainer(Strategy strategy) {
        if(strategy == Strategy.LIFO)
            return new StackContainer();
        else
            return null;
    }

    public static TaskContainerFactory getInstance() {
        return INSTANCE;
    }
}
