package dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.TodayTango;

public class TodayTangoDAO {
	// DB 정보를 담은 마이바티스 객체
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	// DB에서 전체 오늘의 단어 목록 가져오기
	public ArrayList<TodayTango> getTangoList() {
		ArrayList<TodayTango> list = null;
		SqlSession session = null;
		try {
			session = factory.openSession(); // Exception
			TodayTangoMapper mapper = session.getMapper(TodayTangoMapper.class); // UserMapper interface 통째로 mapper에 연결
			list = mapper.getTangoList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public boolean insertTango(TodayTango tangoToInsert) {
		SqlSession session = null;
		int returnNum = 0;
		try {
			session = factory.openSession(); // Exception
			TodayTangoMapper mapper = session.getMapper(TodayTangoMapper.class); // UserMapper interface 통째로 mapper에 연결
			returnNum = mapper.insertTango(tangoToInsert);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (returnNum >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<TodayTango> updateTango(TodayTango tangoToUpdate) {
		ArrayList<TodayTango> list = null;
		SqlSession session = null;
		int returnNum = 0;
		try {
			session = factory.openSession(); // Exception
			TodayTangoMapper mapper = session.getMapper(TodayTangoMapper.class); // UserMapper interface 통째로 mapper에 연결
			returnNum = mapper.updateTango(tangoToUpdate);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (returnNum >= 1) {
			list = getTangoList();
			if (list.size() > 0) {
				System.out.println("단어 정보를 업데이트 했습니다!");
			}
			return list;
		} else {
			return list;
		}
	}

	public boolean deleteTango(TodayTango tangoToDelete) {
		SqlSession session = null;
		int returnNum = 0;
		try {
			session = factory.openSession(); // Exception
			TodayTangoMapper mapper = session.getMapper(TodayTangoMapper.class); // UserMapper interface 통째로 mapper에 연결
			returnNum = mapper.deleteTango(tangoToDelete);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (returnNum >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<TodayTango> updateTangoList(ArrayList<TodayTango> currentTangoList) {
		ArrayList<TodayTango> list = null;
		SqlSession session = null;
		int returnNum = 0;
		try {
			session = factory.openSession(); // Exception
			TodayTangoMapper mapper = session.getMapper(TodayTangoMapper.class); // UserMapper interface 통째로 mapper에 연결
			for (TodayTango tango : currentTangoList) {
				mapper.updateTangoList(tango);
				returnNum++;
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (returnNum >= 1) {
			list = getTangoList();
			if (list.size() > 0) {
				System.out.println("삭제 후 tangoNum을 업데이트 했습니다!");
			}
			return list;
		} else {
			return list;
		}
	}
	
	
}