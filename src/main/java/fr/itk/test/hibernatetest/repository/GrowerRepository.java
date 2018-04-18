package fr.itk.test.hibernatetest.repository;

import fr.itk.test.hibernatetest.model.Grower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class GrowerRepository {

    @Autowired
    private EntityManager em;

    public Grower findByName(String name) {
        return em.createQuery("select g from Grower g where name = :name", Grower.class)
                .setParameter("name", name)
                .getResultList().get(0);
    }

    public void save(Grower grower){
        em.persist(grower);
    }

}
