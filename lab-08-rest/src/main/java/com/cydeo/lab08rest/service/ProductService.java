package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.dto.ProductRequest;
import com.cydeo.lab08rest.entity.Category;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductList();
    ProductDTO save(ProductDTO product);
    ProductDTO update(ProductDTO product);
    ProductDTO getProductListByName(String name);
    List<ProductDTO> getProductListByCategory(Long id);
    int getProductListByPrice(BigDecimal price);
    List<ProductDTO> getTop3ProductList();
    List<ProductDTO> getProductListByPriceAndCategory(ProductRequest categoryAndPrice);

}
