package az.security.bookstore.controller;

import az.security.bookstore.dto.BookDto;
import az.security.bookstore.dto.PagedResponseDto;
import az.security.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {
    private final BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<Void> getBookAdd(@RequestBody BookDto dto){
        return bookService.addBook(dto);
    }

    @GetMapping("/get")
    public List<BookDto> getBooks ( @RequestParam(required = false) String name,
                                      @RequestParam(required = false) Integer stockCount,
                                      @RequestParam(required = false) Double price){
        return bookService.findBooks(name,stockCount,price);
    }

    @GetMapping("/getpage")
    public PagedResponseDto<BookDto> getBooksPage (@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer stockCount,
                                                   @RequestParam(required = false) Double price,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size)
    {
        return bookService.findBooksPaging(name,stockCount,price,page,size);
    }

}
