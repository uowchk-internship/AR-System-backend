package com.example.arsystembackend.service.convert;

import com.example.arsystembackend.entity.report.GradeReport;
import com.example.arsystembackend.entity.report.GradeReportItem;
import com.example.arsystembackend.entity.source.*;
import com.example.arsystembackend.service.source.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeReportConvertService {
    private Argo10Services argo10Services;
    private Argo11Services argo11Services;
    private Argo16Services argo16Services;
    private Argo29Services argo29Services;
    private ProgramPlanServices programPlanServices;
    private CgeServices cgeServices;
    private Map<String, String> courseGradeConvert = new HashMap<>();

    //Get related information from db

    Map<String, List<Argo10>> argo10Map = new HashMap<>();
    Map<String, Argo11> argo11Map = new HashMap<>();
    Map<String, List<Argo16>> argo16Map = new HashMap<>();
    Map<String, List<Argo29>> argo29Map = new HashMap<>();
    Map<String, List<ProgramPlan>> programPlanMap = new HashMap<>();
    Map<String, Cge> cgeMap = new HashMap<>();


    @Autowired

    public GradeReportConvertService(Argo10Services argo10Services, Argo11Services argo11Services, Argo16Services argo16Services, Argo29Services argo29Services, ProgramPlanServices programPlanServices, CgeServices cgeServices) {
        this.argo10Services = argo10Services;
        this.argo11Services = argo11Services;
        this.argo16Services = argo16Services;
        this.argo29Services = argo29Services;
        this.programPlanServices = programPlanServices;
        this.cgeServices = cgeServices;

        this.courseGradeConvert.put("A+","4.3");
        this.courseGradeConvert.put("A","4");
        this.courseGradeConvert.put("A-","3.7");
        this.courseGradeConvert.put("B+","3.3");
        this.courseGradeConvert.put("B","3");
        this.courseGradeConvert.put("B-","2.7");
        this.courseGradeConvert.put("C+","2.3");
        this.courseGradeConvert.put("C","2");
        this.courseGradeConvert.put("C-","1.7");
        this.courseGradeConvert.put("D","1");

    }

    public void updateHashmap(){
        List<Argo10> fullArgo10List = argo10Services.getAll();
        List<Argo11> fullArgo11List = argo11Services.getAll();
        List<Argo16> fullArgo16List = argo16Services.getAll();
        List<Argo29> fullArgo29List = argo29Services.getAll();
        List<ProgramPlan> fullProgramPlanList = programPlanServices.getAll();
        List<Cge> fullCgeList = cgeServices.getAll();

        for (Argo10 item : fullArgo10List) {

            List<Argo10> valueInMap = argo10Map.get(item.getStudentId());
            if (valueInMap != null) {
                valueInMap.add(item);
                argo10Map.put(item.getStudentId(), valueInMap);
            } else {
                valueInMap = new ArrayList<>();
                valueInMap.add(item);
                argo10Map.put(item.getStudentId(), valueInMap);
            }

        }
        for (Argo11 item : fullArgo11List) {
            argo11Map.put(item.getStudentId(), item);
        }
        for (Argo16 item : fullArgo16List) {
            List<Argo16> valueInMap = argo16Map.get(item.getStudId());
            if (valueInMap != null) {
                valueInMap.add(item);
                argo16Map.put(item.getStudId(), valueInMap);
            } else {
                valueInMap = new ArrayList<>();
                valueInMap.add(item);
                argo16Map.put(item.getStudId(), valueInMap);
            }

        }
        for (Argo29 item : fullArgo29List) {
            List<Argo29> valueInMap = argo29Map.get(item.getSpridenId());
            if (valueInMap != null) {
                valueInMap.add(item);
                argo29Map.put(item.getSpridenId(), valueInMap);
            } else {
                valueInMap = new ArrayList<>();
                valueInMap.add(item);
                argo29Map.put(item.getSpridenId(), valueInMap);
            }

        }
        for (ProgramPlan item : fullProgramPlanList) {
            List<ProgramPlan> valueInMap = programPlanMap.get(item.getProgram());
            if (valueInMap != null) {
                valueInMap.add(item);
                programPlanMap.put(item.getProgram(), valueInMap);
            } else {
                valueInMap = new ArrayList<>();
                valueInMap.add(item);
                programPlanMap.put(item.getProgram(), valueInMap);
            }

        }
        for (Cge item : fullCgeList) {
            cgeMap.put(item.getCode(), item);
        }


    }

    public GradeReport getSingleStudent(String sid, boolean multiple) {
        if (!multiple){
            updateHashmap();
        }
        //Get related information from db
        List<Argo10> argo10List = argo10Map.get(sid);
        Argo11 argo11 = argo11Map.get(sid);
        List<Argo16> argo16List = argo16Map.get(sid);
        List<Argo29> argo29List = argo29Map.get(sid);
        List<ProgramPlan> programPlanList = programPlanMap.get(argo11.getProgCode().toUpperCase());
//        List<Cge> cgeList = cgeServices.getAll();

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
        if (programPlanList != null) {
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
        for (String item : req_core) {
            core.add(new GradeReportItem(item));
        }
        for (String item : req_language) {
            language.add(new GradeReportItem(item));
        }
        for (String item : req_cge) {
            cge.add(new GradeReportItem(item));
        }
        for (String item : req_elective1) {
            electiveGroup1.add(new GradeReportItem(item));
        }
        for (String item : req_elective2) {
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

    public List<GradeReport> getAllStudents() {
        List<GradeReport> gradeReports = new ArrayList<>();
        List<Argo11> allStudents = argo11Services.getAll();

        int noProgram = 0;
        int noArgo11 = 0;
        for (Argo11 argo11 : allStudents) {
            if (programPlanMap.get(argo11.getProgCode().toUpperCase()) != null && argo10Map.get(argo11.getStudentId()) != null) {
                gradeReports.add(getSingleStudent(argo11.getStudentId(),true));
            } else if (programPlanMap.get(argo11.getProgCode().toUpperCase()) != null) {
                noProgram++;
            } else if (argo10Map.get(argo11.getStudentId()) != null) {
                if (noArgo11 == 0) {
                    System.out.println(argo11.getStudentId());
                }
                noArgo11++;
            }
        }
        System.out.println("noProgram: " + noProgram + ", noArgo11: " + noArgo11);
        return gradeReports;
    }
}
