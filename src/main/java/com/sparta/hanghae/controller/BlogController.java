package com.sparta.hanghae.controller;

import com.sparta.hanghae.domain.Blog;
import com.sparta.hanghae.domain.BlogRepository;
import com.sparta.hanghae.domain.BlogRequestDto;
import com.sparta.hanghae.service.BlogService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

//    @GetMapping(value =  "/api/blog")
//    public String main (){
//        return "index";
//    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("postpage")
    public String postpage() {
        return "postpage";
    }
    @GetMapping("detail/{id}")
    public String detailpage(@PathVariable Long id, Model model) {
        blogRepository.findById(id).orElse(null);
        model.addAttribute("details",blogRepository.findById(id).orElse(null));
        return "detail";
    }
//    @GetMapping ("/api/blog/postpage")
//    public String postpage (Model model){
//        model.addAttribute("name", model);
//        return "postpage";
//    }

    @PostMapping("/api/blog/postpage")
    @ResponseBody
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @GetMapping("/api/blog")
    @ResponseBody
    public List<Blog> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }
//    @GetMapping("detail/{id}")
//    public ModelAndView findBoard(@PathVariable Long id){
//        ModelAndView mv = new ModelAndView();
//
//        mv.setViewName("detail");
//        return mv;
//    }
//    @GetMapping( "detail/{id}")
//    @ResponseBody
//    public Blog getBlogdetail(@PathVariable Long id) {
//
//        return blogRepository.findById(id).orElse(null);
//    }
//    @GetMapping("detail/{id}")
//    @ResponseBody
//    public ModelAndView findBoard(@PathVariable Long id){
//        ModelAndView mv = new ModelAndView();
//        Blog blog = blogRepository.findById(id).orElseThrow( () -> new NullPointerException("게시물이 없습니다."));
//        mv.setViewName("detail");
//        return mv;
//    }

    @DeleteMapping("/api/delete/{id}")
    @ResponseBody
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/blog/postpage/{id}")
    public Long updataBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.update(id, requestDto);
        return id;
    }
}
