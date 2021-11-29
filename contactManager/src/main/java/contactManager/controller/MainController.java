package contactManager.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import contactManager.dao.ContactDAO;
import contactManager.model.Contact;

@Controller
public class MainController {
    
    @Autowired
    private ContactDAO contactDAO;
    
	@GetMapping("/contacts")
	public String getAllContacts(Model model) throws IOException {
		List<Contact> listContact = contactDAO.list();
		model.addAttribute("listcontacts", listContact);
		return "home";
	}
	
	//Add new Task
	@GetMapping("/contacts/new")
	public String addContact(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact_re", contact);
		return "add_new";
	}
	
	@PostMapping("/contacts")
	public String savePost(@ModelAttribute("contact_re") Contact contact) {
		contactDAO.save(contact);
		return "redirect:/contacts";
	}
	
	//Edit contact
	@GetMapping("/contacts/edit/{id}")
	public String editContact(@PathVariable int id, Model model) {
		Contact contact = contactDAO.get(id);
		model.addAttribute("contact_re", contact);
		return "/edit_contact";
		
	}
	
	@PostMapping("/contacts/{id}")
	public String saveEdit(@PathVariable int id, @ModelAttribute("contact_re") Contact contact) {
		Contact existingContact = contactDAO.get(id);
		existingContact.setName(contact.getName());
		existingContact.setEmail(contact.getEmail());
		existingContact.setAddress(contact.getAddress());
		existingContact.setPhone(contact.getPhone());
		contactDAO.update(existingContact);
		return "redirect:/contacts";
	}
	
	//Delete contact
	@GetMapping("/contacts/{id}")
	public String deleteContact(@PathVariable int id) {
		contactDAO.delete(id);
		return "redirect:/contacts";
	}
}
