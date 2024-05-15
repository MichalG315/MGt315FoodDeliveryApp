package pl.zajavka.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.domain.Order;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-15T21:58:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO mapToDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.receivedDateTime( mapOffsetDateTimeToString( order.getReceivedDateTime() ) );
        orderDTO.completedDateTime( mapOffsetDateTimeToString( order.getCompletedDateTime() ) );
        orderDTO.userName( order.getUserName() );
        orderDTO.restaurantName( order.getRestaurantName() );
        orderDTO.orderNumber( order.getOrderNumber() );
        orderDTO.totalAmount( order.getTotalAmount() );
        orderDTO.status( order.getStatus() );

        return orderDTO.build();
    }
}
