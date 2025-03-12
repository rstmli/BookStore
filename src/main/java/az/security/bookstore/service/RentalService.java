package az.security.bookstore.service;

import az.security.bookstore.dto.BookRentedDto;
import az.security.bookstore.dto.BookStockDto;

import java.util.List;

public interface RentalService {
    String rentBook(Long customerId, Long bookId);
    String returnBook(Long customerId, Long bookId);
    List<BookRentedDto> getRentedBooks();
    List<BookStockDto> getBookStock();
}
