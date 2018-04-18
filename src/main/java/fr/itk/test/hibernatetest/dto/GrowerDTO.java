package fr.itk.test.hibernatetest.dto;

public class GrowerDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GrowerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
