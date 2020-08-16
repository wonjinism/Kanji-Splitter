package io.zerone.kanjisplitter.repository;

import io.zerone.kanjisplitter.entity.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiRepository extends JpaRepository<Kanji, Long> {

}
