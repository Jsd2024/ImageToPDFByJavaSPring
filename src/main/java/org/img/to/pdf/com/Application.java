package org.img.to.pdf.com;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.batch.core.Job;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job readImagesJob;
//
//    @PostConstruct
//    public void launchJob() throws Exception {
//        jobLauncher.run(readImagesJob, new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters());
//    }

    }



