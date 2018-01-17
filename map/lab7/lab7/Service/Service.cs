using System;
using System.Collections.Generic;
using System.Globalization;
using lab7.Domain;
using lab7.Repository;

namespace lab7.Service
{
    public class Service
    {
        private IRepository<Student, int> _stRepo;
        private IRepository<Grade, string> _grRepo;
        private IRepository<Project, int> _prRepo;
        
        public Service(IRepository<Student, int> stRepoP, IRepository<Project, int> prRepoP, IRepository<Grade, string> grRepoP)
        {
            _stRepo = stRepoP;
            _prRepo = prRepoP;
            _grRepo = grRepoP;
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
        
        public Project SaveProject(string id, string desc, string deadline)
        {
            int newId;
            int newDeadline;
            
            Int32.TryParse(id, out newId);
            Int32.TryParse(deadline, out newDeadline);
            Project pr = new Project(newId, desc, newDeadline);
            return _prRepo.Save(pr);
        }
        
        public Project ExtendProject(string id)
        {
            int newId;
            
            Int32.TryParse(id, out newId);
            
            DateTimeFormatInfo dfi = DateTimeFormatInfo.CurrentInfo;
            Calendar cal = dfi.Calendar;

            var week = cal.GetWeekOfYear(DateTime.Now, dfi.CalendarWeekRule, dfi.FirstDayOfWeek);
            var start = cal.GetWeekOfYear(DateTime.Parse("01.10.2017"), dfi.CalendarWeekRule, dfi.FirstDayOfWeek);
            int curWeek;

            if (week < start)
            {
                curWeek = 13 + week;
                if (curWeek > 16)
                    curWeek -= 2;
            }
            else
            {
                curWeek = week - start;
            }
            
            var pr = _prRepo.GetOne(newId);

            if (pr.Week > curWeek)
            {
                pr.Week += 1;
                return _prRepo.Update(pr);
            }

            return pr;
        }
        
        public bool DeleteProject(string id)
        {
            int newId;
            
            Int32.TryParse(id, out newId);
            return _prRepo.Delete(newId);
        }

        public Grade SaveGrade(string stId, string prId, string value, string inWeek, string obs)
        {
            int newStId;
            int newPrId;
            int newInWeek;
            float newValue;
            
            Int32.TryParse(stId, out newStId);
            Int32.TryParse(inWeek, out newInWeek);
            Int32.TryParse(prId, out newPrId);
            float.TryParse(value, out newValue);

            var pr = _prRepo.GetOne(newPrId);
            var st = _stRepo.GetOne(newStId);

            if (newInWeek > pr.Week)
            {
                if (newInWeek > pr.Week + 2)
                    newValue = 1;
                else
                    newValue = newValue - 2 * (newInWeek - pr.Week);
            }

            var gr = new Grade(st.Id, newPrId, newValue, newInWeek, obs);
            return _grRepo.Save(gr);
        }
        
        public Grade UpdateGrade(string stId, string prId, string value, string inWeek, string obs)
        {
            int newStId;
            int newPrId;
            int newInWeek;
            float newValue;
            
            Int32.TryParse(stId, out newStId);
            Int32.TryParse(inWeek, out newInWeek);
            Int32.TryParse(prId, out newPrId);
            float.TryParse(value, out newValue);

            var pr = _prRepo.GetOne(newPrId);
            var st = _stRepo.GetOne(newStId);
            
            var throwException = pr.Id;

            var gr = new Grade(st.Id, newPrId, newValue, newInWeek, obs);
            return _grRepo.Update(gr);
        }
        
        public Dictionary<int, Student> GetAllStudents()
        {
            return _stRepo.GetAll();
        }
        
        public Dictionary<int, Project> GetAllProjects()
        {
            return _prRepo.GetAll();
        }
        
        public Dictionary<string, Grade> GetAllGrades()
        {
            return _grRepo.GetAll();
        }
    }
}