using System;
using System.Collections.Generic;
using System.IO;

namespace ConsoleApplication2
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            List<Cheltuiala> lst = new List<Cheltuiala>();
            try
            {
                using (StreamReader sr = new StreamReader("../../Cheltuieli.txt"))
                {
                    String line = sr.ReadToEnd();
                    var lines = line.Split(';');
                    TipCheltuiala new0;
                    float new1;
                    DateTime new2;
                    
                    DateTime.TryParse(lines[4], out new2);
                    TipCheltuiala.TryParse(lines[0], out new0);
                    float.TryParse(lines[1], out new1);
                    lst.Add(new Cheltuiala(new0, new1, lines[2], lines[3], new2));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("The file could not be read:");
                Console.WriteLine(e.Message);
            }
            lst.ForEach(Console.WriteLine);
        }
    }
}