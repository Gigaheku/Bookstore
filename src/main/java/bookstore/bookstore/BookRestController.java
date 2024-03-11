package bookstore.bookstore;

import bookstore.bookstore.Book;
import bookstore.bookstore.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books") // Kaikki tän kontrollerin reitit alkaa /api/books
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    // Hae kaikki kirjat
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Hae yksittäinen kirja ID:n perusteella
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }
}
