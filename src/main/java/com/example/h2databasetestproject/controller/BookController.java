package com.example.h2databasetestproject.controller;

import com.example.h2databasetestproject.entity.Book;
import com.example.h2databasetestproject.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        log.info("getAllBooks() method called");
        ModelAndView modelAndView = new ModelAndView("list-books");
        modelAndView.addObject("books", bookRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/addBookForm")
    public ModelAndView addBookForm() {
        log.info("addBookForm() method called");
        ModelAndView modelAndView = new ModelAndView("add-book-form");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book, HttpSession session) {
        log.info("saveBook() method called");
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long bookId) {
        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = new Book();
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        }
        mav.addObject("book", book);
        return mav;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId) {
        log.info("deleteBook() method called");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.deleteById(bookId);
        }
        return "redirect:/books";
    }
}
