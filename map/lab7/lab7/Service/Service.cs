using System;
using System.Collections.Generic;
using lab7.Domain;
using lab7.Repository;

namespace lab7.Service
{
    public class Service
    {
        private IRepository<Student, int> _stRepo;
        
        public Service(IRepository<Student, int> stRepoP)
        {
            _stRepo = stRepoP;
        }

        public Student SaveStudent(string id, string name, string group, string email, string guide)
        {
            int newId;
            
            Int32.TryParse(id, out newId);
            Student st = new Student(newId, name, group, email, guide);
            return _stRepo.Save(st);
        }

        public Student UpdateStudent(string id, string name, string group, string email, string guide)
        {
            int newId;
            
            Int32.TryParse(id, out newId);
            Student st = new Student(newId, name, group, email, guide);
            return _stRepo.Update(st);
        }

        public bool DeleteStudent(string id)
        {
            int newId;

            Int32.TryParse(id, out newId);
            return _stRepo.Delete(newId);
        }

        public Dictionary<int, Student> GetAllStudent()
        {
            return _stRepo.GetAll();
        }
    }
}