package az.security.bookstore.service;

import az.security.bookstore.dto.BookDto;
import az.security.bookstore.dto.PagedResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<Void> addBook(BookDto dto);
    List<BookDto> findBooks(String name, Integer stockCount, Double price);
    PagedResponseDto<BookDto> findBooksPaging(
            String name, Integer stockCount, Double price, Integer page, Integer size);

}
