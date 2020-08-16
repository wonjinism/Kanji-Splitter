package io.zerone.kanjisplitter.serivce;

import io.zerone.kanjisplitter.controller.KanjiController;
import io.zerone.kanjisplitter.entity.Kanji;
import io.zerone.kanjisplitter.repository.KanjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KanjiService {

	private final KanjiRepository kanjiRepository;

	@Autowired
	public KanjiService(KanjiRepository kanjiRepository) {
		this.kanjiRepository = kanjiRepository;
	}

	public List<Kanji> getKanjiList() {
		return kanjiRepository.findAll();
	}

	public Kanji getKanjiById(Long id) {
		return kanjiRepository.findById(id).orElse(null);
	}


	/*
	 * 입력된 한자 String을 한자 전체 DB가 들어간 List에서 비교해서 Object를 찾아내는 기능
	 */
	public Kanji findKanji(String kanjiKey) {
		Kanji kanjiObj = null;
		for (Kanji kanji : KanjiController.kanjiList) {
			if (kanji.getKanji().equals(kanjiKey)) {
				kanjiObj = kanji;
				return kanjiObj;
			}
		}
		return kanjiObj;
	}
	
	/*
	 * 입력된 String을 한글자씩 비교해서 한자만 찾아내서 ArrayList로 만드는 기능
	 */
	public ArrayList<Kanji> makeSearchKanjiList(String search){
		ArrayList<Kanji> searchKanjiList = new ArrayList<Kanji>();
		String[] searchKanji = search.split("");
		if(searchKanji == null) {
			return null;
		}
		for (String kanjiStr : searchKanji) {
			Kanji kanjiObj = findKanji(kanjiStr); // 한자 한글자 String을 가져가서 Kanji 객체를 리턴
			if (kanjiObj != null) {
				// 리스트에 이미 등록한 한자는 추가하지 않습니다.
				if(searchKanjiList.contains(kanjiObj) == false){ 
					if(kanjiObj.getSplit() != null) {
						ArrayList<Kanji> splitList = makeSplitList(kanjiObj.getSplit());
						kanjiObj.setSplitList(splitList);
					}
					searchKanjiList.add(kanjiObj);
				}
			}
		}
		if (searchKanjiList.size() == 0) {
			return null;
		}
		return searchKanjiList;
	}
	
	public ArrayList<Kanji> makeSplitList(String split){
		ArrayList<Kanji> splitList = new ArrayList<Kanji>();
		String[] searchKanji = split.split("");
		if(searchKanji == null) {
			return null;
		}
		for (String kanjiStr : searchKanji) {
			Kanji kanjiObj = findKanji(kanjiStr); // 한자 한글자 String을 가져가서 Kanji 객체를 리턴
			if (kanjiObj != null) {
				// 리스트에 이미 등록한 한자는 추가하지 않습니다.
				if(splitList.contains(kanjiObj) == false){ 
					splitList.add(kanjiObj);
				}
			} else if(!kanjiStr.equals("")) { // 등록되지 않은 한자의 모양이라도 표시할 수 있게
				Kanji newKanji = new Kanji();
				newKanji.setKanji(kanjiStr);
				newKanji.setKorean("등록필요");
				splitList.add(newKanji);
			}
		}
		if (splitList.size() == 0) {
			return null;
		}
		return splitList;
	}

}
