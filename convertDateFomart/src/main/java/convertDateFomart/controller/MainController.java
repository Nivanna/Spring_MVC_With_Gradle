package convertDateFomart.controller;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import convertDateFomart.dao.DateDAO;
import convertDateFomart.model.DateFormat;

@Controller
public class MainController {
  @Autowired
  private DateDAO dateDAO;
  
  
  @GetMapping("/")
  public String welcomePage() {
	  return "index";
  }
  
  @GetMapping("/home")
  public String viewAllDates(Model model) {
	  List<DateFormat> listdates = dateDAO.getListOfDate();
	  model.addAttribute("listdates", listdates);
	  return "views";
  }
  
  @GetMapping("/home/date")
  public String addNewDate(DateFormat dateFormat) {
	  Date currentDate = new Date();
	  dateFormat.setDateconvertor(currentDate);
	  dateDAO.addNewDate(dateFormat);
	  return "redirect:/home";
  }
  
  @GetMapping("/home/{id}")
  public String deleteDate(@PathVariable int id) {
	  dateDAO.deleteDate(id);
	  return "redirect:/home";
  }
}
