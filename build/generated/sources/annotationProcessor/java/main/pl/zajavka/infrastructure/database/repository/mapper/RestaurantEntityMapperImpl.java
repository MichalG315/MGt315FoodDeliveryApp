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
import pl.zajavka.infrastructure.database.entity.AddressEntity;
import pl.zajavka.infrastructure.database.entity.AddressExtendedEntity;
import pl.zajavka.infrastructure.database.entity.CustomerAddressEntity;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;
import pl.zajavka.infrastructure.database.entity.FoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemEntity;
import pl.zajavka.infrastructure.database.entity.MenuItemFoodOrderEntity;
import pl.zajavka.infrastructure.database.entity.RestaurantEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T12:40:01+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RestaurantEntityMapperImpl implements RestaurantEntityMapper {

    @Override
    public Restaurant mapFromEntity(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantId( restaurantEntity.getRestaurantId() );
        restaurant.restaurantName( restaurantEntity.getRestaurantName() );
        restaurant.description( restaurantEntity.getDescription() );
        restaurant.addressExtended( addressExtendedEntityToAddressExtended( restaurantEntity.getAddressExtended() ) );

        return restaurant.build();
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

    protected Set<MenuItemFoodOrder> menuItemFoodOrderEntitySetToMenuItemFoodOrderSet(Set<MenuItemFoodOrderEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItemFoodOrder> set1 = new LinkedHashSet<MenuItemFoodOrder>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItemFoodOrderEntity menuItemFoodOrderEntity : set ) {
            set1.add( menuItemFoodOrderEntityToMenuItemFoodOrder( menuItemFoodOrderEntity ) );
        }

        return set1;
    }

    protected MenuItem menuItemEntityToMenuItem(MenuItemEntity menuItemEntity) {
        if ( menuItemEntity == null ) {
            return null;
        }

        MenuItem.MenuItemBuilder menuItem = MenuItem.builder();

        menuItem.menuItemId( menuItemEntity.getMenuItemId() );
        menuItem.menuItemNumber( menuItemEntity.getMenuItemNumber() );
        menuItem.itemName( menuItemEntity.getItemName() );
        menuItem.description( menuItemEntity.getDescription() );
        menuItem.price( menuItemEntity.getPrice() );
        menuItem.category( menuItemEntity.getCategory() );
        menuItem.imagePath( menuItemEntity.getImagePath() );
        menuItem.restaurant( mapFromEntity( menuItemEntity.getRestaurant() ) );
        menuItem.menuItemFoodOrders( menuItemFoodOrderEntitySetToMenuItemFoodOrderSet( menuItemEntity.getMenuItemFoodOrders() ) );

        return menuItem.build();
    }

    protected MenuItemFoodOrder menuItemFoodOrderEntityToMenuItemFoodOrder(MenuItemFoodOrderEntity menuItemFoodOrderEntity) {
        if ( menuItemFoodOrderEntity == null ) {
            return null;
        }

        MenuItemFoodOrder.MenuItemFoodOrderBuilder menuItemFoodOrder = MenuItemFoodOrder.builder();

        menuItemFoodOrder.menuItemFoodOrderId( menuItemFoodOrderEntity.getMenuItemFoodOrderId() );
        menuItemFoodOrder.quantity( menuItemFoodOrderEntity.getQuantity() );
        menuItemFoodOrder.menuItem( menuItemEntityToMenuItem( menuItemFoodOrderEntity.getMenuItem() ) );
        menuItemFoodOrder.foodOrder( foodOrderEntityToFoodOrder( menuItemFoodOrderEntity.getFoodOrder() ) );

        return menuItemFoodOrder.build();
    }

    protected FoodOrder foodOrderEntityToFoodOrder(FoodOrderEntity foodOrderEntity) {
        if ( foodOrderEntity == null ) {
            return null;
        }

        FoodOrder.FoodOrderBuilder foodOrder = FoodOrder.builder();

        foodOrder.foodOrderId( foodOrderEntity.getFoodOrderId() );
        foodOrder.foodOrderNumber( foodOrderEntity.getFoodOrderNumber() );
        foodOrder.receivedDateTime( foodOrderEntity.getReceivedDateTime() );
        foodOrder.completedDateTime( foodOrderEntity.getCompletedDateTime() );
        foodOrder.customerComment( foodOrderEntity.getCustomerComment() );
        foodOrder.totalAmount( foodOrderEntity.getTotalAmount() );
        foodOrder.customer( customerEntityToCustomer( foodOrderEntity.getCustomer() ) );
        foodOrder.restaurant( mapFromEntity( foodOrderEntity.getRestaurant() ) );
        foodOrder.menuItemFoodOrders( menuItemFoodOrderEntitySetToMenuItemFoodOrderSet( foodOrderEntity.getMenuItemFoodOrders() ) );

        return foodOrder.build();
    }

    protected Set<FoodOrder> foodOrderEntitySetToFoodOrderSet(Set<FoodOrderEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<FoodOrder> set1 = new LinkedHashSet<FoodOrder>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FoodOrderEntity foodOrderEntity : set ) {
            set1.add( foodOrderEntityToFoodOrder( foodOrderEntity ) );
        }

        return set1;
    }

    protected Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customerId( customerEntity.getCustomerId() );
        customer.name( customerEntity.getName() );
        customer.surname( customerEntity.getSurname() );
        customer.email( customerEntity.getEmail() );
        customer.phone( customerEntity.getPhone() );
        customer.userId( customerEntity.getUserId() );
        customer.customerAddresses( customerAddressEntitySetToCustomerAddressSet( customerEntity.getCustomerAddresses() ) );
        customer.foodOrders( foodOrderEntitySetToFoodOrderSet( customerEntity.getFoodOrders() ) );

        return customer.build();
    }

    protected CustomerAddress customerAddressEntityToCustomerAddress(CustomerAddressEntity customerAddressEntity) {
        if ( customerAddressEntity == null ) {
            return null;
        }

        CustomerAddress.CustomerAddressBuilder customerAddress = CustomerAddress.builder();

        customerAddress.customerAddressId( customerAddressEntity.getCustomerAddressId() );
        customerAddress.addressExtended( addressExtendedEntityToAddressExtended( customerAddressEntity.getAddressExtended() ) );
        customerAddress.customer( customerEntityToCustomer( customerAddressEntity.getCustomer() ) );

        return customerAddress.build();
    }

    protected AddressExtended addressExtendedEntityToAddressExtended(AddressExtendedEntity addressExtendedEntity) {
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
}
