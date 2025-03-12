package az.security.bookstore.service.impl;

import az.security.bookstore.dao.repository.CustomerRepository;
import az.security.bookstore.dto.CustomerResponseDto;
import az.security.bookstore.mapper.CustomerMapper;
import az.security.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto getByCustomer(Long id) {

        var entity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " id not found "));
        return customerMapper.entityToDto(entity);
    }

    @Override
    public List<CustomerResponseDto> getCustomers() {

        var entity = customerRepository.findAll();
        return customerMapper.entityToListDto(entity);
    }
}
