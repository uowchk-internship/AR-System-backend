package com.example.arsystembackend.entity.source;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "program_plan", schema = "ar-source", catalog = "")
public class ProgramPlan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "course")
    private String course;
    @Basic
    @Column(name = "credit")
    private Integer credit;
    @Basic
    @Column(name = "program")
    private String program;
    @Basic
    @Column(name = "type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramPlan that = (ProgramPlan) o;
        return id == that.id && Objects.equals(course, that.course) && Objects.equals(credit, that.credit) && Objects.equals(program, that.program) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, credit, program, type);
    }
}
