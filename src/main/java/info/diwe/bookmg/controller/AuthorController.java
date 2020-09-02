package info.diwe.bookmg.controller;

import info.diwe.bookmg.model.Author;
import info.diwe.bookmg.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public ModelAndView getAuthorList(ModelAndView modelAndView) {
        modelAndView.addObject("authorList", authorService.getAuthors());
        return findPaginated(1, modelAndView);
    }

    @GetMapping("/newAuthor")
    public ModelAndView newAuthor(ModelAndView modelAndView) {
        Author author = new Author();
        modelAndView.addObject("author", author);
        modelAndView.setViewName("/authors/newAuthor");
        return modelAndView;
    }

    @PostMapping("/saveAuthor")
    public ModelAndView saveBook(@ModelAttribute("book") Author author, ModelAndView modelAndView) {
        authorService.saveAuthor(author);
        modelAndView.setViewName("redirect:/authors/list");
        return modelAndView;
    }

    @GetMapping("/updateAuthor/{id}")
    public ModelAndView updateAuthor(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Author author = authorService.getAuthorById(id);
        modelAndView.addObject("author", author);
        modelAndView.setViewName("/authors/updateAuthor");
        return modelAndView;
    }

    @GetMapping("/deleteAuthor/{id}")
    public ModelAndView deleteAuthor(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        authorService.deleteAuthor(id);
        modelAndView.setViewName("redirect:/authors/list");
        return modelAndView;
    }

    @GetMapping("/page/{pageNo}")
    private ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelAndView modelAndView) {
        int pageSize = 5;

        Page<Author> page = authorService.findPaginated(pageNo, pageSize);
        List<Author> authorList = page.getContent();

        modelAndView.addObject("currentPage", pageNo);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalItems", page.getTotalElements());
        modelAndView.addObject("authorList", authorList);
        modelAndView.setViewName("/authors/authorList");
        return modelAndView;
    }

}
