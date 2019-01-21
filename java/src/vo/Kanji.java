package vo;

public class Kanji {
	private int kanjiID;
	private int typeNum; // 1~6 소학교, 7 중학교 (상용) 8 참고자(부수, 기타)
	private String kanji; // 한자 스트링
	private String hurigana; // 일본어 음
	private String imi; // 일본어 훈
	private String onkunnKR; // 한국 음훈
	private String splitListStr; // 나눠질 한자 String
	private String kanjiMemo; // 기억을 돕기 위한 스토리

	@Override
	public String toString() {
		return "==============================================" + "\n" + kanji + "(" + onkunnKR + ")" + "\n" + "[분할] "
				+ splitListStr + "\n" + "[메모] " + kanjiMemo + "\n" + "[훈독] " + imi + "\n" + "[음독] " + hurigana + "\n"
				+ "==============================================";
	}

	public Kanji() {
	}

	public Kanji(int kanjiID, int typeNum, String kanji, String hurigana, String imi, String onkunnKR,
			String splitListStr, String kanjiMemo) {
		this.kanjiID = kanjiID;
		this.typeNum = typeNum;
		this.kanji = kanji;
		this.hurigana = hurigana;
		this.imi = imi;
		this.onkunnKR = onkunnKR;
		this.splitListStr = splitListStr;
		this.kanjiMemo = kanjiMemo;
	}

	public int getKanjiID() { // 반드시 기본 생성자 만들어주어야 mybatis에서 동작합니다.
		return kanjiID;
	}

	public void setKanjiID(int kanjiID) {
		this.kanjiID = kanjiID;
	}

	public int getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getHurigana() {
		return hurigana;
	}

	public void setHurigana(String hurigana) {
		this.hurigana = hurigana;
	}

	public String getImi() {
		return imi;
	}

	public void setImi(String imi) {
		this.imi = imi;
	}

	public String getOnkunnKR() {
		return onkunnKR;
	}

	public void setOnkunnKR(String onkunnKR) {
		this.onkunnKR = onkunnKR;
	}

	public String getSplitListStr() { 
		return splitListStr;
	}

	public void setSplitListStr(String splitListStr) {
		this.splitListStr = splitListStr;
	}

	public String getKanjiMemo() {
		return kanjiMemo;
	}

	public void setKanjiMemo(String kanjiMemo) {
		this.kanjiMemo = kanjiMemo;
	}

}
