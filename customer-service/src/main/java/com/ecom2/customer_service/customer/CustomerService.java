package com.ecom2.customer_service.customer;

import com.ctc.wstx.util.StringUtil;
import com.ecom2.customer_service.customer.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));

        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    public void  mergeCustomer(Customer customer, CustomerRequest request){
        if (StringUtils.isBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());

    }

    public Boolean exitstById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(format("No customer found with the ID:: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
