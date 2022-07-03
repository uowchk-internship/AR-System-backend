package com.example.arsystembackend.controller.api.report;

import com.example.arsystembackend.entity.report.GradeReport;
import com.example.arsystembackend.service.convert.GradeReportConvertService;
import com.example.arsystembackend.service.report.GradeReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String fileHeader = "inline; filename="+"demo"+".pdf";
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", fileHeader);

        //Get student info
        GradeReport reportInfo = gradeReportConvertService.getSingleStudent("70001235");

        GradeReportServices gradeReportServices = new GradeReportServices();
        gradeReportServices.export(response, reportInfo);
    }

}
