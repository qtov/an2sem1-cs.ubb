package repository;

import domain.Student;

import java.io.*;

public class StudentRepositoryFile extends StudentRepositoryInMemory {
    private String filename;

    public StudentRepositoryFile(StudentValidator _val, String _filename) {
        super(_val);
        this.filename = _filename;
        loadDataFileReader();
    }

    private void loadDataFileReader() {
        try (BufferedReader rd = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = rd.readLine()) != null) {

                String[] fields = line.split(";");

                Integer id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String group = fields[2];
                String email = fields[3];
                String guide = fields[4];
                Student st = new Student(id, name, group, email, guide);
                try {
                    super.save(st);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(Student st) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, true))) {
            br.write(st.getId() + ";" + st.getName() + ";" + st.getGroup() + ";" + st.getEmail() + ";" + st.getGuide() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, false))) {
            for (Student st : findAll()) {
                br.write(st.getId() + ";" + st.getName() + ";" + st.getGroup() + ";" + st.getEmail() + ";" + st.getGuide() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student update(Student st) {
        Student student = super.update(st);
        if (student == null) {
            rewriteFile();
        }
        return student;
    }

    @Override
    public Student save(Student g) throws ValidationException {
        Student gr = super.save(g);

        if (gr == null) {
            saveToFile(g);
        }

        return g;
    }

    @Override
    public Student delete(Integer id) {
        Student student = super.delete(id);
        rewriteFile();
        return student;
    }
}
