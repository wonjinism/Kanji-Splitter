package io.zerone.kanjisplitter.controller;

import io.zerone.kanjisplitter.entity.Kanji;
import io.zerone.kanjisplitter.serivce.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KanjiController {
	
	public static List<Kanji> kanjiList = null;
	private static boolean status = false; 

	@Autowired
	KanjiService kanjiService;
	
	public void updateKanjiList(){
		if(kanjiList == null || status == false) {
			kanjiList = kanjiService.getKanjiList();
			status = true;
		}
	}
	
	@RequestMapping(value = "/split", method = RequestMethod.GET)
	public String split(@RequestParam("search") String search, Model model) {
		updateKanjiList();
		ArrayList<Kanji> searchKanjiList = kanjiService.makeSearchKanjiList(search);
		if (searchKanjiList == null) { // 없다면
			model.addAttribute("warning", "입력하신 내용에 한자가 없습니다.");
		} else { // 있다면 
			model.addAttribute("SearchResult", searchKanjiList);
		}
		return "home";
	}
	
	@RequestMapping(value = "/updateKanji", method = RequestMethod.GET)
	public String updateKanji(Long id, Model model) {
		Kanji kanji = kanjiService.getKanjiById(id);
		model.addAttribute("kanji", kanji);
		return "kanji";
	}
}
