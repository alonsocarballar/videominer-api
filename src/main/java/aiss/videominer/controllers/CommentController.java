package aiss.videominer.controllers;


import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El comentario no existe");
        }

        return comment.get();
    }

}
