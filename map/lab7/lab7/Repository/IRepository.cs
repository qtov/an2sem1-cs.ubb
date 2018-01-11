﻿using System.Collections.Generic;

namespace lab7.Repository
{
    public interface IRepository<T, ID>
    {
        T Save(T t);
        Dictionary<ID, T> GetAll();
        bool Delete(ID id);
        long Size();
        T GetOne(ID id);
        T Update(T t);
    }
}