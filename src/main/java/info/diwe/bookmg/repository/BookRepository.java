package info.diwe.bookmg.repository;

import info.diwe.bookmg.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
