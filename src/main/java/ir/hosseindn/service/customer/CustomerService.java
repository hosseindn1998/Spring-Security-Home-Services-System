package ir.hosseindn.service.customer;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.model.Customer;
import ir.hosseindn.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public Customer register(Customer customer){
        if(customerRepository.findByEmailOrNationalCode(customer.getEmail(),customer.getNationalCode()).isPresent())
            throw new DuplicateInformationException("A Customer with this Email/National Code exist.");
        return customerRepository.save(customer);
    }
}
