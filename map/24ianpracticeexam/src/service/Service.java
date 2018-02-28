package service;

import model.Cheltuiala;
import model.User;
import repository.IRepository;

public class Service {
    private IRepository<User, String> userRepo;

    public Service(IRepository<User, String> r1) {
        this.userRepo = r1;
    }

    public void sayCheese() {
        System.out.println("Cheese");
    }

    public Iterable<User> getUsers() {
        return this.userRepo.getAll();
    }
}
