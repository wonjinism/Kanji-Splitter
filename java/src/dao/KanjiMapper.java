package dao;

import java.util.ArrayList;

import vo.Kanji;

public interface KanjiMapper {
	// 한자 리스트를 가져오기
	public ArrayList<Kanji> getKanjiList();
	
	// 한자 객체 업데이트
	public boolean updateKanjiToUpdate(Kanji kanjiToDo);
}
