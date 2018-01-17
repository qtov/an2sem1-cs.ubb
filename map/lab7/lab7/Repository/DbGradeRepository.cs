using lab7.Domain;
using MySql.Data.MySqlClient;

namespace lab7.Repository
{
    public class DbGradeRepository : AbstractDbRepository<Grade, string>
    {
        public DbGradeRepository(GradeValidator valP, string table) : base(valP, table)
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
                    lst.Add(rdr.GetString(0) + " " + rdr.GetString(1), new Grade(rdr.GetInt32(0), rdr.GetInt32(1), rdr.GetFloat(2), rdr.GetInt32(3), rdr.GetString(4)));
                }
                _db.Close();
            }
        }
        
        public override Grade Save(Grade t)
        {
            var e = base.Save(t);
            
            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "insert into " + _table + "(stId, prId, value, inWeek, obs) values (@stId, @prId, @value, @inWeek, @obs)";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@stId", t.StId);
                cmd.Parameters.AddWithValue("@prId", t.PrId);
                cmd.Parameters.AddWithValue("@value", t.Value);
                cmd.Parameters.AddWithValue("@inWeek", t.InWeek);
                cmd.Parameters.AddWithValue("@obs", t.Obs);
                cmd.ExecuteNonQuery();
                
                cmd.CommandText = "insert into " + _table + "history (stId, prId, value, inWeek, obs, type) values (@stId, @prId, @value, @inWeek, @obs, 'insert')";
                cmd.Prepare();

                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }
        
        public override Grade Update(Grade t)
        {
            var e = base.Update(t);

            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "update " + _table + " set value=@value, inWeek=@inWeek, obs=@obs where stId=@stId and prId=@prId";
                cmd.Prepare();
           
                cmd.Parameters.AddWithValue("@stId", t.StId);
                cmd.Parameters.AddWithValue("@prId", t.PrId);
                cmd.Parameters.AddWithValue("@value", t.Value);
                cmd.Parameters.AddWithValue("@inWeek", t.InWeek);
                cmd.Parameters.AddWithValue("@obs", t.Obs);
                cmd.ExecuteNonQuery();
                
                cmd.CommandText = "insert into " + _table + "history (stId, prId, value, inWeek, obs, type) values (@stId, @prId, @value, @inWeek, @obs, 'update')";
                cmd.Prepare();

                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return e;
        }
    }
}