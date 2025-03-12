package az.security.bookstore.service.impl;

import az.security.bookstore.dao.entity.BookEntity;
import az.security.bookstore.dao.repository.BookRepository;
import az.security.bookstore.dto.BookDto;
import az.security.bookstore.dto.PagedResponseDto;
import az.security.bookstore.mapper.BookMapper;
import az.security.bookstore.service.BookService;
import az.security.bookstore.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public ResponseEntity<Void> addBook(BookDto dto) {
        bookRepository.save(bookMapper.dtoToEntity(dto));
        return ResponseEntity.ok().build();
    }


    public List<BookDto> findBooks(String name, Integer stockCount, Double price) {
        Specification<BookEntity> spec = Specification
                .where(BookSpecification.hasName(name))
                .and(BookSpecification.hasStockCount(stockCount))
                .and(BookSpecification.hasPrice(price));

        return bookMapper.entityToDto(bookRepository.findAll(spec));
    }

    public PagedResponseDto<BookDto> findBooksPaging(
            String name, Integer stockCount, Double price, Integer page, Integer size) {

        Specification<BookEntity> spec =
                Specification
                        .where(BookSpecification.hasName(name))
                        .and(BookSpecification.hasStockCount(stockCount))
                        .and(BookSpecification.hasPrice(price));

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<BookEntity> bookEntities = bookRepository.findAll(spec, pageable);
        Page<BookDto> bookDtoPage = bookMapper.entityToPageDto(bookEntities, pageable);

        return new PagedResponseDto<>(
                bookDtoPage.getContent(),
                bookDtoPage.getNumber(),
                bookDtoPage.getSize(),
                bookDtoPage.getTotalElements(),
                bookDtoPage.getTotalPages(),
                bookDtoPage.isLast()
        );
    }


}
