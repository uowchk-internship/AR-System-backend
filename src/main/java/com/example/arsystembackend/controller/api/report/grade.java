package com.example.arsystembackend.controller.api.report;

import com.example.arsystembackend.entity.report.GradeReport;
import com.example.arsystembackend.service.convert.GradeReportConvertService;
import com.example.arsystembackend.service.report.GradeReportServices;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/report/grade")
public class grade {

    private GradeReportConvertService gradeReportConvertService;

    @Autowired
    public grade(GradeReportConvertService gradeReportConvertService) {
        this.gradeReportConvertService = gradeReportConvertService;
    }

    @GetMapping("/")
    public void downloadSingleDeliveryNoteCsv(HttpServletResponse response) throws IOException {
//        String fileHeader = "attachment; filename="+"demo"+".pdf";
        String fileHeader = "inline; filename=" + "demo" + ".pdf";
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", fileHeader);

        //Get student info
        GradeReport reportInfo = gradeReportConvertService.getSingleStudent("70001235");

        GradeReportServices gradeReportServices = new GradeReportServices();
        gradeReportServices.export(response.getOutputStream(), reportInfo);
    }

    @GetMapping("/zip")
    public void downloadZip(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=GradeReports.zip");

        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());

        //Get student info
        List<GradeReport> reportList = gradeReportConvertService.getAllStudents();

        System.out.println("Total reports generated: "+reportList.size());

        for (GradeReport gradeReport : reportList) {
            String filename = gradeReport.getSid() + ".pdf";
            ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
            GradeReportServices gradeReportServices = new GradeReportServices();
            gradeReportServices.export(fileOutputStream, gradeReport);


            byte[] bytes = fileOutputStream.toByteArray();

            ZipEntry zipEntry = new ZipEntry(filename);
            zipOut.putNextEntry(zipEntry);

            zipOut.write(bytes);

            zipOut.closeEntry();
        }


        zipOut.finish();
        zipOut.close();


    }

}
