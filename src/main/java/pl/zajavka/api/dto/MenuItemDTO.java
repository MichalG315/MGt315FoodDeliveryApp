package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {
    private String menuItemNumber;
    private String itemName;
    private String description;
    private BigDecimal price;
    private String category;
    private String restaurantName;
    private MultipartFile image;
    private String imagePath;

}
