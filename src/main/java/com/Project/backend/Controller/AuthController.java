package com.Project.backend.Controller;

import com.Project.backend.Dto.UserDto;
import com.Project.backend.Model.Post;
import com.Project.backend.Model.User;
import com.Project.backend.service.PostService;
import com.Project.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/registeradmin")
    public String showAdminRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registeradmin";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/adminlogin")
    public String Adminlogin(){
        return "adminlogin";
    }



    @PostMapping(value={"/register/save","/adminregister/adminsave"})
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model,final HttpServletRequest request){


        User existingUser = userService.findUserByEmail(userDto.getEmail());


        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }


        final String url = request.getRequestURI();
        System.out.println(url);
        //admin or user
        if (url.contains("admin")) {
            System.out.println("hello admin");
            userService.saveAdmin(userDto);
        }
        else
        {
            System.out.println("hello user");
            userService.saveUser(userDto);
        }


        return "redirect:/register?success";
    }


    @GetMapping("/admin")
    public String admin(Model model){

        model.addAttribute("listPosts",postService.getAllPosts());
        return "home";
    }
    @GetMapping("/users")
    public String users(){


        return "User";
    }



}
