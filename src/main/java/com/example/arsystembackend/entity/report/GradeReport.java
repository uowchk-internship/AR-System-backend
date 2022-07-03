package com.example.arsystembackend.entity.report;


import com.example.arsystembackend.entity.source.*;

import java.util.List;

public class GradeReport {
    //Report head
    private String name;
    private String sid;
    private String email;
    private String program;
    private String cohort;
    private String status;

    private List<GradeReportItem> core;
    private List<GradeReportItem> language;
    private List<GradeReportItem> cge;
    private List<GradeReportItem> electiveGroup1;
    private List<GradeReportItem> electiveGroup2;
    private List<GradeReportItem> cgeOthers;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GradeReportItem> getCore() {
        return core;
    }

    public void setCore(List<GradeReportItem> core) {
        this.core = core;
    }

    public List<GradeReportItem> getLanguage() {
        return language;
    }

    public void setLanguage(List<GradeReportItem> language) {
        this.language = language;
    }

    public List<GradeReportItem> getCge() {
        return cge;
    }

    public void setCge(List<GradeReportItem> cge) {
        this.cge = cge;
    }

    public List<GradeReportItem> getElectiveGroup1() {
        return electiveGroup1;
    }

    public void setElectiveGroup1(List<GradeReportItem> electiveGroup1) {
        this.electiveGroup1 = electiveGroup1;
    }

    public List<GradeReportItem> getElectiveGroup2() {
        return electiveGroup2;
    }

    public void setElectiveGroup2(List<GradeReportItem> electiveGroup2) {
        this.electiveGroup2 = electiveGroup2;
    }

    public List<GradeReportItem> getCgeOthers() {
        return cgeOthers;
    }

    public void setCgeOthers(List<GradeReportItem> cgeOthers) {
        this.cgeOthers = cgeOthers;
    }
}
