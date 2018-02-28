package repository;

import model.HasId;
import model.ValidationException;

public interface IRepository<T extends HasId, ID> {
    T save(T t) throws ValidationException;
    T remove(ID id);
    T update(T t) throws ValidationException;
    T getOne(ID id);
    Iterable<T> getAll();
}
