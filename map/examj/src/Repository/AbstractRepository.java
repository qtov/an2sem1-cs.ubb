package Repository;

import Domain.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepository<T extends HasId<ID>, ID> implements Repository<T, ID> {
    protected Map<ID, T> lst;
    private Validator<T> val;

    public AbstractRepository(Validator<T> val) {
        this.val = val;
        lst = new HashMap<>();
    }

    @Override
    public T save(T t) throws ValidationException {
        val.validate(t);
        return lst.put(t.getId(), t);
    }

    @Override
    public T delete(ID id) {
        return lst.remove(id);
    }

    @Override
    public T update(T t) throws ValidationException {
        val.validate(t);
        return lst.put(t.getId(), t);
    }

    @Override
    public T findOne(ID id) {
        return lst.get(id);
    }

    @Override
    public Iterable<T> findAll() {
        return lst.values();
    }

    @Override
    public long size() {
        return lst.size();
    }
}
