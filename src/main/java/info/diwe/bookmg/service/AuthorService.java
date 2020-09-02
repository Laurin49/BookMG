package info.diwe.bookmg.service;

import info.diwe.bookmg.model.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthors();
    Author getAuthorById(Long id);

    void saveAuthor(Author author);
    void deleteAuthor(Long id);

    Page<Author> findPaginated(int pageNo, int pageSize);
}
