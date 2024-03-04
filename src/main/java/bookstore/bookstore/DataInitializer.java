package bookstore.bookstore;

import bookstore.bookstore.domain.Category;
import bookstore.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Category scifi = new Category("Sci-Fi");
        Category comic = new Category("Comic");
        Category fantasy = new Category("Fantasy");

        // Tallenna kategoriat tietokantaan
        categoryRepository.save(scifi);
        categoryRepository.save(comic);
        categoryRepository.save(fantasy);
    }
}
