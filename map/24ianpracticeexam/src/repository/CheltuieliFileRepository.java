package repository;

import model.Cheltuiala;
import model.TipCheltuiala;
import model.Validator;

public class CheltuieliFileRepository extends AbstractFileRepository<Cheltuiala, Integer> {
    public CheltuieliFileRepository(Validator<Cheltuiala> val, String filename) {
        super(val, filename);
    }

    @Override
    Cheltuiala buildEntity(String[] lines) {
        return null;
    }
}
