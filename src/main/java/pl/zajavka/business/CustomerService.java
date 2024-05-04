package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.domain.Customer;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }
}
