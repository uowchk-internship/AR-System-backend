package com.example.arsystembackend.service.convert;

import com.example.arsystembackend.entity.report.GradeReport;
import com.example.arsystembackend.entity.report.GradeReportItem;
import com.example.arsystembackend.entity.source.*;
import com.example.arsystembackend.service.source.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class GradeReportConvertService {

    private Argo10Services argo10Services;
    private Argo11Services argo11Services;
    private Argo16Services argo16Services;
    private Argo29Services argo29Services;
    private ProgramPlanServices programPlanServices;
    private CgeServices cgeServices;
    private Map<String, String> courseGradeConvert = Map.of(
            "A+", "4.3", "A", "4", "A-", "3.7",
            "B+", "3.3", "B", "3", "B-", "2.7",
            "C+", "2.3", "C", "2", "C-", "1.7",
            "D", "1");

    @Autowired
    public GradeReportConvertService(Argo10Services argo10Services, Argo11Services argo11Services, Argo16Services argo16Services, Argo29Services argo29Services, ProgramPlanServices programPlanServices, CgeServices cgeServices) {
        this.argo10Services = argo10Services;
        this.argo11Services = argo11Services;
        this.argo16Services = argo16Services;
        this.argo29Services = argo29Services;
        this.programPlanServices = programPlanServices;
        this.cgeServices = cgeServices;
    }

    public GradeReport getSingleStudent(String sid) {
        //Get related information from db
        List<Argo10> argo10List = argo10Services.getSingleStudent(sid);
        Argo11 argo11 = argo11Services.getSingleStudent(sid);
        List<Argo16> argo16List = argo16Services.getSingleStudent(sid);
        List<Argo29> argo29List = argo29Services.getSingleStudent(sid);
        List<ProgramPlan> programPlanList = programPlanServices.getSingleProgramPlan(argo11.getProgCode().toUpperCase(Locale.ROOT));
        List<Cge> cgeList = cgeServices.getAll();

        GradeReport gradeReport = new GradeReport();

        //Report head
        gradeReport.setName(argo11.getLastName() + " " + argo11.getFirstName() + "" + argo11.getChineseName());
        gradeReport.setSid(argo11.getStudentId());
        gradeReport.setEmail(argo11.getCampusEmail());
        gradeReport.setProgram(argo11.getProgCode());
        gradeReport.setCohort(argo11.getCohort());
        gradeReport.setStatus(argo11.getAcadStatusDesc());

        //init the course list
        List<GradeReportItem> core = new ArrayList<>();
        List<GradeReportItem> language = new ArrayList<>();
        List<GradeReportItem> cge = new ArrayList<>();
        List<GradeReportItem> electiveGroup1 = new ArrayList<>();
        List<GradeReportItem> electiveGroup2 = new ArrayList<>();
        List<GradeReportItem> cgeOthers = new ArrayList<>();

        //init required course codes
        List<String> req_core = new ArrayList<>();
        List<String> req_language = new ArrayList<>();
        List<String> req_cge = new ArrayList<>();
        List<String> req_elective1 = new ArrayList<>();
        List<String> req_elective2 = new ArrayList<>();

        //create required courses item
        for (ProgramPlan programPlan : programPlanList) {
            if (programPlan.getType().equals("English") || programPlan.getType().equals("Chinese")) {
                req_language.add(programPlan.getCourse());
            } else if (programPlan.getType().equals("Core")) {
                req_core.add(programPlan.getCourse());
            } else if (programPlan.getType().equals("E1")) {
                req_elective1.add(programPlan.getCourse());
            } else if (programPlan.getType().equals("E2")) {
                req_elective2.add(programPlan.getCourse());
            }
        }

        //Put course into the list
        for (Argo10 argo10 : argo10List) {
            String courseCode = argo10.getShrtcknSubjCode() + argo10.getShrtcknCrseNumb();
            GradeReportItem newGradeReportItem = new GradeReportItem(courseCode,
                    argo10.getShrtcknCrseTitle(),
                    argo10.getShrtckgGrdeCodeFinal(),
                    courseGradeConvert.get(argo10.getShrtckgGrdeCodeFinal()),
                    argo10.getShrtckgCreditHours(),
                    "");
            if (req_core.contains(courseCode)) {
                int i = req_core.indexOf(courseCode);
                core.add(newGradeReportItem);
                req_core.remove(i);
            } else if (req_language.contains(courseCode)) {
                int i = req_language.indexOf(courseCode);
                language.add(newGradeReportItem);
                req_language.remove(i);
            } else if (req_cge.contains(courseCode)) {
                int i = req_cge.indexOf(courseCode);
                cge.add(newGradeReportItem);
                req_cge.remove(i);
            } else if (req_elective1.contains(courseCode)) {
                int i = req_elective1.indexOf(courseCode);
                electiveGroup1.add(newGradeReportItem);
                req_elective1.remove(i);
            } else if (req_elective2.contains(courseCode)) {
                int i = req_elective2.indexOf(courseCode);
                electiveGroup2.add(newGradeReportItem);
                req_elective2.remove(i);
            } else {
                cgeOthers.add(newGradeReportItem);
            }
        }

        //Add empty item to list
        for (String item:req_core){
            core.add(new GradeReportItem(item));
        }
        for (String item:req_language){
            language.add(new GradeReportItem(item));
        }
        for (String item:req_cge){
            cge.add(new GradeReportItem(item));
        }
        for (String item:req_elective1){
            electiveGroup1.add(new GradeReportItem(item));
        }
        for (String item:req_elective2){
            electiveGroup2.add(new GradeReportItem(item));
        }

        //Set values to gradeReport
        gradeReport.setCore(core);
        gradeReport.setLanguage(language);
        gradeReport.setCge(cge);
        gradeReport.setElectiveGroup1(electiveGroup1);
        gradeReport.setElectiveGroup2(electiveGroup2);
        gradeReport.setCgeOthers(cgeOthers);

        return gradeReport;
    }
}
