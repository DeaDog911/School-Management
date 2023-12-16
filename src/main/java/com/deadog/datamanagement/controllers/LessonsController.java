package com.deadog.datamanagement.controllers;

import com.deadog.datamanagement.models.Lesson;
import com.deadog.datamanagement.services.LessonsService;
import com.deadog.datamanagement.services.StudyClassesService;
import com.deadog.datamanagement.services.SubjectsService;
import com.deadog.datamanagement.services.TeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(@RequestParam(value = "studyClass", required = false, defaultValue = "-1") int studyClassId,
                        @RequestParam(value = "dayOfWeek", required = false, defaultValue = "-1") int dayOfWeek,
                        @RequestParam(value = "ordinalNumber", required = false, defaultValue = "-1") int ordinalNumber,
                        Model model) {
        model.addAttribute("lessons", lessonsService.findAllWithParams(studyClassId, dayOfWeek, ordinalNumber));
        model.addAttribute("studyClasses", studyClassesService.findAll());
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
        lessonsService.save(lesson);
        return "redirect:/lessons";
    }

    @PostMapping("/{class_id}/{day_of_week}/{ordinal_number}/delete")
    public String deleteLesson(@PathVariable("class_id") int classId, @PathVariable("day_of_week") int dayOfWeek,
                               @PathVariable("ordinal_number") int ordinalNumber) {
        Lesson lesson = lessonsService.findByPk(classId, dayOfWeek, ordinalNumber);
        lessonsService.delete(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/{class_id}/{day_of_week}/{ordinal_number}/edit")
    public String editLesson(@PathVariable("class_id") int classId, @PathVariable("day_of_week") int dayOfWeek,
                               @PathVariable("ordinal_number") int ordinalNumber, Model model) {
        Lesson lesson = lessonsService.findByPk(classId, dayOfWeek, ordinalNumber);
        model.addAttribute("lesson", lesson);
        model.addAttribute("studyClasses", studyClassesService.findAll());
        model.addAttribute("subjects", subjectsService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "lessons/edit";
    }

    @PostMapping("/{class_id}/{day_of_week}/{ordinal_number}/edit")
    public String updateLesson(@PathVariable("class_id") int classId, @PathVariable("day_of_week") int dayOfWeek,
                             @PathVariable("ordinal_number") int ordinalNumber, @ModelAttribute("lesson") Lesson lesson) {
        lessonsService.update(classId, dayOfWeek, ordinalNumber, lesson);
        return "redirect:/lessons";
    }
}
