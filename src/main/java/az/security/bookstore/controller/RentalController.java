package az.security.bookstore.controller;

import az.security.bookstore.dto.BookRentedDto;
import az.security.bookstore.dto.BookStockDto;
import az.security.bookstore.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book/")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("rental")
    public String getRental(@RequestParam Long customerId, @RequestParam Long bookId){
        return rentalService.rentBook(customerId,bookId);
    }
    @PostMapping("return")
    public String getReturnBook(@RequestParam Long customerId, @RequestParam Long bookId){
        return rentalService.returnBook(customerId,bookId);
    }



    @GetMapping("/rentedBook")
    public List<BookRentedDto> getRentedBooks() {
        return rentalService.getRentedBooks();
    }
    @GetMapping("/bookstock")
    public List<BookStockDto> getBookStock() {
        return rentalService.getBookStock();
    }
}
