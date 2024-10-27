package dev.arias.huapaya.swagger.presentation.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPaginationDTO implements Serializable {

    private String name;

    private String description;

    private BigDecimal price;

}
