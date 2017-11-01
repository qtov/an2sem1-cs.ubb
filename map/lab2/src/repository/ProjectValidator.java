package repository;

import domain.Project;

public class ProjectValidator implements Validator<Project> {
    @Override
    public void validate(Project pr) throws ValidationException {
        if (pr.getWeek() > 14 || pr.getWeek() < 1)
            throw new ValidationException("Week should be between 1 and 14 (inclusive).");
        if (pr.getId() < 0)
            throw new ValidationException("The id is invalid.");
    }
}
