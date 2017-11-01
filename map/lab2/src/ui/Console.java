package ui;

import domain.Project;
import domain.Student;
import repository.ValidationException;
import service.Service;

import java.io.IOException;
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
            System.out.println(e.getMessage());
        }
    }

    private void updateTest(String id) {
        String name = getSaltString() + " " + getSaltString();
        String group = "223";
        String email = getSaltString() + "@scs.ubbcluj.ro";
        String guide = getSaltString() + " " + getSaltString();

        System.out.println(s.update(id, name, group, email, guide));
    }

    private void update() {
        String id = readInput("id");
        String name = readInput("name");
        String group = readInput("group");
        String email = readInput("email");
        String guide = readInput("guide");

        if (s.update(id, name, group, email, guide) == null)
            System.out.println("Update successful.");
        else
            System.out.println("Update unsuccessful.");
    }

    private void deleteTest(String id) {
        System.out.println(s.delete(id));
    }

    private void delete() {
        String id = readInput("id");
        Student st = s.delete(id);
        if (st != null)
            System.out.println("Id " + st.getId() + " deleted.");
        else
            System.out.println("Id not found.");
    }

    private void addProject() {
        String id = readInput("project number");
        String desc = readInput("description");
        String deadline = readInput("deadline");

        try {
            this.s.addProject(id, desc, deadline);
        } catch (ValidationException e) {
            e.getMessage();
        }
    }

    private void addProjectTest(int id) {
        String desc = getSaltString() + " " + getSaltString();
        Integer week = ThreadLocalRandom.current().nextInt(1, 15);

        try {
            this.s.addProject(Integer.toString(id), desc, Integer.toString(week));
        } catch (ValidationException e) {
            e.getMessage();
        }
    }

    private void extendDeadline() {
        String id = readInput("project number");

        if (this.s.extendDeadline(id))
            System.out.println("The deadline has been increased with 1 week.");
        else
            System.out.println("The deadline cannot be increased.");
    }

    private void displayStudents() {
        for (Student st : this.s.findAll())
            System.out.println(st);
    }

    private void displayProjects() {
        for (Project pr : this.s.findAllProject())
            System.out.println(pr);
    }

    private void constructMenu() {
        this.opt.put("1", this::save);
        this.opt.put("2", this::delete);
        this.opt.put("3", this::update);
        this.opt.put("4", this::addProject);
        this.opt.put("5", this::extendDeadline);
        this.opt.put("6", this::displayStudents);
        this.opt.put("7", this::displayProjects);
    }

    private void showMenu() {
        System.out.println("0. Exit.");
        System.out.println("1. Add Student.");
        System.out.println("2. Delete Student.");
        System.out.println("3. Update Student.");
        System.out.println("4. Add Project.");
        System.out.println("5. Extend deadline.");
        System.out.println("6. Show Students.");
        System.out.println("7. Show Projects.");
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

    public void run() {
        this.runTest();

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
