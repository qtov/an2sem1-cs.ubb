package repository;

import model.TipUser;
import model.User;
import model.Validator;

public class MembriFileRepository extends AbstractFileRepository<User, String> {
    public MembriFileRepository(Validator<User> val, String filename) {
        super(val, filename);
    }

    @Override
    public User buildEntity(String[] lines) {
        return new User(lines[0], TipUser.valueOf(lines[1].toUpperCase()), Float.parseFloat(lines[2].replaceAll("\\D", "")));
    }
}
