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
    date = "2024-05-04T18:13:49+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AddressEntityMapperImpl implements AddressEntityMapper {

    @Override
    public AddressEntity mapToEntity(Address address) {
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

    @Override
    public Address mapFromEntity(AddressEntity addressEntity) {
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
        menuItemEntity.restaurant( restaurantToRestaurantEntity( menuItem.getRestaurant() ) );
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

        customerEntity.name( customer.getName() );
        customerEntity.surname( customer.getSurname() );
        customerEntity.email( customer.getEmail() );
        customerEntity.phone( customer.getPhone() );
        customerEntity.userId( customer.getUserId() );
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
        addressExtendedEntity.address( mapToEntity( addressExtended.getAddress() ) );
        addressExtendedEntity.customerAddresses( customerAddressSetToCustomerAddressEntitySet( addressExtended.getCustomerAddresses() ) );

        return addressExtendedEntity.build();
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
        restaurantDeliveryAddressEntity.address( mapToEntity( restaurantDeliveryAddress.getAddress() ) );
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
        menuItem.restaurant( restaurantEntityToRestaurant( menuItemEntity.getRestaurant() ) );
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
        foodOrder.restaurant( restaurantEntityToRestaurant( foodOrderEntity.getRestaurant() ) );
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
        addressExtended.address( mapFromEntity( addressExtendedEntity.getAddress() ) );
        addressExtended.customerAddresses( customerAddressEntitySetToCustomerAddressSet( addressExtendedEntity.getCustomerAddresses() ) );

        return addressExtended.build();
    }

    protected Set<AddressExtended> addressExtendedEntitySetToAddressExtendedSet(Set<AddressExtendedEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressExtended> set1 = new LinkedHashSet<AddressExtended>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressExtendedEntity addressExtendedEntity : set ) {
            set1.add( addressExtendedEntityToAddressExtended( addressExtendedEntity ) );
        }

        return set1;
    }

    protected RestaurantDeliveryAddress restaurantDeliveryAddressEntityToRestaurantDeliveryAddress(RestaurantDeliveryAddressEntity restaurantDeliveryAddressEntity) {
        if ( restaurantDeliveryAddressEntity == null ) {
            return null;
        }

        RestaurantDeliveryAddress.RestaurantDeliveryAddressBuilder restaurantDeliveryAddress = RestaurantDeliveryAddress.builder();

        restaurantDeliveryAddress.restaurantDeliveryAddressId( restaurantDeliveryAddressEntity.getRestaurantDeliveryAddressId() );
        restaurantDeliveryAddress.address( mapFromEntity( restaurantDeliveryAddressEntity.getAddress() ) );
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
}
