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
    date = "2024-05-03T11:42:17+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MenuItemEntityMapperImpl implements MenuItemEntityMapper {

    @Override
    public MenuItem mapFromEntity(MenuItemEntity menuItemEntity) {
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
        menuItem.restaurant( restaurantEntityToRestaurant( menuItemEntity.getRestaurant() ) );

        return menuItem.build();
    }

    @Override
    public MenuItemEntity mapToEntity(MenuItem menuItem) {
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
        menuItemEntity.restaurant( restaurantToRestaurantEntity( menuItem.getRestaurant() ) );
        menuItemEntity.menuItemFoodOrders( menuItemFoodOrderSetToMenuItemFoodOrderEntitySet( menuItem.getMenuItemFoodOrders() ) );

        return menuItemEntity.build();
    }

    protected Restaurant restaurantEntityToRestaurant(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantId( restaurantEntity.getRestaurantId() );
        restaurant.restaurantName( restaurantEntity.getRestaurantName() );
        restaurant.description( restaurantEntity.getDescription() );

        return restaurant.build();
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

    protected MenuItemFoodOrderEntity menuItemFoodOrderToMenuItemFoodOrderEntity(MenuItemFoodOrder menuItemFoodOrder) {
        if ( menuItemFoodOrder == null ) {
            return null;
        }

        MenuItemFoodOrderEntity.MenuItemFoodOrderEntityBuilder menuItemFoodOrderEntity = MenuItemFoodOrderEntity.builder();

        menuItemFoodOrderEntity.menuItemFoodOrderId( menuItemFoodOrder.getMenuItemFoodOrderId() );
        menuItemFoodOrderEntity.quantity( menuItemFoodOrder.getQuantity() );
        menuItemFoodOrderEntity.menuItem( mapToEntity( menuItemFoodOrder.getMenuItem() ) );
        menuItemFoodOrderEntity.foodOrder( foodOrderToFoodOrderEntity( menuItemFoodOrder.getFoodOrder() ) );

        return menuItemFoodOrderEntity.build();
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
        foodOrderEntity.customer( customerToCustomerEntity( foodOrder.getCustomer() ) );
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

    protected CustomerEntity customerToCustomerEntity(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.customerId( customer.getCustomerId() );
        customerEntity.name( customer.getName() );
        customerEntity.surname( customer.getSurname() );
        customerEntity.email( customer.getEmail() );
        customerEntity.phone( customer.getPhone() );
        customerEntity.customerAddresses( customerAddressSetToCustomerAddressEntitySet( customer.getCustomerAddresses() ) );
        customerEntity.foodOrders( foodOrderSetToFoodOrderEntitySet( customer.getFoodOrders() ) );

        return customerEntity.build();
    }

    protected CustomerAddressEntity customerAddressToCustomerAddressEntity(CustomerAddress customerAddress) {
        if ( customerAddress == null ) {
            return null;
        }

        CustomerAddressEntity.CustomerAddressEntityBuilder customerAddressEntity = CustomerAddressEntity.builder();

        customerAddressEntity.customerAddressId( customerAddress.getCustomerAddressId() );
        customerAddressEntity.addressExtended( addressExtendedToAddressExtendedEntity( customerAddress.getAddressExtended() ) );
        customerAddressEntity.customer( customerToCustomerEntity( customerAddress.getCustomer() ) );

        return customerAddressEntity.build();
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

    protected Set<MenuItemEntity> menuItemSetToMenuItemEntitySet(Set<MenuItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItemEntity> set1 = new LinkedHashSet<MenuItemEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItem menuItem : set ) {
            set1.add( mapToEntity( menuItem ) );
        }

        return set1;
    }

    protected RestaurantEntity restaurantToRestaurantEntity(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantEntity.RestaurantEntityBuilder restaurantEntity = RestaurantEntity.builder();

        restaurantEntity.restaurantId( restaurant.getRestaurantId() );
        restaurantEntity.restaurantName( restaurant.getRestaurantName() );
        restaurantEntity.description( restaurant.getDescription() );
        restaurantEntity.addressExtended( addressExtendedToAddressExtendedEntity( restaurant.getAddressExtended() ) );
        restaurantEntity.restaurantDeliveryAddresses( restaurantDeliveryAddressSetToRestaurantDeliveryAddressEntitySet( restaurant.getRestaurantDeliveryAddresses() ) );
        restaurantEntity.menuItems( menuItemSetToMenuItemEntitySet( restaurant.getMenuItems() ) );
        restaurantEntity.foodOrders( foodOrderSetToFoodOrderEntitySet( restaurant.getFoodOrders() ) );

        return restaurantEntity.build();
    }
}
