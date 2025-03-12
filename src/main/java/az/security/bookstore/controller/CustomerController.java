package az.security.bookstore.controller;

import az.security.bookstore.dto.CustomerResponseDto;
import az.security.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("get/{id}")
    public CustomerResponseDto getCustomer(@RequestBody @PathVariable("id") Long id){
        return customerService.getByCustomer(id);
    }
    @GetMapping("/get")
    public List<CustomerResponseDto> getCustomer(){
        return customerService.getCustomers();
    }
}
