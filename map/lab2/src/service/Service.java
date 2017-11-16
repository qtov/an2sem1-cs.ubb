package service;

import domain.Grade;
import domain.Project;
import repository.Repository;
import domain.Student;
import repository.ValidationException;

import java.util.Calendar;

public class Service {
    private Repository<Student, Integer> stRepo;
    private Repository<Project, Integer> prRepo;
    private Repository<Grade, Integer> grRepo;

    public Service(Repository<Student, Integer> _stRepo, Repository<Project, Integer> _prRepo, Repository<Grade, Integer> _grRepo) {
        this.stRepo = _stRepo;
        this.prRepo = _prRepo;
        this.grRepo = _grRepo;
    }

    /**
     * Converts a string in int, -1 otherwise.
     *
     * @param x
     *   The string to convert.
     *
     * @return
     *   Returns the newly converted String as Integer.
     */
    private Integer intConverter(String x) {
        int newX;
        try {
            newX = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            newX = -1;
        }
        return newX;
    }

    /**
     * Converts a string in float, -1 otherwise.
     *
     * @param x
     *   The string to convert.
     *
     * @return
     *   Returns the newly converted String as Float.
     */
    private Float floatConverter (String x) {
        float newX;
        try {
            newX = Float.parseFloat(x);
        } catch (NumberFormatException e) {
            newX = -1;
        }
        return newX;
    }

    /**
     * Adds project to repository.
     *
     * @param id
     *   id
     * @param desc
     *   description
     * @param deadline
     *   deadline
     *
     * @throws ValidationException
     *   If data is invalid.
     */
    public void addProject(String id, String desc, String deadline) throws ValidationException {
        int newId = intConverter(id);
        int newDeadline = intConverter(deadline);

        Project pr = new Project(newId, desc, newDeadline);
        this.prRepo.save(pr);
    }

    /**
     * Increments deadline by one if possible.
     *
     * @param _id
     *   Id of project.
     *
     * @return
     *   True if it was increased, false otherwise.
     */
    public boolean extendDeadline(String _id) {
        Integer id = intConverter(_id);
        Project pr = this.prRepo.findOne(id);
        Integer currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 39;
        currentWeek = currentWeek < 39 ? currentWeek + 13 : currentWeek;
        if (pr != null)
            if (currentWeek < pr.getWeek() && pr.getWeek() < 14)
            {
                pr.incWeek();
                if (this.prRepo.update(pr) == null)
                    return true;
            }
        return false;
    }

    public Iterable<Project> findAllProject() {
        return this.prRepo.findAll();
    }

    public Iterable<Grade> findAllGrade() {
        return this.grRepo.findAll();
    }

    /**
     * Adds Student to repo.
     *
     * @param id
     *   id
     * @param name
     *   name
     * @param group
     *   group
     * @param email
     *   email
     * @param guide
     *   guide
     *
     * @throws ValidationException
     *   If data is invalid.
     */
    public void save(String id, String name, String group, String email, String guide) throws ValidationException {
        Student st = new Student(intConverter(id), name, group, email, guide);
        this.stRepo.save(st);
    }

    /**
     * Deletes a Student from repo.
     *
     * @param id
     *   id
     *
     * @return
     *   Returns the item deleted, null otherwise.
     */
    public Student delete(String id) {
        return this.stRepo.delete(intConverter(id));
    }

    /**
     * Updates the Student in repo.
     *
     * @param id
     *   id
     * @param name
     *   name
     * @param group
     *   group
     * @param email
     *   email
     * @param guide
     *   guide
     *
     * @return
     *   Returns null if the item was edited, the edited object otherwise.
     */
    public Student update(String id, String name, String group, String email, String guide) {
        Student st = new Student(intConverter(id), name, group, email, guide);
        return this.stRepo.update(st);
    }

    public Iterable<Student> findAll() {
        return this.stRepo.findAll();
    }

    public void addGrade(String _stId, String _prId, String _value, String _inWeek, String _obs) throws ValidationException {
        int stId = intConverter(_stId);
        int prId = intConverter(_prId);
        float value = floatConverter(_value);
        int inWeek = intConverter(_inWeek);

        if (prRepo.findOne(prId) == null) {
            prId = -1;
        }

        if (stRepo.findOne(stId) == null) {
            stId = -1;
        }

        Project pr = prRepo.findOne(prId);

        if (inWeek > pr.getWeek() + 2)
            value = 1;
        else if (inWeek > pr.getWeek())
            value = value - 2 * (inWeek - pr.getWeek());

        Grade grade = new Grade(stId, prId, value, inWeek, pr.getWeek(), _obs);

        this.grRepo.save(grade);
    }

    public void updateGrade(String _stId, String _prId, String _value, String _inWeek, String _obs) {
        int stId = intConverter(_stId);
        int prId = intConverter(_prId);
        float value = floatConverter(_value);
        int inWeek = intConverter(_inWeek);

        Grade old = null;

        for (Grade gr : grRepo.findAll()) {
            if (stId == gr.getStId() && prId == gr.getStId()) {
                old = gr;
                break;
            }
        }
        if (old == null)
            return;

        if (inWeek > old.getDeadline() + 2)
            value = 1;
        else if (inWeek > old.getDeadline())
            value = value - 2 * (inWeek - old.getDeadline());

        Grade grade = new Grade(stId, prId, value, inWeek, old.getDeadline(), _obs);
        this.grRepo.update(grade);
    }

    public Project deleteProject(String _id) {
        Integer id = intConverter(_id);
        return this.prRepo.delete(id);
    }
}
