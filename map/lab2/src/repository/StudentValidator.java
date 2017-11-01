package repository;

import domain.Student;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {
    private boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    private boolean validateName(String name) {
        String ePattern = "^[a-zA-Z]+ [a-zA-Z]+[- ]?[a-zA-Z]+$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(name);

        return m.matches();
    }

    @Override
    public void validate(Student st) throws ValidationException {
        if (!validateEmail(st.getEmail()))
            throw new ValidationException("The email is invalid.");

        if (!validateName(st.getName()))
            throw new ValidationException("The name is invalid.");

        if (!validateName(st.getGuide()))
            throw new ValidationException("The guide's name is invalid.");

        if (st.getId() < 0)
            throw new ValidationException("The id is invalid.");
    }
}
