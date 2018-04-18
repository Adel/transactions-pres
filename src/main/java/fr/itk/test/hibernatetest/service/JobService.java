package fr.itk.test.hibernatetest.service;

import fr.itk.test.hibernatetest.Application;
import fr.itk.test.hibernatetest.model.Farm;
import fr.itk.test.hibernatetest.model.Grower;
import fr.itk.test.hibernatetest.model.Plot;
import fr.itk.test.hibernatetest.repository.GrowerRepository;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class JobService {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private GrowerRepository growerRepository;

    @Autowired DSLContext dsl;

    @Async
    public CompletableFuture<String> launchAsync(int n) {

        launchJob(n);
        return CompletableFuture.completedFuture("OK");
    }

    public String launchSync(int n) {
        launchJob(n);
        return "OK";
    }

    public void updateGrower(Long id, String newName) {
        Optional<Grower> grower = growerRepository.findById(id);
        grower.ifPresent(g -> {
            g.setName(newName);
            logger.info("updated name of - > {}", g);
        });
    }

    private void launchJob(int n) {
        for (int j = 0; j < n; j++) {


            logger.info("launching job");
            Grower grower = new Grower("toto" + j);

            for (int i = 0; i < n; i++) {
                Farm farm = new Farm("my awesome farm" + i);
                Plot plot = new Plot("my awesome plot" + i);
                Plot plot2 = new Plot("my second awesome plot" + i);

                plot.setFarm(farm);
                farm.addPlot(plot);

                plot2.setFarm(farm);
                farm.addPlot(plot2);

                grower.addFarm(farm);
                farm.setGrower(grower);
            }

            growerRepository.save(grower);
        }
    }

}
