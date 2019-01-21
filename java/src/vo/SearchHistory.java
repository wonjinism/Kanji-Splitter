package vo;

public class SearchHistory {
	private String searchDate;
	private int searchNum;
	private String searchStr;

	public SearchHistory() {
	}

	public SearchHistory(String searchDate, int searchNum, String searchStr) {
		this.searchDate = searchDate;
		this.searchNum = searchNum;
		this.searchStr = searchStr;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public int getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(int searchNum) {
		this.searchNum = searchNum;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	@Override
	public String toString() {
		return "searchHistory [searchDate=" + searchDate + ", searchNum=" + searchNum + ", searchStr=" + searchStr
				+ "]";
	}
}
