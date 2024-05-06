package myy803.BookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.BookStore.entity.Application;
import myy803.BookStore.entity.Professor;
import myy803.BookStore.entity.Student;
import myy803.BookStore.entity.Subject;
import myy803.BookStore.entity.User;
import myy803.BookStore.service.IApplicationService;
import myy803.BookStore.service.IProfessorService;
import myy803.BookStore.service.IStudentService;
import myy803.BookStore.service.SubjectService;
import myy803.BookStore.service.UserService;

@Controller
public class StudentController {
	
	@Autowired
    UserService userService;
    
	@Autowired
    IStudentService studentService;
    
    @Autowired
    IProfessorService professorService;
    
    @Autowired
    IApplicationService applicationService;
	
    @Autowired
    SubjectService subjectService;
    
    
    @RequestMapping("/student/dashboard")
    public String getStudentHome(){
        return "student/dashboard";
    }
    
    @RequestMapping("/student/set-profile")
    public String set_profile(Model model){
    	Student student = new Student();
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	user = userService.findById(authentication.getName());
    	student = studentService.findStudentById(user.getrole_id());	//this should not fail (e.g. null student) because to get here you have to be logged in with an account
    	model.addAttribute("student", student);
    	return "/student/set-profile";
    }
    
    @RequestMapping("/student/save-profile")
    public String save_profile(@ModelAttribute("student") Student student, Model model){
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	user = userService.findById(authentication.getName());
    	student.setStudent_id(user.getrole_id());	//because of spring url security only the role "STUDENT" will be allowed into urls with "student" in them, so i don't check for role
        
		studentService.saveProfile(student);
    	
    	model.addAttribute("successMessage", "Update successfull!");
    	return "/student/set-profile";
    }
    
    @RequestMapping("/student/view-professors")
    public String view_subjects(Model model){
    	
    	
    	List<Professor> professors = professorService.retrieveAllProfessors();

    	// Add the items list to the model
        model.addAttribute("items", professors);
    	
        return "/student/view-professors";
    }
    
    
    @RequestMapping("/student/professor-details")
    public String view_professor_details(@ModelAttribute("professor") Professor professor, Model model){
    	
    	Professor   prof = professorService.findProfessorById(professor.getProfessorid());
    	List<Subject> subs = prof.getSubjects();    	
    	model.addAttribute("items", subs);
 
        return "/student/professor-details";
    }
    
    
    @RequestMapping("/student/set-application")
    public String applyToSubject(@ModelAttribute("subject") Subject subject,Model model){
    	Application app = new Application();
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());
    	Student stud = studentService.findStudentById(user.getrole_id());
    	Subject sub = subjectService.findById(subject.getSubjectid());
    	
    	app.setSubjectid(subject.getSubjectid());
    	app.setStudentid(stud.getStudent_id());
    	app.setFullname(stud.getfullname());
    	app.setRemaining_courses_for_graduation(stud.getremainingcoursesforgraduation());
    	
    	//System.out.println(sub.getSubjectid()+"e");
    	//System.out.println(sub);
    	
    	model.addAttribute("app", app);
    	model.addAttribute("subject", sub);
    	//model.addAttribute("successMessage", "successfull Submit!");
      	return "/student/set-application";
    }
    
    @RequestMapping("/student/save-application")
    public String applySave(@ModelAttribute("app") Application app,@ModelAttribute("subject") Subject subject,Model model){
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());
    	Student stud = studentService.findStudentById(user.getrole_id());
    	
    	app.setStudentid(stud.getStudent_id());
    	app.setGpa(stud.getcurrentavggrade());
    	
    	applicationService.saveApp(app);
    	model.addAttribute("successMessage", "Submit successfull!");
    	
    	return "/student/set-application";
    }
    
    @RequestMapping("/student/view-applications")
    public String view_applications(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findById(authentication.getName());
    	Student stud = studentService.findStudentById(user.getrole_id());
    	
    	List<Application> applications = stud.getAppList();    	
    	model.addAttribute("items", applications);
    	
    	return "/student/view-applications";
    }
    
    @RequestMapping("/student/remove-application")
    public String remove_application(@ModelAttribute("app") Application app,Model model){
    	
    	applicationService.deleteById(app.getApplicationid());
    	
    	return "redirect:/student/view-applications";
    }
}
