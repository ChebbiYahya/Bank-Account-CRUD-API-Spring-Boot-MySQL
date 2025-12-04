package com.bank.crudbankwithmysql.controllers;

import com.bank.crudbankwithmysql.DTO.CustomerRequestDTO;
import com.bank.crudbankwithmysql.DTO.CustomerResponseDTO;
import com.bank.crudbankwithmysql.entities.Customer;
import com.bank.crudbankwithmysql.interfaces.CustomerService;
import com.bank.crudbankwithmysql.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(customerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return customerMapper.toResponseDTO(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO requestDTO) {
        Customer customer = customerMapper.toEntity(requestDTO);
        Customer saved = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerMapper.toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id,
                                              @RequestBody CustomerRequestDTO requestDTO) {
        Customer customer = customerMapper.toEntity(requestDTO);
        Customer updated = customerService.updateCustomer(id, customer);
        return customerMapper.toResponseDTO(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
