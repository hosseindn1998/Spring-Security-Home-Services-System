package ir.hosseindn.service.customer;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Customer;
import ir.hosseindn.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer register(Customer customer) {
        if (customerRepository.findByEmailOrNationalCode(customer.getEmail(), customer.getNationalCode()).isPresent())
            throw new DuplicateInformationException("A Customer with this Email/National Code exist.");
        customer.setRegisteredDate(LocalDate.now());
        return customerRepository.save(customer);
    }

    public Customer changePassword(String email, String newPassword) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Customer with email :" + email + " Not found.")
        );
        customerRepository.updatePassword(email, newPassword);
        return customer;
    }

    public Customer login(String email, String Password) {
        return customerRepository.findByEmailAndPassword(email, Password).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }
}
