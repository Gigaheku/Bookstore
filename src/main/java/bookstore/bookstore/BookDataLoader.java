package bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BookDataLoader.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Harri Potteri", "Kukku Luuruu", 2021, "ISBN1", 29.99);
        bookRepository.save(book1);
        log.info("Lisätty kirja: {}", book1);

        Book book2 = new Book("Herrojen Sormukset", "Keke Koo", 2022, "ISBN2", 39.99);
        bookRepository.save(book2);
        log.info("Lisätty kirja: {}", book2);

        // Lisää omia kirjoja tarpeen mukaan
    }
}

