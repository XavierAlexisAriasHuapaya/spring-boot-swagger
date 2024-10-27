package dev.arias.huapaya.swagger.presentation.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    @Schema(description = "Product name", example = "Microwave")
    private String name;

    @Schema(description = "Product description", example = "Household appliance")
    private String description;

    @Schema(description = "Product price", example = "1200.00")
    private BigDecimal price;

}
