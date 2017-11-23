import domain.Grade;
import domain.Project;
import domain.Student;
import repository.*;
import service.Service;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentFileRepository(new StudentValidator(), "students.txt");
        Repository<Project, Integer> prRepo = new ProjectFileRepository(new ProjectValidator(), "projects.txt");
        Repository<Grade, String> grRepo = new GradeFileRepository(new GradeValidator(), "grades/");

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
        c.run();
    }
}
