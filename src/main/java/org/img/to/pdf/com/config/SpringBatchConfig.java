//package org.img.to.pdf.com.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//
//
//@Configuration
//@AllArgsConstructor
////@EnableBatchProcessing
//public class SpringBatchConfig {
//
//    @Autowired
//    JobBuilder jobBuilderFactory;
//    @Bean
//    public Job readImagesJob(JobBuilder jobBuilderFactory, Step readImagesStep) {
//        return jobBuilderFactory
////                .get("readImagesJob")
//                .incrementer(new RunIdIncrementer())
//                .start(readImagesStep)
//                .build();
//    }
//
////    @Bean
//    public Step readImagesStep(StepBuilder stepBuilder) {
//        return stepBuilder
////                .get("readImagesStep")
//                .tasklet(readImagesTasklet())
//                .build();
//    }
////
////    @Bean
//    public Tasklet readImagesTasklet() {
//        return (contribution, chunkContext) -> {
//            String folderPath = "C:\\Users\\aninmazu\\OneDrive - Capgemini\\Capgemini\\POC\\IMGTOPDF\\in\\";
//            File folder = new File(folderPath);
//
//            if (!folder.exists() || !folder.isDirectory()) {
//                throw new IOException("Invalid folder path: " + folderPath);
//            }
//
//            File[] files = folder.listFiles();
//            if (files != null) {
//                Arrays.stream(files)
//                        .filter(file -> file.isFile() && isImageFile(file))
//                        .forEach(file -> {
//                            System.out.println("Processing file: " + file.getName());
//                            // Perform your image processing here
//                        });
//            }
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    private boolean isImageFile(File file) {
//        String[] imageExtensions = { ".jpg", ".jpeg", ".png", ".bmp", ".gif" };
//        return Arrays.stream(imageExtensions)
//                .anyMatch(ext -> file.getName().toLowerCase().endsWith(ext));
//    }
//
//}