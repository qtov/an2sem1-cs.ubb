package Repository;

import Domain.HasId;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileRepository<T extends HasId<ID>, ID> extends AbstractRepository<T, ID> {
    protected String filename;
    protected String delimiter;

    public AbstractFileRepository(Validator<T> val, String filename, String delimiter) {
        super(val);
        this.filename = filename;
        this.delimiter = delimiter;
        readData();
    }

    private void readData() {
        try {
            Files.lines(Paths.get(filename)).forEach(line -> {
//                System.out.println(line);
                String[] lines = line.split(delimiter);
//                for (String s : lines) {
//                    System.out.println(s);
//                }
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

    @Override
    public T save(T t) throws ValidationException {
        T e = super.save(t);
        writeFile();
        return e;
    }

    private void writeFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, false))) {
            for (T e : findAll()) {
                br.write(e.toString() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract T buildEntity(String[] lines);
}
