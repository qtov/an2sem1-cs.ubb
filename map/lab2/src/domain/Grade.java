package domain;

public class Grade implements HasID<Integer> {
    private static Integer id = 1;
    private Integer stId;
    private Integer prId;
    private float value;

    public Grade(Integer _stId, Integer _prId, float _value) {
        this.stId = _stId;
        this.prId = _prId;
        this.value = _value;
    }

    @Override
    public void setId(Integer _id) {
        Grade.id = _id;
    }

    @Override
    public Integer getId() {
        return Grade.id;
    }

    public static void incId() {
        ++Grade.id;
    }

    public void setStId(Integer _id) {
        this.stId = _id;
    }

    public Integer getStId() {
        return this.stId;
    }

    public void setPrId(Integer _id) {
        this.prId = _id;
    }

    public Integer getPrId() {
        return this.prId;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float _value) {
        this.value = _value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Grade grade = (Grade) o;

        return grade.getPrId().equals(this.getPrId()) && grade.getStId().equals(this.getStId());
    }

    @Override
    public String toString() {
        return this.getStId() + "; " + this.getPrId() + "; " + this.getValue();
    }
}
