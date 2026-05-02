package com.library;

import com.library.entity.Author;
import com.library.repository.AuthorRepository;
import com.library.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthorService using JUnit 5 and Mockito.
 */
class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author sampleAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAuthor = new Author("J.K. Rowling");
        sampleAuthor.setId(1L);
    }

    // -------------------------------------------------------
    // Test: getAll() returns all authors
    // -------------------------------------------------------
    @Test
    void testGetAll_ReturnsAuthorList() {
        List<Author> authors = Arrays.asList(
                sampleAuthor,
                new Author("George R.R. Martin")
        );
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("J.K. Rowling", result.get(0).getName());
        verify(authorRepository, times(1)).findAll();
    }

    // -------------------------------------------------------
    // Test: save() saves and returns author
    // -------------------------------------------------------
    @Test
    void testSave_ReturnsSavedAuthor() {
        when(authorRepository.save(sampleAuthor)).thenReturn(sampleAuthor);

        Author saved = authorService.save(sampleAuthor);

        assertNotNull(saved);
        assertEquals("J.K. Rowling", saved.getName());
        verify(authorRepository, times(1)).save(sampleAuthor);
    }

    // -------------------------------------------------------
    // Test: getById() returns author when found
    // -------------------------------------------------------
    @Test
    void testGetById_ReturnsAuthor_WhenFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(sampleAuthor));

        Author result = authorService.getById(1L);

        assertNotNull(result);
        assertEquals("J.K. Rowling", result.getName());
    }

    // -------------------------------------------------------
    // Test: getById() throws when author missing
    // -------------------------------------------------------
    @Test
    void testGetById_ThrowsException_WhenNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> authorService.getById(99L));

        assertTrue(ex.getMessage().contains("Author not found"));
    }

    // -------------------------------------------------------
    // Test: update() changes the author's name
    // -------------------------------------------------------
    @Test
    void testUpdate_ChangesAuthorName() {
        Author updateData = new Author("Joanne Rowling");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(sampleAuthor));
        when(authorRepository.save(any(Author.class))).thenAnswer(inv -> inv.getArgument(0));

        Author result = authorService.update(1L, updateData);

        assertEquals("Joanne Rowling", result.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }
}
