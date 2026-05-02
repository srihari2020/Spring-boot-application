package com.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, String genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
}
