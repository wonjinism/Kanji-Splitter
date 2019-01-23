package io.zerone.kanjisplitter.VO;

import java.util.ArrayList;

public class Kanji {
	private int kanji_id;
	private int kanji_type; // 상용, 인명자, 모양자
	private int school; // 소학교 1~6, 중고교
	private int jlpt; // JLPT 1~5
	private int stroke; // 획수
	private String kanji; // 한자
	private String korean; // 한글 음훈
	private String split; // 분해 한자
	private String story; // 분해 메모
	private String furigana;
	private String imi;
	private ArrayList<Kanji> splitList;

	public Kanji() {
	}

	public int getKanji_id() {
		return kanji_id;
	}

	public void setKanji_id(int kanji_id) {
		this.kanji_id = kanji_id;
	}

	public int getKanji_type() {
		return kanji_type;
	}

	public void setKanji_type(int kanji_type) {
		this.kanji_type = kanji_type;
	}

	public int getSchool() {
		return school;
	}

	public void setSchool(int school) {
		this.school = school;
	}

	public int getJlpt() {
		return jlpt;
	}

	public void setJlpt(int jlpt) {
		this.jlpt = jlpt;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getKorean() {
		return korean;
	}

	public void setKorean(String korean) {
		this.korean = korean;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public String getImi() {
		return imi;
	}

	public void setImi(String imi) {
		this.imi = imi;
	}

	public ArrayList<Kanji> getSplitList() {
		return splitList;
	}

	public void setSplitList(ArrayList<Kanji> splitList) {
		this.splitList = splitList;
	}

	@Override
	public String toString() {
		return "Kanji [kanji_id=" + kanji_id + ", kanji_type=" + kanji_type + ", school=" + school + ", jlpt=" + jlpt
				+ ", stroke=" + stroke + ", kanji=" + kanji + ", korean=" + korean + ", split=" + split + ", story="
				+ story + ", furigana=" + furigana + ", imi=" + imi + "]";
	}

}
