package dev.arias.huapaya.swagger.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.swagger.persistence.entity.ProductEntity;
import dev.arias.huapaya.swagger.persistence.repository.ProductRepository;
import dev.arias.huapaya.swagger.presentation.dto.ProductCreateDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductPaginationDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductUpdateDTO;
import dev.arias.huapaya.swagger.presentation.exception.ProductException;
import dev.arias.huapaya.swagger.service.interfaces.ProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImplements implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = false)
    @Override
    public ProductEntity create(ProductCreateDTO product) {
        ProductEntity productCreate = ProductEntity.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice()).build();
        return this.productRepository.save(productCreate);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProductEntity> findOne(Long id) {
        Optional<ProductEntity> productSearch = this.productRepository.findById(id);
        if (!productSearch.isPresent()) {
            throw new ProductException("Product not found.");
        }
        return productSearch;
    }

    @Transactional(readOnly = false)
    @Override
    public ProductEntity update(ProductUpdateDTO product, Long id) {
        Optional<ProductEntity> productSearch = this.findOne(id);
        if (!productSearch.isPresent()) {
            throw new ProductException("Product not found.");
        }
        ProductEntity productUpdate = ProductEntity.builder()
                .id(id)
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus()).build();
        return this.productRepository.save(productUpdate);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductPaginationDTO> findAll(Pageable pageable) {
        Page<ProductEntity> productPage = this.productRepository.findAll(pageable);
        return productPage.map(this::productConvertPaginationDTO);
    }

    private ProductPaginationDTO productConvertPaginationDTO(ProductEntity product) {
        ProductPaginationDTO paginationDTO = ProductPaginationDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
        return paginationDTO;
    }

    @Transactional
    @Override
    public ProductEntity delete(Long id) {
        Optional<ProductEntity> productSearch = this.findOne(id);
        if (!productSearch.isPresent()) {
            throw new ProductException("Product not found.");
        }
        this.productRepository.deleteById(id);
        return productSearch.get();
    }

}
