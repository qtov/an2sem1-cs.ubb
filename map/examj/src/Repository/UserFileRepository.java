package Repository;

import Domain.Rol;
import Domain.User;

public class UserFileRepository extends AbstractFileRepository<User, String> {
    public UserFileRepository(Validator<User> val, String filename, String delimiter) {
        super(val, filename, delimiter);
    }

    @Override
    User buildEntity(String[] lines) {
        return new User(lines[0], Rol.valueOf(lines[1].toUpperCase()));
    }
}
