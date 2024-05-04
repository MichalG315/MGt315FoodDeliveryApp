package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.domain.Customer;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.CustomerEntityMapper;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public void saveCustomer(Customer customer) {
        customerJpaRepository.save(customerEntityMapper.mapToEntity(customer));
    }
}
