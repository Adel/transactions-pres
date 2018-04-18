package fr.itk.test.hibernatetest.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Grower {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "grower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Farm> farms = new ArrayList<>();

    protected Grower() {
    }

    public Grower(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Grower(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void addFarm(Farm farm) {
        this.farms.add(farm);
    }

    @Override
    public String toString() {
        return "Grower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", farms=" + farms +
                '}';
    }
}
