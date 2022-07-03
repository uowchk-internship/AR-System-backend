package com.example.arsystembackend.service.report;

import com.example.arsystembackend.entity.report.GradeReport;
import com.example.arsystembackend.entity.report.GradeReportItem;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class GradeReportServices {
    private GradeReport studentReport;

    public void export(OutputStream response, GradeReport studentReport) throws DocumentException, IOException {
        this.studentReport = studentReport;

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);

        Paragraph p = new Paragraph("Grade Draft", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        document.add(studentInfo());
        document.add(detailTable());

        document.close();

    }

    private PdfPTable studentInfo() {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 1.5f, 3.5f});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);

        //Fullname
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getName(), font));
        table.addCell(cell);

        //SID
        cell.setPhrase(new Phrase("SID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getSid(), font));
        table.addCell(cell);


        //Email
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getEmail(), font));
        table.addCell(cell);


        //Programme
        cell.setPhrase(new Phrase("Programme", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getProgram(), font));
        table.addCell(cell);

        //Entry Cohort
        cell.setPhrase(new Phrase("Entry Cohort", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getCohort(), font));
        table.addCell(cell);

        //Status
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(studentReport.getStatus(), font));
        table.addCell(cell);


        return table;
    }

    private PdfPTable detailTable() {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.8f, 3.5f, 0.8f, 0.8f, 1f, 0.8f});
//        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBackgroundColor(Color.GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);
        font.setColor(Color.WHITE);

        detailTableSectionHeader(table, "Core Course");
        for (GradeReportItem gradeReportItem : studentReport.getCore()) {
            detailTableRow(table, gradeReportItem);
        }
        detailTableSectionHeader(table, "Language Course");
        for (GradeReportItem gradeReportItem : studentReport.getLanguage()) {
            detailTableRow(table, gradeReportItem);
        }
        if (studentReport.getCge().size() != 0) {
            detailTableSectionHeader(table, "CGE Course");
            for (GradeReportItem gradeReportItem : studentReport.getCge()) {
                detailTableRow(table, gradeReportItem);
            }
        }
        if (studentReport.getElectiveGroup1().size() != 0) {
            detailTableSectionHeader(table, "Elective Group 1");
            for (GradeReportItem gradeReportItem : studentReport.getElectiveGroup1()) {
                detailTableRow(table, gradeReportItem);
            }
        }
        if (studentReport.getElectiveGroup2().size() != 0) {
            detailTableSectionHeader(table, "Elective Group 2");
            for (GradeReportItem gradeReportItem : studentReport.getElectiveGroup2()) {
                detailTableRow(table, gradeReportItem);
            }
        }
        detailTableSectionHeader(table, "Others");
        for (GradeReportItem gradeReportItem : studentReport.getCgeOthers()) {
            detailTableRow(table, gradeReportItem);
        }


        return table;
    }

    private void detailTableSectionHeader(PdfPTable table, String sectionName) {
        PdfPCell cell = new PdfPCell();
//        cell.setPadding(5);
        cell.setBackgroundColor(Color.GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase(sectionName, font));
        cell.setColspan(6);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Course", font));
        cell.setColspan(0);
        table.addCell(cell);
        cell.setPhrase(new Phrase("Course Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Grade", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Grade Point", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Earned Credit", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Remarks", font));
        table.addCell(cell);

    }

    private void detailTableRow(PdfPTable table, GradeReportItem gradeReportItem) {
        PdfPCell cell = new PdfPCell();
//        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);

        if (gradeReportItem.getGradePoint() == null && gradeReportItem.getGrade() != null) {
            gradeReportItem.setGradePoint("0");
        }

        cell.setPhrase(new Phrase(gradeReportItem.getCourseCode(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(gradeReportItem.getCourseName(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(gradeReportItem.getGrade(), font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase(gradeReportItem.getGradePoint(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(gradeReportItem.getCredit()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(gradeReportItem.getRemarks(), font));
        table.addCell(cell);

    }


}
