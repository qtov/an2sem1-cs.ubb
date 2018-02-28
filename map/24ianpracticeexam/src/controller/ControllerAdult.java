package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.TipUser;
import model.User;
import service.Service;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAdult implements ControllerFX {
    @FXML
    TableView<User> table;
    private Service s;
    List<User> lst;

    @FXML
    public void initialize() {
    }

    public void linkService(Service s) {
        this.s = s;
        initialize2();
    }

    private void initialize2() {
        s.getUsers().forEach(lst::add);
        table.getItems().setAll(lst);
        System.out.println("qwe");
    }
}
