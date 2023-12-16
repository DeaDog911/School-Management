package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import com.deadog.datamanagement.services.TeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class StudyClassesController {
    private final StudyClassesService studyClassesService;
    private final TeachersService teachersService;
    private final SubjectsService subjectsService;


    public StudyClassesController(StudyClassesService studyClassesService, TeachersService teachersService, SubjectsService subjectsService) {
        this.studyClassesService = studyClassesService;
        this.teachersService = teachersService;
        this.subjectsService = subjectsService;
    }

    @GetMapping
    public String index(@RequestParam(value = "teacher", required = false, defaultValue = "-1") int teacherId,
                        @RequestParam(value = "subject", required = false, defaultValue = "-1") int subjectId, Model model) {
        model.addAttribute("studyClasses", studyClassesService.findAllWithParams(teacherId, subjectId));
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("subjects", subjectsService.findAll());
        return "classes/index";
    }

    @GetMapping("/new")
    public String newClass(@ModelAttribute("class") StudyClass studyClass) {
        return "classes/new";
    }

    @PostMapping
    public String createStudyClass(@ModelAttribute("class") StudyClass studyClass) {
        studyClassesService.save(studyClass);
        return "redirect:/classes";
    }

    @PostMapping("/{id}/delete")
    public String createStudyClass(@PathVariable("id") int studyClassId) {
        studyClassesService.deleteById(studyClassId);
        return "redirect:/classes";
    }

    @GetMapping("/{id}/edit")
    public String editStudyClass(@PathVariable("id") int studyClassId, Model model) {
        model.addAttribute("studyClass", studyClassesService.findById(studyClassId));
        return "classes/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateStudyClass(@PathVariable("id") int studyClassId, @ModelAttribute("studyClass") StudyClass studyClass) {
        studyClassesService.update(studyClassId, studyClass);
        return "redirect:/classes";
    }
}
