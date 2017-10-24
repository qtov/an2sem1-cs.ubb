package sem1;

import java.util.Date;

public class PrinterTaskRunner extends TaskRunnerDecorator {
    public PrinterTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() { //decorated method
        //runner.executeOneTask();
        decoratedTaskRunner.executeOneTask();
        System.out.println("task executed at " + new Date().toString());
    }
}
