package sem1;

public interface TaskArrayI {
    Task get(int pos);
    void add(Task elem);
    void add(int index, Task elem);
    void delete(int pos);
    int size();
}
