package repository;

import domain.Grade;

public class GradeRepositoryInMemory extends AbstractRepository<Grade, Integer> {
    public GradeRepositoryInMemory(GradeValidator _val) {
        super(_val);
    }

    @Override
    public Grade save(Grade g) throws ValidationException {
        for (Grade gr : this.findAll()) {
            if (g.getPrId().equals(gr.getPrId()) && g.getStId().equals(gr.getStId())) {
                throw new ValidationException("The student has a grade for the project.");
            }
        }
        return super.save(g);
    }

    @Override
    public Grade update(Grade g) {
        for (Grade gr : this.findAll()) {
            if (g.getPrId().equals(gr.getPrId()) && g.getStId().equals(gr.getStId())) {
                float gGrade = g.getValue();
                float grGrade = gr.getValue();

                if (gGrade < grGrade) {
                    g.setValue(grGrade);
                }

                this.map.put(gr.getId(), g);

                return null;
            }
        }
        return g;
    }
}
