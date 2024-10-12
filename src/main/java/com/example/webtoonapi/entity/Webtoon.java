package com.example.webtoonapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.List;
@Entity
@Data
public class Webtoon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String summary;
    @ElementCollection
    private List<String> characters;
}
