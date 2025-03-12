package az.security.bookstore.dao.entity;

import az.security.bookstore.util.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "customer_book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    BookEntity book;

    LocalDate rentalDate;
    LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    RentalStatus status;
}
