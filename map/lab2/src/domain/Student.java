package domain;

import repository.HasID;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getId() + "; " + this.getName() + "; " + this.getEmail() + "; " + this.getGroup() + "; " + this.getGuide();
    }
}