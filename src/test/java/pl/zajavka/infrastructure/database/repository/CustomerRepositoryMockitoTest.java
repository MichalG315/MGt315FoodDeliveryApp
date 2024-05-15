package pl.zajavka.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.domain.Customer;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.CustomerEntityMapper;
import pl.zajavka.util.DomainFixtures;
import pl.zajavka.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryMockitoTest {

    @Mock
    private CustomerJpaRepository customerJpaRepository;

    @Mock
    private CustomerEntityMapper customerEntityMapper;

    @InjectMocks
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer() {
        // given
        Customer customer = DomainFixtures.someCustomer1();
        Mockito.when(customerEntityMapper.mapToEntity(customer)).thenReturn(EntityFixtures.someCustomer1());

        // when
        customerRepository.saveCustomer(customer);

        // then
        Mockito.verify(customerEntityMapper, Mockito.times(1)).mapToEntity(customer);
    }
}