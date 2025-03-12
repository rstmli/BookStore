package az.security.bookstore.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookRentedDto {
    private String bookName;
    private List<String> rentedByCustomers;
}
