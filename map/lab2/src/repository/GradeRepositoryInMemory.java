package repository;

import domain.Grade;

public class GradeRepositoryInMemory extends AbstractRepository<Grade, Integer> {
    public GradeRepositoryInMemory(GradeValidator _val) {
        super(_val);
    }
}
