using System.Data.Common;

namespace lab7.Repository
{
    public interface IHasId<ID>
    {
        ID GetId();
        void SetId(ID id);
    }
}