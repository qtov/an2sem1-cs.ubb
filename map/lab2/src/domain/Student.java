package domain;

public class Student {
    private int id;
    private String nume;
    private Float medie;


    public Student(String nume, Float medie) {
        this.nume = nume;
        this.medie = medie;
    }

    public int getId() {
        return this.id;
    }

    public String getNume() {
        return nume;
    }

    public Float getMedie() {
        return medie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        if (nume != null ? !nume.equals(student.nume) : student.nume != null)
            return false;

        return medie != null ? medie.equals(student.medie) : student.medie == null;
    }

    @Override
    public int hashCode() {
        int result = nume != null ? nume.hashCode() : 0;
        result = 31 * result + (medie != null ? medie.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", medie=" + medie +
                '}';
    }
}
