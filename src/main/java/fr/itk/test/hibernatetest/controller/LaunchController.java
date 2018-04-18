package fr.itk.test.hibernatetest.controller;

import fr.itk.test.hibernatetest.Application;
import fr.itk.test.hibernatetest.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class LaunchController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/job/1", method = RequestMethod.POST)
    public CompletableFuture<String> launch() {
        return jobService.launch(10).thenApply(status -> {
            logger.info("job status - > {}", status);
            return "{" + status + "}";
        });
    }
}