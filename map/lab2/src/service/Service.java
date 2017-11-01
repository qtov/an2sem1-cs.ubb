package service;

import domain.Project;
import repository.Repository;
import domain.Student;
import repository.ValidationException;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Service {
    private Repository<Student, Integer> stRepo;
    private Repository<Project, Integer> prRepo;

    public Service(Repository<Student, Integer> _stRepo, Repository<Project, Integer> _prRepo) {
        this.stRepo = _stRepo;
        this.prRepo = _prRepo;
    }

    private Integer intConverter(String x) {
        int newX;
        try {
            newX = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            newX = -1;
        }
        return newX;
    }

    public void addProject(String id, String desc, String deadline) throws ValidationException {
        int newId = intConverter(id);
        int newDeadline = intConverter(deadline);

        Project pr = new Project(newId, desc, newDeadline);
        this.prRepo.save(pr);
    }

    public boolean extendDeadline(String _id) {
        Integer id = intConverter(_id);
        Project pr = this.prRepo.findOne(id);
        Integer currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 39;
        if (pr != null)
            if (currentWeek < pr.getWeek() && pr.getWeek() < 14)
            {
                pr.incWeek();
                if (this.prRepo.update(pr) == null)
                    return true;
            }
        return false;
    }

    public Iterable<Project> findAllProject() {
        return this.prRepo.findAll();
    }

    public void save(String id, String name, String group, String email, String guide) throws ValidationException {
        Student st = new Student(intConverter(id), name, group, email, guide);
        this.stRepo.save(st);
    }

    public Student delete(String id) {
        return this.stRepo.delete(intConverter(id));
    }

    public Student update(String id, String name, String group, String email, String guide) {
        Student st = new Student(intConverter(id), name, group, email, guide);
        return this.stRepo.update(st);
    }

    public Iterable<Student> findAll() {
        return this.stRepo.findAll();
    }
}
