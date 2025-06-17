package com.kopo.c1;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewController {
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		return "index";
//	}
	
	@GetMapping(value = "/")
	public String home(Locale locale, Model model, HttpSession session) {
		Boolean isLogin = (Boolean) session.getAttribute("is_login");
		if (isLogin != null && isLogin) {
			String userId = (String) session.getAttribute("user_id");
			DB db = new DB();
			User user = db.selectUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("isLogin", true);
		} else {
			model.addAttribute("isLogin", false);
		}
		return "index";
	}
	
	@GetMapping(value = "/join")
	public String join(Locale locale, Model model) {
		return "join";
	}
	
	@PostMapping(value = "/join")
	public String joinAction(Locale locale, Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		DB db = new DB();
		db.insertData(new User(id, pwd, name, phone, address));
		return "redirect:/";
	}
	
	@GetMapping(value = "/login")
	public String login(Locale locale, Model model) {
		return "login";
	}

	@PostMapping(value = "/login_action")
	public String loginAction(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		DB db = new DB();
		User loggedUser = db.login(new User(id, pwd));
		if (loggedUser.userType.isEmpty()) {
			session.setAttribute("is_login", false);
			session.setAttribute("user_type", "");
		} else {
			session.setAttribute("is_login", true);
			session.setAttribute("user_type", loggedUser.userType);
			session.setAttribute("user_id", loggedUser.id);
		}
		return "redirect:/";
	}

	@GetMapping(value = "/user_list")
	public String userList(Locale locale, Model model, HttpSession session) {
		Boolean isLogin = (Boolean) session.getAttribute("is_login");
		String userType = (String) session.getAttribute("user_type");

		if (isLogin != null && isLogin && "admin".equals(userType)) {
			DB db = new DB();
			ArrayList<User> userList = db.selectAll();
			model.addAttribute("userList", userList);
			return "user_list";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/mypage")
	public String mypage(Locale locale, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("user_id");
		if (userId == null) return "redirect:/login";

		DB db = new DB();
		User user = db.selectUserById(userId); // 비밀번호는 검사하지 않음
		model.addAttribute("user", user);
		return "mypage";
	}

	@PostMapping(value = "/mypage_action")
	public String mypageAction(HttpServletRequest request, HttpSession session) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String id = (String) session.getAttribute("user_id");
		if (id == null) return "redirect:/login";

		User user = new User();
		user.id = id;
		user.name = request.getParameter("name");
		user.phone = request.getParameter("phone");
		user.address = request.getParameter("address");

		DB db = new DB();
		db.updateUser(user);
		return "redirect:/";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
