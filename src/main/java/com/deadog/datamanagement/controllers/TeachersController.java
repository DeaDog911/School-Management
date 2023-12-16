package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.models.StudyClass;
import com.deadog.datamanagement.models.Subject;
import com.deadog.datamanagement.models.Teacher;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import com.deadog.datamanagement.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    private final TeachersService teachersService;
    private final SubjectsService subjectsService;
    private final StudyClassesService studyClassesService;

    public TeachersController(TeachersService teachersService, SubjectsService subjectsService, StudyClassesService studyClassesService) {
        this.teachersService = teachersService;
        this.subjectsService = subjectsService;
        this.studyClassesService = studyClassesService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("teachers", teachersService.findAll());
        return "teachers/index";
    }

    @GetMapping("/new")
    public String newTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("subjects", subjectsService.findAll());
        model.addAttribute("studyClasses", studyClassesService.findAll());
        return "teachers/new";
    }

    @PostMapping()
    public String createTeacher(@RequestParam("name") String name,
                                @RequestParam("classroom") int classroom,
                                @RequestParam(value = "subjects", required = false) List<String> subjectsNames,
                                @RequestParam(value = "studyClasses", required = false) List<String> studyClassesNumbers) {
        if (subjectsNames == null)
            subjectsNames = new LinkedList<>();
        if (studyClassesNumbers == null) {
            studyClassesNumbers = new LinkedList<>();
        }
        Teacher teacher = teachersService.create(name, classroom, subjectsNames, studyClassesNumbers);
        teachersService.save(teacher);
        return "redirect:/teachers";
    }

    @PostMapping("{id}/delete")
    public String createTeacher(@PathVariable("id") int id) {
        Teacher teacher = teachersService.findById(id);
        teachersService.delete(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("{id}/edit")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", teachersService.findById(id));
        model.addAttribute("subjects", subjectsService.findAll());
        model.addAttribute("studyClasses", studyClassesService.findAll());
        return "teachers/edit";
    }

    @PostMapping("{id}/edit")
    public String updateTeacher(@PathVariable("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("classroom") int classroom,
                                @RequestParam(value = "subjects", required = false) List<String> subjectsNames,
                                @RequestParam(value = "studyClasses", required = false) List<String> studyClassesNumbers) {
        if (subjectsNames == null)
            subjectsNames = new LinkedList<>();
        if (studyClassesNumbers == null) {
            studyClassesNumbers = new LinkedList<>();
        }
        Teacher teacher = teachersService.create(name, classroom, subjectsNames, studyClassesNumbers);
        teachersService.update(id, teacher);
        return "redirect:/teachers";
    }

}
