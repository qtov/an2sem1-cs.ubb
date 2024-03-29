﻿using System;
using lab7.Repository;

namespace lab7.Domain
{
    public class Student : IHasId<int>
    {
        private int _id;
        private string _name;
        private string _group;
        private string _email;
        private string _guide;

        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }

        public string Name
        {
            get { return _name; }
            set { _name = value; }
        }

        public string Group
        {
            get { return _group; }
            set { _group = value; }
        }

        public string Email
        {
            get { return _email; }
            set { _email = value; }
        }

        public string Guide
        {
            get { return _guide; }
            set { _guide = value; }
        }

        public Student(int idP, string nameP, string groupP, string emailP, string guideP)
        {
            _id = idP;
            _name = nameP;
            _group = groupP;
            _email = emailP;
            _guide = guideP;
        }

        public override string ToString()
        {
            return "Id: " + _id + "\n" + "Name: " + _name + "\nGroup: " + _group + "\nEmail: " + _email + "\nGuide: " + _guide + "\n";
        }

        public override bool Equals(Object o)
        {
            if (this == o)
                return true;

            if (o == null || GetType().Name != o.GetType().Name)
                return false;

            Student student = (Student) o;

            if (_id != student.Id || student._name != null)
                return false;
            return true;
        }
    }
}