package repository;

import domain.Grade;

import java.io.*;

public class GradeFileRepository extends AbstractFileRepository<Grade, String> {
    public GradeFileRepository(GradeValidator _val, String _filename) {
        super(_val, _filename);
        loadDataFileReader();
    }

    private void loadDataFileReader() {
        File folder = new File(filename);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return;
        }
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                try (BufferedReader rd = new BufferedReader(new FileReader(filename + listOfFiles[i].getName()))) {

                    String line;

                    while ((line = rd.readLine()) != null) {

                        String[] fields = line.split(";");

                        Integer idSt = Integer.parseInt(listOfFiles[i].getName().replaceFirst("\\.txt$", ""));
                        Integer idPr = Integer.parseInt(fields[1]);
                        Float value = Float.parseFloat(fields[2]);
                        Integer deadline = Integer.parseInt(fields[3]);
                        Integer inWeek = Integer.parseInt(fields[4]);
                        Grade g = new Grade(idSt, idPr, value, deadline, inWeek, fields[5]);

                        if (fields[0].matches("(?i)^adaugare.*")) {
                            try {
                                saveInMem(g);
                            } catch (ValidationException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fields[0].matches("(?i)^modificare.*")) {
                            updateInMem(g);
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToFile(Grade e, String st) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename + e.getStId() + ".txt", true))) {
            br.write(st + e.toString() + "\n");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Grade save(Grade g) throws ValidationException {
        Grade gr = super.saveInMem(g);

        if (gr == null) {
            saveToFile(g, "Agaugare nota;");
        }

        return g;
    }

    @Override
    public Grade update(Grade g) {
        Grade gr = super.updateInMem(g);
        if (gr == null) {
            saveToFile(g, "Modificare nota;");
        }
        return gr;
    }
}
