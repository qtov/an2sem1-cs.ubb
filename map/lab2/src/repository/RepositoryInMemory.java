package repository;

public class RepositoryInMemory<E, ID> implements Repository<E,ID> {
    @Override
    public E update(E e) {
        return e;
    }

    @Override
    public E delete(ID id) {
        //test
        return null;
    }
}
