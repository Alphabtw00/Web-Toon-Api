package com.example.webtoonapi.controller;

import com.example.webtoonapi.entity.Webtoon;
import com.example.webtoonapi.repository.WebtoonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/webtoons")
public class WebtoonController {


    private WebtoonRepository webtoonRepository;

    public WebtoonController(WebtoonRepository webtoonRepository) {
        this.webtoonRepository = webtoonRepository;
    }

    @GetMapping
    public List<Webtoon> getAllWebtoons() {
        return webtoonRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Webtoon> createWebtoon(@RequestBody Webtoon webtoon) {
        Webtoon savedWebtoon = webtoonRepository.save(webtoon);
        return new ResponseEntity<>(savedWebtoon, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webtoon> getWebtoonById(@PathVariable Long id) {
        Optional<Webtoon> webtoon = webtoonRepository.findById(id);
        return webtoon.map(webtoon1 -> {
                    log.info("Webtoon found: {}", webtoon1);
                    return ResponseEntity.ok(webtoon1);
                })
                .orElseGet(() -> {
                    log.info("Webtoon not found with id: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebtoon(@PathVariable Long id) {
        if (webtoonRepository.existsById(id)) {
            webtoonRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}