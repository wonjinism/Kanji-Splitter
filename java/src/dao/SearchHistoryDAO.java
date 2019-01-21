package dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.SearchHistory;

public class SearchHistoryDAO {
	// DB 정보를 담은 마이바티스 객체
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public ArrayList<SearchHistory> getSearchHistory() {
		ArrayList<SearchHistory> list = null;
		SqlSession session = null;
		try {
			session = factory.openSession(); // Exception
			SearchHistoryMapper mapper = session.getMapper(SearchHistoryMapper.class); // UserMapper interface 통째로 mapper에 연결
			list = mapper.getSearchHistory();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean insertSearchHistory(SearchHistory insert) {
		boolean result = false;
		SqlSession session = null;
		try {
			session = factory.openSession(); // Exception
			SearchHistoryMapper mapper = session.getMapper(SearchHistoryMapper.class); // UserMapper interface 통째로 mapper에 연결
			int insertNum = mapper.insertSearchHistory(insert);
			session.commit();
			if(insertNum > 0) {
				result = true;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public boolean deleteSearchHistory(SearchHistory overlap) {
		boolean result = false;
		SqlSession session = null;
		try {
			session = factory.openSession(); // Exception
			SearchHistoryMapper mapper = session.getMapper(SearchHistoryMapper.class); // UserMapper interface 통째로 mapper에 연결
			int insertNum = mapper.deleteSearchHistory(overlap);
			session.commit();
			if(insertNum > 0) {
				result = true;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}