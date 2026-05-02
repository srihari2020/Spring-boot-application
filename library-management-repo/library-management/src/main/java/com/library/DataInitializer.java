package com.library;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only populate if tables are empty
        if (authorRepository.count() == 0) {
            System.out.println(">>> Initializing sample data...");

            // --- Create 10 Authors ---
            Author a1  = authorRepository.save(new Author("J.K. Rowling"));
            Author a2  = authorRepository.save(new Author("George R.R. Martin"));
            Author a3  = authorRepository.save(new Author("J.R.R. Tolkien"));
            Author a4  = authorRepository.save(new Author("Agatha Christie"));
            Author a5  = authorRepository.save(new Author("Stephen King"));
            Author a6  = authorRepository.save(new Author("Dan Brown"));
            Author a7  = authorRepository.save(new Author("Paulo Coelho"));
            Author a8  = authorRepository.save(new Author("Arthur Conan Doyle"));
            Author a9  = authorRepository.save(new Author("Mark Twain"));
            Author a10 = authorRepository.save(new Author("George Orwell"));

            // --- Create 10 Books ---
            bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy",  a1));
            bookRepository.save(new Book("A Game of Thrones",                      "Fantasy",  a2));
            bookRepository.save(new Book("The Fellowship of the Ring",              "Fantasy",  a3));
            bookRepository.save(new Book("Murder on the Orient Express",            "Mystery",  a4));
            bookRepository.save(new Book("The Shining",                             "Horror",   a5));
            bookRepository.save(new Book("The Da Vinci Code",                       "Thriller", a6));
            bookRepository.save(new Book("The Alchemist",                           "Fiction",  a7));
            bookRepository.save(new Book("The Hound of the Baskervilles",           "Mystery",  a8));
            bookRepository.save(new Book("Adventures of Huckleberry Finn",          "Fiction",  a9));
            bookRepository.save(new Book("1984",                                    "Sci-Fi",   a10));

            System.out.println(">>> Sample data inserted: 10 authors, 10 books.");
        } else {
            System.out.println(">>> Database already has data. Skipping initialization.");
        }
    }
}
