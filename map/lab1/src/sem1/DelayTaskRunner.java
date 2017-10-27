package sem1;

import java.util.Date;

public class DelayTaskRunner extends TaskRunnerDecorator {
    public DelayTaskRunner(TaskRunner task) {
        super(task);
    }

    @Override
    public void executeOneTask() {
        try {
            Thread.sleep(3000);
            decoratedTaskRunner.executeOneTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
