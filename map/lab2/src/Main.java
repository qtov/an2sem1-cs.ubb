import domain.Grade;
import domain.Project;
import domain.Student;
import repository.*;
import service.Service;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentRepositoryFile(new StudentValidator(), "repos/students.txt");
        Repository<Project, Integer> prRepo = new ProjectRepositoryFile(new ProjectValidator(), "repos/projects.txt");
        Repository<Grade, Integer> grRepo = new GradeRepositoryFile(new GradeValidator(), "grades/");

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
        c.run();
    }
}
