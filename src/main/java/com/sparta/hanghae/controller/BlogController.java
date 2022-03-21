package com.sparta.hanghae.controller;

import com.sparta.hanghae.domain.Blog;
import com.sparta.hanghae.domain.BlogRepository;
import com.sparta.hanghae.domain.BlogRequestDto;
import com.sparta.hanghae.service.BlogService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/blog/postpage")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @GetMapping("/api/blog")
    public List<Blog> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/memos/{id}")
    public Long updataBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.update(id, requestDto);
        return id;
    }
}