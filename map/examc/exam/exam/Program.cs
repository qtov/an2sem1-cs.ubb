using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace exam
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            int[] nums = new int[91];
            
            string line;
            StreamReader file = new StreamReader(@"../../ppl.dat");  
            while ((line = file.ReadLine()) != null)
            {
                var lines = line.Split('\n');
                
                lines.ToList().ForEach(x =>
                {
                    Console.WriteLine(x);
                    int newX;
                    Int32.TryParse(x, out newX);
                    nums[newX]++;
                });
            }  

            file.Close();

            for (int i = 1; i < 91; i++)
            {
                Console.Write(nums[i] + " ");
                if (i % 15 == 0)
                    Console.WriteLine();
            }
        }
    }
}