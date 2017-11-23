import domain.Grade;
import domain.Project;
import domain.Student;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.*;
import service.Service;
import ui.Console;

public class Main {
//    @Override
//    public void start(Stage stage) {
//        Group root = new Group();
//        Scene scene = new Scene(root, 550, 500);
//        stage.setTitle("Title");
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentFileRepository(new StudentValidator(), "students.txt");
        Repository<Project, Integer> prRepo = new ProjectFileRepository(new ProjectValidator(), "projects.txt");
        Repository<Grade, String> grRepo = new GradeFileRepository(new GradeValidator(), "grades/");

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
        c.run();
//        launch(args);
    }
}
