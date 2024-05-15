package pl.zajavka.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.domain.Customer;
import pl.zajavka.util.DomainFixtures;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void saveCustomer() {
        // given
        Customer customer = DomainFixtures.someCustomer1();

        // when
        customerService.saveCustomer(customer);

        // then
        Mockito.verify(customerDAO, Mockito.times(1)).saveCustomer(customer);
    }
}