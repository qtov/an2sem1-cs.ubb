package model;

public interface HasId<ID> {
    void setId(ID id);
    ID getId();
}
