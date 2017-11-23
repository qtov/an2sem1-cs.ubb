package repository;

import domain.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentFileRepository extends AbstractFileRepository<Student, Integer> {
    public StudentFileRepository(StudentValidator _val, String _filename) {
        super(_val, _filename);
        loadDataFileReader();
    }

    private void loadDataFileReader() {
        try (BufferedReader rd = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = rd.readLine()) != null) {

                String[] fields = line.split("; ");

                Integer id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String group = fields[2];
                String email = fields[3];
                String guide = fields[4];
                Student st = new Student(id, name, group, email, guide);
                try {
                    super.saveInMem(st);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
