using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using lab7.Domain;
using lab7.Repository;
using MySql.Data.MySqlClient;

namespace lab7
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            try
            {
                IRepository<Student, int> stRepo = new DbStudentRepository(new StudentValidator(), "students");
                IRepository<Project, int> prRepo = new DbProjectRepository(new ProjectValidator(), "projects");
                IRepository<Grade, string> grRepo = new DbGradeRepository(new GradeValidator(), "grades");
                Service.Service s = new Service.Service(stRepo, prRepo, grRepo);
                UI.MainConsole c = new UI.MainConsole(s);

                c.Start();
            }
            catch (MySqlException e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("\n" + e.Message + "\n");
                Console.ResetColor();
            }
        }
    }
}