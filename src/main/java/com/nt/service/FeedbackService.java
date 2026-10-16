package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.entity.Feedback;
import com.nt.repo.FeedbackRepo;

@Service
public class FeedbackService {

    private final FeedbackRepo repo;

    public FeedbackService(FeedbackRepo repo) {
        this.repo = repo;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return repo.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return repo.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Feedback not found"));
    }

    public void deleteFeedback(Long id) {
        repo.deleteById(id);
    }
}