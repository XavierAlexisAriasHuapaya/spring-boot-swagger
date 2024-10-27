package dev.arias.huapaya.swagger.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arias.huapaya.swagger.persistence.entity.ProductEntity;
import dev.arias.huapaya.swagger.presentation.dto.ProductCreateDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductPaginationDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductUpdateDTO;
import dev.arias.huapaya.swagger.presentation.exception.ProductException;
import dev.arias.huapaya.swagger.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Tag(name = "Product resource")
@RestController
@RequestMapping(path = "product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Save the product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created product", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateDTO product) {
        Map<String, Object> response = new HashMap<>();
        ProductEntity productCreate = this.productService.create(product);
        response.put("message", "Successfully created product");
        response.put("data", productCreate);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "We search for a product by id")
    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(
            @Parameter(description = "ID of the product to retrieve", example = "1") @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<ProductEntity> product = this.productService.findOne(id);
            response.put("data", product);
        } catch (ProductException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "We modify a product in the database")
    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@RequestBody ProductUpdateDTO product, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProductEntity productUpdate = this.productService.update(product, id);
            response.put("message", "Successfully updated product");
            response.put("data", productUpdate);
        } catch (ProductException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "We show the product pagination")
    @GetMapping(path = "pagination")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<ProductPaginationDTO> productPage = this.productService.findAll(pageable);
        response.put("data", productPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "We delete the product by id in the database")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProductEntity productDelete = this.productService.delete(id);
            response.put("message", "Product removed");
            response.put("data", productDelete);
        } catch (ProductException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
