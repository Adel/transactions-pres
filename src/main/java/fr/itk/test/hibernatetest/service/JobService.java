package fr.itk.test.hibernatetest.service;

import fr.itk.test.hibernatetest.Application;
import fr.itk.test.hibernatetest.model.Farm;
import fr.itk.test.hibernatetest.model.Grower;
import fr.itk.test.hibernatetest.model.Plot;
import fr.itk.test.hibernatetest.repository.GrowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class JobService {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private GrowerRepository growerRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    public void launch() {

        for (int j = 0; j < 50; j++) {


            logger.info("launching job");
            Grower grower = new Grower("toto");

            for (int i = 0; i < 20; i++) {
                Farm farm = new Farm("my awesome farm");
                Plot plot = new Plot("my awesome plot");
                Plot plot2 = new Plot("my second plot");

                plot.setFarm(farm);
                farm.addPlot(plot);

                plot2.setFarm(farm);
                farm.addPlot(plot2);

                grower.addFarm(farm);
                farm.setGrower(grower);
            }

            growerRepository.save(grower);

            logger.info("grower in context: {}", em.contains(grower));
            logger.info("farms in context: {}", em.contains(grower.getFarms().get(0)));
            logger.info("plots in context: {}", em.contains(grower.getFarms().get(0).getPlots().get(0)));

            Grower grower2 = growerRepository.findByName("toto");
            logger.info("grower in context: {}", em.contains(grower2));
            logger.info("farms in context: {}", em.contains(grower2.getFarms().get(0)));
            logger.info("plots in context: {}", em.contains(grower2.getFarms().get(0).getPlots().get(0)));

            Plot plot2 = new Plot("my second plot");
            grower2.getFarms().get(0).addPlot(plot2);
            plot2.setFarm(grower2.getFarms().get(0));
            logger.info("grower: {}", grower2);

            Grower grower3 = growerRepository.findByName("toto");
            logger.info("grower: {}", grower3);
        }
    }
}
