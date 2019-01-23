package io.zerone.kanjisplitter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.zerone.kanjisplitter.DAO.KanjiDAO;
import io.zerone.kanjisplitter.Manager.KanjiManager;
import io.zerone.kanjisplitter.VO.Kanji;

@Controller
public class KanjiController {
	
	public static ArrayList<Kanji> kanjiList = null;
	private static boolean status = false; 
			
	@Autowired
	KanjiDAO dao;
	
	@Autowired
	KanjiManager mgr;
	
	public void updateKanjiList(){
		if(kanjiList == null || status == false) {
			kanjiList = dao.selectKanjiList();
			status = true;
		}
	}
	
	@RequestMapping(value = "/split", method = RequestMethod.GET)
	public String split(String search, Model model) {
		updateKanjiList();
		ArrayList<Kanji> searchKanjiList = mgr.makeSearchKanjiList(search);
		if (searchKanjiList == null) { // 없다면
			model.addAttribute("warning", "입력하신 내용에 한자가 없습니다.");
		} else { // 있다면 
			model.addAttribute("SearchResult", searchKanjiList);
		}
		return "home";
	}
	
	@RequestMapping(value = "/updateKanji", method = RequestMethod.GET)
	public String updateKanji(int kanji_id, Model model) {
		Kanji kanji = dao.selectKanji(kanji_id);
		model.addAttribute("kanji", kanji);
		return "kanji";
	}
}
