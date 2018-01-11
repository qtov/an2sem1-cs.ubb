namespace lab7.Domain
{
    public interface IValidator<T>
    {
        void Validate(T e);
    }
}