using System.Data.Common;

namespace lab7.Repository
{
    public interface IHasId<ID>
    {
        ID Id { get; set; }
    }
}