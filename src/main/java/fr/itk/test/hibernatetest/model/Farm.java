package fr.itk.test.hibernatetest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Farm {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Grower grower;

    @OneToMany(mappedBy = "farm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plot> plots = new ArrayList<>();

    protected Farm() {
    }

    public Farm(String name) {
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

    public Grower getGrower() {
        return grower;
    }

    public void setGrower(Grower grower) {
        this.grower = grower;
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public void addPlot(Plot plot) {
        plots.add(plot);
    }

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plots=" + plots +
                '}';
    }
}
