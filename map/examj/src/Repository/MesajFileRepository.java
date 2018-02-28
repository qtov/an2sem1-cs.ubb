package Repository;

import Domain.Mesaj;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MesajFileRepository extends AbstractFileRepository<Mesaj, String> {
    public MesajFileRepository(Validator<Mesaj> val, String filename, String delimiter) {
        super(val, filename, delimiter);
    }

    @Override
    Mesaj buildEntity(String[] lines) {
//        for (String line : lines) {
//            System.out.println(line);
//        }

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.from(f.parse(lines[2]));

        return new Mesaj(lines[0], lines[1], dateTime, lines[3]);
    }
}
