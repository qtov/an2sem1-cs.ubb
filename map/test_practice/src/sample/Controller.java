package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Controller {
    @FXML private TextField smtxt;
    @FXML private ComboBox<TaskE> statusT;
    @FXML private TableView<Task> table;
    @FXML private ComboBox<String> userT;
    List<Task> tasks;
    List<String> names;

    public Controller() {}

    @FXML
    public void initialize() {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Task(i++, TaskE.todo, "this" + i, "" + i + "that"));
            tasks.add(new Task(i++, TaskE.inprogress, "this" + i, "that" + i));
            tasks.add(new Task(i, TaskE.done, "this" + i, "that" + i));
        }
        this.names = new ArrayList<>();
        for (Task x : tasks) {
            names.add(x.getUser());
        }
        statusT.setItems(FXCollections.observableArrayList(TaskE.values()));
        statusT.getSelectionModel().selectFirst();
        userT.setItems(FXCollections.observableArrayList(names));
        userT.getSelectionModel().selectFirst();
//        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showTask(newValue)));
    }

//    private void showTask(Task g) {
//        if (g != null) {
//
//        }
//    }

    public void thisandthat() {
        String name = this.userT.getValue();
        String st = this.statusT.getSelectionModel().getSelectedItem().toString();
        System.out.println(name + " " + st);

        Predicate<Task> pred = x -> x.getStatus() == statusT.getValue() && x.getUser().equals(userT.getValue());
        Comparator<Task> comp = (x, y) -> x.getStatus().compareTo(y.getStatus());
        Filter<Task> fil = new Filter<>(tasks, pred, comp);

        for (Task e : fil.doFilter()) {
            System.out.println(e);
        }
//        table.getItems().setAll(FXCollections.observableArrayList(fil.doFilter()));
        table.getItems().setAll(fil.doFilter());
    }
}
