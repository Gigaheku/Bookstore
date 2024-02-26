package bookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/booklist")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
    @GetMapping("/editbook/{id}")
public String showEditBookForm(@PathVariable("id") long id, Model model) {
    Book book = bookRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

    model.addAttribute("book", book);
    return "editbook";
}
}