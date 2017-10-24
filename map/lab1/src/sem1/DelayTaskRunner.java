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
        } catch (InterruptedException e) {
            e.printStackTrace();
            decoratedTaskRunner.executeOneTask();
            System.out.println("task executed at " + new Date().toString());
        }
    }
}
