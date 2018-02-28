package repository;

import model.HasId;
import model.ValidationException;
import model.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileRepository<T extends HasId<ID>, ID> extends AbstractRepository<T, ID> {
    protected String filename;

    public AbstractFileRepository(Validator<T> val, String filename) {
        super(val);
        this.filename = filename;
        readData();
    }

    private void readData() {
        try {
            Files.lines(Paths.get(filename)).forEach(line -> {
                String[] lines = line.split(" ");
                T t = buildEntity(lines);
                try {
                    super.save(t);
                }
                catch (ValidationException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract T buildEntity(String[] lines);
}
