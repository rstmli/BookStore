package az.security.bookstore.service;

import az.security.bookstore.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto getByCustomer(Long id);
    List<CustomerResponseDto> getCustomers();
}
