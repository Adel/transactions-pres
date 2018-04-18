package fr.itk.test.hibernatetest;

import fr.itk.test.hibernatetest.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaunchController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JobService jobService;

    @RequestMapping("/job")
    public void launch() {
        jobService.launch();
    }
}
