using System;
using System.Collections.Generic;
using System.Linq;
using lab7.Domain;
using lab7.Repository;

namespace lab7
{
    internal class Program
    {
        public static void Main(string[] args)
        {
//            string[] names = { "Burke", "Connor", "Frank", 
//                "Everett", "Albert", "George", 
//                "Harris", "David" };
//            
//            IEnumerable<string> query = from s in names 
//                where s.Length == 5
//                orderby s
//                select s.ToUpper();
//
//            query.ToList().ForEach(Console.WriteLine);

            IRepository<Student, int> stRepo = new FileStudentRepository(new StudentValidator());
            Service.Service s = new Service.Service(stRepo);
            UI.MainConsole c = new UI.MainConsole(s);

            c.Start();
        }
    }
}