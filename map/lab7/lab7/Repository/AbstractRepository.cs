using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using lab7.Domain;

namespace lab7.Repository
{
    public class AbstractRepository<T, ID> : IRepository<T, ID> where T : IHasId<ID>
    {
        protected Dictionary<ID, T> lst = new Dictionary<ID, T>();
        protected IValidator<T> _val;
        
        public long Size()
        {
            return lst.Count;
        }

        public T Save(T t)
        {
            lst.Add(t.GetId(), t);
            return lst.ContainsValue(t) ? default(T) : t;
        }

        public Dictionary<ID, T> GetAll()
        {
            return lst;
        }

        public bool Delete(ID id)
        {
            return lst.Remove(id);
        }

        public T GetOne(ID id)
        {
            return lst[id];
        }

        public T Update(T t)
        {
            if (!lst.ContainsKey(t.GetId()))
                return t;
            lst[t.GetId()] = t;
            return default(T);
        }
    }
}