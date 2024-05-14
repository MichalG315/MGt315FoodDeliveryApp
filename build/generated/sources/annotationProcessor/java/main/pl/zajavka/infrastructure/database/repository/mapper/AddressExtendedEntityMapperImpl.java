package pl.zajavka.infrastructure.database.repository.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.CustomerAddress;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.CustomerAddressEntity;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-14T22:26:42+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AddressExtendedEntityMapperImpl implements AddressExtendedEntityMapper {

    @Override
    public AddressExtendedEntity mapToEntity(AddressExtended addressExtended) {
        if ( addressExtended == null ) {
            return null;
        }

        AddressExtendedEntity.AddressExtendedEntityBuilder addressExtendedEntity = AddressExtendedEntity.builder();

        addressExtendedEntity.addressExtendedId( addressExtended.getAddressExtendedId() );
        addressExtendedEntity.streetNumber( addressExtended.getStreetNumber() );
        addressExtendedEntity.address( addressToAddressEntity( addressExtended.getAddress() ) );
        addressExtendedEntity.customerAddresses( customerAddressSetToCustomerAddressEntitySet( addressExtended.getCustomerAddresses() ) );

        return addressExtendedEntity.build();
    }

    @Override
    public AddressExtended mapFromEntity(AddressExtendedEntity addressExtendedEntity) {
        if ( addressExtendedEntity == null ) {
            return null;
        }

        AddressExtended.AddressExtendedBuilder addressExtended = AddressExtended.builder();

        addressExtended.addressExtendedId( addressExtendedEntity.getAddressExtendedId() );
        addressExtended.streetNumber( addressExtendedEntity.getStreetNumber() );
        addressExtended.address( addressEntityToAddress( addressExtendedEntity.getAddress() ) );
        addressExtended.customerAddresses( customerAddressEntitySetToCustomerAddressSet( addressExtendedEntity.getCustomerAddresses() ) );

        return addressExtended.build();
    }

    protected Set<AddressExtendedEntity> addressExtendedSetToAddressExtendedEntitySet(Set<AddressExtended> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressExtendedEntity> set1 = new LinkedHashSet<AddressExtendedEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressExtended addressExtended : set ) {
            set1.add( mapToEntity( addressExtended ) );
        }

        return set1;
    }

    protected RestaurantEntity restaurantToRestaurantEntity(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantEntity.RestaurantEntityBuilder restaurantEntity = RestaurantEntity.builder();

        restaurantEntity.restaurantName( restaurant.getRestaurantName() );
        restaurantEntity.description( restaurant.getDescription() );
        restaurantEntity.userId( restaurant.getUserId() );

        return restaurantEntity.build();
    }

    protected RestaurantDeliveryAddressEntity restaurantDeliveryAddressToRestaurantDeliveryAddressEntity(RestaurantDeliveryAddress restaurantDeliveryAddress) {
        if ( restaurantDeliveryAddress == null ) {
            return null;
        }

        RestaurantDeliveryAddressEntity.RestaurantDeliveryAddressEntityBuilder restaurantDeliveryAddressEntity = RestaurantDeliveryAddressEntity.builder();

        restaurantDeliveryAddressEntity.restaurantDeliveryAddressId( restaurantDeliveryAddress.getRestaurantDeliveryAddressId() );
        restaurantDeliveryAddressEntity.address( addressToAddressEntity( restaurantDeliveryAddress.getAddress() ) );
        restaurantDeliveryAddressEntity.restaurant( restaurantToRestaurantEntity( restaurantDeliveryAddress.getRestaurant() ) );

        return restaurantDeliveryAddressEntity.build();
    }

    protected Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddressSetToRestaurantDeliveryAddressEntitySet(Set<RestaurantDeliveryAddress> set) {
        if ( set == null ) {
            return null;
        }

        Set<RestaurantDeliveryAddressEntity> set1 = new LinkedHashSet<RestaurantDeliveryAddressEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RestaurantDeliveryAddress restaurantDeliveryAddress : set ) {
            set1.add( restaurantDeliveryAddressToRestaurantDeliveryAddressEntity( restaurantDeliveryAddress ) );
        }

        return set1;
    }

    protected AddressEntity addressToAddressEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.addressId( address.getAddressId() );
        addressEntity.country( address.getCountry() );
        addressEntity.city( address.getCity() );
        addressEntity.postalCode( address.getPostalCode() );
        addressEntity.streetName( address.getStreetName() );
        addressEntity.addressesExtended( addressExtendedSetToAddressExtendedEntitySet( address.getAddressesExtended() ) );
        addressEntity.restaurantDeliveryAddresses( restaurantDeliveryAddressSetToRestaurantDeliveryAddressEntitySet( address.getRestaurantDeliveryAddresses() ) );

        return addressEntity.build();
    }

    protected Set<CustomerAddressEntity> customerAddressSetToCustomerAddressEntitySet(Set<CustomerAddress> set) {
        if ( set == null ) {
            return null;
        }

        Set<CustomerAddressEntity> set1 = new LinkedHashSet<CustomerAddressEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CustomerAddress customerAddress : set ) {
            set1.add( customerAddressToCustomerAddressEntity( customerAddress ) );
        }

        return set1;
    }

    protected CustomerEntity customerToCustomerEntity(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.name( customer.getName() );
        customerEntity.surname( customer.getSurname() );
        customerEntity.email( customer.getEmail() );
        customerEntity.phone( customer.getPhone() );
        customerEntity.userId( customer.getUserId() );
        customerEntity.customerAddresses( customerAddressSetToCustomerAddressEntitySet( customer.getCustomerAddresses() ) );

        return customerEntity.build();
    }

    protected CustomerAddressEntity customerAddressToCustomerAddressEntity(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }

        CustomerAddressEntity.CustomerAddressEntityBuilder customerAddressEntity = CustomerAddressEntity.builder();

        customerAddressEntity.customerAddressId( customerAddress.getCustomerAddressId() );
        customerAddressEntity.addressExtended( mapToEntity( customerAddress.getAddressExtended() ) );
        customerAddressEntity.customer( customerToCustomerEntity( customerAddress.getCustomer() ) );

        return customerAddressEntity.build();
    }

    protected Set<AddressExtended> addressExtendedEntitySetToAddressExtendedSet(Set<AddressExtendedEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressExtended> set1 = new LinkedHashSet<AddressExtended>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressExtendedEntity addressExtendedEntity : set ) {
            set1.add( mapFromEntity( addressExtendedEntity ) );
        }

        return set1;
    }

    protected Restaurant restaurantEntityToRestaurant(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantName( restaurantEntity.getRestaurantName() );
        restaurant.description( restaurantEntity.getDescription() );
        restaurant.userId( restaurantEntity.getUserId() );

        return restaurant.build();
    }

    protected RestaurantDeliveryAddress restaurantDeliveryAddressEntityToRestaurantDeliveryAddress(RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity) {
        if ( restaurantDeliveryAddressEntity == null ) {
            return null;
        }

        RestaurantDeliveryAddress.RestaurantDeliveryAddressBuilder restaurantDeliveryAddress = RestaurantDeliveryAddress.builder();

        restaurantDeliveryAddress.restaurantDeliveryAddressId( restaurantDeliveryAddressEntity.getRestaurantDeliveryAddressId() );
        restaurantDeliveryAddress.address( addressEntityToAddress( restaurantDeliveryAddressEntity.getAddress() ) );
        restaurantDeliveryAddress.restaurant( restaurantEntityToRestaurant( restaurantDeliveryAddressEntity.getRestaurant() ) );

        return restaurantDeliveryAddress.build();
    }

    protected Set<RestaurantDeliveryAddress> restaurantDeliveryAddressEntitySetToRestaurantDeliveryAddressSet(Set<RestaurantDeliveryAddressEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RestaurantDeliveryAddress> set1 = new LinkedHashSet<RestaurantDeliveryAddress>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity : set ) {
            set1.add( restaurantDeliveryAddressEntityToRestaurantDeliveryAddress( restaurantDeliveryAddressEntity ) );
        }

        return set1;
    }

    protected Address addressEntityToAddress(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.addressId( addressEntity.getAddressId() );
        address.country( addressEntity.getCountry() );
        address.city( addressEntity.getCity() );
        address.postalCode( addressEntity.getPostalCode() );
        address.streetName( addressEntity.getStreetName() );
        address.addressesExtended( addressExtendedEntitySetToAddressExtendedSet( addressEntity.getAddressesExtended() ) );
        address.restaurantDeliveryAddresses( restaurantDeliveryAddressEntitySetToRestaurantDeliveryAddressSet( addressEntity.getRestaurantDeliveryAddresses() ) );

        return address.build();
    }

    protected Set<CustomerAddress> customerAddressEntitySetToCustomerAddressSet(Set<CustomerAddressEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<CustomerAddress> set1 = new LinkedHashSet<CustomerAddress>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CustomerAddressEntity customerAddressEntity : set ) {
            set1.add( customerAddressEntityToCustomerAddress( customerAddressEntity ) );
        }

        return set1;
    }

    protected Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( customerEntity.getName() );
        customer.surname( customerEntity.getSurname() );
        customer.email( customerEntity.getEmail() );
        customer.phone( customerEntity.getPhone() );
        customer.userId( customerEntity.getUserId() );
        customer.customerAddresses( customerAddressEntitySetToCustomerAddressSet( customerEntity.getCustomerAddresses() ) );

        return customer.build();
    }

    protected CustomerAddress customerAddressEntityToCustomerAddress(CustomerAddressEntity customerAddressEntity) {
        if ( customerAddressEntity == null ) {
            return null;
        }

        CustomerAddress.CustomerAddressBuilder customerAddress = CustomerAddress.builder();

        customerAddress.customerAddressId( customerAddressEntity.getCustomerAddressId() );
        customerAddress.addressExtended( mapFromEntity( customerAddressEntity.getAddressExtended() ) );
        customerAddress.customer( customerEntityToCustomer( customerAddressEntity.getCustomer() ) );

        return customerAddress.build();
    }
}
