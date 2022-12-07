package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.DiscountDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getDiscountList(){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved Discounts", discountService.getDiscountList(), HttpStatus.OK));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getDiscountListByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved discounts by name", discountService.getDiscountListByName(name), HttpStatus.OK));
    }

    @PutMapping
    public  ResponseEntity<ResponseWrapper> updateDiscount(@RequestBody DiscountDTO discount){
        return ResponseEntity.ok(new ResponseWrapper("Discount successfully updated", discountService.updateDiscount(discount), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createDiscount(@RequestBody DiscountDTO discount){
        return ResponseEntity.ok(new ResponseWrapper("Discount successfully created", discountService.createDiscount(discount), HttpStatus.OK));
    }

}
