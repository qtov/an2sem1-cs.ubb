package service;

import repository.Repository;
import domain.Student;
import repository.ValidationException;

public class Service {
    private Repository<Student, Integer> repo;

    public Service(Repository<Student, Integer> repo1) {
        this.repo = repo1;
    }

    public void save(String id, String name, String group, String email, String guide) throws ValidationException {
        Student st = new Student(Integer.parseInt(id), name, group, email, guide);
        this.repo.save(st);
    }

    public Student delete(String id) {
        return this.repo.delete(Integer.parseInt(id));
    }

    public Student update(String id, String name, String group, String email, String guide) {
        Student st = new Student(Integer.parseInt(id), name, group, email, guide);
        return this.repo.update(st);
    }

    public Iterable<Student> findAll() {
        return this.repo.findAll();
    }
}
