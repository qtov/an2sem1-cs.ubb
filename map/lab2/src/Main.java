import domain.Grade;
import domain.Project;
import domain.Student;
import repository.*;
import service.Service;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentRepositoryInMemory(new StudentValidator());
        Repository<Project, Integer> prRepo = new ProjectRepositoryInMemory(new ProjectValidator());
        Repository<Grade, Integer> grRepo = new GradeRepositoryInMemory(new GradeValidator());

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
        c.run();
    }
}
