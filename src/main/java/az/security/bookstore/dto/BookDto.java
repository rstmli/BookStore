package az.security.bookstore.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    Long id;
    String name;
    Integer stockCount;
    Double price;

}
