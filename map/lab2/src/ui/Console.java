package ui;

import domain.Grade;
import domain.Project;
import domain.Student;
import repository.ValidationException;
import service.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Console {
    private Service s;
    private HashMap<String, Runnable> opt = new HashMap<String, Runnable>();

    public Console(Service serv) {
        this.s = serv;
    }

    private String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    /**
     * Reads from standard input with message.
     *
     * @param type
     *   Message to display.
     *
     * @return
     *   Returns the newly read line.
     */
    private String readInput(String type) {
        Scanner sc = new Scanner(System.in);

        System.out.print(type + ": ");
        return sc.nextLine();
    }

    private void saveTest(String id) {
        String name = getSaltString() + " " + getSaltString();
        String group = "223";
        String email = getSaltString() + "@scs.ubbcluj.ro";
        String guide = getSaltString() + " " + getSaltString();
        try {
            this.s.save(id, name, group, email, guide);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads input and send it to the Service.
     */
    private void save() {
        String id = readInput("id");
        String name = readInput("name");
        String group = readInput("group");
        String email = readInput("email");
        String guide = readInput("guide");

        try {
            this.s.save(id, name, group, email, guide);
            System.out.println("Save successful.");
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateTest(String id) {
        String name = getSaltString() + " " + getSaltString();
        String group = "223";
        String email = getSaltString() + "@scs.ubbcluj.ro";
        String guide = getSaltString() + " " + getSaltString();

        System.out.println(s.update(id, name, group, email, guide));
    }

    /**
     * Reads from input and prints whether the update was successful or not.
     */
    private void update() {
        String id = readInput("id");
        String name = readInput("name");
        String group = readInput("group");
        String email = readInput("email");
        String guide = readInput("guide");

        if (s.update(id, name, group, email, guide) == null)
            System.out.println("Update successful.");
        else
            System.err.println("Update unsuccessful.");
    }

    private void deleteTest(String id) {
        System.out.println(s.delete(id));
    }

    /**
     * Reads id from input and sends to Service.
     * Outputs message regarding the deletion.
     */
    private void delete() {
        String id = readInput("id");
        Student st = s.delete(id);
        if (st != null)
            System.out.println("Id " + st.getId() + " deleted.");
        else
            System.err.println("Id not found.");
    }

    /**
     * Reads id, desc, deadline and sends to Service.
     * Displays message informing user.
     */
    private void addProject() {
        String id = readInput("project number");
        String desc = readInput("description");
        String deadline = readInput("deadline");

        try {
            this.s.addProject(id, desc, deadline);
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addProjectTest(int id) {
        String desc = getSaltString() + " " + getSaltString();
        Integer week = ThreadLocalRandom.current().nextInt(1, 15);

        try {
            this.s.addProject(Integer.toString(id), desc, Integer.toString(week));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Reads id of Project and sends to Service for processing.
     */
    private void extendDeadline() {
        String id = readInput("project number");

        if (this.s.extendDeadline(id))
            System.out.println("The deadline has been increased with 1 week.");
        else
            System.err.println("The deadline cannot be increased.");
    }

    private void displayStudents() {
        for (Student st : this.s.findAll())
            System.out.println(st);
    }

    private void displayProjects() {
        for (Project pr : this.s.findAllProject())
            System.out.println(pr);
    }

    private void displayGrades() {
        for (Grade gr : this.s.findAllGrade())
            System.out.println(gr);
    }

    private void addGrade() {
        String idSt = readInput("id student");
        String prSt = readInput("id project");
        String value = readInput("value");
        String inWeek = readInput("given week");
        String obs = readInput("observations");

        try {
            this.s.addGrade(idSt, prSt, value, inWeek, obs);
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateGrade() {
        String idSt = readInput("id student");
        String prSt = readInput("id project");
        String value = readInput("value");
        String inWeek = readInput("given week");
        String obs = readInput("observations");

        Grade g = this.s.updateGrade(idSt, prSt, value, inWeek, obs);
        if (g == null) {
            System.out.println("Update successful.");
        }
        else {
            System.err.println("Update unsuccessful.");
        }
    }

    private void deleteProject() {
        String id = readInput("id");

        if (this.s.deleteProject(id) != null) {
            System.out.println("Delete successful.");
        } else {
            System.err.println("Delete unsuccessful.");
        }

    }

    /**
     * Maps the methods to values.
     */
    private void constructMenu() {
        this.opt.put("1", this::save);
        this.opt.put("2", this::update);
        this.opt.put("3", this::delete);
        this.opt.put("4", this::addProject);
        this.opt.put("5", this::extendDeadline);
        this.opt.put("6", this::deleteProject);
        this.opt.put("7", this::addGrade);
        this.opt.put("8", this::updateGrade);
        this.opt.put("9", this::displayStudents);
        this.opt.put("10", this::displayProjects);
        this.opt.put("11", this::displayGrades);
    }

    private void showMenu() {
        System.out.println("0. Exit.");
        System.out.println("1. Add Student.");
        System.out.println("2. Update Student.");
        System.out.println("3. Delete Student.");
        System.out.println("4. Add Project.");
        System.out.println("5. Extend deadline.");
        System.out.println("6. Delete Project.");
        System.out.println("7. Add grade.");
        System.out.println("8. Update grade.");
        System.out.println("9. Show Students.");
        System.out.println("10. Show Projects.");
        System.out.println("11. Show Grades.");
    }

    private void runTest() {
        for (int i = 1; i < 6; i++)
            this.saveTest("" + i);

        displayStudents();

        System.out.println();
        this.deleteTest("2");
        this.deleteTest("4");
        System.out.println();

        this.updateTest("1");

        System.out.println();

        displayStudents();

        for (int i = 1; i < 6; i++)
            this.addProjectTest(i);

        displayProjects();
    }

    /**
     * Main console method.
     */
    public void run() {
//        this.runTest();

        Scanner sc = new Scanner(System.in);

        this.constructMenu();

        String choice = "";
        while (!choice.equals("0")) {
            this.showMenu();
            choice = sc.nextLine();
            if (this.opt.containsKey(choice))
                this.opt.get(choice).run();
        }
    }
}
