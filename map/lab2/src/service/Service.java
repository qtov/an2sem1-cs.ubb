package service;

import repository.Repository;
import domain.Student;

public class Service {
    private Repository<Student, Integer> repo;

    public Service(Repository<Student, Integer> repo1) {
        this.repo = repo1;
    }
}
