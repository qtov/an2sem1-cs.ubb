using lab7.Domain;

namespace lab7.Repository
{
    public class FileProjectRepository : AbstractRepository<Project, int>
    {
        public FileProjectRepository(ProjectValidator val)
        {
            _val = val;
        }
    }
}