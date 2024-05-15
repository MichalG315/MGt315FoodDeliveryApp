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
import pl.zajavka.infrastructure.database.entity.*;
import pl.zajavka.infrastructure.database.repository.jpa.*;

import java.util.List;

import static pl.zajavka.util.EntityFixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FoodOrderRepositoryDataJpaTest {

    private final FoodOrderJpaRepository foodOrderJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final AddressExtendedJpaRepository addressExtendedJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void canFindAllFoodOrdersByCustomer() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        CustomerEntity customer = customerJpaRepository.saveAndFlush(someCustomer1());
        FoodOrderEntity foodOrder = foodOrderJpaRepository.saveAndFlush(someFoodOrderEntity1(customer, restaurant));

        // when
        List<FoodOrderEntity> result = foodOrderJpaRepository.findAllByCustomer(customer);

        //then
        Assertions.assertThat(foodOrder).isEqualTo(result.get(0));
    }

    @Test
    void canFindFoodOrderByNumber() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        CustomerEntity customer = customerJpaRepository.saveAndFlush(someCustomer1());
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        FoodOrderEntity foodOrder = foodOrderJpaRepository.saveAndFlush(someFoodOrderEntity1(customer, restaurant));

        // when
        FoodOrderEntity result = foodOrderJpaRepository.findByFoodOrderNumber(foodOrder.getFoodOrderNumber());

        // then
        Assertions.assertThat(foodOrder).isEqualTo(result);
    }

    @Test
    void canFindAllFoodOrdersByRestaurant() {
        // given
        AddressEntity address = addressJpaRepository.saveAndFlush(someAddress1());
        AddressExtendedEntity addressExtended = addressExtendedJpaRepository
                .saveAndFlush(someAddressExtended1(address));
        CustomerEntity customer = customerJpaRepository.saveAndFlush(someCustomer1());
        RestaurantEntity restaurant = restaurantJpaRepository.saveAndFlush(someRestaurant1(addressExtended));
        FoodOrderEntity foodOrder = foodOrderJpaRepository.saveAndFlush(someFoodOrderEntity1(customer, restaurant));

        // when
        List<FoodOrderEntity> result = foodOrderJpaRepository.findAllByRestaurant(restaurant);

        // then
        Assertions.assertThat(foodOrder).isEqualTo(result.get(0));
    }


}