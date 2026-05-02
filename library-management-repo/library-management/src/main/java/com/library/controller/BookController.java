package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.service.AuthorService;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // -------------------------------------------------------
    // READ: List all books (Home page)
    // GET /
    // -------------------------------------------------------
    @GetMapping("/")
    public String listBooks(@RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "genre", required = false) String genre,
                            Model model) {
        try {
            List<Book> books;
            if (search != null && !search.trim().isEmpty()) {
                books = bookService.searchByTitle(search);
                model.addAttribute("pageTitle", "Search Results for '" + search + "'");
            } else if (genre != null && !genre.trim().isEmpty() && !genre.equals("All")) {
                books = bookService.filterByGenre(genre);
                model.addAttribute("pageTitle", genre + " Books");
            } else {
                books = bookService.getAll();
                model.addAttribute("pageTitle", "All Books");
            }
            model.addAttribute("books", books);
            model.addAttribute("searchKeyword", search);
            model.addAttribute("selectedGenre", genre);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load books: " + e.getMessage());
        }
        return "list";
    }

    // -------------------------------------------------------
    // CREATE: Show the add-book form
    // GET /add
    // -------------------------------------------------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            List<Author> authors = authorService.getAll();
            model.addAttribute("book", new Book());
            model.addAttribute("authors", authors);
            model.addAttribute("pageTitle", "Add New Book");
            model.addAttribute("formAction", "/save");
            model.addAttribute("buttonLabel", "Add Book");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load form: " + e.getMessage());
        }
        return "form";
    }

    // -------------------------------------------------------
    // CREATE: Handle form submission and save new book
    // POST /save
    // -------------------------------------------------------
    @PostMapping("/save")
    public String saveBook(@RequestParam("title") String title,
                           @RequestParam("genre") String genre,
                           @RequestParam("authorId") Long authorId,
                           RedirectAttributes redirectAttributes) {
        try {
            Author author = authorService.getById(authorId);
            Book book = new Book(title, genre, author);
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book '" + title + "' added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Integrity violation: This book may already exist or violates a database constraint.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error saving book: " + e.getMessage());
        }
        return "redirect:/";
    }

    // -------------------------------------------------------
    // UPDATE: Show the edit form pre-filled with book data
    // GET /edit/{id}
    // -------------------------------------------------------
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Book book = bookService.getById(id);
            List<Author> authors = authorService.getAll();
            model.addAttribute("book", book);
            model.addAttribute("authors", authors);
            model.addAttribute("pageTitle", "Edit Book");
            model.addAttribute("formAction", "/update/" + id);
            model.addAttribute("buttonLabel", "Update Book");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Book not found: " + e.getMessage());
            return "list";
        }
        return "form";
    }

    // -------------------------------------------------------
    // UPDATE: Handle update form submission
    // POST /update/{id}
    // -------------------------------------------------------
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam("title") String title,
                             @RequestParam("genre") String genre,
                             @RequestParam("authorId") Long authorId,
                             RedirectAttributes redirectAttributes) {
        try {
            Author author = authorService.getById(authorId);
            Book updatedBook = new Book(title, genre, author);
            bookService.update(id, updatedBook);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Integrity violation while updating book. Please check your inputs.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error updating book: " + e.getMessage());
        }
        return "redirect:/";
    }

    // -------------------------------------------------------
    // DELETE: Remove a book by ID
    // GET /delete/{id}
    // -------------------------------------------------------
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Cannot delete: book is referenced by other records.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error deleting book: " + e.getMessage());
        }
        return "redirect:/";
    }
}
