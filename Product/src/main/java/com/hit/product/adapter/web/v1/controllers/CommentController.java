package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.CommentService;
import com.hit.product.domains.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("")
    public ResponseEntity<?> getComments() {
        return VsResponseUtil.ok(commentService.getComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(commentService.getCommentById(id));
    }

    @PostMapping("/{idUser}/{idProductRate}")
    public ResponseEntity<?> createCommentLv1(@PathVariable("idUser") Long idUser, @PathVariable("idProductRate") Long idProductRate, @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.createComment(idUser, idProductRate, commentDto));
    }

    @PostMapping("/{idUser}/{idProductRate}/{idCommentParent}")
    public ResponseEntity<?> createCommentLvN(@PathVariable("idUser") Long idUser, @PathVariable("idProductRate") Long idProductRate, @PathVariable("idCommentParent") Long idCommentParent, @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.createCommentChild(idUser, idProductRate, idCommentParent, commentDto));
    }

    @PatchMapping("/{idComment}")
    public ResponseEntity<?> createCommentLvN(@PathVariable("idComment") Long idComment, @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.updateComment(idComment, commentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(commentService.deleteComment(id));
    }

}
