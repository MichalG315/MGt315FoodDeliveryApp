package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.User;
import pl.zajavka.infrastructure.security.entity.UserEntity;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;



    public Customer buildCustomer(User user, UserEntity saved) {
        return Customer.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userId(saved.getId())
                .build();
    }

    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }
}
