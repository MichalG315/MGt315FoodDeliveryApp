package pl.zajavka.api.dto;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {


    private String menuItemNumber;
    @Length(max = 64)
    private String itemName;
    private String description;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    private String category;
    private String restaurantName;
    private MultipartFile image;
    private String imagePath;

}
