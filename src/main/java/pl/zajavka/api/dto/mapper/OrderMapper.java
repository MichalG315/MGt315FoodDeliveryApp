package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.api.dto.OrderDTO;
import pl.zajavka.domain.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper extends OffsetDateTimeMapper{

    @Mapping(source = "receivedDateTime", target = "receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "completedDateTime", target = "completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    OrderDTO mapToDTO(Order order);
}
