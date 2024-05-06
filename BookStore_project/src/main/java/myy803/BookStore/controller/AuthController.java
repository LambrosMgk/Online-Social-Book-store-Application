package myy803.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.BookStore.entity.Professor;
import myy803.BookStore.entity.Student;
import myy803.BookStore.entity.User;
import myy803.BookStore.service.IProfessorService;
import myy803.BookStore.service.IStudentService;
import myy803.BookStore.service.UserService;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    IStudentService studentService;
    @Autowired
    IProfessorService professorService;

    @RequestMapping("/login")
    public String login(){
        return "auth/signin";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model){
       
    	Student student;
    	Professor professor;
    	
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }

        
        if(user.getRole().toString().equals("STUDENT"))
        {
        	student = new Student();
        	student.setfullname(user.getUsername());
        	studentService.saveProfile(student);
        	student = studentService.retriveProfile(student.getfullname());
        	System.out.println("ID : " + student.getStudent_id());
        	user.setrole_id(student.getStudent_id());
        }
        else if(user.getRole().toString().equals("PROFESSOR"))
        {
        	professor = new Professor();
        	professor.setFullname(user.getUsername());
        	professorService.saveProfile(professor);
        	professor = professorService.retrieveProfile(professor.getFullname());
        	System.out.println("ID : " + professor.getProfessorid());
        	user.setrole_id(professor.getProfessorid());
        }
        
        userService.saveUser(user);
        System.out.println("User registered with role " + user.getRole().toString());
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }
}
