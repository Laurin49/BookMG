package info.diwe.bookmg.service;

import info.diwe.bookmg.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    Book getBookById(long id);

    void saveBook(Book book);
    void deleteBookById(long id);

    Page<Book> findPaginated(int pageNo, int pageSize);

}
