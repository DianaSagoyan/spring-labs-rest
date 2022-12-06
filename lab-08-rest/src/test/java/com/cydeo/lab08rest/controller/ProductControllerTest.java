package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    public void createProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("milk");
        productDTO.setQuantity(123);
        productDTO.setRemainingQuantity(123);
        productDTO.setPrice(BigDecimal.valueOf(34));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/product")
                        .content(toJsonString(productDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("milk"));
    }
    @Test
    public void updateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("milk");
        productDTO.setQuantity(123);
        productDTO.setRemainingQuantity(123);
        productDTO.setPrice(BigDecimal.valueOf(34));

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/product")
                        .content(toJsonString(productDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("milk"));
    }
    @Test
    public void getProductList() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/product")
                        .accept(MediaType.APPLICATION_JSON));
                actions.andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(500));
    }


    @Test
    public void getTop3ProductList() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/product/top3")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(324))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()").value(3));
    }


    @Test
    public void getProductListByName() throws Exception {

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/product/Tomatoes")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Tomatoes"));
    }

    @Test
    public void getProductListByCategory() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/product/category/1")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].size()").value(5));
    }

    @Test
    public void getProductListByPrice() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/product/price/50")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(244));
    }
    @Test
    public void getProductListByPriceAndQuantity() throws Exception {
        List<Long> array = new ArrayList<>();
        array.add(1L);
        array.add(2L);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setCategoryList(array);
        productRequest.setPrice(BigDecimal.TEN);
        System.out.println(toJsonString(productRequest));

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/product/categoryandprice")
                .content(toJsonString(productRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].size()").value(5));
    }



    private static String toJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
