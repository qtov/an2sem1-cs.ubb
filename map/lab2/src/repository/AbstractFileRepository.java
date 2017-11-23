package repository;

import domain.HasID;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractFileRepository<E extends HasID<ID>, ID> extends AbstractRepository<E, ID> implements Repository<E, ID> {
    protected String filename;

    AbstractFileRepository(Validator<E> _val, String _filename) {
        super(_val);
        this.filename = _filename;
    }

    private void loadDataFileReader() {

    }

    private void saveToFile(E e) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, true))) {
            br.write(e.toString() + "\n");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public E saveInMem(E e) throws ValidationException {
        return super.save(e);
    }

    public E updateInMem(E e) {
        return super.update(e);
    }

    @Override
    public E save(E entity) throws ValidationException {
        E e = super.save(entity);

        if (e == null) {
            saveToFile(entity);
        }

        return entity;
    }

    private void rewriteFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, false))) {
            for (E e : findAll()) {
                br.write(e.toString() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        rewriteFile();
        return e;
    }

    @Override
    public E update(E e) {
        E entity = super.update(e);
        if (entity == null) {
            rewriteFile();
        }
        return entity;
    }
}
