package repository;

import domain.HasID;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractFileRepository<E extends HasID<ID>, ID> extends AbstractRepository<E, ID> implements Repository<E, ID>{
    protected String filename;

    public AbstractFileRepository(Validator<E> _val, String _filename) {
        super(_val);
        this.filename = _filename;
    }

    public E update(E st) {
        E E = super.update(st);
        if (E == null) {
            rewriteFile();
        }
        return E;
    }
    
    public E save(E g) throws ValidationException {
        E gr = super.save(g);

        if (gr == null) {
            saveToFile(g);
        }

        return g;
    }
    
    public E delete(ID id) {
        E E = super.delete(id);
        rewriteFile();
        return E;
    }

    private void saveToFile(E st) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, true))) {
            br.write(st.toString() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, false))) {
            for (E st : findAll()) {
                br.write(st.toString() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
