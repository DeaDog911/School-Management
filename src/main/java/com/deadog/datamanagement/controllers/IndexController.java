package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.repositories.SubjectsRepository;
import com.deadog.datamanagement.repositories.TeachersRepository;
import com.deadog.datamanagement.services.StudentsService;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import com.deadog.datamanagement.services.TeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final SubjectsService subjectsService;
    private final StudyClassesService studyClassesService;
    private final TeachersService teachersService;
    private final StudentsService studentsService;

    public IndexController(SubjectsService subjectsService, StudyClassesService studyClassesService, TeachersService teachersService, StudentsService studentsService) {
        this.subjectsService = subjectsService;
        this.studyClassesService = studyClassesService;
        this.teachersService = teachersService;
        this.studentsService = studentsService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("subjects", subjectsService.findAll());
        model.addAttribute("studyClasses", studyClassesService.findAll());
        model.addAttribute("roomsCount", teachersService.getRoomsCount());
        model.addAttribute("students2Count", studentsService.getStudents2Count());
        model.addAttribute("students4Count", studentsService.getStudents4Count());
        model.addAttribute("students5Count", studentsService.getStudents5Count());
        return "info";
    }
}
