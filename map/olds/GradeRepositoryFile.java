package repository;

import domain.Grade;

import java.io.*;

public class GradeRepositoryFile extends GradeRepositoryInMemory {
    private String foldername;

    public GradeRepositoryFile(GradeValidator _val, String _foldername) {
        super(_val);
        this.foldername = _foldername;
        loadDataFileReader();
    }

    private void saveToFile(Grade g) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(foldername + g.getStId() + ".txt", true))) {
            br.write("Adaugare nota;" + g.getPrId() + ";" + g.getValue() + ";" + g.getDeadline() + ";" + g.getInWeek() + ";" + g.getObs() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFileReader() {
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return;
        }
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                try (BufferedReader rd = new BufferedReader(new FileReader(foldername + listOfFiles[i].getName()))) {

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
                                super.save(g);
                            } catch (ValidationException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fields[0].matches("(?i)^modificare.*")) {
                            super.update(g);
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Grade save(Grade g) throws ValidationException {
        Grade gr = super.save(g);

        if (gr == null) {
            saveToFile(g);
        }

        return g;
    }

    private void updateToFile(Grade g) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(foldername + g.getStId() + ".txt", true))) {
            br.write("Modificare nota;" + g.getPrId() + ";" + g.getValue() + ";" + g.getDeadline() + ";" + g.getInWeek() + ";" + g.getObs() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Grade update(Grade g) {
        Grade gr = super.update(g);
        if (gr == null) {
            updateToFile(g);
        }
        return gr;
    }
}
