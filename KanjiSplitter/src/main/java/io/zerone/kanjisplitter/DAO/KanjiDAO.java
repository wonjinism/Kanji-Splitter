package io.zerone.kanjisplitter.DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.zerone.kanjisplitter.VO.Kanji;

@Repository
public class KanjiDAO {

	@Autowired
	SqlSession session;

	public ArrayList<Kanji> selectKanjiList() {
		KanjiMapper mapper = session.getMapper(KanjiMapper.class);
		ArrayList<Kanji> selectList = null;
		try {
			selectList = mapper.selectKanjiList();
		} catch (Exception e) {
			e.printStackTrace();
			return selectList;
		}
		return selectList;
	}

	public Kanji selectKanji(int kanji_id) {
		KanjiMapper mapper = session.getMapper(KanjiMapper.class);
		Kanji kanji = null;
		try {
			kanji = mapper.selectKanji(kanji_id);
		} catch (Exception e) {
			e.printStackTrace();
			return kanji;
		}
		return kanji;
	}
}
