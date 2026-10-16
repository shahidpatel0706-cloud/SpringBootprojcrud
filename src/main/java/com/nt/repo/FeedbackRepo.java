package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nt.entity.Feedback;

public interface FeedbackRepo
        extends JpaRepository<Feedback, Long> {
}