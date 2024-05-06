package myy803.BookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.BookStore.entity.Application;
import myy803.BookStore.entity.Professor;
import myy803.BookStore.entity.Student;
import myy803.BookStore.entity.Subject;
import myy803.BookStore.entity.Thesis;
import myy803.BookStore.entity.User;
import myy803.BookStore.service.ApplicationService;
import myy803.BookStore.service.IProfessorService;
import myy803.BookStore.service.IStudentService;
import myy803.BookStore.service.IThesisService;
import myy803.BookStore.service.SubjectService;
import myy803.BookStore.service.UserService;

@Controller
public class ProfessorController {
	@Autowired
    UserService userService;
    @Autowired
    IProfessorService professorService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    IStudentService studentService;
    @Autowired
    IThesisService thesisService;
	
    @RequestMapping("/professor/dashboard")
    public String getProfessorHome(){
        return "professor/dashboard";
    }
    //Set profile
    @RequestMapping("/professor/set-profile")
    public String set_profile(Model model){
    	Professor professor = new Professor();
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	user = userService.findById(authentication.getName());
    	professor = professorService.findProfessorById(user.getrole_id());	//this should not fail (e.g. null professor) because to get here you have to be logged in with an account
    	model.addAttribute("professor", professor);
    	return "professor/set-profile";
    }
    
    @RequestMapping("/professor/view-subjects")
    public String view_subjects(){
        return "professor/view-subjects";
    }
    

    @RequestMapping("/professor/save-profile")
    public String save_profile(@ModelAttribute("professor") Professor professor, Model model){
    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	user = userService.findById(authentication.getName());
    	professor.setProfessorid(user.getrole_id());
        
    	professorService.saveProfile(professor);
    	
    	model.addAttribute("successMessage", "Update successfull!");
    	return "professor/set-profile";
    }
    //end of Set profile
    
    //Diploma thesis subjects
    @RequestMapping("/professor/diploma-thesis-subjects")
    public String getProfessor_Diploma_thesis_subjects(Model model){
    	Subject subject = new Subject();
    	model.addAttribute("newDiplomaSubject", subject);

    	User user = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());    	
    	List<Subject> subjects = professorService.listProfessorSubject(user.getrole_id());

    	// Add the items list to the model
        model.addAttribute("items", subjects);
        
        return "/professor/diploma-thesis-subjects";
    }
    
    @RequestMapping("/professor/new-diploma-thesis-subjects")
    public String add_Diploma_thesis_subjects(@ModelAttribute("newDiplomaSubject") Subject newDiplomaSubject, Model model){
    	User user = null;
    	Professor professor = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(newDiplomaSubject.getSubjectname().equals(""))
    	{
    		return "/professor/diploma-thesis-subjects";
    	}
    	
    	user = userService.findById(authentication.getName());
    	professor = professorService.findProfessorById(user.getrole_id());
    	newDiplomaSubject.setProfessorid(professor.getProfessorid());
    	subjectService.save(newDiplomaSubject);
    	
    	return "redirect:/professor/diploma-thesis-subjects";
    }
    /*
    @RequestMapping("/professor/edit-diploma-subject")
    public String edit_diploma_subject(Model model){
    	return "/professor/diploma-thesis-subjects";
    }
  
    
    @RequestMapping("/professor/remove-diploma-subject")
    public String remove_diploma_subject(@ModelAttribute("newDiplomaSubject") Subject newDiplomaSubject, Model model){
    	
    	
    	subjectService.deleteByid(newDiplomaSubject.getSubjectid());
    	return "redirect:/professor/diploma-thesis-subjects";
    }
     */
    //end of Diploma thesis subjects
    
    //Diploma thesis subjects applications
    @RequestMapping("/professor/diploma-thesis-applications")
    public String getProfessor_Diploma_thesis_applications(Model model){
    	User user = null;
    	Professor professor = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());
    	professor = professorService.findProfessorById(user.getrole_id());
    	List<Subject> subs  = professor.getSubjects();
    	model.addAttribute("items", subs);
        return "/professor/subject-list";
    }
    
    @RequestMapping("/professor/view-subject-applications")
    public String viewSubjectApplication(@ModelAttribute("subject") Subject subject , Model model){
    	Subject sub=subjectService.findById(subject.getSubjectid());
    	System.out.println(sub);
    	
    	List<Application> apps = sub.getApplications();
    	//applicationService.findApplicationsBysubjectid(subject.getSubjectid());
    	
    	model.addAttribute("items", apps);
    	
    	return "/professor/app-list";
    }
    
    @RequestMapping("/professor/application-strategy")
    public String select_application_from_strategy(@ModelAttribute("app") Application application,@RequestParam("strategy") String strategy,
            @RequestParam(value = "avgGrade", required = false) Double avgGrade,
            @RequestParam(value = "coursesCompleted", required = false) Integer coursesCompleted,
            @RequestParam("subjectid") int subjectId,Model model){
    	
    	
    	User user = null;
    	Professor professor = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());
    	professor = professorService.findProfessorById(user.getrole_id());
    	
    	    	
    	//System.out.println(subjectId);
    	Subject sub=subjectService.findById(subjectId);
    	int bestApplicantid = 0;
    	Student student = null;
    	
    	if(strategy.equals("best_avg_courses"))
    	{
    		System.out.println("avgGrade: " + avgGrade);
        	System.out.println("coursesCompleted: " + coursesCompleted);
        	bestApplicantid = sub.findBestApplicant(strategy,avgGrade,coursesCompleted);
        	student = studentService.findStudentById(bestApplicantid);
        	System.out.println("Selected strategy best applicant: " + student.getfullname()); 
        	System.out.println("Selected strategy: " + strategy); 
    	}else{
    		
        	bestApplicantid = sub.findBestApplicant(strategy,0.0,0);
        	student = studentService.findStudentById(bestApplicantid);
        	
        	System.out.println("Selected strategy best applicant: " + student.getfullname()); 
        	System.out.println("Selected strategy: " + strategy); 
        	
    	}
    	
    	//set thesis 
    	Thesis thesis = new Thesis(); 
    	thesis.setProfessorid(professor.getProfessorid());
    	thesis.setStudentid(bestApplicantid);
    	thesis.setSubjectid(subjectId);
    	thesis.setTitle(sub.getSubjectname());
    	thesis.setObjectives(sub.getDescription());
    	thesis.setSupervisor(professor.getFullname());
    	thesisService.saveThesis(thesis);
    	
    	model.addAttribute("successMessage", "Selected application with id : ");
        return "redirect:/professor/diploma-thesis-applications";
    }
    
    //end of Diploma thesis subjects applications
    
    @RequestMapping("/professor/assigned-diploma-thesis-projects")
    public String getProfessorAssigned_diploma_thesis_projects(@ModelAttribute("thesis") Thesis thesis,Model model){
    	User user = null;
    	Professor professor = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user = userService.findById(authentication.getName());
    	professor = professorService.findProfessorById(user.getrole_id());
    	
    	List<Thesis> thesislist = thesisService.findThesisByprofessorid(professor.getProfessorid());
    	
    	model.addAttribute("items",thesislist);
    	
        return "/professor/thesis-list";
    }
    
    @RequestMapping("/professor/thesis-edit")
    public String edit_thesis(@ModelAttribute("thesis") Thesis thesis, Model model){
    	
    	Thesis thesi = thesisService.findThesisBythesisid(thesis.getThesisid());
    	model.addAttribute("thesis", thesi);
    	
        return "/professor/thesis-edit";
    }
    
    @RequestMapping("/professor/thesis-edit-save")
    public String thesis_edit_save(@ModelAttribute("thesis") Thesis thesis, @RequestParam("thesisid") int thesisId,
    								@RequestParam("implmentationgrade") float implmentationgrade, @RequestParam("reportgrade") float reportgrade,
    								@RequestParam("presentationgrade") float presentationgrade, Model model){
    	
    	Thesis thesi = thesisService.findThesisBythesisid(thesisId);
    	thesi.setImplmentationgrade(implmentationgrade);
    	thesi.setPresentationgrade(presentationgrade);
    	thesi.setReportgrade(reportgrade);
    	thesisService.updateThesis(thesi);
    	
    	model.addAttribute("successMessage", "Update successful!");
    	double resultGrade = thesi.getTotalGrade();
    	model.addAttribute("ResultGrade" ,resultGrade);
    	return "/professor/thesis-edit";
    }
}

