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
import view.StudentController;
import view.StudentView;

public class Main extends Application {
    private static Repository<Student, Integer> stRepo = new StudentFileRepository(new StudentValidator(), "./src/data/students.txt");
    private static Repository<Project, Integer> prRepo = new ProjectFileRepository(new ProjectValidator(), "src/data/projects.txt");
    private static Repository<Grade, String> grRepo = new GradeFileRepository(new GradeValidator(), "src/data/grades/");
    private static Service s = new Service(stRepo, prRepo, grRepo);
    private static Console c = new Console(s);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Laborator 2");
        primaryStage.setScene(new Scene(initView(), 750, 600));
        primaryStage.show();
    }

    private Parent initView() {
        StudentController ctrl = new StudentController(s);
        s.addObserver(ctrl);
        StudentView view = new StudentView(ctrl);
        ctrl.setView(view);
        return view.getView();
    }

    public static void main(String[] args) {
//        c.run();
        launch(args);
    }
}
