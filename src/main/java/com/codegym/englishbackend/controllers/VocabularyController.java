package com.codegym.englishbackend.controllers;


import com.codegym.englishbackend.exception.ResourceNotFoundException;
import com.codegym.englishbackend.model.User;
import com.codegym.englishbackend.model.Vocabulary;
import com.codegym.englishbackend.repositories.UserRepository;
import com.codegym.englishbackend.repositories.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.codegym.englishbackend.controllers.ResponseUtil.resourceUri;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class VocabularyController {
    private final UserRepository userRepository;
    private final VocabularyRepository vocabularyRepository;

    @GetMapping("/users/{userId}/vocabularies")
    public Page<Vocabulary> getAllVocabulary(@PathVariable(value = "userId") Long userId,
                                             Pageable pageable) {
        return vocabularyRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/vocabulary")
    public ResponseEntity<Vocabulary> createVocabulary(@PathVariable(value = "userId") Long userId,
                                                      @Valid @RequestBody Vocabulary vocabularyReq){
        return userRepository.findById(userId)
                .map(user -> {
                    vocabularyReq.setUser(user);
                    return vocabularyRepository.save(vocabularyReq);
                })
                .map(vocabulary -> ResponseEntity.created(resourceUri(vocabulary.getId()))
                .body(vocabulary))
                .orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/vocabularies/{vocabularyId}")
    public ResponseEntity<Vocabulary> updateVocabulary(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "vocabularyId") Long vocabularyId,
            @Valid @RequestBody Vocabulary vocabularyReq
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UserId " + userId + " not found"
                ));

        return vocabularyRepository.findById(vocabularyId)
                .map(vocabulary -> {
                    vocabulary.setUser(user);
                    vocabulary.setDes(vocabularyReq.getDes());
                    vocabulary.setLang(vocabularyReq.getLang());
                    vocabulary.setMean(vocabularyReq.getMean());
                    vocabulary.setWord(vocabularyReq.getWord());
                    vocabulary.setUpdateAt(vocabularyReq.getUpdateAt());
                    return vocabularyRepository.save(vocabulary);
                })
                .map(vocabulary -> ResponseEntity
                .ok().location(
                        resourceUri(vocabulary.getId()
                        )
                ).body(vocabulary)
                ).orElseThrow(() -> new ResourceNotFoundException("VocabularyId " + vocabularyId + "not found"));
    }

    @DeleteMapping("/users/{userId}/vocabularies/{vocabularyId}")
    public ResponseEntity<?> DeleteVocabulary(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "vocabularyId") Long vocabularyId
    ) {
        return vocabularyRepository.findByIdAndUserId(userId, vocabularyId)
                .map(vocabulary -> {
                    vocabularyRepository.delete(vocabulary);
                    return ResponseEntity.ok().build();
                }).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "vocabulary not found with userId "
                                        + userId +
                                        " and vocabularyId "
                                        + vocabularyId
                        )
                );
    }
}
