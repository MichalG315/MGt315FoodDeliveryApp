package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.configuration.PersistenceContainerTestConfiguration;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.repository.jpa.CustomerJpaRepository;

import static pl.zajavka.infrastructure.database.repository.support.EntityFixtures.someCustomer1;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerRepositoryDataJpaTest {

    private final CustomerJpaRepository customerJpaRepository;

    @Test
    void canFindCustomerByEmail() {
        // given
        CustomerEntity customer = customerJpaRepository.saveAndFlush(someCustomer1());

        // when
        CustomerEntity result = customerJpaRepository.findByEmail(customer.getEmail()).orElseThrow();

        // then
        Assertions.assertThat(customer).isEqualTo(result);
    }
}