using System;

namespace ConsoleApplication2
{
    public class Cheltuiala
    {
        private TipCheltuiala tip;
        private float suma;
        private string desc;
        private string efecDe;

        public Cheltuiala(TipCheltuiala tip, float suma, string desc, string efecDe, DateTime data)
        {
            this.tip = tip;
            this.suma = suma;
            this.desc = desc;
            this.efecDe = efecDe;
            this.data = data;
        }

        public override string ToString()
        {
            return tip + ";" + suma + ";" + desc + ";" + efecDe + ";" + data;
        }

        private DateTime data;

        public TipCheltuiala Tip
        {
            get { return tip; }
            set { tip = value; }
        }

        public float Suma
        {
            get { return suma; }
            set { suma = value; }
        }

        public string Desc
        {
            get { return desc; }
            set { desc = value; }
        }

        public string EfecDe
        {
            get { return efecDe; }
            set { efecDe = value; }
        }

        public DateTime Data
        {
            get { return data; }
            set { data = value; }
        }
    }
}