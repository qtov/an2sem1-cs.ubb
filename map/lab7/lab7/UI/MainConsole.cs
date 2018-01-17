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
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine("\n" + "Add successful." + "\n");
                Console.ResetColor();
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
            catch (ArgumentException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
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
                if (_s.UpdateStudent(id, name, group, email, guide) == default(Student))
                {
                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("\n" + "Update successful." + "\n");
                    Console.ResetColor();
                }
                else
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("\n" + "Id not found." + "\n");
                    Console.ResetColor();
                }
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
        }

        private void DeleteStudent()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();

            if (_s.DeleteStudent(id))
            {
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine("\n" + "Delete successful." + "\n");
                Console.ResetColor();
            }
            else
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\nStudent not found.\n ");
                Console.ResetColor();
            }
        }
        
        private void SaveProject()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();
            
            Console.Write("Description: ");
            string desc = Console.ReadLine();
            
            Console.Write("Deadline: ");
            string deadline = Console.ReadLine();
            
            try
            {
                _s.SaveProject(id, desc, deadline);
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine("\n" + "Add successful." + "\n");
                Console.ResetColor();
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
            catch (ArgumentException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
        }
        
        private void ExtendDeadline()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();

            try
            {
                if (_s.ExtendProject(id) != null)
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("\n" + "Deadline cannot be extended.\n ");
                    Console.ResetColor();
                }
                else
                {
                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("\n" + "Deadline was extended.\n ");
                    Console.ResetColor();
                }
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
            catch (KeyNotFoundException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\nId not found.\n ");
                Console.ResetColor();
            }
        }
        
        private void DeleteProject()
        {
            Console.Write("Id: ");
            string id = Console.ReadLine();

            if (_s.DeleteProject(id))
            {
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine("\n" + "Delete successful.\n ");
                Console.ResetColor();
            }
            else
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + "Project not found.\n ");
                Console.ResetColor();
            }
        }
        
        private void SaveGrade()
        {
            Console.Write("Id Student: ");
            string idSt = Console.ReadLine();
            
            Console.Write("Id Proiect: ");
            string idPr = Console.ReadLine();
            
            Console.Write("Nota: ");
            string grade = Console.ReadLine();
            
            Console.Write("In sapt: ");
            string inWeek = Console.ReadLine();
            
            Console.Write("Obs: ");
            string obs = Console.ReadLine();

            try
            {
                _s.SaveGrade(idSt, idPr, grade, inWeek, obs);
                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine("\n" + "Add successful." + "\n");
                Console.ResetColor();
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
            catch (KeyNotFoundException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + "Studentul or project does not exist." + "\n");
                Console.ResetColor();
            }
            catch (ArgumentException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
        }
        
        private void UpdateGrade()
        {
            Console.Write("Id Student: ");
            string idSt = Console.ReadLine();
            
            Console.Write("Id Proiect: ");
            string idPr = Console.ReadLine();
            
            Console.Write("Nota: ");
            string grade = Console.ReadLine();
            
            Console.Write("In sapt: ");
            string inWeek = Console.ReadLine();
            
            Console.Write("Obs: ");
            string obs = Console.ReadLine();

            try
            {
                if (_s.UpdateGrade(idSt, idPr, grade, inWeek, obs) == default(Grade))
                {
                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("\n" + "Update successful." + "\n");
                    Console.ResetColor();
                }
                else
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("\n" + "Id pair not found." + "\n");
                    Console.ResetColor();
                }
            }
            catch (ValidationException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
            catch (KeyNotFoundException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + "Studentul or proiectul not found." + "\n");
                Console.ResetColor();
            }
        }
        
        private void DisplayStudents()
        {
            var lst = _s.GetAllStudents();
            Console.WriteLine("\n");
            lst.ToList().ForEach(
                pair =>
                {
                    Console.WriteLine(pair.Value);
                }
            );
            Console.WriteLine("\n");
        }
        
        private void DisplayProjects()
        {
            var lst = _s.GetAllProjects();
            Console.WriteLine("\n");
            lst.ToList().ForEach(
                pair =>
                {
                    Console.WriteLine(pair.Value);
                }
            );
            Console.WriteLine("\n");
        }
        
        private void DisplayGrades()
        {
            var lst = _s.GetAllGrades();
            Console.WriteLine("\n");
            lst.ToList().ForEach(
                pair =>
                {
                    Console.WriteLine(pair.Value);
                }
            );
            Console.WriteLine("\n");
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