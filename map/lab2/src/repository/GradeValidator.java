package repository;

import domain.Grade;

import java.util.ArrayList;

public class GradeValidator implements Validator<Grade> {
    @Override
    public void validate(Grade gr) throws ValidationException {
        ArrayList<String> error_msg = new ArrayList<>();
        if (gr.getValue() > 10 || gr.getValue() < 1) {
            error_msg.add("Value isn't between 1 and 10.");
        }

        if (gr.getStId() < 1) {
            error_msg.add("The id of the student is invalid.");
        }

        if (gr.getPrId() < 1) {
            error_msg.add("The id of the project is invalid.");
        }

        if (!error_msg.isEmpty()) {
            throw new ValidationException(String.join("\n", error_msg));
        }
    }
}
