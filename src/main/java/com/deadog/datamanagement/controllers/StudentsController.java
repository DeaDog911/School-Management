package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.models.Grade;
import com.deadog.datamanagement.models.Student;
import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.models.Subject;
import com.deadog.datamanagement.services.GradesService;
import com.deadog.datamanagement.services.StudentsService;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudyClassesService studyClassesService;
    private final StudentsService studentsService;
    private final SubjectsService subjectsService;
    private final GradesService gradesService;

    public StudentsController(StudyClassesService studyClassesService, StudentsService studentsService, SubjectsService subjectsService, GradesService gradesService) {
        this.studyClassesService = studyClassesService;
        this.studentsService = studentsService;
        this.subjectsService = subjectsService;
        this.gradesService = gradesService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", studentsService.findAll());
        return "students/index";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("studyClasses", studyClassesService.findAll());
        return "students/new";
    }

    @PostMapping()
    public String createStudent(@ModelAttribute("student") Student student) {
        studentsService.save(student);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id) {
        studentsService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable("id") int id, Model model) {
        Student student = studentsService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("studyClasses", studyClassesService.findAll());
        return "students/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute("student") Student student) {
        studentsService.update(id, student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/grades")
    public String showGrades(@PathVariable("id") int id, Model model) {
        Student student = studentsService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("grade", new Grade());
        model.addAttribute("subjects", subjectsService.findAll());
        return "students/grades";
    }

    @PostMapping("/{id}/grades")
    public String addGrade(@PathVariable("id") int id, @ModelAttribute("grade") Grade grade) {
        Student student = studentsService.findById(id);
        grade.setStudent(student);
        gradesService.create(grade);
        return "redirect:/students/"+id+"/grades";
    }

    @GetMapping("/{stud_id}/grades/{grade_id}/edit")
    public String editGrade(@PathVariable("stud_id") int stud_id, @PathVariable("grade_id") int grade_id,
                            Model model) {
        Grade grade = gradesService.findById(grade_id);
        model.addAttribute("grade", grade);
        return "students/edit_grade";
    }

    @PostMapping("/{id}/grades/{grade_id}/edit")
    public String updateGrade(@PathVariable("id") int id,
                              @PathVariable("grade_id") int grade_id,
                              @ModelAttribute("grade") Grade grade) {
        gradesService.update(grade_id, grade);
        return "redirect:/students/" + id + "/grades";
    }

    @GetMapping("/classes/new")
    public String newClass(@ModelAttribute("class") StudyClass studyClass) {
        return "classes/new";
    }

    @PostMapping("/classes")
    public String createStudyClass(@ModelAttribute("class") StudyClass studyClass) {
        studyClassesService.save(studyClass);
        return "redirect:/students";
    }

    @GetMapping("/subjects/new")
    public String newSubject(@ModelAttribute("subject") Subject subject) {
        return "subjects/new";
    }

    @PostMapping("/subjects")
    public String createSubject(@ModelAttribute("subject") Subject subject) {
        subjectsService.save(subject);
        return "redirect:/students";
    }
}
