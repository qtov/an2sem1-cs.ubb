package ui;

import domain.Student;
import repository.ValidationException;
import service.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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

    private String readInput() {
        return getSaltString() + " " + getSaltString();
    }

    private void save(String id) {
        String name = readInput();
        String group = "223";
        String email = getSaltString() + "@scs.ubbcluj.ro";
        String guide = readInput();
        try {
            this.s.save(id, name, group, email, guide);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void update(String id) {
        String name = readInput();
        String group = "223";
        String email = getSaltString() + "@scs.ubbcluj.ro";
        String guide = readInput();

        System.out.println(s.update(id, name, group, email, guide));
    }

    private void delete(String id) {
        System.out.println(s.delete(id));
    }

    private void constructMenu() {
//        this.opt.put("1", () -> save(""));
//        this.opt.put("2", () -> delete(""));
    }

    public void run() {
//        Scanner sc = new Scanner(System.in);
//
//        this.constructMenu();
//
//        String choice = "";
//        while (!choice.equals("0")) {
//            choice = sc.nextLine();
//            if (this.opt.containsKey(choice))
//                this.opt.get(choice).run();
//        }
        for (int i = 1; i < 6; i++)
            this.save("" + i);

        for (Student st : this.s.findAll())
            System.out.println(st);

        System.out.println();
        this.delete("2");
        this.delete("4");
        System.out.println();

        this.update("1");

        System.out.println();

        for (Student st : this.s.findAll())
            System.out.println(st);
    }
}
