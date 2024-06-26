package org.img.to.pdf.com.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.img.to.pdf.com.Application;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;


@Slf4j
@CrossOrigin("*")
@RestController
public class Controller {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired(required = false)
    private Job readImagesJob;
    @RequestMapping("/get/docx")
    public ResponseEntity<?> getEmpData() {
        Document document = null;
        ResponseEntity<?> response = null;
        try {
            // Create a Tesseract instance
            ITesseract instance = new Tesseract();
            // Set the tessdata path
            instance.setDatapath("C:\\Users\\aninmazu\\OneDrive - Capgemini\\Capgemini\\POC\\IMGTOPDF\\langs");
            instance.setTessVariable("debug_file", "/dev/null");


            //Fetch All Img Files from src Location
            File folder = new File("C:\\Users\\aninmazu\\OneDrive - Capgemini\\Capgemini\\POC\\IMGTOPDF\\in");
            File[] files = folder.listFiles();
            String result = "";
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());

                    // Read the input image
                    File imageFile = new File("C:\\Users\\aninmazu\\OneDrive - Capgemini\\Capgemini\\POC\\IMGTOPDF\\in\\" + file.getName());

                    result = result + "\n" + instance.doOCR(imageFile);

                    // Print the result
                    log.info(result);
                }
            }


            document = new Document(PageSize.A4, 20, 20, 20, 20);
            document.addAuthor("authorname");
            document.addTitle("This is my pdf doc");

            PdfWriter.getInstance(document, new FileOutputStream("fileout.pdf"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();


            // Set the font
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);//Font.BOLD

            // Add a paragraph to the document
            Paragraph paragraph = new Paragraph(result, font);
            document.add(paragraph);

            // Close the document
            document.close();


            //PDF
            HttpHeaders responseHeaders = new HttpHeaders();

            InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
            response = new ResponseEntity<>(inputStreamResource,
                    responseHeaders,
                    HttpStatus.OK);


            log.info("Image converted to DOCX successfully!");

        } catch (Exception ex) {
            log.error("Exception: {}", ex);
        }
        return response;
    }








//    @RequestMapping("/get/pdf/job")
//    public void launchJob() throws Exception {
//        jobLauncher.run(readImagesJob, new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters());
//    }
}



