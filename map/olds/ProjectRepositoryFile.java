package repository;

import domain.Project;

import java.io.*;

public class ProjectRepositoryFile extends ProjectRepositoryInMemory {

    private String filename;

    public ProjectRepositoryFile(ProjectValidator _val, String _filename) {
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
                String desc = fields[1];
                Integer week = Integer.parseInt(fields[2]);
                Project pr = new Project(id, desc, week);
                try {
                    super.save(pr);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(Project pr) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, true))) {
            br.write(pr.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename, false))) {
            for (Project pr : findAll()) {
                br.write(pr.getId() + ";" + pr.getDesc() + ";" + pr.getWeek() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project update(Project pr) {
        Project project = super.update(pr);
        if (project == null) {
            rewriteFile();
        }
        return project;
    }

    @Override
    public Project save(Project pr) throws ValidationException {
        Project proj = super.save(pr);

        if (proj == null) {
            saveToFile(pr);
        }

        return proj;
    }

    @Override
    public Project delete(Integer id) {
        Project pr = super.delete(id);
        rewriteFile();
        return pr;
    }
}
