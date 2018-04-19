package fr.itk.test.hibernatetest.controller;

import fr.itk.test.hibernatetest.Application;
import fr.itk.test.hibernatetest.model.Grower;
import fr.itk.test.hibernatetest.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class MainController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/job/async", method = RequestMethod.POST)
    public CompletableFuture<String> launch() {
        return jobService.launchAsync(10).thenApply(status -> {
            logger.info("job status - > {}", status);
            return status;
        });
    }

    @RequestMapping(value = "/job/sync", method = RequestMethod.POST)
    public String simple() {
        return jobService.launchSync(100);
    }

    @RequestMapping(value = "/grower/{id}", method = RequestMethod.PATCH)
    public void updateGrower(@PathVariable Long id, @RequestBody Grower dto) {
        jobService.updateGrower(id, dto.getName());
    }

    @RequestMapping(value = "/job/lockGrower", method = RequestMethod.POST)
    public void longJob(@RequestBody Grower grower) {
        jobService.longUpdateToShowLock(grower);
    }
}
