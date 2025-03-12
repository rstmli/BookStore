package az.security.bookstore.dao.repository;

import az.security.bookstore.dao.entity.BookEntity;
import az.security.bookstore.dao.entity.CustomerBookEntity;
import az.security.bookstore.dao.entity.CustomerEntity;
import az.security.bookstore.util.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerBookRepository extends JpaRepository<CustomerBookEntity,Long> {
    Optional<CustomerBookEntity> findByCustomerAndBook(CustomerEntity customer, BookEntity book);
    Long countByBookAndStatus(BookEntity book, RentalStatus status);
    boolean existsByCustomerAndBookAndStatus(CustomerEntity customer, BookEntity book, RentalStatus status);

}
