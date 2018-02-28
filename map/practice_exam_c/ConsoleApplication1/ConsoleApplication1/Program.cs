using System;

namespace ConsoleApplication1
{
    internal class Program
    {
        delegate string CustomDel(string s);

        static string Hello(string s)
        {
            Console.WriteLine("Hello");
            return "Hello " + s;
        }

        static string Goodbye(string s)
        {
            Console.WriteLine("GoodBy");
            return "GoodBy " + s;
        }

        public static void Main(string[] args)
        {
            CustomDel del = Hello;
            del += (x) => "How are your? " + x;
            del += Goodbye;
            Console.WriteLine(del.Invoke("John"));
        }
    }
}