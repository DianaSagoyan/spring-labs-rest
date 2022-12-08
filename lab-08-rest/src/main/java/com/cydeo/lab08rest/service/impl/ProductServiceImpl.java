package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.dto.ProductRequest;
import com.cydeo.lab08rest.entity.Category;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.CategoryRepository;
import com.cydeo.lab08rest.repository.ProductRepository;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> getProductList() {
        return productRepository.findAll().stream().map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }



    @Override
    public ProductDTO save(ProductDTO product) {
        //??
        return product;
    }

    @Override
    public ProductDTO update(ProductDTO product) {
        //??
        return product;
    }

    @Override
    public ProductDTO getProductListByName(String name) {
        return mapperUtil.convert(productRepository.findFirstByName(name), new ProductDTO());
    }

    @Override
    public List<ProductDTO> getProductListByCategory(Long id) {
        Category category = categoryRepository.findById(id).get();
        return productRepository.findAllByCategoryListContaining(category).stream().map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public int getProductListByPrice(BigDecimal price) {
        return productRepository.countProductByPriceGreaterThan(price);
    }

    @Override
    public List<ProductDTO> getTop3ProductList() {
        return productRepository.findTop3ByOrderByPriceDesc().stream().map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductListByPriceAndCategory(ProductRequest categoryAndPrice) {

        return productRepository.retrieveProductListByCategory(categoryAndPrice.getCategoryList(), categoryAndPrice.getPrice()).stream().map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }


}
