package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get all authors from the database.
     */
    public List<Author> getAll() {
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching all authors: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Save a new author or update an existing one.
     */
    public Author save(Author author) {
        try {
            return authorRepository.save(author);
        } catch (Exception e) {
            System.err.println("Error saving author: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get an author by their ID.
     */
    public Author getById(Long id) {
        try {
            Optional<Author> optional = authorRepository.findById(id);
            return optional.orElseThrow(() ->
                    new RuntimeException("Author not found with id: " + id));
        } catch (Exception e) {
            System.err.println("Error fetching author by id: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Update an existing author's details.
     */
    public Author update(Long id, Author updatedAuthor) {
        try {
            Author existing = getById(id);
            existing.setName(updatedAuthor.getName());
            return authorRepository.save(existing);
        } catch (Exception e) {
            System.err.println("Error updating author: " + e.getMessage());
            throw e;
        }
    }
}
