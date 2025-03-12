package az.security.bookstore.service.impl;

import az.security.bookstore.dao.entity.BookEntity;
import az.security.bookstore.dao.entity.CustomerBookEntity;
import az.security.bookstore.dao.repository.BookRepository;
import az.security.bookstore.dao.repository.CustomerBookRepository;
import az.security.bookstore.dao.repository.CustomerRepository;
import az.security.bookstore.dto.BookRentedDto;
import az.security.bookstore.dto.BookStockDto;
import az.security.bookstore.service.RentalService;
import az.security.bookstore.util.enums.RentalStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final CustomerBookRepository customerBookRepository;


    @Override
    @Transactional
    public String rentBook(Long customerId, Long bookId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(customerId + " id-e sahib müştəri tapılmadı"));

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException(bookId + " id-e sahib kitab tapılmadı"));

        boolean isBookRented = customerBookRepository.existsByCustomerAndBookAndStatus(customer,book,RentalStatus.RENTED);
        if(isBookRented){
            return book.getName() + " adli kitaba sahibsiniz";
        }
        if (book.getStockCount() <= 0) {
            return "Bağışlayın, bu kitab artıq mövcud deyil!";
        }

        if (customer.getBalance() < book.getPrice()) {
            return "Balansınız kifayət etmir";
        }


        customer.setBalance(customer.getBalance() - book.getPrice());
        customerRepository.save(customer);

        book.setStockCount(book.getStockCount() - 1);
        bookRepository.save(book);

        customerBookRepository.save(
                CustomerBookEntity.builder()
                        .book(book)
                        .customer(customer)
                        .rentalDate(LocalDate.now())
                        .status(RentalStatus.RENTED)
                        .build()
        );

        return book.getName() + " adlı kitab uğurla kirayəyə götürüldü!";
    }


    @Override
    @Transactional
    public String returnBook(Long customerId, Long bookId) {

        var book = bookRepository.findById(bookId).orElseThrow(() ->
                new RuntimeException(bookId + " id-e sahib kitab tapilmadi"));

        var customer = customerRepository.findById(customerId).orElseThrow(() ->
                new RuntimeException(customerId + " id-e sahib müştəri tapılmadı"));

        var customerBook = customerBookRepository.findByCustomerAndBook(customer, book)
                .orElseThrow(() -> new RuntimeException("Bu müştəri bu kitabı icareye götürməyib!"));

        book.setStockCount(book.getStockCount() + 1);
        bookRepository.save(book);
        customerBookRepository.delete(customerBook);

        return book.getName() + " kitabı uğurla geri qaytarıldı!";
    }

    @Override
    public List<BookRentedDto> getRentedBooks() {
        List<BookEntity> books = bookRepository.findAll();

        return books.stream().map((book) -> BookRentedDto.builder()
                .bookName(book.getName())
                .rentedByCustomers(book.getCustomers()
                        .stream()
                        .map((customer) -> customer.getName() + " " + customer.getSurname())
                        .toList()
                )
                .build()
        ).toList();
    }

    @Override
    public List<BookStockDto> getBookStock() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(
                        (book) ->
                                BookStockDto.builder()
                                        .bookName(book.getName())
                                        .totalCount(book.getStockCount())
                                        .rentedCount(
                                                (int) (long) customerBookRepository.countByBookAndStatus(book,RentalStatus.RENTED)
                                        )
                                        .availableCount(book.getStockCount() -
                                                (int) (long) customerBookRepository.countByBookAndStatus(book,RentalStatus.RENTED))
                                        .build()
                ).toList();
    }

}
