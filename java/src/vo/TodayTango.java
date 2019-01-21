package vo;

public class TodayTango {
	private String tangoDate;
	private int tangoNum;
	private String tangoStr;
	private String tangoHurigana;
	private String tangoImi;

	public TodayTango() { // 반드시 기본 생성자 만들어주어야 mybatis에서 동작합니다.
	}

	public TodayTango(String tangoDate, int tangoNum, String tangoStr, String tangoHurigana, String tangoImi) {
		this.tangoDate = tangoDate;
		this.tangoNum = tangoNum;
		this.tangoStr = tangoStr;
		this.tangoHurigana = tangoHurigana;
		this.tangoImi = tangoImi;
	}

	public String getTangoDate() {
		return tangoDate;
	}

	public void setTangoDate(String tangoDate) {
		this.tangoDate = tangoDate;
	}

	public int getTangoNum() {
		return tangoNum;
	}

	public void setTangoNum(int tangoNum) {
		this.tangoNum = tangoNum;
	}

	public String getTangoStr() {
		return tangoStr;
	}

	public void setTangoStr(String tangoStr) {
		this.tangoStr = tangoStr;
	}

	public String getTangoHurigana() {
		return tangoHurigana;
	}

	public void setTangoHurigana(String tangoHurigana) {
		this.tangoHurigana = tangoHurigana;
	}

	public String getTangoImi() {
		return tangoImi;
	}

	public void setTangoImi(String tangoImi) {
		this.tangoImi = tangoImi;
	}

	@Override
	public String toString() {
		return "TodayTango [tangoDate=" + tangoDate + ", tangoNum=" + tangoNum + ", tangoStr=" + tangoStr
				+ ", tangoHurigana=" + tangoHurigana + ", tangoImi=" + tangoImi + "]";
	}

}
