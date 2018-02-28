package Controller;

import Domain.Mesaj;
import Domain.Stare;
import Domain.User;
import Repository.*;
import Service.Service;
import Utils.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserController implements Observer {
    private Service s;
    private String user;
    private List<User> lst;
    private List<Mesaj> lsm;
    @FXML
    Button sendBtn;
    @FXML
    TextArea sendTxt;
    @FXML
    TableView<User> activ;
    @FXML
    TableView<Mesaj> usersT;
    @FXML
    Button retrag;

    @FXML
    public void initialize() {
        s = new Service(new MesajFileRepository(new MesajValidator(), "src/discutiiCuSefu.txt", ";"), new UserFileRepository(new UserValidator(), "src/users.txt", ":"));
        lst = new ArrayList<>();
        lsm = new ArrayList<>();
        s.getAllUseri().forEach(lst::add);
        activ.getItems().setAll(lst);
        s.getAllMesaje().forEach(x -> {
            if (x.getToWho().equals("all") || x.getToWho().equals("user1")) {
                lsm.add(x);
            }
        });
        usersT.getItems().setAll(lsm);
    }

    public void retragAction() {
        System.out.println(user);
        Stare st = s.changeState(user);
    }

    public void setUser(String t) {
        user = t;
    }

    public void sendAction() {
        try {
            s.saveMessage(new Mesaj("user1", sendTxt.getText(), LocalDateTime.now(), "all"));
        }
        catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void notifyEvent() {
        s.getAllMesaje().forEach(x -> {
            if (x.getToWho().equals("all") || x.getToWho().equals("user1")) {
                lsm.add(x);
            }
        });
        usersT.getItems().setAll(lsm);
    }
}
