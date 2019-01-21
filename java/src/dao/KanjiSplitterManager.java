package dao;

import java.util.ArrayList;

import vo.Kanji;
import vo.SearchHistory;
import vo.TodayTango;

public class KanjiSplitterManager {
	private static ArrayList<Kanji> kanjiList = new ArrayList<Kanji>();
	private ArrayList<SearchHistory> history = new ArrayList<SearchHistory>(); 
	private ArrayList<TodayTango> tangoList = null;
	private DateManager dateMgr = new DateManager();
	private KanjiDAO kanjiDAO = new KanjiDAO();
	private SearchHistoryDAO historyDAO = new SearchHistoryDAO();
	private TodayTangoDAO tangoDAO = new TodayTangoDAO();
	
	public boolean getKanjiList() {
		boolean result = false;
		kanjiList = kanjiDAO.getKanjiList();
		if (kanjiList.size() > 0 ) {
			result = true;
		}
		return result;
	}
	
	public boolean getSearchHistory() {
		boolean result = false;
		history = historyDAO.getSearchHistory();
		if (history.size() > 0 ) {
			result = true;
		}
		return result;
	}
	
	public boolean getTangoList() {
		boolean result = false;
		tangoList = tangoDAO.getTangoList();
		if (tangoList.size() > 0) {
			result = true;
		}
		return result;
	}


	public ArrayList<TodayTango> insertCurrentTangoList(TodayTango tangoToInsert, String date) {
		ArrayList<TodayTango> list = new ArrayList<TodayTango>();
		tangoList.add(tangoToInsert);
		for (TodayTango todayTango : tangoList) {
			if (todayTango.getTangoDate().equals(date)) {
				list.add(todayTango);
			}
		}
		return list;
	}
	
	public ArrayList<TodayTango> insertCurrentTangoList(ArrayList<TodayTango> currentTangolist, String date) {
		ArrayList<TodayTango> list = new ArrayList<TodayTango>();
		tangoList.addAll(currentTangolist);
		for (TodayTango todayTango : tangoList) {
			if (todayTango.getTangoDate().equals(date)) {
				list.add(todayTango);
			}
		}
		return list;
	}
	

	public ArrayList<TodayTango> udpateCurrentTangoList(TodayTango tangoToUpdate, String date) {
		ArrayList<TodayTango> list = new ArrayList<TodayTango>();
		int tangoNum = tangoToUpdate.getTangoNum();
		TodayTango todayTango = null;
		for (int i = 0; i < tangoList.size(); i++) {
			todayTango = tangoList.get(i);
			if (todayTango.getTangoDate().equals(date) && todayTango.getTangoNum() == tangoNum) {
				tangoList.set(i, tangoToUpdate);
			}
			todayTango = tangoList.get(i);
			if (todayTango.getTangoDate().equals(date)) {
				list.add(todayTango);
			}
		}
		return list;
	}
	
	public boolean deleteCurrentTangoList(ArrayList<TodayTango> list, String date) {
		tangoList.removeAll(list);
		return true;
	}
	
	public boolean insertSearchHistory(String searchStr) {
		boolean result = false;
		// TODO 만약에 이전에 검색한 것을 또 다시 검색했다면? 일단 찾아서 지우자.
		SearchHistory overlap = null;
		String searchDate = dateMgr.getToday();
		for (SearchHistory searchHistory : history) {
			if(searchHistory.getSearchDate().equals(searchDate) && searchHistory.getSearchStr().equals(searchStr)){
				overlap = searchHistory;
			}				
		}
		if(overlap != null) {
			result = historyDAO.deleteSearchHistory(overlap);
			if(result) {
				getSearchHistory();
			}else {
				System.out.println("검색 기록 갱신에 실패했습니다.");
				return result;
			}
		}
		
		int searchNum = 1;
		for (SearchHistory searchHistory : history) {
			if(searchHistory.getSearchDate().equals(searchDate) && searchHistory.getSearchNum() >= searchNum) {
				searchNum = searchHistory.getSearchNum() + 1;
				searchHistory.toString();
			}
		}
		
		SearchHistory insert = new SearchHistory(searchDate, searchNum, searchStr);
		if(insert != null) {
			result = historyDAO.insertSearchHistory(insert);
			if(result != true) {
				System.out.println("히스토리 생성 실패");
			}
		}
		return result;
	}
	
	public ArrayList<SearchHistory> getSearchHistoryList(String date){
		ArrayList<SearchHistory> list = new ArrayList<SearchHistory>();
		for (SearchHistory searchHistory : history) {
			if(searchHistory.getSearchDate().equals(date)) {
				list.add(searchHistory);
			}
		}
		return list;
	}
	
	public boolean updateKanjiToUpdate(Kanji kanjiToDo) {
		boolean result = false;
		result = kanjiDAO.updateKanjiToUpdate(kanjiToDo); // DB에 업데이트
		if(result) {
			result = getKanjiList(); // DB에 업데이트한 값으로 전체 리스트 업데이트
		}
		return result;
	}
	
	/*
	 * 입력된 String을 한글자씩 비교해서 한자만 찾아내서 ArrayList로 만드는 기능
	 */
	public ArrayList<Kanji> kanjiSplit(String input) {
		ArrayList<Kanji> splitList = new ArrayList<Kanji>();
		String[] inputKanji = input.split("");
		for (String kanjiStr : inputKanji) {
			Kanji kanjiObj = findKanji(kanjiStr); // 한자 한글자 String을 가져가서 Kanji 객체를 리턴
			if (kanjiObj != null) {
				// 리스트에 등록한 한자는 추가하지 않습니다.
				if(splitList.contains(kanjiObj) == false){ 
					splitList.add(kanjiObj);
				}
			}
		}
		if (splitList.size() == 0) {
			return null;
		}
		return splitList;
	}

	/*
	 * 입력된 한자 String을 한자 전체 DB가 들어간 List에서 비교해서 Object를 찾아내는 기능
	 */
	public Kanji findKanji(String kanjiKey) {
		Kanji kanjiObj = null;
		for (Kanji kanji : kanjiList) {
			if (kanji.getKanji().equals(kanjiKey)) {
				kanjiObj = kanji;
				return kanjiObj;
			}
		}
		return kanjiObj;
	}
	
	/*
	 * 날짜 String으로 원하는 날짜의 단어 리스트만 ArrayList로 반환
	 */
	public ArrayList<TodayTango> getCurrentTangoList(String date) {
		ArrayList<TodayTango> firstList = new ArrayList<TodayTango>();
		ArrayList<TodayTango> list = new ArrayList<TodayTango>();
		for (TodayTango todayTango : tangoList) {
			if (todayTango.getTangoDate().equals(date)) {
				firstList.add(todayTango);
			}
		}
		// TODO 리스트에 tangoNum 번호 순서대로 재정렬 하는 알고리즘
		for (int i = 0; i < firstList.size(); i++) {
			for (TodayTango tango : firstList) {
				if(tango.getTangoNum() == i + 1) {
					list.add(tango);
				}
			}
		}
		return list;
	}
	
	public boolean deleteTango(TodayTango tangoToDelete){
		boolean result = false;
		result = tangoDAO.deleteTango(tangoToDelete);
		if(result) {
			result = true;
		}
		return result;
	}
	
	public boolean updateTango(TodayTango tangoToUpdate) {
		boolean result = false;
		tangoList = tangoDAO.updateTango(tangoToUpdate);
		if(tangoList.size() > 0) {
			result = true;
		}
		return result;
	}
	
	public boolean updateTangoList(ArrayList<TodayTango> currentTangoList) {
		boolean result = false;
		tangoList = tangoDAO.updateTangoList(currentTangoList);
		if(tangoList.size() > 0) {
			result = true;
		}
		return result;
	}
}
