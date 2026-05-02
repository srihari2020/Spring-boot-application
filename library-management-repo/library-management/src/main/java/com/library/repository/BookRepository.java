package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Custom JPQL query performing an INNER JOIN between Book and Author.
     * Returns books that have a valid author (inner join filters out unmatched rows).
     */
    @Query("SELECT b FROM Book b INNER JOIN b.author a ORDER BY a.name, b.title")
    List<Book> findAllBooksWithAuthors();

    /**
     * Find books by genre (case-insensitive).
     */
    List<Book> findByGenreIgnoreCase(String genre);

    /**
     * Find books by title containing a keyword (case-insensitive).
     */
    List<Book> findByTitleContainingIgnoreCase(String title);

    /**
     * Find books by author id.
     */
    List<Book> findByAuthorId(Long authorId);
}
