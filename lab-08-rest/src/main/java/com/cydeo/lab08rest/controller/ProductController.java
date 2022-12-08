package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.dto.ProductRequest;
import com.cydeo.lab08rest.entity.Category;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getProductList(){
        return ResponseEntity.ok(new ResponseWrapper("Products successfully retrieved", productService.getProductList(), HttpStatus.OK));
    }

    @PostMapping("/categoryandprice")
    public ResponseEntity<ResponseWrapper> getProductListByPriceAndQuantity(@RequestBody ProductRequest categoryAndPrice){
        return ResponseEntity.ok(new ResponseWrapper("Products successfully saved", productService.getProductListByPriceAndCategory(categoryAndPrice), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok(new ResponseWrapper("Product successfully created", productService.save(product), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateeProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok(new ResponseWrapper("Product successfully updated", productService.update(product), HttpStatus.OK));
    }

    @GetMapping("{name}")
    public ResponseEntity<ResponseWrapper> getProductListByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Products successfully retrieved by name", productService.getProductListByName(name), HttpStatus.OK));
    }


    @GetMapping("category/{id}")
    public ResponseEntity<ResponseWrapper> getProductListByCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(new ResponseWrapper("Products successfully retrieved by category", productService.getProductListByCategory(id), HttpStatus.OK));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<ResponseWrapper> getProductListByPrice(@PathVariable("price") BigDecimal price){
        return ResponseEntity.ok(new ResponseWrapper("Products successfully retrieved by price", productService.getProductListByPrice(price), HttpStatus.OK));
    }

    @GetMapping("/top3")
    public ResponseEntity<ResponseWrapper> getTop3ProductList(){
        return ResponseEntity.ok(new ResponseWrapper("Top 3 products recieved", productService.getTop3ProductList(), HttpStatus.OK));
    }



}
