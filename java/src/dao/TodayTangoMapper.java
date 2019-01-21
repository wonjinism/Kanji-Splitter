package dao;

import java.util.ArrayList;

import vo.TodayTango;

public interface TodayTangoMapper {
	// 단어 리스트를 가져오기
	public ArrayList<TodayTango> getTangoList();
	
	// 단어 추가
	public int insertTango(TodayTango todayTango);
	
	// 단어 업데이트
	public int updateTango(TodayTango todayTango);
	
	// 단어 삭제
	public int deleteTango(TodayTango tangoToDelete);
	
	// 삭제 후 변경된 tangoNum 업데이트
	public int updateTangoList(TodayTango tangoToUpdate);
	
}
