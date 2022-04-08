package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Book;
import uz.pdp.payload.BookDTO;
import uz.pdp.repository.BookRepository;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    //ADMIN,SUPER_ADMIN
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')") //role based authentication
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Book> list = bookRepository.findAll();
        return ResponseEntity.ok(list);
    }

    //ADMIN,SUPER_ADMIN
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')") //role based authentication
    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        Book save = bookRepository.save(book);
        return ResponseEntity.ok(save);
    }

    //ADMIN,SUPER_ADMIN
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')") //role based authentication
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody BookDTO dto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(dto.getName());
            book.setAuthor(dto.getAuthor());
            book.setPrice(dto.getPrice());
            book.setDescription(dto.getDescription());
            Book save = bookRepository.save(book);
            return ResponseEntity.ok(save);
        }
        return ResponseEntity.notFound().build();
    }

    //SUPER_ADMIN
//    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")//role based authentication
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setActive(false);
            bookRepository.save(book);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //ADMIN,SUPER_ADMIN
//        @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')") //role based authentication
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }


    //--------------------------VALIDATION-----------------------
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
