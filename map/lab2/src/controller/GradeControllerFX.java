package controller;

import domain.Grade;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GradeControllerFX extends ControllerFX<Grade> {
    @FXML private TextField idStText;
    @FXML private TextField idPrText;
    @FXML private TextField valueText;
    @FXML private TextField weekText;
    @FXML private TextArea obsText;

    public GradeControllerFX() {}

    @FXML
    public void initialize() {
        super.initialize();
        model = FXCollections.observableList(s.findAllListGrade());
        grRB.setSelected(true);
        grRB.setDisable(true);

        lst = s.findAllListGrade();

        table.getItems().setAll(lst);
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                Grade gr = table.getSelectionModel().getSelectedItem();
                if (gr != null && idStText != null && !(gr.getId().equals(idStText.getText() + " " + idPrText.getText()))) {
                    idStText.setText(gr.getStId().toString());
                    idPrText.setText(gr.getPrId().toString());
                    valueText.setText(gr.getValue() + "");
                    weekText.setText(gr.getInWeek() + "");
                    obsText.setText(gr.getObs());
                }
            }
        });
        s.addObserverGrade(this);
    }

    public void filUncheck() {

    }

    public void addBtnAction() {

    }

    public void editBtnAction() {

    }

    public void stRBAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/student.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    public void prRBAction() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/project.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    public void checkBoxAction() {

    }
}
