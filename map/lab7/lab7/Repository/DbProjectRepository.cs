using lab7.Domain;
using MySql.Data.MySqlClient;

namespace lab7.Repository
{
    public class DbProjectRepository : AbstractDbRepository<Project, int>
    {
        public DbProjectRepository(IValidator<Project> val, string table) : base(val, table)
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
                    lst.Add(rdr.GetInt32(0), new Project(rdr.GetInt32(0), rdr.GetString(1), rdr.GetInt32(2)));
                }
                _db.Close();
            }
        }
        
        public override Project Save(Project t)
        {
            var e = base.Save(t);
            
            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "insert into " + _table + "(id, description, deadline) values (@id, @desc, @week)";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@id", t.Id);
                cmd.Parameters.AddWithValue("@desc", t.Desc);
                cmd.Parameters.AddWithValue("@week", t.Week);
                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }

        public override Project Update(Project t)
        {
            var e = base.Update(t);

            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "update " + _table + " set description=@desc, deadline=@week where id=@id";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@id", t.Id);
                cmd.Parameters.AddWithValue("@desc", t.Desc);
                cmd.Parameters.AddWithValue("@week", t.Week);
                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }
    }
}