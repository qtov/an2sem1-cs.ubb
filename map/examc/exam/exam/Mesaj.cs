using System;

namespace exam
{
    public class Mesaj
    {
        private string user;
        private string mesaj;
        private DateTime data;
        private string toWho;

        public Mesaj(string user, string mesaj, DateTime data, string toWho)
        {
            this.user = user;
            this.mesaj = mesaj;
            this.data = data;
            this.toWho = toWho;
        }

        public string User
        {
            get { return user; }
            set { user = value; }
        }

        public string Mesaj1
        {
            get { return mesaj; }
            set { mesaj = value; }
        }

        public DateTime Data
        {
            get { return data; }
            set { data = value; }
        }

        public string ToWho
        {
            get { return toWho; }
            set { toWho = value; }
        }

        public override string ToString()
        {
            return "" + user + ";" + mesaj + ";" + data + ";" + toWho;
        }
    }
}