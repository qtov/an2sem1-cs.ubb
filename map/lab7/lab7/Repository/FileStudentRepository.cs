using lab7.Domain;

namespace lab7.Repository
{
    public class FileStudentRepository : AbstractRepository<Student, int>
    {
        public FileStudentRepository(StudentValidator valP)
        {
            _val = valP;
            lst.Add(1, new Student(1, "qwe", "Qwe", "qwe", "qwe"));
        }
    }
}