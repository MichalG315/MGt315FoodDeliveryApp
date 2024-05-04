package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String userName;
    private String restaurantName;
    private String orderNumber;
    private List<MenuItemDTO> menuItemList;
    private OffsetDateTime receivedDateTime;
    private BigDecimal totalAmount;
}
