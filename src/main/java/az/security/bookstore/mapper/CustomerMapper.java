package az.security.bookstore.mapper;

import az.security.bookstore.dao.entity.CustomerEntity;
import az.security.bookstore.dto.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "SPRING",unmappedTargetPolicy = ReportingPolicy.IGNORE
        ,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "surname", target = "userSurname")
    CustomerResponseDto entityToDto(CustomerEntity entity);

    List<CustomerResponseDto> entityToListDto(List<CustomerEntity> entities);

}
