using System;
using System.Collections.Generic;
using System.Linq;
using lab7.Domain;

namespace lab7.UI
{
    public class MainConsole
    {
        private Dictionary<string, System.Action> _opt = new Dictionary<string, System.Action>();
        private Service.Service _s;
        
        public MainConsole(Service.Service sP)
        {
            _s = sP;
        }

        private void SaveStudent()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();
            
            Console.Write("Name: ");
            string name = Console.ReadLine();
            
            Console.Write("Group: ");
            string group = Console.ReadLine();
            
            Console.Write("Email: ");
            string email = Console.ReadLine();
            
            Console.Write("Guide: ");
            string guide = Console.ReadLine();

            try
            {
                _s.SaveStudent(id, name, group, email, guide);
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }
        
        private void UpdateStudent()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();
            
            Console.Write("Name: ");
            string name = Console.ReadLine();
            
            Console.Write("Group: ");
            string group = Console.ReadLine();
            
            Console.Write("Email: ");
            string email = Console.ReadLine();
            
            Console.Write("Guide: ");
            string guide = Console.ReadLine();

            try
            {
                _s.UpdateStudent(id, name, group, email, guide);
            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        private void DeleteStudent()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();

            if (_s.DeleteStudent(id))
            {
                Console.WriteLine("Successful.");
            }
            else
            {
                Console.WriteLine("Student not found.");
            }
        }
        
        private void SaveProject()
        {
        }
        
        private void ExtendDeadline()
        {
        }
        
        private void DeleteProject()
        {
        }
        
        private void SaveGrade()
        {
        }
        
        private void UpdateGrade()
        {
        }
        
        private void DisplayStudents()
        {
            var lst = _s.GetAllStudent();
            lst.ToList().ForEach(
                pair =>
                {
                    Console.WriteLine(pair.Value);
                }
            );
        }
        
        private void DisplayProjects()
        {
        }
        
        private void DisplayGrades()
        {
        }

        private void ConstructMenu()
        {
            _opt.Add("1", SaveStudent);
            _opt.Add("2", UpdateStudent);
            _opt.Add("3", DeleteStudent);
            _opt.Add("4", SaveProject);
            _opt.Add("5", ExtendDeadline);
            _opt.Add("6", DeleteProject);
            _opt.Add("7", SaveGrade);
            _opt.Add("8", UpdateGrade);
            _opt.Add("9", DisplayStudents);
            _opt.Add("10", DisplayProjects);
            _opt.Add("11", DisplayGrades);
        }

        private static void ShowMenu()
        {
            Console.WriteLine("0. Exit.");
            Console.WriteLine("1. Add Student.");
            Console.WriteLine("2. Update Student.");
            Console.WriteLine("3. Delete Student.");
            Console.WriteLine("4. Add Project.");
            Console.WriteLine("5. Extend deadline.");
            Console.WriteLine("6. Delete Project.");
            Console.WriteLine("7. Add grade.");
            Console.WriteLine("8. Update grade.");
            Console.WriteLine("9. Show Students.");
            Console.WriteLine("10. Show Projects.");
            Console.WriteLine("11. Show Grades.");
        }

        public void Start()
        {
            var choice = "";

            ConstructMenu();
            while (choice != "0")
            {
                ShowMenu();
                Console.Write("Choice: ");
                choice = Console.ReadLine();
                if (choice != null && _opt.ContainsKey(choice))
                    _opt[choice].Invoke();
            }
        }
    }
}