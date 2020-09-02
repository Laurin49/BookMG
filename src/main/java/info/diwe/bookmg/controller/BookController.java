package info.diwe.bookmg.controller;

import info.diwe.bookmg.model.Book;
import info.diwe.bookmg.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // List of Books
    @GetMapping("/list")
    public ModelAndView getBookList(ModelAndView modelAndView) {
        modelAndView.addObject("bookList", bookService.getBooks());
        return findPaginated(1, modelAndView);
    }

    @GetMapping("/newBook")
    public ModelAndView newBook(ModelAndView modelAndView) {
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("/books/newBook");
        return modelAndView;
    }

    @PostMapping("/saveBook")
    public ModelAndView saveBook(@ModelAttribute("book") Book book, ModelAndView modelAndView) {
        bookService.saveBook(book);
        modelAndView.setViewName("redirect:/books/list");
        return modelAndView;
    }

    @GetMapping("/updateBook/{id}")
    public ModelAndView updateBook(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Book book = bookService.getBookById(id);
        modelAndView.addObject("book", book);
        modelAndView.setViewName("/books/updateBook");
        return modelAndView;
    }

    @GetMapping("/deleteBook/{id}")
    public ModelAndView deleteBook(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        bookService.deleteBookById(id);
        modelAndView.setViewName("redirect:/books/list");
        return modelAndView;
    }

    @GetMapping("/page/{pageNo}")
    private ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelAndView modelAndView) {
        int pageSize = 5;

        Page<Book> page = bookService.findPaginated(pageNo, pageSize);
        List<Book> bookList = page.getContent();

        modelAndView.addObject("currentPage", pageNo);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalItems", page.getTotalElements());
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("/books/bookList");
        return modelAndView;
    }
}
