package az.security.bookstore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookStockDto {
    private String bookName;
    private int totalCount;
    private int rentedCount;
    private int availableCount;
}
