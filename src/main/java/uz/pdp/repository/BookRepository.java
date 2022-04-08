package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
