package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Task(i++, TaskE.todo, "this" + i, "" + i + "that"));
            tasks.add(new Task(i++, TaskE.inprogress, "this" + i, "that" + i));
            tasks.add(new Task(i, TaskE.done, "this" + i, "that" + i));
        }

//        for (Task e : tasks) {
//            System.out.println(e);
//        }

        System.out.println();

        Predicate<Task> pred = x -> x.getStatus() == TaskE.inprogress;
        TComparator comp = new TComparator();
        Filter<Task> fil = new Filter<>(tasks, pred, comp);

//        for (Task e : fil.doFilter()) {
//            System.out.println(e);
//        }

        System.out.println();

        Predicate<Task> pred1 = x -> x.getStatus() == TaskE.done && x.getUser().equals("that11");
        Comparator<Task> comp1 = (x, y) -> x.getStatus().compareTo(y.getStatus());
        Filter<Task> fil1 = new Filter<>(tasks, pred1, comp1);

//        for (Task e : fil1.doFilter()) {
//            System.out.println(e);
//        }

        System.out.println();

        Predicate<Task> pred2 = x -> x.getUser().equals("that2");
        Comparator<Task> comp2 = (x, y) -> -1 * x.getStatus().compareTo(y.getStatus());
        Filter<Task> fil2 = new Filter<>(tasks, pred2, comp2);

        for (Task e : fil2.doFilter()) {
            System.out.println(e);
        }
//        launch(args);
    }
}
