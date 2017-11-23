package domain;

public class Student implements HasID<Integer> {
    private int id;
    private String name;
    private String group;
    private String email;
    private String guide;


    public Student(int _id, String _name, String _group, String _email, String _guide) {
        this.id = _id;
        this.name = _name;
        this.group = _group;
        this.email = _email;
        this.guide = _guide;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGroup() {
        return this.group;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGuide() {
        return this.guide;
    }

    @Override
    public void setId(Integer _id) {
        this.id = _id;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setGroup(String _group) {
        this.group = _group;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public void setGuide(String _guide) {
        this.guide = _guide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        if (id == student.getId() || student.name != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getId() + "; " + this.getName() + "; " + this.getGroup() + "; " + this.getEmail() + "; " + this.getGuide();
    }
}
