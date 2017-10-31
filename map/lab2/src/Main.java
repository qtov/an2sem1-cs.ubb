import domain.Student;
import repository.*;
import service.Service;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Repository<Student, Integer> repo = new StudentRepositoryInMemory(new StudentValidator());
        Service s = new Service(repo);
        Console c = new Console(s);
        c.run();
    }
}
