package io.zerone.kanjisplitter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.zerone.kanjisplitter.DAO.UserDAO;
import io.zerone.kanjisplitter.VO.User;

@Controller
public class UserController {

	@Autowired
	UserDAO dao;
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(User user, Model model, HttpSession session) {
		int result = dao.insertUser(user);
		if(result != 0) {
			session.setAttribute("user_id", user.getUser_id());
		}else if(result == 0) {
			model.addAttribute("warning", "이미 존재하는 ID입니다.");
			model.addAttribute("user", user);
			return "register"; // 
		}
		return "home";
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginUser(User user, HttpSession session, Model model){
		int result = dao.loginUser(user);
		if(result == 1) {
			session.setAttribute("user_id", user.getUser_id());
//			if(user.getUser_id().equals("admin")) {
//				return "admin";
//			}
			return "redirect:selectFermiList";
		}else if (result == 0) {
			model.addAttribute("id_warning", "일치하는 아이디가 없습니다.");
		}else if (result == 2) {
			model.addAttribute("pw_warning", "패스워드가 일치하지 않습니다.");
		}
		model.addAttribute("user_id", user.getUser_id());
		return "signin";
	}
}