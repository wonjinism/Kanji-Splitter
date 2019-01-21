package dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Kanji;

public class KanjiDAO {
	// DB 정보를 담은 마이바티스 객체
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	private static ArrayList<Kanji> kanjiList = null;

	/*
	 * 객체에 한자 정보를 업데이트 후 반환 > DB에 index 기준으로 업데이트 >
	 */
	public boolean updateKanjiToUpdate(Kanji kanjiToDo) {
		SqlSession session = null;
		boolean result = false;
		try {
			session = factory.openSession(); // Exception
			KanjiMapper mapper = session.getMapper(KanjiMapper.class); // UserMapper interface 통째로 mapper에 연결
			result = mapper.updateKanjiToUpdate(kanjiToDo);
			session.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 전체회원 목록 출력
	public ArrayList<Kanji> getKanjiList() {
		ArrayList<Kanji> list = null;
		SqlSession session = null;
		try {
			session = factory.openSession(); // Exception
			KanjiMapper mapper = session.getMapper(KanjiMapper.class); // UserMapper interface 통째로 mapper에 연결
			list = mapper.getKanjiList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}