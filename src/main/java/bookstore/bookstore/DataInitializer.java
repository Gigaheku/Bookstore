package bookstore.bookstore;

import bookstore.bookstore.domain.AppUser;
import bookstore.bookstore.Book;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.repository.CategoryRepository;
import bookstore.bookstore.BookRepository;
import bookstore.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
 
        Category category1 = new Category("Sci-Fi");
        Category category2 = new Category("Comic");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Book book1 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "ISBN1", 7.99);
        book1.setCategory(category1);
        bookRepository.save(book1);

        Book book2 = new Book("Watchmen", "Alan Moore", 1987, "ISBN2", 15.99);
        book2.setCategory(category2);
        bookRepository.save(book2);

        AppUser user1 = new AppUser("user", bCryptPasswordEncoder.encode("password"), "user@example.com", "ROLE_USER");
        userRepository.save(user1);
        AppUser admin = new AppUser("admin", bCryptPasswordEncoder.encode("admin"), "admin@example.com", "ROLE_ADMIN");
        userRepository.save(admin);
        AppUser user2 = new AppUser("herkko", bCryptPasswordEncoder.encode("herkko"), "herkko@example.com", "ROLE_ADMIN");
        userRepository.save(user2);

        log.info("Successfully loaded initial data.");
    }
}