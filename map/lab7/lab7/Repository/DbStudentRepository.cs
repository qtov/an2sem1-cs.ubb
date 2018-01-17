using System;
using lab7.Domain;
using MySql.Data.MySqlClient;

namespace lab7.Repository
{
    public class DbStudentRepository : AbstractDbRepository<Student, int>
    {
        public DbStudentRepository(StudentValidator valP, string table) : base(valP, table)
        {
            ReadData();
        }
        
        private void ReadData()
        {
            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "select * from " + _table;
                cmd.Prepare();
           
                var rdr = cmd.ExecuteReader();
                while (rdr.Read())
                {
                    lst.Add(rdr.GetInt32(0),
                        new Student(rdr.GetInt32(0), rdr.GetString(1), rdr.GetString(2), rdr.GetString(3),
                            rdr.GetString(4)));
                }
                _db.Close();
            }
        }
        
        public override Student Save(Student t)
        {
            var e = base.Save(t);
            
            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "insert into " + _table + "(id, name, group_, email, guide) values (@id, @name, @group, @email, @guide)";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@id", t.Id);
                cmd.Parameters.AddWithValue("@name", t.Name);
                cmd.Parameters.AddWithValue("@group", t.Group);
                cmd.Parameters.AddWithValue("@email", t.Email);
                cmd.Parameters.AddWithValue("@guide", t.Guide);
                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }

        public override Student Update(Student t)
        {
            var e = base.Update(t);

            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "update " + _table + " set name=@name, group_=@group, email=@email, guide=@guide where id=@id";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@id", t.Id);
                cmd.Parameters.AddWithValue("@name", t.Name);
                cmd.Parameters.AddWithValue("@group", t.Group);
                cmd.Parameters.AddWithValue("@email", t.Email);
                cmd.Parameters.AddWithValue("@guide", t.Guide);
                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }
    }
}