package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DateManager;
import dao.KanjiSplitterManager;
import dao.TodayTangoDAO;
import vo.Kanji;
import vo.SearchHistory;
import vo.TodayTango;

public class KanjiSplitterUI {
	private Scanner input = new Scanner(System.in);
	private KanjiSplitterManager manager = new KanjiSplitterManager();
	private TodayTangoDAO todayTangoDAO = new TodayTangoDAO();
	private ArrayList<Kanji> currentSplitList = null; // 현재 분해한 결과값을 저장
	private ArrayList<ArrayList<Kanji>> splitHistory = null; // 분해가 연속되면 이전 값으로 돌아갈 수 있도록 기록
	private ArrayList<SearchHistory> currentSearchHistory = null;
	private ArrayList<TodayTango> currentTangoList = null;
	private DateManager dateMgr = new DateManager();
	private String date = null;
	private String day = null;
	private int historyNum = 0;

	public KanjiSplitterUI() {
		date = dateMgr.getToday();
		day = dateMgr.getDay(date);
		boolean update = manager.getKanjiList(); // 시작할 때 전체 한자 리스트 업데이트
		if (update != true) {
			System.out.println("최신 한자 정보를 가져오지 못했습니다.");
			System.out.println("프로그램을 종료합니다.");
		} else {
			System.out.println("최신 한자 정보를 업데이트 했습니다.");
			update = manager.getSearchHistory();// TODO
			update = manager.getTangoList();//TODO
			int select = 0;
			// 메인 메뉴
			while (true) {
				try {
					mainMenu();
					select = input.nextInt();
					input.nextLine();
					switch (select) {
					case 1: // 1. 한자 문장 분해하기
						splitKanji();
						break;
					case 2: // 2. 지난 문장 분해 히스토리
						showHistory();
						break;
					case 3: // 3. 오늘의 초급반 한자
						todayTango();
						break;
					case 9: // 9. 종료
						System.out.println("프로그램을 종료합니다.");
						return;
					default:
						System.out.println("존재하지 않는 메뉴 번호입니다.");
						break;
					}
				} catch (Exception e) {
					System.out.println("잘못 입력했습니다.");
					input.nextLine();
					continue;
				}
			}
		}
	}

	public void splitKanji() { // 1. 한자 문장 분해하기
		while (true) {
			System.out.print("[ 분해 문장 입력 ] ");
			String searchStr = input.nextLine();
			// 엔터만 입력하면 되돌아가는 기능
			if (searchStr.equals("")) {
				System.out.println("입력을 취소합니다.");
				return;
			}
			// String을 보내서 한자 오브젝트 객체의 리스트로 돌려 받는다.
			ArrayList<Kanji> checkKanji = manager.kanjiSplit(searchStr);
			// 한자 유무 체크
			if (checkKanji == null) { // 없다면
				System.out.println("입력하신 내용에 한자가 없습니다.");
			} else { // 있다면
				// TODO 검색한 결과를 DB에 저장해서 히스토리를 추가해야 하겠다.. (미구현)
				manager.insertSearchHistory(searchStr);
				manager.getSearchHistory();
				if (historyNum == 0) {
					currentSplitList = checkKanji;
				} else {
					splitHistory = new ArrayList<ArrayList<Kanji>>();
					splitHistory.add(currentSplitList);
					currentSplitList = checkKanji;
					historyNum++;
				}
				break;
			}
		}

		// 분해된 리스트를 출력
		if (currentSplitList == null) {
			System.out.println("선택한 한자에 분해 정보가 입력되어 있지 않습니다.");
			System.out.println("분해 결과 수정을 통해 입력해 주시겠습니까?");
		} else {
			splitListInfo();
			splitResult();
		}
	}

	public void showHistory() { // 2. 지난 문장 분해 히스토리
		while (true) {
			try {			
				currentSearchHistory = manager.getSearchHistoryList(date);
				selectCurrentSearchHistoryList();
				System.out.println("0. 이전 메뉴 / -1. 이전 날짜 / -2. 다음 날짜");
				System.out.print("[다시 분해할 문장 선택] ");
				int select = input.nextInt();
				input.nextLine();
				if (select <= currentSearchHistory.size() && select > 0) {
					// 선택한 문장을 가져오자.
					String selectStr = null;
					SearchHistory selectHistory = currentSearchHistory.get(select - 1);
					selectStr = selectHistory.getSearchStr();
					splitKanjiFromSelect(selectStr);
				} else if (select == 0) {
					return;
				} else if (select == -1) {
					date = dateMgr.getPrevDate(date);
					day = dateMgr.getDay(date);
				} else if (select == -2) {
					date = dateMgr.getNextDate(date);
					day = dateMgr.getDay(date);
				} else {
					System.out.println("없는 번호입니다.");
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
				continue;
			}
		}
	}
	
	public void todayTango() { // 3. 오늘의 초급반 한자
		startTodayTango(); 
		while (true) {
			try {
				currentTangoList = manager.getCurrentTangoList(date);
				selectCurrentTangoList();
				System.out.println("0. 이전 메뉴 / -1. 이전 날짜 / -2. 다음 날짜");
				System.out.println("9. 전체 단어 자세히 보기 / -9. 관리자 모드");
				System.out.print("[분해할 단어 선택] ");
				int select = input.nextInt();
				input.nextLine();
				if (select <= currentTangoList.size() && select > 0) {
					// 선택한 문장을 가져오자.
					String selectStr = null;
					TodayTango todayTango = currentTangoList.get(select - 1);
					selectStr = todayTango.getTangoStr();
					splitKanjiFromSelect(selectStr);
				}else {
					switch (select) {
					case 0:
						return;
					case -1:
						date = dateMgr.getPrevDate(date);
						day = dateMgr.getDay(date);	
						break;
					case -2:
						date = dateMgr.getNextDate(date);
						day = dateMgr.getDay(date);
						break;
					case 9:
						showCurrentTangoList();
						break;
					case -9:
						updateTangoMenu();
						break;
					default:
						System.out.println("없는 번호입니다.");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
				continue;
			}
		}
	}
	
	public void startTodayTango() {
		date = dateMgr.getToday(); // 시작할 때는 무조건 오늘 날짜
		day = dateMgr.getDay(date); // 사용하지는 않지만 date 사용할 때는 항상 같이 바꿔주기
		currentTangoList = manager.getCurrentTangoList(date);
		if (currentTangoList == null) {
			System.out.println("오늘의 한자가 입력되지 않았습니다. 관리자에게 문의하세요.\r\n" + "[이메일] wonjinism@gmail.com");
			System.out.println();
		}
	}
	
	public void selectCurrentTangoList() {
		System.out.println();
		System.out.println("[ 초급반 한자 ] " + date + day);
		System.out.println("==================================");
		if (currentTangoList.size() == 0) {
			System.out.println();
			System.out.println("입력된 단어가 없습니다.");
			System.out.println();
			System.out.println("==================================");
		} else {
			int tangoNum = 0;
			String tangoStr = null;
			String tangoHurigana = null;
			String tangoImi = null;
			for (TodayTango todayTango : currentTangoList) {
				tangoNum = todayTango.getTangoNum();
				tangoStr = todayTango.getTangoStr();
				tangoHurigana = todayTango.getTangoHurigana();
				tangoImi = todayTango.getTangoImi();
				System.out.println(tangoNum + ". " + tangoStr + "(" + tangoHurigana + ") : " + tangoImi);
			}
			System.out.println("==================================");
		}
	}
	
	public void showCurrentTangoList() {
		int count = 0;
		System.out.println("[ 날짜 ] " + date + day);
		System.out.println("==================================");
		System.out.println();
		if (currentTangoList.size() == 0) {
			System.out.println("입력된 단어가 없습니다.");
			System.out.println();
			System.out.println("==================================");
		} else {
			for (TodayTango todayTango : currentTangoList) {
				String tangoStr = todayTango.getTangoStr();
				String tangoHurigana = todayTango.getTangoHurigana();
				String tangoImi = todayTango.getTangoImi();
				System.out.println("[단어] " + tangoStr + "(" + tangoHurigana + ")");
				System.out.println("[의미] " + tangoImi);
				showTangoSplit(tangoStr);
				count++;
				if (count < currentTangoList.size()) {
					System.out.println("\n=======================");
					System.out.println("다음 단어를 보려면 Enter를 입력하세요..");
					pause();
					input.nextLine();
				} else {
					System.out.println("\n=======================");
					System.out.println("모든 단어 검색을 마쳤습니다.");
					System.out.println("Enter를 입력하면 메뉴를 출력합니다.");
					pause();
				}
			}
		}
	}
	
	public void showTangoSplit(String tangoStr) {
		ArrayList<Kanji> splitResultList = manager.kanjiSplit(tangoStr); // 한자가 있는 경우에만 ArrayList 삽입
		// 한자가 있으면
		if (splitResultList != null) {
			int count = 0;
			System.out.print("[분할] ");
			for (Kanji splitKanji : splitResultList) {
				System.out.print(splitKanji.getKanji());
				count++;
				if (count < splitResultList.size()) {
					System.out.print("(" + splitKanji.getOnkunnKR() + ") / ");
				} else {
					System.out.print("(" + splitKanji.getOnkunnKR() + ")");
				}
			}
			System.out.println();
		} else {
			System.out.println("[분할] 정보가 없습니다.");
		}
	}
	
	public void updateTangoMenu() {
		while (true) {
			try {
				currentTangoList = manager.getCurrentTangoList(date);
				selectCurrentTangoList();
				selectUpdateTangoMenu();
				int select = input.nextInt();
				input.nextLine(); // TODO
				switch (select) {
				case 1: // 1. 단어 추가 하기
					selectCurrentTangoList();
					select = 0;
					TodayTango tangoToInsert = makeTango(select);
					if (tangoToInsert == null) {
						break;
					} else {
						currentTangoList = manager.insertCurrentTangoList(tangoToInsert, date);
						boolean result = todayTangoDAO.insertTango(tangoToInsert); // TODO
						if (result) {
							System.out.println("단어를 추가 했습니다.");
						}
					}
					break;
				case 2: // 2. 단어 수정 하기
					boolean updateResult = updateTango(); // TODO
					if (updateResult == false) {
						return;
					}
					break;
				case 3: // 3. 단어 삭제 하기
					boolean deleteResult = deleteTango();
					if (deleteResult == false) {
						return;
					}
					break;
				case -1: // -1. 이전 날짜
					date = dateMgr.getPrevDate(date);
					break;
				case -2: // -2. 다음 날짜
					date = dateMgr.getNextDate(date);
					break;
				case 0: // 0. 이전 메뉴
					return;
				default:
					System.out.println("존재하지 않는 메뉴 번호입니다.");
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				System.out.println("error 3");
				input.nextLine();
				continue;
			}
		}
	}
	
	public boolean updateTango() {
		boolean result = false;
		while (true) {
			try {
				selectCurrentTangoList();
				System.out.println("0. 이전 메뉴");
				System.out.print("[ 번호 선택 ] ");
				int select = input.nextInt();
				input.nextLine();
				if (select <= currentTangoList.size() && select > 0) {
					TodayTango tangoToUpdate = makeTango(select);
					if (tangoToUpdate != null) {
						currentTangoList = manager.udpateCurrentTangoList(tangoToUpdate, date);
						boolean updateResult = manager.updateTango(tangoToUpdate);
//						deleteResult = manager.deleteTango(tangoToDelete);
						manager.getTangoList();
						if (updateResult) {
							System.out.println("단어를 수정 했습니다.");
							result = true;
							return result;
						} else {
							System.out.println("단어가 정상적으로 수정되지 않았습니다."); ////
							result = true;
							return result;
						}
					}
				} else if (select == 9) {
					result = false;
					return result;
				} else {
					System.out.println("없는 번호입니다.");
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
				continue;
			}
		}
	}
	

	public boolean deleteTango() {
		boolean result = false;
		while (true) {
			try {
				selectCurrentTangoList();
				System.out.println("0. 이전 메뉴");
				System.out.print("[ 번호 선택 ] ");
				int select = input.nextInt();
				input.nextLine();
				if (select <= currentTangoList.size() && select > 0) {
					// 선택된 번호로 단어를 미리 찾아 놓고, tangoNum을 모두 업데이트 해주자.
					TodayTango tangoToDelete = currentTangoList.get(select - 1);
					// 전체 리스트로 가서 기존 리스트의 단어를 모두 삭제한다. 나중에 번호가 변경된 리스트로 다시 추가해준다.
					boolean deleteResult = manager.deleteCurrentTangoList(currentTangoList, date);
					// 현재 리스트에서 선택된 단어를 삭제하고 tangoNum을 변경해 준다.
					currentTangoList.remove(tangoToDelete);
					if(currentTangoList.size() != 0) {
						for (int i = 0; i < currentTangoList.size(); i++) {
							TodayTango tango = currentTangoList.get(i);
							tango.setTangoNum(i + 1);
							currentTangoList.set(i, tango);
						}
						// 변경된 currentTangoList를 가져가서 전체 리스트에 추가해준다.
						currentTangoList = manager.insertCurrentTangoList(currentTangoList, date);
						boolean updateResult = manager.updateTangoList(currentTangoList);
					}
					// 이제 DB로 가서 삭제하고 나머지 단어의 tangoNum을 업데이트 해준다.
					deleteResult = manager.deleteTango(tangoToDelete);
					manager.getTangoList();
					currentTangoList = manager.getCurrentTangoList(date);
					if (deleteResult) {
						System.out.println("단어를 삭제했습니다.");
						result = true;
						return result;
					} else {
						System.out.println("단어가 정상적으로 수정되지 않았습니다."); ////
						result = true;
						return result;
					}
				} else if (select == 0) {
					result = false;
					return result;
				} else {
					System.out.println("없는 번호입니다.");
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
				continue;
			}
		}
	}
	
	public TodayTango makeTango(int select) {
		TodayTango tango = null;
		int tangoNum = currentTangoList.size();
		String tangoDate = date;
		String tangoStr = null;
		// 신규 단어 등록인지 수정 등록인지 확인
		if (select == 0) {
			if (tangoNum == 5) {
				System.out.println("5개의 단어만 등록할 수 있습니다. 삭제하거나 수정하세요.");
				return tango;
			} else {
				tangoNum++;
				System.out.println("새로운 단어 입력을 시작합니다.");
				System.out.println("Enter만 입력하면 입력을 취소합니다.");
			}
		} else {
			tangoNum = select;
			System.out.println(tangoNum + "번째 단어의 수정을 시작합니다.");
			System.out.println("Enter만 입력하면 변경하지 않습니다.");
		}
		while (true) {
			try {
				System.out.print("[" + tangoNum + "번째 일본어 입력] ");
				tangoStr = input.nextLine();
				// Enter 입력 점검
				if (tangoStr.equals("")) {
					if (select == 0) {
						return tango;
					} else {
						tangoStr = currentTangoList.get(tangoNum - 1).getTangoStr();
					}
				}
				if (select == 0) { // 신규 입력 선택한 사람은 이미 입력한 단어는 입력 받지 않음.
					String check = findTangoStr(tangoStr);
					if (check != null) {
						System.out.println("이미 입력한 단어입니다.");
					} else { // 없으면 이제 나머지 정보 입력으로 이동
						break;
					}
				} else { // 그 외에는 무조건 입력 루트
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
			}
		}
		System.out.print("[" + tangoNum + "번째 후리가나 입력] ");
		String tangoHurigana = input.nextLine();
		// Enter 입력 점검
		if (tangoHurigana.equals("")) {
			if (select == 0) {
				return tango;
			} else {
				tangoHurigana = currentTangoList.get(tangoNum - 1).getTangoHurigana();
			}
		}
		System.out.print("[" + tangoNum + "번째 한글 뜻 입력] ");
		String tangoImi = input.nextLine();
		// Enter 입력 점검
		if (tangoImi.equals("")) {
			if (select == 0) {
				System.out.println("입력을 취소합니다.");
				return tango;
			} else {
				tangoImi = currentTangoList.get(tangoNum - 1).getTangoImi();
			}
		}
		tango = new TodayTango(tangoDate, tangoNum, tangoStr, tangoHurigana, tangoImi);
		return tango;
	}
	
	public void selectUpdateTangoMenu() {
		System.out.println("1. 단어 추가 / 2. 단어 수정 / 3. 단어 삭제");
		System.out.println("==================================");
		System.out.println("0. 이전 메뉴 / -1. 이전 날짜 / -2. 다음 날짜");
		System.out.print("[ 메뉴 선택 ] ");
	}

	public String findTangoStr(String inputStr) {
		String tangoStr = null;
		for (TodayTango todayTango : currentTangoList) {
			if (todayTango.getTangoStr().equals(inputStr)) {
				tangoStr = todayTango.getTangoStr();
			}
		}
		return tangoStr;
	}

	
	public void splitKanjiFromSelect(String selectStr) {
		ArrayList<Kanji> checkKanji = manager.kanjiSplit(selectStr);
		if (historyNum == 0) {
			currentSplitList = checkKanji;
		} else {
			splitHistory = new ArrayList<ArrayList<Kanji>>();
			splitHistory.add(currentSplitList);
			currentSplitList = checkKanji;
			historyNum++;
		}
		splitListInfo();
		splitResult();
	}

	public boolean selectCurrentSearchHistoryList() { // TODO
		System.out.println();
		System.out.println("[ 지난 문장 분해 히스토리 ] " + date + day);
		System.out.println("==================================");
		if (currentSearchHistory.size() == 0) {
			System.out.println();
			System.out.println("문장 문해 히스토리가 없습니다.");
			System.out.println();
			System.out.println("==================================");
			return false;
		} else {
			int searchNum = 0;
			String searchStr;
			for (SearchHistory searchHistory : currentSearchHistory) {
				searchNum++;
				searchStr = searchHistory.getSearchStr();
				System.out.println(searchNum + ". " + searchStr);
			}
			System.out.println("==================================");
			return true;
		}
	}

	public void splitResult() {
		while (true) {
			try {
				if (currentSplitList == null) {
					System.out.println("선택한 한자에 분해 정보가 입력되어 있지 않습니다.");
					System.out.println("분해 결과 수정을 통해 입력해 주시겠습니까?");
					break;
				} else {
					splitResultMenu();
					int select = input.nextInt();
					input.nextLine();
					switch (select) {
					case 9: // 9. 새로운 문장 분해하기
						splitKanji();
						break;
					case 1: // 1. 현재 분해 결과 다시보기
						splitListInfo();
						break;
					case 2: // 2. 현재 분해 결과 더 분해하기
						splitKanjiAgain();
						break;
					case 3: // 3. 현재 분해 결과 수정하기
						// 분석한 한자를 나열해서 보여주고 번호로 선택
						splitListUpdate();
						break;
					case 4: // 4. 이전 분해 결과로
						if (historyNum == 0) {
							System.out.println("최상위 결과입니다. 이전 결과가 없습니다.");
						} else {
							currentSplitList = splitHistory.get(historyNum - 1);
							historyNum--;
							splitListInfo();
						}
						break;
					case 0: // 0. 이전 메뉴
						return;
					default:
						System.out.println("존재하지 않는 메뉴 번호입니다.");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다. (입력값 오류)");
				input.nextLine();
				continue;
			}
		}
	}

	/*
	 * 한자 분석 결과를 가지고 있는 ArrayList를 풀어서 보여주는 메소드
	 */
	public void splitListInfo() {
		// 분해된 한자의 목록을 한줄 나열
		System.out.print("\n[ 분해된 한자 ] ");
		showListKanjiOnly();
		System.out.println("=======================");
		// 검색된 결과의 한자 오브젝트의 정보를 콘솔 출력
		int count = 0;
		System.out.println();
		for (Kanji kanji : currentSplitList) {
			// 한자와 한글 음훈 출력
			showKanjiKR(kanji);
			// Kanji 객체가 가지고 있는 splitResult를 분해해서 음훈 뜻을 콘솔 출력
			showSplitListStr(kanji);
			// 스토리 메모 출력
			showKanjiMemo(kanji);
			// 일본어 음훈 출력
			System.out.println("[음독] " + kanji.getHurigana());
			System.out.println("[훈독] " + kanji.getImi());
			// 다음 한자 대기
			count++;
			if (count < currentSplitList.size()) {
				System.out.println("\n=======================");
				System.out.println("다음 한자를 보려면 Enter를 입력하세요..");
				pause();
				input.nextLine();
			} else {
				System.out.println("\n=======================");
				System.out.println("모든 한자 검색을 마쳤습니다.");
				System.out.println("Enter를 입력하면 메뉴를 출력합니다.");
				pause();
			}
		}
	}

	public void splitKanjiAgain() {
		while (true) {
			try {
				System.out.println("\n[ 분해 결과 추가 분해하기 ]");
				splitListToSelect();
				int selectNum = 0;
				// 숫자 입력 받고 숫자의 오류 체크
				selectNum = input.nextInt();
				input.nextLine();
				boolean checkResult = splitListSelectNumCheck(selectNum);
				if (checkResult) {
					int maxMenuNum = currentSplitList.size() + 1;
					if (selectNum == maxMenuNum) { // 이전 메뉴로 돌아가기
						return;
					} else {
						splitKanjiAgainAction(selectNum - 1);
						break;
					}
				} else {
					System.out.println("잘못 입력했습니다. (메뉴 번호 오류)");
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다. (입력 오류)");
				input.nextLine();
				continue;
			}
		}
	}

	public boolean splitKanjiAgainAction(int kanjiNum) {
		// 남은 것은 한자 번호이므로 한자 추가 분해를 시작합니다.
		Kanji kanjiToDo = currentSplitList.get(kanjiNum);
		String splitListStr = kanjiToDo.getSplitListStr();
		if (currentSplitList == null) {
			System.out.println("선택한 한자에 분해 정보가 입력되어 있지 않습니다.");
			System.out.println("분해 결과 수정을 통해 입력해 주시겠습니까?");
			return false;
		} else {
			if (checkString(splitListStr) == 0) { // 한자가 입력되어 있다.
				// 선택된 한자의 분해 정보 String을 가져와서 List로 쪼개기
				if (splitHistory == null) {
					splitHistory = new ArrayList<ArrayList<Kanji>>();
				}
				splitHistory.add(currentSplitList);
				currentSplitList = manager.kanjiSplit(splitListStr);
				historyNum++;
				// 저장된 리스트를 보여주고 끝내기
				splitListInfo();
				return true;
			} else {
				System.out.println("선택한 한자에 분해 정보가 입력되어 있지 않습니다.");
				System.out.println("분해 결과 수정을 통해 입력해 주시겠습니까?");
				return false;
			}
		}
	}

	public void splitListUpdate() {
		while (true) {
			try {
				System.out.println("\n[ 분해 결과 수정하기 ]");
				splitListToSelect();
				int selectNum = 0;
				// 숫자 입력 받고 숫자의 오류 체크
				selectNum = input.nextInt();
				input.nextLine();
				// 원하는 범위의 숫자값인지 확인
				boolean checkResult = splitListSelectNumCheck(selectNum);
				if (checkResult) {
					int maxMenuNum = currentSplitList.size() + 1;
					if (selectNum == maxMenuNum) { // 이전 메뉴로 돌아가기
						return;
					} else {
						splitListUpdateAction(selectNum - 1);
						break;
					}
				} else {
					System.out.println("잘못 입력했습니다. (메뉴 번호 오류)");
				}
			} catch (Exception e) {
				System.out.println("잘못 입력했습니다.");
				input.nextLine();
				continue;
			}
		}
	}

	public void splitListUpdateAction(int kanjiNum) {
		// 남은 것은 한자 번호이므로 한자 수정 시작합니다.
		Kanji kanjiToDo = currentSplitList.get(kanjiNum);
		String splitListStr = null;
		String kanjiMemo = null;
		showKanjiToUpdate(kanjiToDo);
		// 분할 정보 수정
		while (true) {
			System.out.println("==================================");
			System.out.println("Enter만 입력하면 수정하지 않습니다.");
			System.out.print("[ 분할 정보 수정 입력 ] ");
			splitListStr = input.nextLine();
			// 입력 String 1차 확인
			if (splitListStr.equals("")) { // Enter만 친 경우 다음으로 넘어갑니다.
				splitListStr = kanjiToDo.getSplitListStr();
				break;
			}
			// 이제 입력한 값에 한자가 있는지 체크합시다. String을 보내서 한자 오브젝트 객체의 리스트로 돌려 받기
			ArrayList<Kanji> checkKanji = manager.kanjiSplit(splitListStr);
			// 한자 유무 체크
			if (checkKanji == null) { // 없다면
				System.out.println("입력하신 내용에 한자가 없습니다.");
			} else { // 있다면 이제 메모를 입력하러 갑시다.
				break;
			}
		}
		// 메모 정보 수정
		while (true) {
			System.out.print("[ 메모 정보 수정 입력 ] ");
			kanjiMemo = input.nextLine();
			// 입력 String 1차 확인
			if (kanjiMemo.equals("")) { // Enter만 친 경우 다음으로 넘어갑니다.
				kanjiMemo = kanjiToDo.getKanjiMemo();
			}
			break;
		}
		// 입력이 끝난 값을 업데이트 하고 여기 결과값 리스트에도 업데이트
		kanjiToDo.setSplitListStr(splitListStr);
		kanjiToDo.setKanjiMemo(kanjiMemo);
		currentSplitList.set(kanjiNum, kanjiToDo);

		// 수정 진행 확인 후 수정
		showKanjiToUpdate(kanjiToDo);// 일단 보여주고
		System.out.println("==================================");
		System.out.println("위 같이 수정 하시겠습니까? (Y/N)");
		String result = input.nextLine();
		if (result.equals("Y") || result.equals("y")) {
			boolean updateResult = manager.updateKanjiToUpdate(kanjiToDo);
			if (updateResult) {
				System.out.println("수정에 성공했습니다..");
			} else {
				System.out.println("수정에 실패했습니다.");
			}
		} else {
			System.out.println("수정을 취소했습니다.");
		}
	}

	public boolean splitListSelectNumCheck(int inputNum) {
		try {
			int maxMenuNum = currentSplitList.size() + 1;
			if (inputNum > maxMenuNum || inputNum < 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void showKanjiToUpdate(Kanji kanjiToUpdate) {
		System.out.println();
		showKanjiKR(kanjiToUpdate);
		showSplitListStr(kanjiToUpdate);
		showKanjiMemo(kanjiToUpdate);
	}

	private void splitListToSelect() {
		System.out.println("==================================");
		// searchSplitList에 저장된 검색 결과를 번호 붙여서 나열
		showSplitList();
		System.out.println((currentSplitList.size() + 1) + ". 이전 메뉴로");
		System.out.println("==================================");
		System.out.print("[ 원하는 한자 번호 입력 ] ");
	}

	public void showSplitList() {
		int count = 0;
		for (Kanji kanji : currentSplitList) {
			count++;
			System.out.print(count + ". " + kanji.getKanji() + "(" + kanji.getOnkunnKR() + ")");
			System.out.println();
		}
	}

	/*
	 * 메인 메뉴
	 */
	public void mainMenu() {
		System.out.println("\n==== [ 꼬리에 꼬리를 무는 한자 학습 ] =====");
		System.out.println("1. 한자 문장 분해하기");
		System.out.println("2. 지난 문장 분해 히스토리");
		System.out.println("3. 오늘의 초급반 한자");
		System.out.println("9. 종료");
		System.out.println("==================================");
		System.out.print("[ 메뉴 선택 ] ");
	}

	/*
	 * 분해 결과를 나열한 이후 서브 메뉴 UI
	 */
	public void splitResultMenu() {
		System.out.print("\n[ 분해된 한자 ] ");
		// 결과물이 들어간 ArrayList에서 한자를 끄집어 내서 한자만 표기 메소드로
		showListKanjiOnly();
		System.out.println("==================================");
		System.out.println("1. 현재 분해 결과 다시보기");
		System.out.println("2. 현재 분해 결과 더 분해하기");
		System.out.println("3. 현재 분해 결과 수정하기");
		System.out.println("4. 이전 분해 결과로");
		System.out.println("9. 새로운 문장 분해하기");
		System.out.println("==================================");
		System.out.println("0. 이전 메뉴");
		System.out.print("[ 메뉴 선택 ] ");
	}

	/*
	 * ArrayList에서 Kanji String만 보여주는 메서드
	 */
	public void showListKanjiOnly() {
		for (Kanji kanji : currentSplitList) {
			System.out.print(kanji.getKanji());
		}
		System.out.println();
	}

	/*
	 * Kanji Obj의 한자와 한글 음훈을 출력
	 */
	public void showKanjiKR(Kanji kanji) {
		System.out.println(kanji.getKanji() + "(" + kanji.getOnkunnKR() + ")");
	}

	/*
	 * Kanji Obj의 메모를 출력
	 */
	public void showKanjiMemo(Kanji kanji) {
		String story = kanji.getKanjiMemo();
		if (story == null || story.equals("null") || story.length() == 0) {
			System.out.println("[메모] 정보가 없습니다.");
		} else {
			System.out.println("[메모] " + story);
		}
	}

	/*
	 * Kanji Obj의 splitResult의 Kanji를 Obj로 잘라서 List에 저장하고 출력
	 */
	public void showSplitListStr(Kanji kanji) {
		// 우선 이 안에 한자가 있는지 확인
		String splitListStr = kanji.getSplitListStr();
		ArrayList<Kanji> splitResultList = manager.kanjiSplit(splitListStr); // 한자가 있는 경우에만 ArrayList 삽입
		// 한자가 있으면
		if (splitResultList != null) {
			int count = 0;
			System.out.print("[분할] ");
			for (Kanji splitKanji : splitResultList) {
				System.out.print(splitKanji.getKanji());
				count++;
				if (count < splitResultList.size()) {
					System.out.print("(" + splitKanji.getOnkunnKR() + ") / ");
				} else {
					System.out.print("(" + splitKanji.getOnkunnKR() + ")");
				}
			}
			System.out.println();
		} else {
			System.out.println("[분할] 정보가 없습니다.");
		}
	}

	public void pause() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 입력 받은 스트링을 잘못 입력했는지 확인
	 */
	public int checkString(String input) {
		// 공백을 입력했거나 비정상적인 값일 경우
		if (input == null || input.equals("") || input.equals("정보가 없습니다.")) {
			return 1; // 공백 또는 비정상값
		}
		return 0;
	}
}
