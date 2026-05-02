package com.library.controller;

import com.library.entity.Author;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // -------------------------------------------------------
    // READ: List all authors
    // GET /authors
    // -------------------------------------------------------
    @GetMapping
    public String listAuthors(Model model) {
        try {
            List<Author> authors = authorService.getAll();
            model.addAttribute("authors", authors);
            model.addAttribute("pageTitle", "All Authors");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load authors: " + e.getMessage());
        }
        return "author-list";
    }

    // -------------------------------------------------------
    // CREATE: Show the add-author form
    // GET /authors/add
    // -------------------------------------------------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Add New Author");
        model.addAttribute("formAction", "/authors/save");
        model.addAttribute("buttonLabel", "Add Author");
        return "author-form";
    }

    // -------------------------------------------------------
    // CREATE: Save new author
    // POST /authors/save
    // -------------------------------------------------------
    @PostMapping("/save")
    public String saveAuthor(@RequestParam("name") String name,
                             RedirectAttributes redirectAttributes) {
        try {
            Author author = new Author(name.trim());
            authorService.save(author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author '" + name + "' added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Integrity violation: An author with this name may already exist.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error saving author: " + e.getMessage());
        }
        return "redirect:/authors";
    }

    // -------------------------------------------------------
    // UPDATE: Show edit form for an author
    // GET /authors/edit/{id}
    // -------------------------------------------------------
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Author author = authorService.getById(id);
            model.addAttribute("author", author);
            model.addAttribute("pageTitle", "Edit Author");
            model.addAttribute("formAction", "/authors/update/" + id);
            model.addAttribute("buttonLabel", "Update Author");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Author not found: " + e.getMessage());
        }
        return "author-form";
    }

    // -------------------------------------------------------
    // UPDATE: Handle update form submission
    // POST /authors/update/{id}
    // -------------------------------------------------------
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @RequestParam("name") String name,
                               RedirectAttributes redirectAttributes) {
        try {
            Author updatedAuthor = new Author(name.trim());
            authorService.update(id, updatedAuthor);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Integrity violation while updating author. Please check your input.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error updating author: " + e.getMessage());
        }
        return "redirect:/authors";
    }
}
