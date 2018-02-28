package Service;

import Domain.Mesaj;
import Domain.Stare;
import Domain.User;
import Repository.*;
import Utils.Observable;
import Utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private Repository<Mesaj, String> mRepo;
    private Repository<User, String> uRepo;
    private List<Observer> obs;

    public Service(Repository<Mesaj, String> mRepo, Repository<User, String> uRepo) {
        this.mRepo = mRepo;
        this.uRepo = uRepo;
        obs = new ArrayList<>();
    }

    public Iterable<User> getAllUseri() {
        return uRepo.findAll();
    }

    public Iterable<Mesaj> getAllMesaje() {
        return mRepo.findAll();
    }

    public Stare changeState(String nume) {
        User u = uRepo.findOne(nume);
        if (u.getStare().equals(Stare.ACTIV))
            u.setStare(Stare.INACTIV);
        else
            u.setStare(Stare.ACTIV);
        try {
            uRepo.update(u);
        }
        catch (ValidationException e) {
            e.printStackTrace();
        }
        return u.getStare();
    }

    public void saveMessage(Mesaj m) throws ValidationException {
        mRepo.save(m);
//        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        obs.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers() {
        obs.forEach(x -> x.notifyEvent());
    }
}
