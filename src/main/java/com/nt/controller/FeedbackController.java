package com.nt.controller;

import com.nt.entity.Feedback;
import com.nt.service.FeedbackService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("editing", false);
        return "feedback-form";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute Feedback feedback) {
        service.saveFeedback(feedback);
        return "redirect:/feedback/list";
    }

    @GetMapping("/list")
    public String showFeedback(Model model) {
        model.addAttribute(
            "feedbackList", service.getAllFeedback()
        );
        return "feedback-list";
    }

    @GetMapping("/edit/{id}")
    public String editFeedback(
            @PathVariable Long id, Model model) {

        model.addAttribute(
            "feedback", service.getFeedbackById(id)
        );
        model.addAttribute("editing", true);
        return "feedback-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        service.deleteFeedback(id);
        return "redirect:/feedback/list";
    }
}