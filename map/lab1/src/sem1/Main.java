package sem1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
       // System.out.println(args[0]);
        // write your code here
        MessageTask mesaj1 = new MessageTask("1231","asd","mesaj1");
        MessageTask mesaj2 = new MessageTask("1232","asd","mesaj2");
        MessageTask mesaj3 = new MessageTask("1233","asd","mesaj3");
        TaskRunner runner = new StrategyTaskRunner(Strategy.valueOf(args[0]));
        runner.addTask(mesaj1);
        runner.addTask(mesaj2);
        runner.addTask(mesaj3);
        runner.executeAll();
        int[] a = {4,3,2,1};
        SortingTask test1 = new SortingTask("1234", "qwe", a);
        test1.execute();
    }
}
