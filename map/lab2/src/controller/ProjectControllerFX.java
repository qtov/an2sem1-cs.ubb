package controller;

import domain.Project;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProjectControllerFX extends ControllerFX<Project> {
    public ProjectControllerFX() {}

    @FXML private RadioButton prRB;
    @FXML private RadioButton stRB;
    @FXML private RadioButton grRB;

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        model = FXCollections.observableList(s.findAllListProject());
        prRB.setSelected(true);
        prRB.setDisable(true);
    }

    public void stRBAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/student.fxml"));
        rootpane.getChildren().setAll(pane);
    }
}
