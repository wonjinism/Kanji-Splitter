package dao;

import java.util.ArrayList;

import vo.SearchHistory;

public interface SearchHistoryMapper {
	public ArrayList<SearchHistory> getSearchHistory();
	
	public int insertSearchHistory(SearchHistory insert);
	
	public int deleteSearchHistory(SearchHistory overlap);
}
