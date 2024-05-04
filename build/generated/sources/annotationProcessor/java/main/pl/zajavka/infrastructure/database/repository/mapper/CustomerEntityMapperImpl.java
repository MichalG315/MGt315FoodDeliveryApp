package pl.zajavka.infrastructure.database.repository.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.domain.Address;
import pl.zajavka.domain.AddressExtended;
import pl.zajavka.domain.Customer;
import pl.zajavka.domain.CustomerAddress;
import pl.zajavka.domain.FoodOrder;
import pl.zajavka.domain.MenuItem;
import pl.zajavka.domain.MenuItemFoodOrder;
import pl.zajavka.domain.Restaurant;
import pl.zajavka.domain.RestaurantDeliveryAddress;
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.CustomerAddressEntity;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T23:34:43+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CustomerEntityMapperImpl implements CustomerEntityMapper {

    @Override
    public CustomerEntity mapToEntity(Customer customer) {
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
        customerEntity.foodOrders( foodOrderSetToFoodOrderEntitySet( customer.getFoodOrders() ) );

        return customerEntity.build();
    }

    protected Set<AddressExtendedEntity> addressExtendedSetToAddressExtendedEntitySet(Set<AddressExtended> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressExtendedEntity> set1 = new LinkedHashSet<AddressExtendedEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressExtended addressExtended : set ) {
            set1.add( addressExtendedToAddressExtendedEntity( addressExtended ) );
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

    protected AddressExtendedEntity addressExtendedToAddressExtendedEntity(AddressExtended addressExtended) {
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

    protected CustomerAddressEntity customerAddressToCustomerAddressEntity(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }

        CustomerAddressEntity.CustomerAddressEntityBuilder customerAddressEntity = CustomerAddressEntity.builder();

        customerAddressEntity.customerAddressId( customerAddress.getCustomerAddressId() );
        customerAddressEntity.addressExtended( addressExtendedToAddressExtendedEntity( customerAddress.getAddressExtended() ) );
        customerAddressEntity.customer( mapToEntity( customerAddress.getCustomer() ) );

        return customerAddressEntity.build();
    }

    protected Set<MenuItemFoodOrderEntity> menuItemFoodOrderSetToMenuItemFoodOrderEntitySet(Set<MenuItemFoodOrder> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItemFoodOrderEntity> set1 = new LinkedHashSet<MenuItemFoodOrderEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItemFoodOrder menuItemFoodOrder : set ) {
            set1.add( menuItemFoodOrderToMenuItemFoodOrderEntity( menuItemFoodOrder ) );
        }

        return set1;
    }

    protected MenuItemEntity menuItemToMenuItemEntity(MenuItem menuItem) {
        if ( menuItem == null ) {
            return null;
        }

        MenuItemEntity.MenuItemEntityBuilder menuItemEntity = MenuItemEntity.builder();

        menuItemEntity.menuItemId( menuItem.getMenuItemId() );
        menuItemEntity.menuItemNumber( menuItem.getMenuItemNumber() );
        menuItemEntity.itemName( menuItem.getItemName() );
        menuItemEntity.description( menuItem.getDescription() );
        menuItemEntity.price( menuItem.getPrice() );
        menuItemEntity.category( menuItem.getCategory() );
        menuItemEntity.imagePath( menuItem.getImagePath() );
        menuItemEntity.menuItemFoodOrders( menuItemFoodOrderSetToMenuItemFoodOrderEntitySet( menuItem.getMenuItemFoodOrders() ) );

        return menuItemEntity.build();
    }

    protected MenuItemFoodOrderEntity menuItemFoodOrderToMenuItemFoodOrderEntity(MenuItemFoodOrder menuItemFoodOrder) {
        if ( menuItemFoodOrder == null ) {
            return null;
        }

        MenuItemFoodOrderEntity.MenuItemFoodOrderEntityBuilder menuItemFoodOrderEntity = MenuItemFoodOrderEntity.builder();

        menuItemFoodOrderEntity.menuItemFoodOrderId( menuItemFoodOrder.getMenuItemFoodOrderId() );
        menuItemFoodOrderEntity.quantity( menuItemFoodOrder.getQuantity() );
        menuItemFoodOrderEntity.menuItem( menuItemToMenuItemEntity( menuItemFoodOrder.getMenuItem() ) );
        menuItemFoodOrderEntity.foodOrder( foodOrderToFoodOrderEntity( menuItemFoodOrder.getFoodOrder() ) );

        return menuItemFoodOrderEntity.build();
    }

    protected FoodOrderEntity foodOrderToFoodOrderEntity(FoodOrder foodOrder) {
        if ( foodOrder == null ) {
            return null;
        }

        FoodOrderEntity.FoodOrderEntityBuilder foodOrderEntity = FoodOrderEntity.builder();

        foodOrderEntity.foodOrderId( foodOrder.getFoodOrderId() );
        foodOrderEntity.foodOrderNumber( foodOrder.getFoodOrderNumber() );
        foodOrderEntity.receivedDateTime( foodOrder.getReceivedDateTime() );
        foodOrderEntity.completedDateTime( foodOrder.getCompletedDateTime() );
        foodOrderEntity.customerComment( foodOrder.getCustomerComment() );
        foodOrderEntity.totalAmount( foodOrder.getTotalAmount() );
        foodOrderEntity.customer( mapToEntity( foodOrder.getCustomer() ) );
        foodOrderEntity.restaurant( restaurantToRestaurantEntity( foodOrder.getRestaurant() ) );
        foodOrderEntity.menuItemFoodOrders( menuItemFoodOrderSetToMenuItemFoodOrderEntitySet( foodOrder.getMenuItemFoodOrders() ) );

        return foodOrderEntity.build();
    }

    protected Set<FoodOrderEntity> foodOrderSetToFoodOrderEntitySet(Set<FoodOrder> set) {
        if ( set == null ) {
            return null;
        }

        Set<FoodOrderEntity> set1 = new LinkedHashSet<FoodOrderEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FoodOrder foodOrder : set ) {
            set1.add( foodOrderToFoodOrderEntity( foodOrder ) );
        }

        return set1;
    }
}
