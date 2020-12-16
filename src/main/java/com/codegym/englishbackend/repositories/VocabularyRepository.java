package com.codegym.englishbackend.repositories;

import com.codegym.englishbackend.entity.Vocabulary;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    Page<Vocabulary> findByUserId(Long userId, Pageable pageable);
    Optional<Vocabulary> findByIdAndUserId(Long id, Long userId);
}
