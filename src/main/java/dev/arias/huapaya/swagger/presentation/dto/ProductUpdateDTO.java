package dev.arias.huapaya.swagger.presentation.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {

    @Schema(description = "Product name", example = "TV")
    private String name;

    @Schema(description = "Product description", example = "Household appliance")
    private String description;

    @Schema(description = "Product price", example = "2500.00")
    private BigDecimal price;

    @Schema(description = "Product status", example = "true")
    private Boolean status;

}
