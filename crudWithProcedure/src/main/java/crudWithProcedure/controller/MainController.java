package crudWithProcedure.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import crudWithProcedure.dao.SubjectDAO;
import crudWithProcedure.model.Subject;

@Controller
public class MainController {
	@Autowired 
	private SubjectDAO subjectDAO;
	
	@GetMapping("/subjects")
	public String getAllContact(Model model) {
		List<Subject> subjects = subjectDAO.view();
		model.addAttribute("records", subjects);
		return "home";
	}
	
	
	//add new record
	@GetMapping("/subjects/new")
	public String addNew(Model model) {
		Subject subject = new Subject();
		model.addAttribute("record", subject);
		return "new_record";
	}
	@PostMapping("/subjects")
	public String saveRecord(@ModelAttribute("record") Subject subject) {
		subjectDAO.save(subject);
		return "redirect:/subjects";
	}
	
	//edit the new record
	@GetMapping("/subjects/edit/{id}")
	public String editRecord(@PathVariable int id, Model model) {
	    Subject subject = subjectDAO.get(id);
	    model.addAttribute("record", subject);
		return "edit_record";
	}
	@PostMapping("/subjects/{id}")
	public String saveEditRecord(@PathVariable int id, @ModelAttribute("record") Subject subject) {
		Subject existingSubject = subjectDAO.get(id);
		existingSubject.setSubjectName(subject.getSubjectName());
		existingSubject.setTeacherName(subject.getTeacherName());
		subjectDAO.update(existingSubject);
		return "redirect:/subjects";
	}
	
	//delete subject by id
	@GetMapping("/subjects/{id}")
	public String deleteRecord(@PathVariable int id) {
		subjectDAO.delete(id);
		return "redirect:/subjects";
	}
	
	@GetMapping("/")
	public String testDate(Model model){
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		model.addAttribute("currentDate", new Date());
		return "index";
	}

}
