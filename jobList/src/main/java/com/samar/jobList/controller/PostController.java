package com.samar.jobList.controller;

import com.samar.jobList.repository.PostRepository;
import com.samar.jobList.model.Post;
import com.samar.jobList.repository.SearchRepository;
import com.samar.jobList.repository.SearchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PostController {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository srepo;


    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
            response.sendRedirect("/hello");
    }
    @GetMapping("/allPost")
    public List<Post> getAllPost(){
        return repo.findAll();
    }
    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return srepo.findByText(text);
    }
    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
      return   repo.save(post);

    }
}
