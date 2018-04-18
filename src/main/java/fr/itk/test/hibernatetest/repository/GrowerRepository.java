package fr.itk.test.hibernatetest.repository;

import fr.itk.test.hibernatetest.model.Grower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Set;
@RepositoryRestResource(path = "/growers")
@Repository
public interface GrowerRepository extends JpaRepository<Grower, Long> {
    Set<Grower> findByName(String name);
}
