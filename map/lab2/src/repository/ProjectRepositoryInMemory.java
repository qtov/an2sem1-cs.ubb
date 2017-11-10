package repository;

import domain.Project;

public class ProjectRepositoryInMemory extends AbstractRepository<Project, Integer> {
    public ProjectRepositoryInMemory(ProjectValidator _val) {
        super(_val);
    }

//    public boolean extendDeadline(Integer id) {
//        return true;
//    }
}
