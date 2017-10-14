package sem1;

/**
 * Created by camelia on 10/5/2017.
 */
public class TaskContainerFactory implements Factory {

    @Override
    public Container createContainer(Strategy strategy) {
        if(strategy == Strategy.LIFO)
            return new StackContainer();
        else
            return null;
    }
}
