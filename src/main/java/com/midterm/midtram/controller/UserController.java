package com.midterm.midtram.controller;

import com.midterm.midtram.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.midterm.midtram.model.User;
import com.midterm.midtram.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private HttpSession session;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	public String redirectUser(String url) {
		Object s = session.getAttribute("user");
		if(s == null) {
			return "redirect:/login";
		}
		return url;
	}

	public String redirectUserRole(String url) {
		Object role = session.getAttribute("user_role");
		Object s = session.getAttribute("user");
		if(s != null && role != null) {
			if(role.toString().equals("ADMIN")) {
				return "redirect:/home-admin";
			}
			return "redirect:/home-user";
		}
		return url;
	}

	@RequestMapping("/")
	public String indexPage(Model model) {
		return "redirect:/login";
	}

	@RequestMapping("/home-user")
	public String homeUser(Model model) {
		model.addAttribute("products", productService.getAll());
//		model.addAttribute("products", productService.filterName("op"));
//		model.addAttribute("products", productService.filter("Household", "Tiki"));
		return redirectUser("home-user");
	}

	@RequestMapping("/home-admin")
	public String homeAdmin(Model model) {
		return redirectUser("home-admin");
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return redirectUserRole("login");
	}

	@PostMapping("/login")
	public String signIn(Model model, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		System.out.println("post login: "+username+", "+password);
		String errMessage = "";

		if(username.trim().isEmpty()) {
			errMessage = "Username is empty";
		} else if(password.trim().isEmpty()) {
			errMessage = "Password is empty";
		} else if(password.trim().length() < 6) {
			errMessage = "Password must be at least 6 characters";
		}

		if(errMessage.length() > 0) {
			model.addAttribute("alert", errMessage);
			model.addAttribute("input_username", username);
			model.addAttribute("input_password", password);
			return "login";
		} else {
			User u = userService.get(username);
			if(u == null) {
				errMessage = "User not exits";
			} else {
				boolean flag = passwordEncoder.matches(password, u.getPassword());
				if(!flag) {
					errMessage = "Wrong password";
				}
			}

			if(errMessage.length() > 0) {
				model.addAttribute("input_username", username);
				model.addAttribute("input_password", password);
				model.addAttribute("alert", errMessage);
				return "login";
			} else {
				session.setAttribute("user", u.getUsername());
				session.setAttribute("user_role", u.getRole());
				if(u.getRole().equals("ADMIN")) {
					return "redirect:/home-admin";
				} else {
					return "redirect:/home-user";
				}
			}
		}
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		return redirectUserRole("register");
	}

	@PostMapping("/register")
	public String signUp(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "username") String username,
		@RequestParam(name = "pwd") String pwd, @RequestParam(name = "pwd_confirm") String pwd_confirm) {

		String errMessage = "";
		User new_user = userService.get(username);
		if(name.trim().isEmpty()) {
			errMessage = "Name is empty";
		}
		else if(username.trim().isEmpty()) {
			errMessage = "Username is empty";
		} else if(pwd.trim().isEmpty()) {
			errMessage = "Password is empty";
		} else if(pwd.trim().length() < 6) {
			errMessage = "Password must be at least 6 characters";
		} else if(!pwd_confirm.equals(pwd)) {
			errMessage = "Confirm password is not match";
		} else if(new_user != null) {
			errMessage = "This username already exists";
		}

		if(errMessage.length() > 0) {
			model.addAttribute("alert", errMessage);
			model.addAttribute("input_name", name);
			model.addAttribute("input_username", username);
			model.addAttribute("input_password", pwd);
			model.addAttribute("input_re_password", pwd_confirm);
			return "register";
		} else {
			User u = new User();
			u.setName(name);
			u.setUsername(username);
			u.setRole("USER");
			u.setPassword(passwordEncoder.encode(pwd));
			userService.add(u);
			session.setAttribute("user", u.getUsername());
			session.setAttribute("user_role", u.getRole());
			return "redirect:/home-user";
		}
	}

	@PostMapping("/logout")
	public void logout(Model model) {
//		session.removeAttribute("user");
//		session.removeAttribute("user_role");
		session.invalidate();
	}
}
