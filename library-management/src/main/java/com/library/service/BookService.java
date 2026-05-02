package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Get all books using custom INNER JOIN query (joins with Author table).
     */
    public List<Book> getAll() {
        try {
            return bookRepository.findAllBooksWithAuthors();
        } catch (Exception e) {
            System.err.println("Error fetching all books: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Search books by title.
     */
    public List<Book> searchByTitle(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    /**
     * Filter books by genre.
     */
    public List<Book> filterByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    /**
     * Save a new book to the database.
     */
    public Book save(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            System.err.println("Error saving book: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get a single book by its ID.
     */
    public Book getById(Long id) {
        try {
            Optional<Book> optional = bookRepository.findById(id);
            return optional.orElseThrow(() ->
                    new RuntimeException("Book not found with id: " + id));
        } catch (Exception e) {
            System.err.println("Error fetching book by id: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Delete a book by its ID.
     */
    public void delete(Long id) {
        try {
            Book book = getById(id);
            bookRepository.delete(book);
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Update an existing book's details.
     */
    public Book update(Long id, Book updatedBook) {
        try {
            Book existing = getById(id);
            existing.setTitle(updatedBook.getTitle());
            existing.setGenre(updatedBook.getGenre());
            existing.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existing);
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
            throw e;
        }
    }
}
