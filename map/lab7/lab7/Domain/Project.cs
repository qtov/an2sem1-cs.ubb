﻿using System;
using System.Security.Principal;
using lab7.Repository;

namespace lab7.Domain
{
    public class Project : IHasId<int>
    {
        private int _id;
        private string _desc;
        private int _week;

        public int GetId()
        {
            return _id;
        }

        public void SetId(int id)
        {
            _id = id;
        }

        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }

        public string Desc
        {
            get { return _desc; }
            set { _desc = value; }
        }

        public int Week
        {
            get { return _week; }
            set { _week = value; }
        }

        public Project(int id, string desc, int week)
        {
            _id = id;
            _desc = desc;
            _week = week;
        }
        
        public override string ToString()
        {
            return _id + ";" + _desc + ";" + _week;
        }
        
        public override bool Equals(Object o)
        {
            if (this == o)
                return true;

            if (o == null || GetType().Name != o.GetType().Name)
                return false;

            Project pr = (Project) o;

            if (_id != pr.GetId())
                return false;
            return true;
        }
    }
}