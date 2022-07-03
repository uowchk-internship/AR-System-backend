package com.example.arsystembackend.entity.report;

import java.math.BigDecimal;

public class GradeReportItem {
    private String courseCode;
    private String courseName;
    private String grade;
    private String gradePoint;
    private int credit;
    private String remarks;

    public GradeReportItem(String courseCode) {
        this.courseCode = courseCode;
    }

    public GradeReportItem(String courseCode, String courseName, String grade, String gradePoint, int credit, String remarks) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.grade = grade;
        this.gradePoint = gradePoint;
        this.credit = credit;
        this.remarks = remarks;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
