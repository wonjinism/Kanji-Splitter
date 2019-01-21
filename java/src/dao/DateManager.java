package dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
	public String getToday() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		String today = null;
		if (month < 10 && date < 10) {
			today = year + "-0" + month + "-0" + date;
		} else if (month < 10) {
			today = year + "-0" + month + "-" + date;
		} else if (date < 10) {
			today = year + "-" + month + "-0" + date;
		} else {
			today = year + "-" + month + "-" + date;
		}
		return today;
	}

	public String getDay(String date) {
		String korDayOfWeek = "";
		Date nDate = getStringToDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			korDayOfWeek = "(일)";
			break;
		case 2:
			korDayOfWeek = "(월)";
			break;
		case 3:
			korDayOfWeek = "(화)";
			break;
		case 4:
			korDayOfWeek = "(수)";
			break;
		case 5:
			korDayOfWeek = "(목)";
			break;
		case 6:
			korDayOfWeek = "(금)";
			break;
		case 7:
			korDayOfWeek = "(토)";
			break;
		}
		return korDayOfWeek;
	}

	public Date getStringToDate(String date) {
		try {
			Date dateType = new SimpleDateFormat("yyyy-MM-DD").parse(date);
			return dateType;
		} catch (ParseException e) {
			System.out.println("날짜 정보에 오류가 있습니다.");
			e.printStackTrace();
			return null;
		}
	}

	public String getPrevDate(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String prevDateStr = null;
		try {
			Date date = df.parse(dateStr);
			// 날짜 더하기
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);

			prevDateStr = df.format(cal.getTime());
			return prevDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
			return prevDateStr;
		}
	}

	public String getNextDate(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String prevDateStr = null;
		try {
			Date date = df.parse(dateStr);
			// 날짜 더하기
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);

			prevDateStr = df.format(cal.getTime());
			return prevDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
			return prevDateStr;
		}
	}
}
