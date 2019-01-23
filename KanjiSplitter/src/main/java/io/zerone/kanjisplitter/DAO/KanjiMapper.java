package io.zerone.kanjisplitter.DAO;

import java.util.ArrayList;

import io.zerone.kanjisplitter.VO.Kanji;

public interface KanjiMapper {
	
	public ArrayList<Kanji> selectKanjiList();
	public Kanji selectKanji(int kanji_id);
}
