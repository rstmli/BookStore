package az.security.bookstore.mapper;

import az.security.bookstore.dao.entity.BookEntity;
import az.security.bookstore.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "SPRING",unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    BookEntity dtoToEntity(BookDto dto);
    List<BookDto> entityToDto(List<BookEntity>dto);

    default Page<BookDto> entityToPageDto(Page<BookEntity> entities, Pageable pageable) {
        List<BookDto> dtoList = entityToDto(entities.getContent());
        return new PageImpl<>(dtoList, pageable, entities.getTotalElements());
    }



}
