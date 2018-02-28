package main;

import controller.ControllerAdult;
import controller.ControllerCopil;
import controller.ControllerFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import repository.AbstractFileRepository;
import repository.MembriFileRepository;
import service.Service;

public class Main extends Application {
    private static Service s;
    private static AbstractFileRepository<User, String> r1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        s.getUsers().forEach(user -> {
            String view;
            if (user.getTip() == TipUser.ADULT) {
                view = "../view/adult.fxml";
            } else {
                view = "../view/copil.fxml";
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
                AnchorPane anchorPane = loader.load();
                ControllerFX controller = loader.getController();
                // Set data in the controller
                controller.linkService(s);
                Scene scene = new Scene(anchorPane, 200, 200);
                primaryStage.setScene(scene);
                primaryStage.show();


//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
//                Parent root1 = (Parent) fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.setTitle(user.getNume());
//                stage.setScene(new Scene(root1));
//                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        r1 = new MembriFileRepository(new UserValidator(), "src/Membri.txt");
        s = new Service(r1);
        launch(args);
    }
}
