package com.codingdojo.moviecourt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.moviecourt.models.Comment;
import com.codingdojo.moviecourt.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository cRepo;

    public Comment createComment(Comment comment) {
        return cRepo.save(comment);
    }
    
    public Comment getCommentById(Long id) {
        return cRepo.findById(id).orElse(null);
    }

    public void deleteComment(Long id) {
        cRepo.deleteById(id);
    }
}

