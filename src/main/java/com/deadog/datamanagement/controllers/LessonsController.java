package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.services.LessonsService;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import com.deadog.datamanagement.services.TeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lessons")
public class LessonsController {
    private final LessonsService lessonsService;
    private final StudyClassesService studyClassesService;
    private final SubjectsService subjectsService;
    private final TeachersService teachersService;


    public LessonsController(LessonsService lessonsService, StudyClassesService studyClassesService, SubjectsService subjectsService, TeachersService teachersService) {
        this.lessonsService = lessonsService;
        this.studyClassesService = studyClassesService;
        this.subjectsService = subjectsService;
        this.teachersService = teachersService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("lessons", lessonsService.findAll());
        return "lessons/index";
    }

    @GetMapping("/new")
    public String newLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("studyClasses", studyClassesService.findAll());
        model.addAttribute("subjects", subjectsService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "lessons/new";
    }

    @PostMapping("/new")
    public String createLesson(@ModelAttribute("lesson") Lesson lesson) {

        return "redirect:/lessons";
    }
}
