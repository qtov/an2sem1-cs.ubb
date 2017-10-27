package sem1;

import java.util.Arrays;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        MessageTask mesaj1 = new MessageTask("1231","asd","mesaj1");
        MessageTask mesaj2 = new MessageTask("1232","asd","mesaj2");
        MessageTask mesaj3 = new MessageTask("1233","asd","mesaj3");
        TaskRunner runner = new StrategyTaskRunner(Strategy.valueOf(args[0]));
        runner.addTask(mesaj1);
        runner.addTask(mesaj2);
        runner.addTask(mesaj3);
        runner.executeAll();

        MessageTask mesaj4 = new MessageTask("1234", "qwe", "mesaj4");
        MessageTask mesaj5 = new MessageTask("1235", "qwe", "mesaj5");
        MessageTask mesaj6 = new MessageTask("1236", "qwe", "mesaj6");

        TaskRunner runner2 = new DelayTaskRunner(new StrategyTaskRunner(Strategy.valueOf(args[0])));
        TaskRunner runner3 = new PrinterTaskRunner(new StrategyTaskRunner(Strategy.valueOf(args[0])));

        runner2.addTask(mesaj4);
        runner2.addTask(mesaj5);
        runner2.addTask(mesaj6);

        runner3.addTask(mesaj4);
        runner3.addTask(mesaj5);
        runner3.addTask(mesaj6);

        runner3.executeAll();
        runner2.executeAll();
    }
}
