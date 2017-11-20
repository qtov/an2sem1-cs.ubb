package repository;

import domain.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProjectFileRepository extends AbstractFileRepository<Project, Integer> {
    public ProjectFileRepository(ProjectValidator _val, String _filename) {
        super(_val, _filename);
        loadDataFileReader();
    }

    private void loadDataFileReader() {
        try (BufferedReader rd = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = rd.readLine()) != null) {

                String[] fields = line.split("; ");

                Integer id = Integer.parseInt(fields[0]);
                String desc = fields[1];
                Integer week = Integer.parseInt(fields[2]);
                Project pr = new Project(id, desc, week);
                try {
                    super.saveInMem(pr);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
