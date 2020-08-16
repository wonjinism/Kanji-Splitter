package io.zerone.kanjisplitter.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter @Setter
public class Kanji {
	@Id @GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private KanjiType kanjiType; // 상용, 인명자, 모양자
	private int school; // 소학교 1~6, 중고교
	private int jlpt; // JLPT 1~5
	private int stroke; // 획수
	private String kanji; // 한자
	private String korean; // 한글 음훈
	private String split; // 분해 한자
	private String story; // 분해 메모
	private String furigana;
	private String imi;
	private String selfRadical;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private Kanji superKanji;
//
//	@OneToMany(mappedBy = "superKanji", cascade = CascadeType.ALL)
	@Transient
	private List<Kanji> splitList;

}
