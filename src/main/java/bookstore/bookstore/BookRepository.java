package bookstore.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Tarvittaessa lisää mukautettuja kyselyjä
}
