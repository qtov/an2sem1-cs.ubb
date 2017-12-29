using System;
using System.Collections.Generic;
using System.Linq;

namespace lab7
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            string[] names = { "Burke", "Connor", "Frank", 
                "Everett", "Albert", "George", 
                "Harris", "David" };
            
            IEnumerable<string> query = from s in names 
                where s.Length == 5
                orderby s
                select s.ToUpper();

            query.ToList().ForEach(Console.WriteLine);
        }
    }
}