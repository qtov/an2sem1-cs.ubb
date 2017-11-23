import domain.Grade;
import domain.Project;
import domain.Student;
import filter.FilterAndSorter;
import repository.*;
import service.Service;
import ui.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentFileRepository(new StudentValidator(), "repos/students.txt");
        Repository<Project, Integer> prRepo = new ProjectFileRepository(new ProjectValidator(), "repos/projects.txt");
        Repository<Grade, String> grRepo = new GradeFileRepository(new GradeValidator(), "grades/");

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
        c.run();
    }
}
