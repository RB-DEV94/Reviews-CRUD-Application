package com.Project.backend.Controller;


import com.Project.backend.Dto.PostDto;
import com.Project.backend.Model.Post;
import com.Project.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;


    @RequestMapping("/home")
    public String landHomePage(Model model) {
        model.addAttribute("listPosts",postService.getAllPosts());
        return "home.html";
    }

    @GetMapping("/createPost")
    public String createPost(Model model) {
         Post post=new Post();
         model.addAttribute("post",post);
         System.out.println(model.toString());
        return "createPostPage.html";
    }
    @PostMapping("/savePost")
    public String saveEmployee(@ModelAttribute("post") Post post) {

        postService.createPost(post);

        return "redirect:/createPost?success";
    }

    @GetMapping("/showUpdate/{id}")
    public String ShowUpdateForm(@PathVariable(value = "id") Long id, Model model){

        Post post=postService.getPostById(id);
         model.addAttribute("post",post);
        return "updateForm";
    }
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id){

        postService.DeleteById(id);

        return "redirect:/home?success";

    }
}