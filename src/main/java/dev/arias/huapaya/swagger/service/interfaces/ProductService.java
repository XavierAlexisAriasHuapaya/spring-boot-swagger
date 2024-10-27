package dev.arias.huapaya.swagger.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.swagger.persistence.entity.ProductEntity;
import dev.arias.huapaya.swagger.presentation.dto.ProductCreateDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductPaginationDTO;
import dev.arias.huapaya.swagger.presentation.dto.ProductUpdateDTO;

public interface ProductService {

    public ProductEntity create(ProductCreateDTO product);

    public Optional<ProductEntity> findOne(Long id);

    public ProductEntity update(ProductUpdateDTO product, Long id);

    public Page<ProductPaginationDTO> findAll(Pageable pageable);

    public ProductEntity delete(Long id);

}
