package Controller;

import Domain.Mesaj;
import Domain.Rol;
import Domain.User;
import Repository.MesajFileRepository;
import Repository.MesajValidator;
import Repository.UserFileRepository;
import Repository.UserValidator;
import Service.Service;
import Utils.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AdminController implements Observer {
    private Service s;
    private List<User> lst;
    private List<Mesaj> lsm;
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
            if (x.getToWho().equals("all") || x.getToWho().equals("sef")) {
                lsm.add(x);
            }
        });
        usersT.getItems().setAll(lsm);
        s.getAllUseri().forEach(x -> {
            if (x.getRol().equals(Rol.MEMBRU)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/user.fxml"));
//                    UserController controller = fxmlLoader.getController();
//                    controller.setUser(x.getNume());
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle(x.getNume());
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void notifyEvent() {
        s.getAllMesaje().forEach(x -> {
            if (x.getToWho().equals("all") || x.getToWho().equals("sef")) {
                lsm.add(x);
            }
        });
        usersT.getItems().setAll(lsm);
    }
}
