package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.domain.Customer;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }
}
