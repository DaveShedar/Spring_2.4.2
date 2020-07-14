package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "list-users";
	}

	@GetMapping(value = "/addUser")
	public String showFormForAdd(User user) {
		return "add-user-form";
	}

	@GetMapping(value = "/dataSuccess")
	public String dataSuccess(){
		return "add-edit-success";
	}

	//submit button action here
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/admin/list";
	}

	@GetMapping(value = "/updateForm/{id}")
	public String showFormForUpdate(@PathVariable(name = "id") Long id,
									Model theModel) {
		User user = userService.getUser(id);
		theModel.addAttribute("user", user);
		return "user-form";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin/list";
	}
}
