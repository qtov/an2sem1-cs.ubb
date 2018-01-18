using System;
using System.Collections.Generic;
using System.IO;
using lab7.Domain;
using MySql.Data.MySqlClient;

namespace lab7.Repository
{
    public class AbstractDbRepository<T, ID> : AbstractRepository<T, ID> where T : IHasId<ID>
    {
        protected DbConnection _db;
        protected string _table;
            
        public AbstractDbRepository(IValidator<T> valP, string table) : base(valP)
        {
            _db = DbConnection.Instance();
            _db.DatabaseName = "lab7map2";
            _table = table;
        }
        
        public override bool Delete(ID id)
        {
            if (_db.Connect())
            {
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = _db.Connection;
                cmd.CommandText = "delete from " + _table + " where id=@id";
                cmd.Prepare();

                cmd.Parameters.AddWithValue("@id", id);
                cmd.ExecuteNonQuery();
                _db.Close();
            }
            
            return lst.Remove(id);
        }
    }
}