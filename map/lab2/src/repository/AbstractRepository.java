package repository;

import java.util.HashMap;

public class AbstractRepository<E extends HasID<ID>, ID> implements Repository<E, ID> {
    private HashMap<ID, E> map = new HashMap<ID, E>();
    private Validator<E> val;

    AbstractRepository(Validator<E> _val) {
        this.val = _val;
    }

    @Override
    public long size() {
        return this.map.size();
    }

    @Override
    public E save(E entity) throws ValidationException {
        this.val.validate(entity);

        if (this.map.containsKey(entity.getId()))
            return this.map.get(entity.getId());

        return this.map.put(entity.getId(), entity);
    }

    @Override
    public E delete(ID id) {
        return this.map.remove(id);
    }

    @Override
    public E findOne(ID id) {
        return this.map.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return this.map.values();
    }

    @Override
    public E update(E entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
            return null;
        }

        return entity;
    }
}
