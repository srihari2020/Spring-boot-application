package com.library;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
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
 * Unit tests for BookService using JUnit 5 and Mockito.
 */
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Author sampleAuthor;
    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAuthor = new Author("Test Author");
        sampleAuthor.setId(1L);
        sampleBook = new Book("Test Book", "Fantasy", sampleAuthor);
        sampleBook.setId(1L);
    }

    // -------------------------------------------------------
    // Test: getAll() returns list of books
    // -------------------------------------------------------
    @Test
    void testGetAll_ReturnsBookList() {
        List<Book> books = Arrays.asList(sampleBook,
                new Book("Another Book", "Mystery", sampleAuthor));
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(books);

        List<Book> result = bookService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    // -------------------------------------------------------
    // Test: save() persists a book
    // -------------------------------------------------------
    @Test
    void testSave_ReturnsSavedBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);

        Book saved = bookService.save(sampleBook);

        assertNotNull(saved);
        assertEquals("Test Book", saved.getTitle());
        assertEquals("Fantasy", saved.getGenre());
        verify(bookRepository, times(1)).save(sampleBook);
    }

    // -------------------------------------------------------
    // Test: getById() returns correct book
    // -------------------------------------------------------
    @Test
    void testGetById_ReturnsBook_WhenExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        Book result = bookService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Book", result.getTitle());
    }

    // -------------------------------------------------------
    // Test: getById() throws exception when not found
    // -------------------------------------------------------
    @Test
    void testGetById_ThrowsException_WhenNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bookService.getById(99L));

        assertTrue(ex.getMessage().contains("Book not found"));
    }

    // -------------------------------------------------------
    // Test: update() updates book fields correctly
    // -------------------------------------------------------
    @Test
    void testUpdate_UpdatesBookDetails() {
        Book updatedData = new Book("Updated Title", "Mystery", sampleAuthor);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book result = bookService.update(1L, updatedData);

        assertEquals("Updated Title", result.getTitle());
        assertEquals("Mystery", result.getGenre());
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
