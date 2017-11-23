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
    public static void test() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(5);
        lst.add(3);
        lst.add(2);
        lst.add(6);

        Predicate<Integer> cond = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 1;
            }
        };
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };

        FilterAndSorter.filterAndSorter(lst, cond -> Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 1;
            }
        }, comp);
    }

    public static void main(String[] args) {
        Repository<Student, Integer> stRepo = new StudentFileRepository(new StudentValidator(), "repos/students.txt");
        Repository<Project, Integer> prRepo = new ProjectFileRepository(new ProjectValidator(), "repos/projects.txt");
        Repository<Grade, String> grRepo = new GradeFileRepository(new GradeValidator(), "grades/");

        Service s = new Service(stRepo, prRepo, grRepo);
        Console c = new Console(s);
//        c.run();
        test();
    }
}
